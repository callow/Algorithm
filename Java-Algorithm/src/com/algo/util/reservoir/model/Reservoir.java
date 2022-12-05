package com.algo.util.reservoir.model;
/**
 * 一个只能装N个球的盒子，球是等概率放入盒子
 *
 */
public class Reservoir {

	private int[] bag; // 一个袋子，里面只有N个球的空间
	private int N; // 有N个求
	private int count; 

	public Reservoir(int capacity) {
		bag = new int[capacity];
		N = capacity;
		count = 0;
	}

	private int rand(int max) {
		return (int) (Math.random() * max) + 1;
	}

	public void add(int num) {
		count++;
		if (count <= N) {
			bag[count - 1] = num;
		} else {
			if (rand(count) <= N) {
				bag[rand(N) - 1] = num;
			}
		}
	}

	public int[] sampling() {
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			ans[i] = bag[i];
		}
		return ans;
	}

}
