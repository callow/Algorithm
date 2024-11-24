package dynamic_program;

import java.util.Arrays;

/**
 * 最低票价问题： https://leetcode.cn/problems/minimum-cost-for-tickets/
 * 
 * 来到任何i位置都有3种方案：
 * 	1天（a元） + f(1...)从下标1开始往后所有旅行至少花多少元
 *  7天（b元） + f(3...)从下标3开始往后所有旅行至少花多少元
 *  30天（c元） + f(4...)从下标4开始往后所有旅行至少花多少元
 */
public class LowestTicketPrice2 {
	
	public static int[] duration = {1, 7, 30};
	
	// 暴力递归： 从下标0开始
	public static int lowestTicket1(int[] days, int[] costs) {
		return f1(days,costs, 0);
	}
	static int f1(int[] days, int[] costs, int i) {
		if(i == days.length) {//无旅行了，花费0
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		// 依次枚举1、7、30天，对应调用不同的后续f1， 从J下标出发搞定后面旅行至少花多少钱
		for(int k = 0, j = i; k < 3; k++) {
			while(j < days.length && days[i] + duration[k] > days[j]) {
				j++;
			}
			ans = Math.min(ans, costs[k] + f1(days,costs,j)); // 决策出3次min
		}
		return ans;
	}
	
	//----------------------------------------------------
	//记忆化搜索 = 从顶到底的dp
	public static int lowestTicket2(int[] days, int[] costs) {
		int n = days.length;
		int[] dp = new int[n];
		for(int i = 0; i < n; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		return f2(days, costs, 0, dp);
	}
	static int f2(int[] days, int[] costs, int i, int[] dp) {
		if(i == days.length) {//无旅行了，花费0
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
			ans = Math.min(ans, costs[k] + f1(days,costs,j)); // 决策出3次min
		}
		dp[i] = ans;
		return ans;
	}
	//-----------------------------------------------------
	 
	// 严格位置依赖 = 简单位置先填复杂的后填 = 从底到顶的dp = 下标从右往左推 = 把dp表从右往左填好即可
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
