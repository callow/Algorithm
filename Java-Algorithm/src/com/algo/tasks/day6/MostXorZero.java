package com.algo.tasks.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * һ��arr[], �����з�Ϊ���ɸ����ཻ�����飬������һ�����Ž�ʹ���г�����=0����������࣬���������ٸ���
 * 
 *
 */
public class MostXorZero {
	/**
	 * 
	 * ������
	 */
	public static int mostXorZero1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		// �����鼼�� �� ���� ���� Ԥ��������
		int[] eor = new int[N];
		eor[0] = arr[0];
		for (int i = 1; i < N; i++) {
			eor[i] = eor[i-1] ^ arr[i];
		}
		
		return f(eor, 1, new ArrayList<>());
	}
	
	/**
	 * 
	 * @param eor : �̶���
	 * @param index �� ��ǰ����Indexλ��
	 * @param parts �� �����зֵ�ÿ�����ֽ�����λ��
	 * indexȥ������ǰһ�粿���Ƿ�������������(�з�)�Ͱ�index����parts�У����������(����)�Ͳ���
	 */
	private static int f(int[] eor, int index, List<Integer> parts) {
		int ans = 0;
		if (index == eor.length) { // ��������ʱ��
			parts.add(eor.length);
			ans = eorZeroParts(eor, parts); // ͳ��һ�����˼���
			parts.remove(parts.size() - 1); // ������ȱ��� �����ֳ�
		} else {
			// û�а�index����parts�У� ǰһ���ֲ�����
			int p1 = f(eor, index + 1, parts);
			parts.add(index);
			int p2 = f(eor, index + 1, parts);
			parts.remove(parts.size() - 1);
			ans = Math.max(p1, p2);
		}
		return ans;
	}
	// ����ͳ�ƣ�ͳ�����и���
	private static int eorZeroParts(int[] eor, List<Integer> parts) {
		int L = 0;
		int ans = 0;
		for (Integer end : parts) {
			if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
				ans++;
			}
			L = end;
		}
		return ans;
	}
	
	// ʱ�临�Ӷ�O(N)�ķ���
	public static int mostXor(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] dp = new int[N];
		
		// key ĳһ��ǰ׺����
		// value ���ǰ׺�����ϴγ��ֵ�λ��(����)
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		// 0~i���������
		int xor = 0;
		for (int i = 0; i < N; i++) {
			xor ^= arr[i];
			if (map.containsKey(xor)) { // ������2
				int pre = map.get(xor);
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
			}
			if (i > 0) { // Ҫ��dp[i-1] pkһ�£����һ��Ҫ��֤i>0
				dp[i] = Math.max(dp[i - 1], dp[i]);
			}
			map.put(xor, i);
		}
		return dp[N - 1];
	}
	
	
}
