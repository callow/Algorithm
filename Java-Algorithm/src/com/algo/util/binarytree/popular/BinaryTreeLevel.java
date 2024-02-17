package com.algo.util.binarytree.popular;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.util.common.model.BTNode;

/**
 * �����������������֪��ÿһ��Ľ���λ�� : https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 
 * ��ʵ���Ƕ�����BFS
 * 
 * 1. �õ����еĳ���
 * 2. ��Ϊ�ظ�Size�飺���������󣬼���
 */
public class BinaryTreeLevel {

	public static List<List<Integer>> levelOrder(BTNode head) {
		List<List<Integer>> ans = new ArrayList<>();
		Queue<BTNode> queue = new LinkedList<>();
		
		if (head != null) {
			queue.add(head);
			while (!queue.isEmpty()) { // �������ε�����˳�����BFS��˳��
				int size = queue.size();
				
				List<Integer> level = new ArrayList<Integer>();
				
				for (int i = 0; i < size; i++) {
					BTNode cur = queue.poll();
					level.add(cur.value);
					
					// һ�η�2�� ����
					if (cur.left != null) {
						queue.add(cur.left);
					}
					if (cur.right != null) {
						queue.add(cur.right);
					}
				}
				ans.add(level);
			}
		}
		return ans;
	}
}
