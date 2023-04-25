package com.algo.util.dp.impl;

import java.util.List;
import java.util.PriorityQueue;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.CommonStringUtil;
import com.algo.util.dp.DPService;
import com.algo.util.dp.model.CoffeeMachine;
import com.algo.util.dp.model.CoffeeMachineComparator;
import com.algo.util.dp.model.Info;

/**
 * 
 * 严格表结构，dp[][] 就是 傻缓存的全部大小
 */
public class DPGrid implements DPService {

	@Override
	public void hanoi(int n) {
	}

	@Override
	public List<String> subsequence(String n) {
		return null;
	}

	@Override
	public List<String> permutation(String n) {
		return null;
	}

	@Override
	public List<String> getAllBrackets(int n) {
		return null;
	}

	@Override
	public Integer uniquePaths(int row, int col) {
		int[] dp = new int[row];

		for (int i = 0; i < row; i++) { // fill 1st col = 1
			dp[i] = 1;
		}

		for (int j = 1; j < col; j++) {
			for (int i = 1; i < row; i++) {
				dp[i] = dp[i] + dp[i - 1];
			}
		}

		return dp[row - 1]; // row of paths
	}

	// (cur,rest)
	@Override
	public Integer uniqueWays(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		int[][] dp = new int[N + 1][K + 1];
		dp[aim][0] = 1;
		for (int rest = 1; rest <= K; rest++) {
			dp[1][rest] = dp[2][rest - 1]; // 填第一行
			for (int cur = 2; cur < N; cur++) {
				dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1]; // 填中间行
			}
			dp[N][rest] = dp[N - 1][rest - 1]; // 填最后一行
		}
		return dp[start][K];
	}

	// 暴力递归改写
	@Override
	public Integer drawCardGame(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			fmap[i][i] = arr[i];
		}
		for (int startCol = 1; startCol < N; startCol++) {
			int L = 0;
			int R = startCol;
			while (R < N) {
				fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
				gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
				L++;
				R++;
			}
		}
		return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
	}

	// index > 0 ~ N
	// rest > -1 ~ bag
	@Override
	public int knapsackMaxValue(int[] w, int[] v, int bag) {
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int index = N - 1; index >= 0; index--) { // row from bottom up
			for (int rest = 0; rest <= bag; rest++) {
				int p1 = dp[index + 1][rest];
				int p2 = 0;
				int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
				if (next != -1) {
					p2 = v[index] + next;
				}
				dp[index][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][bag];
	}

	/**
	 * 递归中，i 位置依赖i+1和i+2位置，因此从右往左填dp表
	 */

	@Override
	public int convertNumToLetter(String s) {
		if (CommonStringUtil.isEmpty(s)) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] != '0') {
				int ways = dp[i + 1];
				if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
					ways += dp[i + 2];
				}
				dp[i] = ways;
			}
		}
		return dp[0];
	}

	@Override
	public int minStickersToSpellWords(String[] stickers, String target) {
		return 0;
	}

	@Override
	public int longestCommonSubsequence(String str1, String str2) {
		return 0;
	}

	@Override
	public int longestPalindromeSubsequence(String s) {
		if (CommonStringUtil.isEmpty(s)) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];
		dp[N - 1][N - 1] = 1;
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
		}
		for (int L = N - 3; L >= 0; L--) {
			for (int R = L + 2; R < N; R++) {
				// int p1 = dp[L + 1][R - 1]; 因为 p2 > p1, p3 > p1 因此 p1就不需要比max了
				int p2 = dp[L][R - 1];
				int p3 = dp[L + 1][R];
				dp[L][R] = Math.max(p2, p3);
				if (str[L] == str[R]) {
					dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]); // P4
				}
			}
		}
		return dp[0][N - 1];
	}

	@Override
	public int horseJumpMethods(int a, int b, int k) {

		int[][][] dp = new int[10][9][k + 1];
		dp[a][b][0] = 1;
		for (int rest = 1; rest <= k; rest++) {
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 9; y++) {
					int ways = pick(dp, x + 2, y + 1, rest - 1);
					ways += pick(dp, x + 1, y + 2, rest - 1);
					ways += pick(dp, x - 1, y + 2, rest - 1);
					ways += pick(dp, x - 2, y + 1, rest - 1);
					ways += pick(dp, x - 2, y - 1, rest - 1);
					ways += pick(dp, x - 1, y - 2, rest - 1);
					ways += pick(dp, x + 1, y - 2, rest - 1);
					ways += pick(dp, x + 2, y - 1, rest - 1);
					dp[x][y][rest] = ways;
				}
			}
		}
		return dp[0][0][k];
	}

	public int pick(int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x > 9 || y < 0 || y > 8) { // 越界就返回0，否则就去拿
			return 0;
		}
		return dp[x][y][rest];
	}

	@Override
	public int minCoffeeTime(int[] arr, int n, int a, int b) {
		PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new CoffeeMachineComparator());
		for (int i = 0; i < arr.length; i++) {
			heap.add(new CoffeeMachine(0, arr[i]));
		}
		int[] drinks = new int[n];
		for (int i = 0; i < n; i++) {
			CoffeeMachine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur);
		}
		return bestTime(drinks, a, b);
	}

	private int bestTime(int[] drinks, int wash, int air) {
		int N = drinks.length;
		int maxFree = 0;
		for (int i = 0; i < drinks.length; i++) {
			maxFree = Math.max(maxFree, drinks[i]) + wash;
		}
		int[][] dp = new int[N + 1][maxFree + 1];
		for (int index = N - 1; index >= 0; index--) {
			for (int free = 0; free <= maxFree; free++) {
				int selfClean1 = Math.max(drinks[index], free) + wash;
				if (selfClean1 > maxFree) {
					break; // 因为后面的也都不用填了
				}
				// index号杯子 决定洗
				int restClean1 = dp[index + 1][selfClean1];
				int p1 = Math.max(selfClean1, restClean1);
				// index号杯子 决定挥发
				int selfClean2 = drinks[index] + air;
				int restClean2 = dp[index + 1][free];
				int p2 = Math.max(selfClean2, restClean2);
				dp[index][free] = Math.min(p1, p2);
			}
		}
		return dp[0][0];
	}

	/**
	 * 申请一个二维数组 dp[][] 有点浪费，其实可以用一维数组dp[] = 数组压缩 <br>
	 * 
	 */
	@Override
	public int minPathSum(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];

		// 先填第0行的值
		for (int j = 1; j < col; j++) {
			dp[j] = dp[j - 1] + m[0][j];
		}

		// 滚动dp[] 自我更新：
		for (int i = 1; i < row; i++) {
			dp[0] += m[i][0]; // dp[0] -> 从dp[上一行][0] -> 更新去 dp[这一行][0]

			for (int j = 1; j < col; j++) {
				int left = dp[j - 1]; // dp[这一行][j-1] 左侧的值
				int up = dp[j]; // dp[上一行][j] 上侧的值

				dp[j] = Math.min(left, up) + m[i][j];
			}
		}
		return dp[col - 1];
	}

	@Override
	public int coinWays(int[] coins, int target) {
		if (target == 0) {
			return 1;
		}
		int N = coins.length;
		int[][] dp = new int[N + 1][target + 1];
		dp[N][0] = 1;

		// index 都依赖index+1位置，因此是从下往上
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= target; rest++) {

				// process(coins, index + 1, rest);
				int withoutIndex = dp[index + 1][rest];

				// process(coins, index + 1, rest - coins[index]);
				int withIndex = (rest - coins[index] >= 0 ? dp[index + 1][rest - coins[index]] : 0);

				dp[index][rest] = withoutIndex + withIndex;
			}
		}
		return dp[0][target];
	}

	/**
	 * ________[*] [^] <br>
	 * [d] [c] [b] [a] <br>
	 * 
	 * [^] = [d] + [c] + [b] + [a] 优化成 [^] = [*] + [a] ，不需要遍历了
	 */
	@Override
	public int coinWaysNoLimit(int[] coins, int target) {
		if (CommonArrayUtil.isEmpty(coins)) {
			return 0;
		}
		int N = coins.length;
		int[][] dp = new int[N + 1][target + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= target; rest++) {
				dp[index][rest] = dp[index + 1][rest]; // 我下方格子
				if (rest - coins[index] >= 0) {
					dp[index][rest] += dp[index][rest - coins[index]]; // 我自己行左侧
				}
			}
		}
		return dp[0][target];
	}

	@Override
	public int coinWaysSameValue(int[] arr, int target) {
		if (CommonArrayUtil.isEmpty(arr) || target < 0) {
			return 0;
		}
		Info info = Info.getInfo(arr);
		int[] coins = info.coins;
		int[] zhangs = info.zhangs;
		int N = coins.length;
		int[][] dp = new int[N + 1][target + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= target; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - coins[index] >= 0) {
					dp[index][rest] += dp[index][rest - coins[index]];
				}
				if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
					dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
				}
			}
		}
		return dp[0][target];
	}

	@Override
	public double chessBoardSurvive(int row, int col, int k, int N, int M) {
		long[][][] dp = new long[N][M][k + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dp[i][j][0] = 1;
			}
		}
		for (int rest = 1; rest <= k; rest++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
					dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
					dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
					dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
				}
			}
		}
		return dp[row][col][k] / Math.pow(4, k);
	}

	public long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
		if (r < 0 || r == N || c < 0 || c == M) {
			return 0;
		}
		return dp[r][c][rest];
	}

	/**
	 * 范围优化观察格子规律来凑：<br>
	 * ∵ dp[5][10] = dp[4][10~3] <br>
	 * ∴ dp[4][11~4] = dp[5][10] + dp[4][11] - dp[4][3] <br>
	 * ∴ dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1-M]
	 */
	@Override
	public double killMonster(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
				if (hp - 1 - M >= 0) {
					dp[times][hp] -= dp[times - 1][hp - 1 - M];
				} else {
					dp[times][hp] -= Math.pow(M + 1, times - 1);
				}
			}
		}
		long kill = dp[K][N];
		return (double) kill / (double) all;
	}

	@Override
	public int splitNumberWays(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre <= n; pre++) {
			dp[pre][0] = 1;
			dp[pre][pre] = 1;
		}
		for (int pre = n - 1; pre >= 1; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				dp[pre][rest] = dp[pre + 1][rest];
				dp[pre][rest] += dp[pre][rest - pre];
			}
		}
		return dp[1][n];
	}

}
