package dynamic_program;

import java.util.Arrays;

/**
 * ���Ʊ�����⣺ https://leetcode.cn/problems/minimum-cost-for-tickets/
 * 
 * �����κ�iλ�ö���3�ַ�����
 * 	1�죨aԪ�� + f(1...)���±�1��ʼ���������������ٻ�����Ԫ
 *  7�죨bԪ�� + f(3...)���±�3��ʼ���������������ٻ�����Ԫ
 *  30�죨cԪ�� + f(4...)���±�4��ʼ���������������ٻ�����Ԫ
 */
public class LowestTicketPrice2 {
	
	public static int[] duration = {1, 7, 30};
	
	// �����ݹ飺 ���±�0��ʼ
	public static int lowestTicket1(int[] days, int[] costs) {
		return f1(days,costs, 0);
	}
	static int f1(int[] days, int[] costs, int i) {
		if(i == days.length) {//�������ˣ�����0
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		// ����ö��1��7��30�죬��Ӧ���ò�ͬ�ĺ���f1�� ��J�±�����㶨�����������ٻ�����Ǯ
		for(int k = 0, j = i; k < 3; k++) {
			while(j < days.length && days[i] + duration[k] > days[j]) {
				j++;
			}
			ans = Math.min(ans, costs[k] + f1(days,costs,j)); // ���߳�3��min
		}
		return ans;
	}
	
	//----------------------------------------------------
	//���仯���� = �Ӷ����׵�dp
	public static int lowestTicket2(int[] days, int[] costs) {
		int n = days.length;
		int[] dp = new int[n];
		for(int i = 0; i < n; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		return f2(days, costs, 0, dp);
	}
	static int f2(int[] days, int[] costs, int i, int[] dp) {
		if(i == days.length) {//�������ˣ�����0
			return 0;
		}
		if(dp[i] != Integer.MAX_VALUE) {
			return dp[i];
		}
		int ans = Integer.MAX_VALUE;
		for(int k = 0, j = i; k < 3; k++) {
			while(j < days.length && days[i] + duration[k] > days[j]) {
				j++;
			}
			ans = Math.min(ans, costs[k] + f1(days,costs,j)); // ���߳�3��min
		}
		dp[i] = ans;
		return ans;
	}
	//-----------------------------------------------------
	 
	// �ϸ�λ������ = ��λ������ӵĺ��� = �ӵ׵�����dp = �±���������� = ��dp�����������ü���
	public static int lowestTicket3(int[] days, int[] costs) {
		int n = days.length;
		int[] dp = new int[365];
		Arrays.fill(dp, 0, n + 1, Integer.MAX_VALUE);
		dp[n] = 0;
		for(int i = n-1; i >=0; i--) {
			
			for(int k = 0, j = i; k < 3; k++) {
				while(j < n && days[i] + duration[k] > days[j]) {
					j++;
				}
				dp[i] = Math.min(dp[i], costs[k] + dp[j]);
			}
			
		}
		return dp[0];
		
	}
	
	
}
