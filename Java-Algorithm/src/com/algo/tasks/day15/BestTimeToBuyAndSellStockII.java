package com.algo.tasks.day15;
/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
 * 
 * һ����������һ�ɣ�������һ�������������й�Ʊ�ſ���
 *
 */
public class BestTimeToBuyAndSellStockII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
		}
		return ans;
	}
}
