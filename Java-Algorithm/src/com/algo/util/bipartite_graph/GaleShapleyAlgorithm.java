package com.algo.util.bipartite_graph;

import java.util.Arrays;

/**
 * 
 * Œ»∂®ªÈ≈‰Œ Ã‚
 *
 */
public class GaleShapleyAlgorithm {
	
	// Number of Men or Women
	int n = 4;
	private boolean wPrefersM1OverM(int prefer[][], int w, int m, int m1){
		for (int i = 0; i < n; i++) {
			if (prefer[w][i] == m1) {
				return true;
			}
			if (prefer[w][i] == m) {
				return false;
			}
		}
		return false;
	}
	
	public void stableMarriage(int prefer[][]) {
	    int wPartner[] = new int[n];
	    boolean mFree[] = new boolean[n];
	    Arrays.fill(wPartner, -1);
	    int freeCount = n;
	 
	    // While there are free men
	    while (freeCount > 0){
	        int m;
	        for (m = 0; m < n; m++)
	            if (mFree[m] == false)
	                break;
	        for (int i = 0; i < n && mFree[m] == false; i++) {
	            int w = prefer[m][i];
	            if (wPartner[w - n] == -1) {
	                wPartner[w - n] = m;
	                mFree[m] = true;
	                freeCount--;
	            }
	            else {
	                int m1 = wPartner[w - n];
	                if (wPrefersM1OverM(prefer, w, m, m1) == false) {
	                    wPartner[w - n] = m;
	                    mFree[m] = true;
	                    mFree[m1] = false;
	                }
	            } 
	        }
	    }
		// Print the solution
		System.out.println("Woman Man");
		for (int i = 0; i < n; i++) {
		    System.out.print(" ");
		    System.out.println(i + n + "     " + wPartner[i]);
		}
	}
}
