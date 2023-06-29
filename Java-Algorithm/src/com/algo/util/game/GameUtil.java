package com.algo.util.game;

import java.util.List;

public class GameUtil {

	/**
	 * ��N��ʯ�ӣ�˭�����һ������˭Ӯ(���ֺͺ���)<br>
	 * 
	 * true = ����Ӯ<br>
	 * 
	 * �⣺˭�����0��0��0��0... ˭�� ��������� ^�� != 0�� ����Ӯ����Ϊ�����������ƶ�ʯ���ú������ȫ0<br>
	 * 
	 * �Ƿ����ֻ�Ӯ��
	 */
	public static boolean nim(List<Integer> stones) {
		int first = stones.get(0);

		if (stones.size() == 1) {
			return true;
		}

		if (stones.size() == 2) {
			int second = stones.get(1);
			return first != second;
		}

		if (stones.size() > 1) {
			for (int i = 2; i < stones.size(); i++) {
				first ^= stones.get(i);
			}
		}
		return first != 0;
	}

	/**
	 * ֻ��1��ʯ��n����ÿ����1~m����˭�����һ������˭Ӯ <br>
	 * true = ����Ӯ<br>
	 * �⣺����Ҫά�� m+1�ı���, ��Ϊ����ÿ�ζ�����n % (m + 1) != 0 ��������������Ӯ��
	 * 
	 */
	public static boolean bash(int m, int n) {
		return n % (m + 1) != 0;
	}

	/**
	 * 2��ʯ�ӣ�a����b����ÿ�������� 1~m ����˭���������һ��˭Ӯ
	 */
	public static String whoWin(int a, int b, int m) {
		if (m > Math.max(a, b)) { // nim = ������
			return nim(List.of(a, b)) ? "����" : "����";
		}

		if (a == b) { // ����һ������copycat strategy
			// ���ֲ�����ʲô�������ܻ�����һ����ͬ���İ����ֱ���
			return "����";
		}

		// ��Ϊa==b��Ϸ�ͽ����˺���Ӯ����ô���ǰ� (a��b�Ĳ�ֵ ���� 1��)����ֵû���˵�ʱ����Ǿֵ���
		int diff = Math.max(a, b) - Math.min(a, b);
		return bash(m, diff) ? "����" : "����"; // ˭�ȰѲ�ֵ����

	}
}
