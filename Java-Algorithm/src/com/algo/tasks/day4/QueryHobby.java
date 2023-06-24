package com.algo.tasks.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)。意思是在数组里下标0~3这个范围上，有几个2？返回2。
 * 假设给你一个数组arr，对这个数组的查询非常频繁，请返回所有查询的结果
 *
 */
public class QueryHobby {

	public static class QueryBox {
		private Map<Integer, List<Integer>> map;

		public QueryBox(int[] arr) {
			map = new HashMap<>();
			for (int i = 0; i < arr.length; i++) {
				if (!map.containsKey(arr[i])) {
					map.put(arr[i], new ArrayList<>());
				}
				map.get(arr[i]).add(i); // 统计词位的时候天然有序
			}
		}

		/**
		 * O(Log(N))
		 */
		
		public int query(int L, int R, int value) {
			if (!map.containsKey(value)) {
				return 0;
			}
			List<Integer> indexArr = map.get(value);
			// 查询 < L 的下标有几个
			int a = countLess(indexArr, L);
			// 查询 < R+1 的下标有几个
			int b = countLess(indexArr, R + 1);
			return b - a;
		}

		// 在有序数组arr中，用二分的方法数出<limit的数有几个
		// 也就是用二分法，找到<limit的数中最右的位置
		private int countLess(List<Integer> arr, int limit) {
			int L = 0;
			int R = arr.size() - 1;
			int mostRight = -1;
			while (L <= R) {
				int mid = L + ((R - L) >> 1);
				if (arr.get(mid) < limit) {
					mostRight = mid;
					L = mid + 1;
				} else {
					R = mid - 1;
				}
			}
			return mostRight + 1;
		}
	}
}
