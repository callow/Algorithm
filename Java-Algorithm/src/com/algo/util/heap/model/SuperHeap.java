package com.algo.util.heap.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *  加强堆： <br><br>
 *  普通PriorityQueue没办法根据堆中数据属性的变化自动调整。 T一定要是非基础类型，有基础类型需求包一层<br>
 *  使用反向索引
 *
 */
public class SuperHeap<T> {
	private ArrayList<T> heap; // 动态数组
	private HashMap<T, Integer> indexMap; // 反向索引，就是对象放在数组中的什么位置！
	private int heapSize;
	private Comparator<? super T> comp;

	public SuperHeap(Comparator<? super T> c) {
		heap = new ArrayList<>();
		indexMap = new HashMap<>();
		heapSize = 0;
		comp = c;
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public int size() {
		return heapSize;
	}

	public boolean contains(T obj) {
		return indexMap.containsKey(obj);
	}

	public T peek() {
		return heap.get(0);
	}

	public void push(T obj) {
		heap.add(obj);
		indexMap.put(obj, heapSize);
		heapInsert(heapSize++);
	}

	/**
	 * 弹出堆顶：（删除堆顶）
	 */
	
	public T pop() {
		T ans = heap.get(0);
		swap(0, heapSize - 1); // 最后一个与0位置交换
		indexMap.remove(ans); // 删最后哪个
		heap.remove(--heapSize); // 删最后哪个
		heapify(0); // 在0位置玩heapify - Logn
		return ans;
	}

	/**
	 * 删除任意堆中对象： （不单单是堆顶）
	 */
	
	public void remove(T obj) {
		T replace = heap.get(heapSize - 1); // 拿出堆中最后一个元素
		int index = indexMap.get(obj); // obj在的位置
		indexMap.remove(obj); // 删除Obj
		heap.remove(--heapSize); // 堆中移除最后一个元素，没关系因为之前已经提取出来了replace
		if (obj != replace) {
			heap.set(index, replace); // 最后一个元素来到当前obj的位置
			indexMap.put(replace, index); // 跟新反向索引
			resign(replace); // 调整
		}
	}

	/**
	 * 给一个已经变化了的对象，自动调整堆为有序，利用了反向索引 Logn <br><br>
	 * heapInsert 与  heapify 上下两个方向只会动一个最终.
	 */
	
	public void resign(T obj) {
		int loc = indexMap.get(obj); // 拿到它的位置
		heapInsert(loc); 
		heapify(loc);
	}

	// 请返回堆上的所有元素
	public List<T> getAllElements() {
		List<T> ans = new ArrayList<>();
		for (T c : heap) {
			ans.add(c);
		}
		return ans;
	}

	private void heapInsert(int index) {
		while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
			swap(index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	private void heapify(int index) {
		int left = index * 2 + 1;
		while (left < heapSize) {
			int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
			best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
			if (best == index) {
				break;
			}
			swap(best, index);
			index = best;
			left = index * 2 + 1;
		}
	}

	private void swap(int i, int j) {
		T o1 = heap.get(i);
		T o2 = heap.get(j);
		heap.set(i, o2);
		heap.set(j, o1);
		indexMap.put(o2, i);
		indexMap.put(o1, j);
	}
}
