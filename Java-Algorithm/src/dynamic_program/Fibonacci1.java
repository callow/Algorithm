package dynamic_program;
/**
 * F（i） = F(i -1) + F(i-2)
 * 0 1 1 2 3 5 8
 * 
 * 求斐波那契数列第n项？
 * 
 * 递归含义： i位置的数字
 */
public class Fibonacci1 {
	
	// O(2^n) 无记忆化搜索
	public static int f(int i) {
		if (i == 0) {
			return 0;
		}
		if(i == 1) {
			return 1;
		}
		return f(i - 1) + f(i - 2);
	}
	
	// O(n) 记忆化搜索 = 从顶到底的、从后往前的
	public static int f2(int i, int[] dp) {
		if (i == 0) {
			return 0;
		}
		if(i == 1) {
			return 1;
		}
		
		if(dp[i] != -1) {
			return dp[i];
		}
		int ans =  f2(i - 1, dp) + f2(i - 2, dp);
		dp[i] = ans;
		return ans;
	}
	
	// O(n) 严格位置依赖的动态规划 = 简单位置先算，复杂后算 = 从底到顶的 = 从前往后的
	public static int f3(int n) {
		if (n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[1] = 1; // 填dp完
		for(int i  = 2; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		return dp[n];
	}
	
	// O(n) 滚动更新 空间优化
	public static int f4(int n) {
		if (n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		int lastlast = 0, last = 1;
		for(int i = 2, cur; i <= n; i++) {
			cur = lastlast + last;
			lastlast = last;
			last = cur;
		}
		
		return last;
	}
}
