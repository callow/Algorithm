package com.algo.util.array.model;

/**
 * 数组实现： 设计循环双端队列(固定大小): https://leetcode.cn/problems/design-circular-deque/
 * 
 * addLast : 放在r+1位置 r++
 * addFirst: 
 * 	if L == 0, 放在k-1位置
 *  if L != 0, 放在L-1位置， L--
 *  
 * popFirst: 加入时候L--，弹出时候L++
 * popLast:	加入时候R++ 到了K-1位置跳到0， 弹出时候R--走到0时就去K-1位置
 * 
 */
public class CircularDeque {
	
	public int[] deque;
	public int l, r, size, limit;
	
	
	public CircularDeque (int k){
		deque = new int[k];
		l = r = size = 0;
		limit = k;
	}
	
	public boolean insertFront(int value) {
		if (isFull()) {
			return false;
		} else {
			if (isEmpty()) {
				l = r = 0;
				deque[0] = value;
			} else {
				l = l == 0 ? (limit - 1) : (l - 1);
				deque[l] = value;
			}
			size++;
			return true;
		}
	}

	public boolean insertLast(int value) {
		if (isFull()) {
			return false;
		} else {
			if (isEmpty()) {
				l = r = 0;
				deque[0] = value;
			} else {
				r = r == limit - 1 ? 0 : (r + 1);
				deque[r] = value;	
			}
			size++;
			return true;
		}
	}

	public boolean deleteFront() {
		if (isEmpty()) {
			return false;
		} else {
			l = (l == limit - 1) ? 0 : (l + 1);
			size--;
			return true;
		}
	}

	public boolean deleteLast() {
		if (isEmpty()) {
			return false;
		} else {
			r = r == 0 ? (limit - 1) : (r - 1);
			size--;
			return true;
		}
	}

	public int getFront() {
		if (isEmpty()) {
			return -1;
		} else {
			return deque[l];
		}
	}

	public int getRear() {
		if (isEmpty()) {
			return -1;
		} else {
			return deque[r];
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == limit;
	}
	
}
