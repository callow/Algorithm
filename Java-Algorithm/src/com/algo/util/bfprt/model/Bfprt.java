package com.algo.util.bfprt.model;

import com.algo.util.common.CommonArrayUtil;
/**
 * 
 *  ������������: < k | = k | >k , ���е�pivotѡ��������ġ���� =��������k�� ֱ�ӷ��ء�
 *  ��֮�����/�Ҳ�ֻ��һ��<br>
 *  bfprt �ǽ�����ѡһ��pivot������90% ����ŵĺ�������һ����<br><br>
 *  1. ����ÿ5������һС��<br>
 *  2. ÿһС�����򣬶�������Ȼ���� O(N)<br>
 *  3. �ó�ÿ��С�����λ�������һ���µ�����m[] ��ż����������λ������С�� N/5<br>
 *  4. ��m[]�е���λ��: p<br>
 *  5. ���h���ǽ�����pivot/p. ����bfprt��< p | = p | > p, û���м���bfprt <br> <br>
 *  
 *  ��Ϊ�� <= p �� 3N/10. >p ��7N/10. ����ÿ�εݹ�������̭��3N/10. ������Ҳֻ��Ҫ�е�7N/10����. <br>
 *  bfprtĿ�ľ�����<p�Ĺ�ģ��С ������3N/10�Ĺ�ģ>=p �������7N/10 <P.
 *	
 */
public class Bfprt {
	
	
	public static int findMinKth(int[] array, int k ) {
		int[] arr = CommonArrayUtil.copyArray(array);
		return Bfprt.go(arr, 0, arr.length - 1, k - 1);
	}

	/**
	 *  O(n)
	 */
	private static int go(int[] arr, int L, int R, int index) {
		if (L == R) {
			return arr[L];
		}
		// L...R  ÿ�����һ��
		// ÿһ��С���ڲ��ź���
		// С�����λ�����������
		// ������������λ������
		int pivot = medianOfMedians(arr, L, R);
		int[] range = partition(arr, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return arr[index];
		} else if (index < range[0]) {
			return go(arr, L, range[0] - 1, index);
		} else {
			return go(arr, range[1] + 1, R, index);
		}
	}
	
	// arr[L...R]  �����һ��
	// ÿ��С���ڲ�����
	// ÿ��С����λ������������marr
	// marr�е���λ��������
	private static int medianOfMedians(int[] arr, int L, int R) {
		int size = R - L + 1;
		int offset = size % 5 == 0 ? 0 : 1;
		int[] mArr = new int[size / 5 + offset];
		for (int team = 0; team < mArr.length; team++) {
			int teamFirst = L + team * 5;
			// L ... L + 4
			// L +5 ... L +9
			// L +10....L+14
			mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
		}
		// marr�У��ҵ���λ��
		// marr(0, marr.len - 1,  mArr.length / 2 )
		return go(mArr, 0, mArr.length - 1, mArr.length / 2);
	}
	
	private static int getMedian(int[] arr, int L, int R) {
		insertionSort(arr, L, R);
		return arr[(L + R) / 2];
	}
	
	private static void insertionSort(int[] arr, int L, int R) {
		for (int i = L + 1; i <= R; i++) {
			for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
				CommonArrayUtil.swap(arr, j, j + 1);
			}
		}
	}
	
	private static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int cur = L;
		while (cur < more) {
			if (arr[cur] < pivot) {
				CommonArrayUtil.swap(arr, ++less, cur++);
			} else if (arr[cur] > pivot) {
				CommonArrayUtil.swap(arr, cur, --more);
			} else {
				cur++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}
}
