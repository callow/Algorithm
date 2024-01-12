package com.algo.util.difference;

/**
 * 
 * ����һά��֣�
 * 	- �� 2 �� 5 ͳһ +3 / �� 1 - 6 �������� - 2 / ....
 *  - ��֧�ֱ߸ı߲飬ֻ֧���޸�һ����ȫ���꣬����ٲ� 
 *  
 * �Ȳ����в�֣�
 * 	- ��֧�ֱ߸ı߲飬ֻ֧���޸�һ����ȫ���꣬����ٲ� 
 *	- ǰ�᣺1��n��Χȫ��0����m��������Ҫ����l-r�ϣ����μ�����Ϊs,ĩ��Ϊe, ����Ϊd�ĵȲ�����
 *  - ������
 *  	Lλ��+s
 *  	L+1λ��+��d-s)
 *  	R+1λ��- (d+e)
 *  	R+2λ��+e
 *      ������2��ǰ׺��
 */
public class DifferenceUtil {

	/**
	 *  һά��֣�https://leetcode.cn/problems/corporate-flight-bookings/ =�� [1,5,4] [2,9,3]
	 *  [L] += v
	 *  [R+1] -= v
	 *  �����ǰ׺�����飡
	 *  
	 *  ���̣�
	 *   - ���� L ~ R��Χ + v �� �ڵ���Lλ��+v, ����R+1λ��-v
	 *   - �������Ҽӹ���ǰ׺�ͣ���ɵ�ǰ׺������������յĽ��
	 *  
	 */
	public static int[] corpFlightBookings(int[][] bookings, int n) {
		// ������飺 cnt
		int[] cnt = new int[n + 2];
		// ���ò�����飬ÿһ��������Ӧ��������
		for (int[] book : bookings) {
			cnt[book[0]] += book[2];
			cnt[book[1] + 1] -= book[2];
		}
		// �ӹ�ǰ׺��
		for (int i = 1; i < cnt.length; i++) {
			cnt[i] += cnt[i - 1];
		}
		// �����������ans������
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = cnt[i + 1];
		}
		
		return ans;
	}
	
	
	
	
}
