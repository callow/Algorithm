package com.algo.util.array.model;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * 使用循环数组实现Queue
 */
public class Queue<T> {

	private T[] arr;
	private int pushi;// end， 进来的数放在哪
	private int polli;// begin， 弹出数从哪拿
	private int size;
	private final int limit; // 总容量，超了的话给用户报错
	private Class<T> type; // 用于生成泛型Class,因为不可以T.class

	public Queue(int limit) {
		arr = CommonArrayUtil.getArray(type, limit);
		pushi = 0;
		polli = 0;
		size = 0;
		this.limit = limit;
	}

	public void push(T value) {
		if (size == limit) {
			throw new RuntimeException("队列满了，不能再加了");
		}
		size++;
		arr[pushi] = value;
		pushi = nextIndex(pushi);
	}

	public T pop() {
		if (size == 0) {
			throw new RuntimeException("队列空了，不能再拿了");
		}
		size--;
		T ans = arr[polli];
		polli = nextIndex(polli);
		return ans;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// 如果现在的下标是i，返回下一个位置，没到最后就+1，到了最后就是0
	private int nextIndex(int i) {
		return i < limit - 1 ? i + 1 : 0;
	}
}
