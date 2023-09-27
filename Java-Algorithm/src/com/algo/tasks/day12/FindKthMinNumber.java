package com.algo.tasks.day12;
/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class FindKthMinNumber {
	
		// A[s1...e1]
		// B[s2...e2]
		// һ���ȳ���
		// ��������ģ�����λ����8��4�� 10��5�� 12��6��
		public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
			int mid1 = 0;
			int mid2 = 0;
			while (s1 < e1) {
				// mid1 = s1 + (e1 - s1) >> 1
				mid1 = (s1 + e1) / 2;
				mid2 = (s2 + e2) / 2;
				if (A[mid1] == B[mid2]) {
					return A[mid1];
				}
				// �����е�һ�����ȣ�
				if (((e1 - s1 + 1) & 1) == 1) { // ��������
					if (A[mid1] > B[mid2]) {
						if (B[mid2] >= A[mid1 - 1]) { // ���ȳ������������3��
							return B[mid2];
						}
						// ��ʼ�ݹ�
						e1 = mid1 - 1;
						s2 = mid2 + 1;
					} else { // A[mid1] < B[mid2]
						if (A[mid1] >= B[mid2 - 1]) {
							return A[mid1];
						}
						e2 = mid2 - 1;
						s1 = mid1 + 1;
					}
				} else { // ż������
					if (A[mid1] > B[mid2]) {
						e1 = mid1;
						s2 = mid2 + 1;
					} else {
						e2 = mid2;
						s1 = mid1 + 1;
					}
				}
			}
			return Math.min(A[s1], B[s2]);
		}

}
