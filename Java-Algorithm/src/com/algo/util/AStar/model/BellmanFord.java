package com.algo.util.AStar.model;

import java.util.Arrays;

/**
 * ������Ford�㷨��
 * 	M: �ߵ�����
 *  N �� ����������������N-1��
 *  Time: O(M*N)�� ���ӶȺܸ� ֻ������Сͼ
 *  
 * 
 * ��Դ���·�� ��DJһ��Ŀ�ģ����ǿ����и��� �������и���
 *  - �ɳڲ�����Ŀ��A -> S, ������P�� ��distance[p] + w < distance[s], ����distance[s]��С�ģ���P���������߶�S�������ɳڲ��� 
 *  - ��ͣ����ѯ�ߺܶ��ֽ����ɳڲ�����ֱ���������ɳڲ�����Ϊ�� �㷨ֹͣ
 *  - ��ʼ״̬: [0, ��,��,��]  
 *  		  a  b  c d
 *   - ���¹���:[0, ��, 8,6]
 *            a  b c d
 *  
 *  ��Ҫ�ƹ㣺 �ж��Ƿ��и���������A��������N���ɳڻ����ڣ�˵��A�����ܹ�����һ������
 *  
 *  https://leetcode.cn/problems/cheapest-flights-within-k-stops/
 *   - �ɳ������̶���K��
 */
public class BellmanFord {

	// Bellman-Ford�㷨
	// ��Դ����д���ɳ��߼������Ͻ���ϸ��
	public static int findCheapestPrice(int n, int[][] flights, int start, int target, int k) {
		int[] cur = new int[n];
		Arrays.fill(cur, Integer.MAX_VALUE); // ��ʼ���ϱ�
		cur[start] = 0;
		for (int i = 0; i <= k; i++) {
			int[] next = Arrays.copyOf(cur, n); // �����ϱ�
			for (int[] edge : flights) { // �������к���
				// ��һ���ߣ�a -> b , w. ���Ǵ��ϱ����ÿ����Ƿ���·(!=��), Ȼ������±�
				if (cur[edge[0]] != Integer.MAX_VALUE) {
					next[edge[1]] = Math.min(next[edge[1]], cur[edge[0]] + edge[2]);
				}
			}
			cur = next;
		}
		return cur[target] == Integer.MAX_VALUE ? -1 : cur[target];
	}
}
