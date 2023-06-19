package com.algo.tasks.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.algo.util.common.model.BTNode;
/**
 * 
 * ����������ͷ�ڵ�Head, ����ĳ���ڵ�Target, ����K�� ��Target������������(����ͼ)�� ���ؾ���TargetΪK�����нڵ�
 *
 */
public class DistanceKNodes {
	
	public static List<BTNode> distanceKNodes(BTNode root, BTNode target, int K) {
		int curLevel = 0;
		List<BTNode> result = new ArrayList<>();
		
		HashMap<BTNode, BTNode> parents = new HashMap<>();
		parents.put(root, null);
		
		// ���ø�Map
		createParentMap(root, parents);
		
		// Graph BFS
		Queue<BTNode> queue = new LinkedList<>();
		Set<BTNode> visited = new HashSet<>();
		queue.offer(target);
		visited.add(target);
		
		while(!queue.isEmpty()) {
			/**
			 * ����2��BFS��������
			 */
			int size = queue.size(); // ��̼��ɣ�һ��һ������Queue����һ���м����ڵ㣬e.g: size = 3
			while (size-- > 0) { // ͬһ�㵯������
				BTNode cur = queue.poll();
				if (curLevel == K) {
					result.add(cur);
				}
				if (cur.left != null && !visited.contains(cur.left)) {
					visited.add(cur.left);
					queue.offer(cur.left);
				}
				if (cur.right != null && !visited.contains(cur.right)) {
					visited.add(cur.right);
					queue.offer(cur.right);
				}
				if (parents.get(cur) != null && !visited.contains(parents.get(cur))) {
					visited.add(parents.get(cur));
					queue.offer(parents.get(cur));
				}
			}
			
			curLevel++;
			if (curLevel > K) {
				break;
			}
		}
		return result;
	}
	
	/**
	 * ����1�� ���ø�Map
	 */
	private static void createParentMap(BTNode cur, HashMap<BTNode, BTNode> parents) {
		if (cur == null) {
			return;
		}
		if (cur.left != null) {
			parents.put(cur.left, cur);
			createParentMap(cur.left, parents);
		}
		if (cur.right != null) {
			parents.put(cur.right, cur);
			createParentMap(cur.right, parents);
		}
	}
}
