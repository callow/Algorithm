package com.algo.util.prime;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ŷ��ɸ
 * 
 *  - ͳ��0 ~ n��Χ�ڵ�����
 *  - O(n)
 *  - ����ʱ���
 */
public class Euler {

	public static int count(int n) {
		// visit[i] = true������i�Ǻ���
		// visit[i] = false������i������
		// ��ʼʱ��Ϊ0~n��������������
		boolean[] visit = new boolean[n + 1];
		// prime�ռ����е��������ռ��ĸ�����cnt
		int[] prime = new int[n / 2 + 1];
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				prime[cnt++] = i;
			}
			for (int j = 0; j < cnt; j++) {
				if (i * prime[j] > n) {
					break;
				}
				visit[i * prime[j]] = true;
				if (i % prime[j] == 0) {
					break;
				}
			}
		}
		return cnt;
	}
	
	public static List<Integer> collect(int n) {
		// visit[i] = true������i�Ǻ���
		// visit[i] = false������i������
		// ��ʼʱ��Ϊ0~n��������������
		boolean[] visit = new boolean[n + 1];
		// prime�ռ����е��������ռ��ĸ�����cnt
		int[] prime = new int[n / 2 + 1];
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				prime[cnt++] = i;
			}
			for (int j = 0; j < cnt; j++) {
				if (i * prime[j] > n) {
					break;
				}
				visit[i * prime[j]] = true;
				if (i % prime[j] == 0) {
					break;
				}
			}
		}
		return Arrays.stream(prime).boxed().collect(Collectors.toList());
	}
}
