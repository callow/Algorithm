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
	public int paveBricks(int n, int m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
