package com.algo.tasks.day12;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * ����������� ��https://leetcode.com/problems/longest-consecutive-sequence/
 * 
 * 
 */

public class LongestConsecutive {

	// ���Ͻ��Ľⷨ,ֻʹ��һ��hashmap�Ϳ��Ը����
		public static int longestConsecutive(int[] nums) {
			HashMap<Integer, Integer> map = new HashMap<>();
			int len = 0;
			for (int num : nums) {
				if (!map.containsKey(num)) {
					map.put(num, 1);
					int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
					int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
					int all = preLen + posLen + 1;
					map.put(num - preLen, all);
					map.put(num + posLen, all);
					len = Math.max(len, all);
				}
			}
			return len;
		}

		// ����һ�����ű�ͷ��β���ǳ������ķ���
		// �������Ž⣬���Ǻ����
		public static int longestConsecutive2(int[] nums) {
			HashMap<Integer, Integer> headMap = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> tailMap = new HashMap<Integer, Integer>();
			HashSet<Integer> visited = new HashSet<>();
			for (int num : nums) {
				if (!visited.contains(num)) {
					visited.add(num);
					headMap.put(num, 1);
					tailMap.put(num, 1);
					if (tailMap.containsKey(num - 1)) {
						int preLen = tailMap.get(num - 1);
						int preHead = num - preLen;
						headMap.put(preHead, preLen + 1);
						tailMap.put(num, preLen + 1);
						headMap.remove(num);
						tailMap.remove(num - 1);
					}
					if (headMap.containsKey(num + 1)) {
						int preLen = tailMap.get(num);
						int preHead = num - preLen + 1;
						int postLen = headMap.get(num + 1);
						int postTail = num + postLen;
						headMap.put(preHead, preLen + postLen);
						tailMap.put(postTail, preLen + postLen);
						headMap.remove(num + 1);
						tailMap.remove(num);
					}
				}
			}
			int ans = 0;
			for (int len : headMap.values()) {
				ans = Math.max(ans, len);
			}
			return ans;
		}
}
