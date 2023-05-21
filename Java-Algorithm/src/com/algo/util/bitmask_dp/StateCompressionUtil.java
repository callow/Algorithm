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
	 * TSP���⣺Travelling salesman problem O��n*2^n��<br>
	 * 
	 * N����������ͼ�����е㵽��������N*N�����У�ѡһ�����У��߱��������ܾ�����С<br>
	 * 
	 * ���մ���һ��������˴�ʲô���г�������Ӱ�����յĴ𰸣�ǿ�а�0��Ϊ��ʼ/������
	 */
	public static int tsp(int[][] distances) {
		return new DPStateCompressiondp().tsp(distances);
	}
	
	/**
	 * �̴�ש���⣺��1*2��ש������ M * N �����м��ֲ�ͬ�̷���<br>
	 * 
	 * �⣺����һ�£���שֻ������/�����̴�ש, ��ôi-2�����ϵ���һ����������
	 * 
	 */
	public static int paveBricks(int n, int m) {
		return new DPStateCompressiondp().paveBricks(n, m);
	}
	
	
}
