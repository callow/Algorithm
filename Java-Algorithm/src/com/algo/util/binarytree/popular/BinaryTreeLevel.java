package com.algo.util.binarytree.popular;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.util.common.model.BTNode;

/**
 * 二叉树按层遍历，且知道每一层的结束位置 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 
 * 其实就是二叉树BFS
 * 
 * 1. 拿到队列的长度
 * 2. 行为重复Size遍：弹出，加左，加右
 */
public class BinaryTreeLevel {

	public static List<List<Integer>> levelOrder(BTNode head) {
		List<List<Integer>> ans = new ArrayList<>();
		Queue<BTNode> queue = new LinkedList<>();
		
		if (head != null) {
			queue.add(head);
			while (!queue.isEmpty()) { // 队列依次弹出的顺序就是BFS的顺序
				int size = queue.size();
				
				List<Integer> level = new ArrayList<Integer>();
				
				for (int i = 0; i < size; i++) {
					BTNode cur = queue.poll();
					level.add(cur.value);
					
					// 一次放2个 左，右
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
