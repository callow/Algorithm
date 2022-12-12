package com.algo.leecode.L1To50;

import com.algo.util.sort.SortUtil;

/**
 *  求在num1中取前几个来凑k
 *
 */

public class Medien2SortedArray_4 {
	
	public static void main(String[] args) {
		
		findMedianSortedArrays(new int[] {1,2}, new int[] {3,4});
	}
	/**
	 * O(log (m+n) <br>
	 * 	二分来做 Space O(1) ,二分同时查找2个SortedArray <br>
	 * 	中位数位置 k =  (n1 + n2 + 1) / 2 
	 *  从n1中取几个元素贡献给k => n1'？ 从n2中取几个  => n2'？<br>
	 *  关注： num1[n1'-1], num1[n1'], num2[n2'-1], num2[n2'] <br>
	 *  二分：num1[n1'] < num2[n2'-1] ? num1用的还太少，还需用更多元素 -> 找到n1' -> n2' = k - n1'
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length , n2 = nums2.length;
		if (n1 > n2) { // 数量不平衡时候，在小的数组上(nums2)做2分更快
			return findMedianSortedArrays(nums2,nums1);
		}
		
		int k = (n1 + n2 + 1) / 2; // 上中位数个数k , 其下标为k-1
		int L = 0;
		int R = n1;
		while (L < R) { // 只看nums1
			int m1 = L + (R - L) / 2;
			int m2 = k - m1;
			if (nums1[m1] < nums2[m2 -1] ) { // nums1中元素个数不够多继续加
				L = m1 + 1; 
			} else {
				R = m1; // nums1中元素太多了，减少一下
			}
		}
		
		// left = right 时循环结束，这时候标识从nums1中取前m1个，从nums2中取前m2个可以凑成K
		// e.g: nums1: m1-1=5 m1=7   nums2: m2-1=6 m2=8
		int m1 = L;
		int m2 = k - L;
		
		// nums1 -> 5,7 , num2 -> 6,8 其中 5，6是2个数组地左中位数取一个大地
		int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 -1], 
						  m2 <= 0 ? Integer.MIN_VALUE: nums2[m2 -1]);
		if ((n1 + n2) % 2  == 1) { // 基数个
			return c1;
		}
		
		// // nums1 -> 5,7 , num2 -> 6,8 其中 7，8是2个数组地右中位数取一个小地
		int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
						  m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
		return (c1 + c2) * 0.5; // 偶数个
	}
	
	
	
	/**
	 * O(N)
	 * 也可以用O（N）地排序算法来做 e.g countSort, RadixSort
	 */
	
	public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		int n = nums1.length + nums2.length;
	    int ans = 0;
	    double val = 0;
	    int k=0;
	    int C[]= new int [n];
	    // store the value in array 
	    for (int i=0 ; i<nums1.length ; i++){
	        C[i] = nums1[i];
	    }
	    for (int i =nums1.length ; i<n ; i++){
	        C[i] = nums2[k];
	        k++;
	    }
	    // sort the array
	    
	    SortUtil.countingSort(C);
	    
	    // finding median value 
	    
	    for (int i=0 ; i<n ; i++){
	        // even 
	        if (n%2 ==0){
	            ans = n/2;
	            val = C[ans]+C[ans-1];
				val = val/2;
	            
	        }
	        else{
	            // odd
	            ans = n/2;
	            val = C[ans];
	        }
	    }
	    return val;
    }
	
	
}
