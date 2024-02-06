package com.algo.util.array.model;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * 使用循环数组实现Queue : ringArray， 如果L == R, 表示队列为Empty
 * 
 * [a,b,c,d....]
 *  L     R
 *  头     尾 
 * 加入： 放在R位置R++
 * 弹出： 拿出L位置L++
 * 
 * 
 * 循环队列： 头在追尾，尾也追头，加入x,若尾++结束了重新回到0，弹出头，拿头 头++，结束（使用一个变量Size控制队列元素个数）
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
