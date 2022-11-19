package com.algo.util.array.model;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * ʹ��ѭ������ʵ��Queue
 */
public class Queue<T> {

	private T[] arr;
	private int pushi;// end�� ��������������
	private int polli;// begin�� ������������
	private int size;
	private final int limit; // �����������˵Ļ����û�����
	private Class<T> type; // �������ɷ���Class,��Ϊ������T.class

	public Queue(int limit) {
		arr = CommonArrayUtil.getArray(type, limit);
		pushi = 0;
		polli = 0;
		size = 0;
		this.limit = limit;
	}

	public void push(T value) {
		if (size == limit) {
			throw new RuntimeException("�������ˣ������ټ���");
		}
		size++;
		arr[pushi] = value;
		pushi = nextIndex(pushi);
	}

	public T pop() {
		if (size == 0) {
			throw new RuntimeException("���п��ˣ�����������");
		}
		size--;
		T ans = arr[polli];
		polli = nextIndex(polli);
		return ans;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// ������ڵ��±���i��������һ��λ�ã�û������+1������������0
	private int nextIndex(int i) {
		return i < limit - 1 ? i + 1 : 0;
	}
}
