package com.algo.util.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ʽɸ
 * 
 * - 0 ~ n��Χ�ڵ�����
 * - O(n * log(logn)) , ���ͼ���
 * - ����ʱ��С
 */
public class Ehrlich {

	/**
	 * ͳ�Ƹ���
	 */
	public static int count(int n) {
		// visit[i] = true������i�Ǻ���
		// visit[i] = false������i������
		// ��ʼʱ��Ϊ0~n��������������
		boolean[] visit = new boolean[n + 1];
		for (int i = 2; i * i <= n; i++) {
			if (!visit[i]) {
				for (int j = i * i; j <= n; j += i) {
					visit[j] = true;
				}
			}
		}
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				// ��ʱi���������������ռ���Ҳ���Լ���
				cnt++;
			}
		}
		return cnt;
	}
	
	
	/**
	 * �ռ����
	 */
	public static List<Integer> collect(int n) {
		
		// visit[i] = true������i�Ǻ���
		// visit[i] = false������i������
		// ��ʼʱ��Ϊ0~n��������������
		boolean[] visit = new boolean[n + 1];
		for (int i = 2; i * i <= n; i++) {
			if (!visit[i]) {
				for (int j = i * i; j <= n; j += i) {
					visit[j] = true;
				}
			}
		}
		List<Integer> r = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				// ��ʱi���������������ռ���Ҳ���Լ���
				r.add(i);
			}
		}
		return r;
	}
}
