package com.algo.tasks.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * ����Ϊ{3, 2, 2, 3, 1}����ѯΪ(0, 3, 2)����˼�����������±�0~3�����Χ�ϣ��м���2������2��
 * �������һ������arr�����������Ĳ�ѯ�ǳ�Ƶ�����뷵�����в�ѯ�Ľ��
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
				map.get(arr[i]).add(i); // ͳ�ƴ�λ��ʱ����Ȼ����
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
			// ��ѯ < L ���±��м���
			int a = countLess(indexArr, L);
			// ��ѯ < R+1 ���±��м���
			int b = countLess(indexArr, R + 1);
			return b - a;
		}

		// ����������arr�У��ö��ֵķ�������<limit�����м���
		// Ҳ�����ö��ַ����ҵ�<limit���������ҵ�λ��
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
