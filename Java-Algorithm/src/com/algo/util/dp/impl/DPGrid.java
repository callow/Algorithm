package com.algo.util.dp.impl;

import java.util.List;
import java.util.PriorityQueue;

import com.algo.util.bit.BitUtil;
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

	@Override
	public int splitArrSum(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		sum /= 2;
		int N = arr.length;
		int[][] dp = new int[N + 1][sum + 1];
		for (int i = N - 1; i >= 0; i--) {
			for (int rest = 0; rest <= sum; rest++) {
				// 可能性1，不使用arr[i]
				int p1 = dp[i + 1][rest];
				// 可能性2，要使用arr[i]
				int p2 = 0;
				if (arr[i] <= rest) {
					p2 = arr[i] + dp[i + 1][rest - arr[i]];
				}
				dp[i][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][sum];
	}

	/**
	 * 3个可变参数 是一个3维表
	 */
	@Override
	public int splitArrSumSizeHalf(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		sum /= 2;
		int N = arr.length;
		int M = (N + 1) / 2;
		int[][][] dp = new int[N + 1][M + 1][sum + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				for (int k = 0; k <= sum; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		for (int rest = 0; rest <= sum; rest++) {
			dp[N][0][rest] = 0;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int picks = 0; picks <= M; picks++) {
				for (int rest = 0; rest <= sum; rest++) {
					int p1 = dp[i + 1][picks][rest];
					// 就是要使用arr[i]这个数
					int p2 = -1;
					int next = -1;
					if (picks - 1 >= 0 && arr[i] <= rest) {
						next = dp[i + 1][picks - 1][rest - arr[i]];
					}
					if (next != -1) {
						p2 = arr[i] + next;
					}
					dp[i][picks][rest] = Math.max(p1, p2);
				}
			}
		}
		if ((arr.length & 1) == 0) {
			return dp[0][arr.length / 2][sum];
		} else {
			return Math.max(dp[0][arr.length / 2][sum], dp[0][(arr.length / 2) + 1][sum]);
		}
	}

	@Override
	public int nQueens(int n) {
		return 0;
	}

	/**
	 * DPRecursive和DPCache是2种递归的方式：<br>
	 * -DPRecursive : dp[i][j], i 是当前怪兽index，j已经形成的能力,如果想通过以后所有的怪兽最少花多少钱<br>
	 * -DPCache : dp[i][j], i 是当前怪兽index，严格花j元所能形成的最大能力<br>
	 * 
	 * 根据能力和钱，谁的极限(规模)小选谁. <br>
	 * - 如果贿赂怪兽的总钱额度很大，不可以用DPRecursive只能用DPCache，因为复杂度超过了10^8<br>
	 * - 如果贿赂怪兽总钱额度不大 比如1-10以内，那么就可以用DPRecursive
	 * 
	 * 技巧：根据数据量猜解法
	 */
	@Override
	public long bribeMonster(int[] d, int[] p) {
		int sum = 0;
		for (int num : d) {
			sum += num;
		}
		long[][] dp = new long[d.length + 1][sum + 1];
		for (int cur = d.length - 1; cur >= 0; cur--) {
			for (int hp = 0; hp <= sum; hp++) {
				// 如果这种情况发生，那么这个hp必然是递归过程中不会出现的状态
				// 既然动态规划是尝试过程的优化，尝试过程碰不到的状态，不必计算
				if (hp + d[cur] > sum) {
					continue;
				}
				if (hp < d[cur]) {
					dp[cur][hp] = p[cur] + dp[cur + 1][hp + d[cur]];
				} else {
					dp[cur][hp] = Math.min(p[cur] + dp[cur + 1][hp + d[cur]], dp[cur + 1][hp]);
				}
			}
		}
		return dp[0][0];
	}

	/**
	 * 此题是经典背包动态规划，有如下优化点：
	 * 
	 * 优化点1： 你可以认为arr中都是非负数,因为即便是arr中有负数，比如[3,-4,2]
	 * 	因为你能在每个数前面用+或者-号,所以[3,-4,2]其实和[3,4,2]达成一样的效果,那么我们就全把arr变成非负数，不会影响结果的.
	 * 
	 * 优化点2：如果arr都是非负数，并且所有数的累加和是sum,那么如果target<sum，很明显没有任何方法可以达到target，可以直接返回0
	 * 
	 * 优化点3：arr内部的数组，不管怎么+和-，最终的结果都一定不会改变奇偶性,所以，如果所有数的累加和是sum，
	 * 	并且与target的奇偶性不一样，没有任何方法可以达到target，可以直接返回0
	 * 
	 * 优化点4：偏理论
	 * 	// 比如说给定一个数组, arr = [1, 2, 3, 4, 5] 并且 target = 3
		 其中一个方案是 : +1 -2 +3 -4 +5 = 3
		 该方案中取了正的集合为P = {1，3，5}
		 该方案中取了负的集合为N = {2，4}
		 所以任何一种方案，都一定有 sum(P) - sum(N) = target
		 现在我们来处理一下这个等式，把左右两边都加上sum(P) + sum(N)，那么就会变成如下：
		 sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
		 2 * sum(P) = target + 数组所有数的累加和
		 sum(P) = (target + 数组所有数的累加和) / 2
		 也就是说，任何一个集合，只要累加和是(target + 数组所有数的累加和) / 2
		 那么就一定对应一种target的方式
		 也就是说，比如非负数组arr，target = 7, 而所有数累加和是11
		 求有多少方法组成7，其实就是求有多少种达到累加和(7+11)/2=9的方法
	 * 
	 * 优化点5：二维动态规划空间压缩技巧，一个一维数组滚过去
	 * 
	 */
	@Override
	public int assembleTargetSum(int[] arr, int target) {
		int sum = 0;
		// 此题已知全是正整数，因此可以直接加出sum，否则要负变正- 优化点1
		for (int n : arr) {
			sum += n; 
		}
		
		return sum < target // 优化点2
				||  BitUtil.isSameParity(target, sum) ? 0 : // 优化点3
				assemble(arr, (target + sum) >> 1); // 优化点4，5
	}
	
	/**
	 * 求非负数组nums有多少个子集，累加和是s => 背包问题 
	 * 		=> dp[i][j] // 0~i上自由选择，搞出累加和j的方法数
	 * 二维动态规划用空间压缩:
	 * 	核心就是for循环里面的：for (int i = s; i >= n; i--) {
		 为啥不枚举所有可能的累加和？只枚举 n...s 这些累加和？
		 因为如果 i - n < 0，dp[i]怎么更新？和上一步的dp[i]一样！所以不用更新
		 如果 i - n >= 0，dp[i]怎么更新？上一步的dp[i] + 上一步dp[i - n]的值，这才需要更新
	 * 
	 */
	private  int assemble(int[] nums, int s) {
		if (s < 0) {
			return 0;
		}
		int[] dp = new int[s + 1];
		dp[0] = 1;
		for (int n : nums) {
			for (int i = s; i >= n; i--) {
				dp[i] += dp[i - n];
			}
		}
		return dp[s];
	}

	@Override
	public int longestIncreasingPath(int[][] matrix) {
		// TODO Auto-generated method stub
		return 0;
	}

}
