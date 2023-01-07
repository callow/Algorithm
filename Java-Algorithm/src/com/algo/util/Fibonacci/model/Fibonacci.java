package com.algo.util.Fibonacci.model;

/**
 * 
 * Fibonacci������ʽ��ϵ�� <br>
 * 	                   (a,b)
 * |F3,F2| = |F2,F1| * (c,d)   
 * 
 * Ȼ����� a,b,c,d !<br>
 * 
 * ͨ�ʽ�� 
 * 
 * 	                                        (1,1)
 * |Fn,Fn-1| = |F2,F1| * (A)^ n-2 where A = (1,0)
 * 
 */
public class Fibonacci {
	/**
	 * 
	 * n : ��Fib�ڼ��� <br>
	 *                             (1,1)^ n-2           (x,y)
	 * �� |F(n),F(n-1)| = |F2,F1| * (1,0)      = |1,1| * (z,t) = |x+z, y+t| <br>
	 * 
	 *                                                    (x,y)
	 * �� F(n) = x + z = res[0][0] + res[1][0] <b>Where</b> res = (z,t)
	 * 				          
	 */
	public static int nth(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		// [ 1 ,1 ]
		// [ 1, 0 ]
		int[][] base = {{ 1, 1 }, { 1, 0 }};
		
		int[][] res = QuickExponential.matrix2DQuickPower(base, n - 2); // �� base ^ n-2
		return res[0][0] + res[1][0];  
	}
	
}
