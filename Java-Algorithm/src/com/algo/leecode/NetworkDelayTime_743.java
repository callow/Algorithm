package com.algo.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkDelayTime_743 {

	/**
	 * 
	 * @param times: times[i] = (ui, vi, wi), ui �����㣬vi ��Ŀ�ģ�wi �ߵ�Ȩ�� <br>
	 * k �� ��K���������ź�
	 * @return ����n���ڵ㶼�յ��źŵ���Сʱ�䣺 ��Dijkstra's max 
	 */
    public int networkDelayTime(int[][] times, int n, int k) {
		List<List<int[]>> nexts = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			nexts.add(new ArrayList<>());
		}
		for (int[] delay : times) {
			nexts.get(delay[0]).add(new int[] { delay[1], delay[2] });
		}
		// ��ǿ��
		Heap heap = new Heap(n);
		heap.add(k, 0);
		int num = 0;
		int max = 0;
		while (!heap.isEmpty()) {
			int[] record = heap.poll(); 
			int cur = record[0];
			int delay = record[1];
			num++;
			max = Math.max(max, delay);
			for (int[] next : nexts.get(cur)) {
				heap.add(next[0], delay + next[1]);
			}
		}
		return num < n ? -1 : max;
    }
    
    
	public static class Heap {
		public boolean[] used;
		public int[][] heap;
		public int[] hIndex;
		public int size;

		public Heap(int n) {
			used = new boolean[n + 1];
			heap = new int[n + 1][2];
			hIndex = new int[n + 1];
			Arrays.fill(hIndex, -1);
			size = 0;
		}

		public void add(int cur, int delay) {
			if (used[cur]) {
				return;
			}
			if (hIndex[cur] == -1) {
				heap[size][0] = cur;
				heap[size][1] = delay;
				hIndex[cur] = size;
				heapInsert(size++);
			} else {
				int hi = hIndex[cur];
				if (delay <= heap[hi][1]) {
					heap[hi][1] = delay;
					heapInsert(hi);
				}
			}
		}

		public int[] poll() {
			int[] ans = heap[0];
			swap(0, --size);
			heapify(0);
			used[ans[0]] = true;
			hIndex[ans[0]] = -1;
			return ans;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		private void heapInsert(int i) {
			int parent = (i - 1) / 2;
			while (heap[i][1] < heap[parent][1]) {
				swap(i, parent);
				i = parent;
				parent = (i - 1) / 2;
			}
		}

		private void heapify(int i) {
			int l = (i * 2) + 1;
			while (l < size) {
				int smallest = l + 1 < size && heap[l + 1][1] < heap[l][1] ? (l + 1) : l;
				smallest = heap[smallest][1] < heap[i][1] ? smallest : i;
				if (smallest == i) {
					break;
				}
				swap(smallest, i);
				i = smallest;
				l = (i * 2) + 1;
			}
		}

		private void swap(int i, int j) {
			int[] o1 = heap[i];
			int[] o2 = heap[j];
			int o1hi = hIndex[o1[0]];
			int o2hi = hIndex[o2[0]];
			heap[i] = o2;
			heap[j] = o1;
			hIndex[o1[0]] = o2hi;
			hIndex[o2[0]] = o1hi;
		}

	}
}