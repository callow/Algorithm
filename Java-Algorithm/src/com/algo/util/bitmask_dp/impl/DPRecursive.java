package com.algo.util.bitmask_dp.impl;

import java.util.ArrayList;
import java.util.List;

import com.algo.util.bitmask_dp.StateCompressionDPService;

public class DPRecursive implements StateCompressionDPService {

	/**
	 * O(N*N!) ̫����
	 */
	@Override
	public boolean canWin(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		int[] arr = new int[choose];
		for (int i = 0; i < choose; i++) {
			arr[i] = i + 1;
		}
		// arr[i] != -1 ��ʾarr[i]������ֻ�û������
		// arr[i] == -1 ��ʾarr[i]��������Ѿ�������
		// ���ϣ�arr��1~choose
		return pick(arr, total);
	}

	/**
	 * 
	 * ��ǰ�ֵ������ã�����ֻ��ѡ����arr�л����ڵ����֣���ʣrest��ôֵ���������ֻ᲻��ӮO(N!)
	 */
	public static boolean pick(int[] arr, int rest) {
		if (rest <= 0) {
			return false; // ��������Ѿ��������ֱ������
		}
		// ����ȥ�������е����
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != -1) {
				int cur = arr[i];
				arr[i] = -1; // ����
				boolean next = pick(arr, rest - cur); // ���ֵĺ������̣��㱨��˭Ӯ��
				arr[i] = cur; // ���곢�Ժ󣬰����ֻ�ԭ��ȥ���ָ��ֳ���
				if (!next) { // ���ֵĺ������̸������֣�(�Ǻ������̵ĺ���Ӯ�� = �ǵ�ǰ���̵�����Ӯ��)
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int tsp(int[][] distances) {
		int cities = distances.length; // 0...N-1
		
		// set.get(i) != null i���������ڼ�����
		// set.get(i) == null i�������в��ڼ�����
		List<Integer> set = new ArrayList<>();
		for (int i = 0; i < cities; i++) {
			set.add(1);
		}
		return go(distances, set, 0);
	}
	/**
	 * 
	  �κ���������֮��ľ��룬������matrix�����õ�
	  set�б�ʾ����Щ���еļ��ϣ�
	  start������һ����set�
	  ��start������Ҫ��set�����еĳ��й�һ�飬���ջص�0�������У���С�����Ƕ���
	  
	  e.g: go(matrix, {null,1,null,1,1,null}, 3)
	  	city:			0   1  2   3 4  5
	 */
	public static int go(int[][] matrix, List<Integer> set, int start) {
		int cityNum = 0; 
		// ������ʣ�������У�set�л�ʣ��������
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) != null) {
				cityNum++;
			}
		}
		
		// ���ֻʣ1������= base case, ���شӵ�ǰstartֱ�ӻع��޵�0�ľ���
		if (cityNum == 1) {
			return matrix[start][0];
		}
		// cityNum > 1  ��ֻstart��һ����
		set.set(start, null); // �Ȱ��Լ��Ӽ�����ȥ��
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) != null) {
				// start -> i i... -> 0
				int cur = matrix[start][i] + go(matrix, set, i);
				min = Math.min(min, cur);
			}
		}
		set.set(start, 1); // �ָ��ֳ�
		return min;
	}

	@Override
	public int paveBricks(int N, int M) {
		if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
			return 0;
		}
		if (N == 1 || M == 1) {
			return 1;
		}
		int[] pre = new int[M]; // pre����-1�е�״��
		for (int i = 0; i < pre.length; i++) {
			pre[i] = 1;
		}
		return process(pre, 0, N);
	}
	
	// pre ��ʾlevel-1�е�״̬
	// level��ʾ������level��������
	// N ��ʾһ���ж����� �̶���
	// level-2�м���֮�������У�������ש��
	// level��������������������������������
	public int process(int[] pre, int level, int N) {
		if (level == N) { // base case
			for (int i = 0; i < pre.length; i++) {
				if (pre[i] == 0) { // �Ѿ�����Level�㣬ֻҪ�ϲ��ש��һ������ => 0�ַ���
					return 0;
				}
			}
			return 1;
		}

		// û����ֹ�У�����ѡ���ڵ�ǰ��level�аڴ�ש
		int[] op = getOp(pre);
		return dfs(op, 0, level, N);
	}
	
	/**
	 * ������һ�е�״̬������ʱ���Խ��еĲ��� => 0��1�� 1��0
	 * pre: 0001100
	 * op:  1110011
	 */
	private int[] getOp(int[] pre) {
		int[] cur = new int[pre.length];
		for (int i = 0; i < pre.length; i++) {
			cur[i] = pre[i] ^ 1;
		}
		return cur;
	}
	

	/**
	 * op[i] == 0 ���Կ��ǰ�ש
	   op[i] == 1 ֻ����������
	 */
	private int dfs(int[] op, int col, int level, int N) {
		// ���������ɷ��ӣ���������ȱ�������col������ֹ�У�i�еľ���������
		// �ֵ�i+1�У�������
		if (col == op.length) { // ��ǰ���������һ�У���ôȥ��һ�м���
			return process(op, level + 1, N);
		}
		// ��ǰ�����û�������һ��
		int ans = 0;
		// colλ�ò����
		ans += dfs(op, col + 1, level, N); // colλ���ϲ��ں�ת
		// colλ�ú��, ����
		if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
			op[col] = 1;
			op[col + 1] = 1;
			ans += dfs(op, col + 2, level, N);
			op[col] = 0;
			op[col + 1] = 0;
		}
		return ans;
	}


}
