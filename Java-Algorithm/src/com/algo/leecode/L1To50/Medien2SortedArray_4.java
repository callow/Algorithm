package com.algo.leecode.L1To50;

import com.algo.util.sort.SortUtil;

/**
 *  ����num1��ȡǰ��������k
 *
 */

public class Medien2SortedArray_4 {
	
	public static void main(String[] args) {
		
		findMedianSortedArrays(new int[] {1,2}, new int[] {3,4});
	}
	/**
	 * O(log (m+n) <br>
	 * 	�������� Space O(1) ,����ͬʱ����2��SortedArray <br>
	 * 	��λ��λ�� k =  (n1 + n2 + 1) / 2 
	 *  ��n1��ȡ����Ԫ�ع��׸�k => n1'�� ��n2��ȡ����  => n2'��<br>
	 *  ��ע�� num1[n1'-1], num1[n1'], num2[n2'-1], num2[n2'] <br>
	 *  ���֣�num1[n1'] < num2[n2'-1] ? num1�õĻ�̫�٣������ø���Ԫ�� -> �ҵ�n1' -> n2' = k - n1'
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length , n2 = nums2.length;
		if (n1 > n2) { // ������ƽ��ʱ����С��������(nums2)��2�ָ���
			return findMedianSortedArrays(nums2,nums1);
		}
		
		int k = (n1 + n2 + 1) / 2; // ����λ������k , ���±�Ϊk-1
		int L = 0;
		int R = n1;
		while (L < R) { // ֻ��nums1
			int m1 = L + (R - L) / 2;
			int m2 = k - m1;
			if (nums1[m1] < nums2[m2 -1] ) { // nums1��Ԫ�ظ��������������
				L = m1 + 1; 
			} else {
				R = m1; // nums1��Ԫ��̫���ˣ�����һ��
			}
		}
		
		// left = right ʱѭ����������ʱ���ʶ��nums1��ȡǰm1������nums2��ȡǰm2�����Դճ�K
		// e.g: nums1: m1-1=5 m1=7   nums2: m2-1=6 m2=8
		int m1 = L;
		int m2 = k - L;
		
		// nums1 -> 5,7 , num2 -> 6,8 ���� 5��6��2�����������λ��ȡһ�����
		int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 -1], 
						  m2 <= 0 ? Integer.MIN_VALUE: nums2[m2 -1]);
		if ((n1 + n2) % 2  == 1) { // ������
			return c1;
		}
		
		// // nums1 -> 5,7 , num2 -> 6,8 ���� 7��8��2�����������λ��ȡһ��С��
		int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
						  m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
		return (c1 + c2) * 0.5; // ż����
	}
	
	
	
	/**
	 * O(N)
	 * Ҳ������O��N���������㷨���� e.g countSort, RadixSort
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
