package Searching;

import java.util.Arrays;

public class FindThreeLargestNum {

	public static void main(String[] args) {
		int[] input = { 141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7 };
		System.out.println(Arrays.toString(findThreeLargestNumbers(input)));
	}

	public static int[] findThreeLargestNumbers(int[] array) {
		int[] threeLargest = { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };
		for (int i : array) {
			update(threeLargest, i);
		}
		return threeLargest;
	}

	public static void update(int[] container, int num) {
		if (num > container[2]) {
			shift(container, num, 2);
		} else if (num > container[1]) {
			shift(container, num, 1);
		} else if (num > container[0]) {
			shift(container, num, 0);
		}
	}

	// ����Ǵ�������
	public static void shift(int[] container, int num, int idx) {
		// ���Ҫ���µ�3�������2����1��ҲҪ���ƣ�
		// ���Ҫ���µ�2�������1��ҲҪ���ơ�
		// ���Ҫ���µ�1���������ƶ�������
		for (int i = 0; i < idx; i++) {
			if (i == idx) {
				container[i] = num; // ��������
			} else {
				container[i] = container[i + 1]; // �����Ƹ�ֵ
			}
		}
	}
}
