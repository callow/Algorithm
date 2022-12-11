package com.algo.util.reservoir.model;
/**
 * һ��ֻ��װN����ĺ��ӣ����ǵȸ��ʷ������
 *
 */
public class Reservoir {

	private int[] bag; // һ�����ӣ�����ֻ��N����Ŀռ�
	private int N; // ��N����
	private int count; 

	/**
	 * ��ʼ����ˮ��
	 */
	public Reservoir(int capacity) {
		bag = new int[capacity];
		N = capacity;
		count = 0;
	}

	/**
	 * ���0 ~ 1
	 */
	private int rand(int max) {
		return (int) (Math.random() * max) + 1;
	}

	/**
	 * ����ÿ������ �����ӵĸ��ʾ���
	 */
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
	/**
	 * ������ȡ��
	 */
	public int[] sampling() {
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			ans[i] = bag[i];
		}
		return ans;
	}

}