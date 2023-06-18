package com.algo.util.backtracking;

/**
 * 
 * backtracking ���� = �ݹ� �� �����̵��ӹ���<br>
 * 
 *  - �κεݹ鶼���Ըĳɷǵݹ�
 *  - �ݹ��ʱ���� �� Base case
 *	
 */
public class BacktrackUtil {

	/**
	 * ��Array�����ֵ�� �����õݹ�ʵ�������
	 */
	public static int getArrayMax(int[] arr) {
		return f(arr, 0, arr.length -1);
	}
	
	private static int f(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int mid = L + (L+R) / 2;
		
		// ����Max -> ����ϵͳջѹ��
		int leftMax = f(arr,  L,  mid);
		// �Ҳ��Max
		int rightMax = f(arr,  mid,  R);
		
		// �Ƚ�һ��
		return Math.max(leftMax, rightMax);
	}
	
}
