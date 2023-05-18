package com.algo.util.bitmask_dp;

import com.algo.util.bitmask_dp.impl.DPStateCompressionCache;
import com.algo.util.bitmask_dp.impl.DPStateCompressiondp;

/**
 * 
 * ״̬ѹ���Ķ�̬�滮: ����λ������ѹ���ռ� = ����λѹ���ļ��仯����<br>
 * 
 * ���÷�Χ��Ҫ/��Ҫ ����
 *
 */
public class StateCompressionUtil {

	/**
	 * 100 GAME : һ����Ϸ��2��ѡ��������maxChoosableInteger��һ�����ӣ�˭���ȼӵ�desiredTotal˭��Ӯ<br>
	 * 
	 * maxChoosableInteger(99): �� 1 -99 �в��ɷŻ�ȡ��<br>
	 * desiredTotal(100): ˭����ȵ�100��˭��Ӯ (<=300)
	 * 
	 * �������ֻ᲻��Ӯ
	 */
	public static boolean canWin100Game(int maxChoosableInteger, int desiredTotal) {
		return new DPStateCompressionCache().canWin(maxChoosableInteger, desiredTotal);
	}
	
	/**
	 * 
	 * TSP���⣺Travelling salesman problem <br>
	 * 
	 * N����������ͼ�����е㵽��������N*N�����У�ѡһ�����У��߱��������ܾ�����С<br>
	 * 
	 * ���մ���һ��������˴�ʲô���г�������Ӱ�����յĴ𰸣�ǿ�а�0��Ϊ��ʼ/������
	 */
	public static int tsp(int[][] distances) {
		return new DPStateCompressiondp().tsp(distances);
	}
	
	
}
