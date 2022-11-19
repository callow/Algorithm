package com.algo.util.heap.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *  ��ǿ�ѣ� <br><br>
 *  ��ͨPriorityQueueû�취���ݶ����������Եı仯�Զ������� Tһ��Ҫ�Ƿǻ������ͣ��л������������һ��<br>
 *  ʹ�÷�������
 *
 */
public class SuperHeap<T> {
	private ArrayList<T> heap; // ��̬����
	private HashMap<T, Integer> indexMap; // �������������Ƕ�����������е�ʲôλ�ã�
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
	 * �����Ѷ�����ɾ���Ѷ���
	 */
	
	public T pop() {
		T ans = heap.get(0);
		swap(0, heapSize - 1); // ���һ����0λ�ý���
		indexMap.remove(ans); // ɾ����ĸ�
		heap.remove(--heapSize); // ɾ����ĸ�
		heapify(0); // ��0λ����heapify - Logn
		return ans;
	}

	/**
	 * ɾ��������ж��� ���������ǶѶ���
	 */
	
	public void remove(T obj) {
		T replace = heap.get(heapSize - 1); // �ó��������һ��Ԫ��
		int index = indexMap.get(obj); // obj�ڵ�λ��
		indexMap.remove(obj); // ɾ��Obj
		heap.remove(--heapSize); // �����Ƴ����һ��Ԫ�أ�û��ϵ��Ϊ֮ǰ�Ѿ���ȡ������replace
		if (obj != replace) {
			heap.set(index, replace); // ���һ��Ԫ��������ǰobj��λ��
			indexMap.put(replace, index); // ���·�������
			resign(replace); // ����
		}
	}

	/**
	 * ��һ���Ѿ��仯�˵Ķ����Զ�������Ϊ���������˷������� Logn <br><br>
	 * heapInsert ��  heapify ������������ֻ�ᶯһ������.
	 */
	
	public void resign(T obj) {
		int loc = indexMap.get(obj); // �õ�����λ��
		heapInsert(loc); 
		heapify(loc);
	}

	// �뷵�ض��ϵ�����Ԫ��
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
