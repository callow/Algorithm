package com.algo.util.bitmask_dp;

import com.algo.util.bitmask_dp.impl.DPStateCompression;

/**
 * 
 * ״̬ѹ���Ķ�̬�滮: ����λ������ѹ���ռ� = ����λѹ���ļ��仯����
 *
 */
public class StateCompressionUtil {

	/**
	 * 100 GAME : һ����Ϸ��2��ѡ��������maxChoosableInteger��һ�����ӣ�˭���ȼӵ�desiredTotal˭��Ӯ<br>
	 * 
	 * maxChoosableInteger(99): �� 1 -99 �в��ɷŻ�ȡ��<br>
	 * desiredTotal(100): ˭����ȵ�100��˭��Ӯ
	 * 
	 * �������ֻ᲻��Ӯ
	 */
	public static boolean canWin100Game(int maxChoosableInteger, int desiredTotal) {
		return new DPStateCompression().canWin(maxChoosableInteger, desiredTotal);
	}
}
