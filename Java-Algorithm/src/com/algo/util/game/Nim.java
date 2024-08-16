package com.algo.util.game;

import java.util.List;

public class Nim {

	
	/**
	 * ��N��ʯ�ӣ�˭�����һ������˭Ӯ(���ֺͺ���)<br>
	 * 
	 * true = ����Ӯ<br>
	 * 
	 * �⣺˭�����0��0��0��0... ˭�� ��������� ^�� != 0�� ����Ӯ����Ϊ�����������ƶ�ʯ���ú������ȫ0<br>
	 * 
	 * 	  ^ != 0 , ��ʤ̬�� ��Ϊ���ֿ��Զ�һ�����ú������ ^==0��״̬��ֱ����Ϊֹ
	 *    ^ == 0 , �ذ�̬
	 * 
	 * �Ƿ����ֻ�Ӯ��
	 */
	public static String whoWin(List<Integer> stones) {
		int first = stones.get(0);

		if (stones.size() == 1) {
			return "����";
		}

		if (stones.size() == 2) {
			int second = stones.get(1);
			return first != second ? "����" : "����";
		}

		if (stones.size() > 1) {
			for (int i = 2; i < stones.size(); i++) {
				first ^= stones.get(i);
			}
		}
		return first != 0 ? "����" : "����";
	}
}
