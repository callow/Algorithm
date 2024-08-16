package com.algo.util.game;

import java.util.List;
/**
 * ICG(��ƽ�����Ϸ)
 */
public class GameUtil {




	/**
	 * 2��ʯ�ӣ�a����b����ÿ�������� 1~m ����˭���������һ��˭Ӯ
	 */
	public static String whoWin(int a, int b, int m) {
		if (m > Math.max(a, b)) { // nim = ������
			return Nim.whoWin(List.of(a, b));
		}

		if (a == b) { // ����һ������copycat strategy
			// ���ֲ�����ʲô�������ܻ�����һ����ͬ���İ����ֱ���
			return "����";
		}

		// ��Ϊa==b��Ϸ�ͽ����˺���Ӯ����ô���ǰ� (a��b�Ĳ�ֵ ���� 1��)����ֵû���˵�ʱ����Ǿֵ���
		int diff = Math.max(a, b) - Math.min(a, b);
		return Bash.whoWin(m, diff); // ˭�ȰѲ�ֵ����

	}
}
