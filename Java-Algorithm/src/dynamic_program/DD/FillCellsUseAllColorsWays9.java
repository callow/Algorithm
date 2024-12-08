package dynamic_program.DD;

import java.util.Arrays;

/**
 * 有效涂色问题： 给定n、m两个参数，一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法 求一共有多少种有效的涂色方法
 * 
 * 结果比较大请 % 1000000007 之后返回
 */
public class FillCellsUseAllColorsWays9 {

	/**
	 * dp[i][j] = i个格子凑j种颜色的方法数，(6个格子凑齐4种颜色)
	 * 
	 * dp[i][j]可能性：
	 *  1. 无需添加新颜色：
	 *      求dp[5][3]，用了3个颜色了,要填满5格 看看用了3个颜色填满4格时候的值*3，因为新添的颜色给新格子可以是3个颜色任意一种 = dp[4][3] * 3
	 *  	dp[i-1][j] * j
	 * 
	 *  2. 添加新颜色：
	 *      还缺一种颜色没用，那么我现在要填的格子就要用了
	 * 		dp[i-1][j-1] * (m-(j-1))
	 */
	// 暴力递归(带路径的)，为了验证的对数器，路径是path
	public static int ways1(int n, int m) {
		return f(new int[n], new boolean[m + 1], 0, n, m);
	}
	
	public static int f(int[] path, boolean[] set, int i, int n, int m) {
		if (i == n) {
			Arrays.fill(set, false);
			int colors = 0;
			for (int c : path) {
				if (!set[c]) {
					set[c] = true;
					colors++;
				}
			}
			return colors == m ? 1 : 0;
		} else {
			int ans = 0;
			for (int j = 1; j <= m; j++) {
				path[i] = j;
				ans += f(path, set, i + 1, n, m);
			}
			return ans;
		}
	}
	
	public static int MAXN = 5001;
	public static int[][] dp = new int[MAXN][MAXN];
	public static int mod = 1000000007;
	public static int ways2(int n, int m) {
		// dp[i][j]:
		// 一共有m种颜色
		// 前i个格子涂满j种颜色的方法数
		for (int i = 1; i <= n; i++) {
			dp[i][1] = m;
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= m; j++) {
				dp[i][j] = (int) (((long) dp[i - 1][j] * j) % mod);
				dp[i][j] = (int) ((((long) dp[i - 1][j - 1] * (m - j + 1)) + dp[i][j]) % mod);
			}
		}
		return dp[n][m];
	}
	
	public static void main(String[] args) {
		// 测试的数据量比较小
		// 那是因为数据量大了，暴力方法过不了
		// 但是这个数据量足够说明正式方法是正确的
		int N = 9;
		int M = 9;
		System.out.println("功能测试开始");
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				int ans1 = ways1(n, m);
				int ans2 = ways2(n, m);
				if (ans1 != ans2) {
					System.out.println("出错了!");
				}
			}
		}
		System.out.println("功能测试结束");

		System.out.println("性能测试开始");
		int n = 5000;
		int m = 4877;
		System.out.println("n : " + n);
		System.out.println("m : " + m);
		long start = System.currentTimeMillis();
		int ans = ways2(n, m);
		long end = System.currentTimeMillis();
		System.out.println("取模之后的结果 : " + ans);
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}
	
	
}
