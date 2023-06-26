package com.algo.tasks.day5;
/**
 * 
 * 编辑距离问题：从str1 编辑成 str2 的最小代价
 *
 */
public class EditDistanceCost {
	
	/**
	 * 无空间压缩，经典版
	 * @param ic - 插入代价
	 * @param dc - 删除代价
	 * @param rc - 替换代价
	 */
	public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length + 1;
		int M = str2.length + 1;
		int[][] dp = new int[N][M];
		// dp[0][0] = 0
		for (int i = 1; i < N; i++) { // 填充第0列
			dp[i][0] = dc * i;
		}
		for (int j = 1; j < M; j++) { // 填充第0行
			dp[0][j] = ic * j;
		}
		
		// 填充普遍位置依赖
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				dp[i][j] = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : rc); // p3 ,p4
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic); // p2
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc); // p1
			}
		}
		return dp[N - 1][M - 1];
	}
	
	/**
	 * 做了空间压缩: 因为dp表只依赖上一行的数据，因此可以改成1维表
	 */
	
	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) {
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = ic * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int pre = dp[0];
			dp[0] = dc * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j];
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = pre;
				} else {
					dp[j] = pre + rc;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				dp[j] = Math.min(dp[j], tmp + dc);
				pre = tmp;
			}
		}
		return dp[shorts.length];
	}
}
