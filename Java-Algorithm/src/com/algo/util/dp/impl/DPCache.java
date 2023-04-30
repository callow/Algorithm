package com.algo.util.dp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.CommonStringUtil;
import com.algo.util.dp.DPService;
import com.algo.util.dp.model.Info;

/**
 * ɵ���� = ���仯���� = �Ӷ����µĶ�̬�滮
 */

public class DPCache implements DPService {

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
	public Integer uniquePaths(int m, int n) {
		return null;
	}

	@Override
	public Integer uniqueWays(int N, int start, int aim, int K) {

		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}

		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		// dp���ǻ����
		// dp[cur][rest] == -1 -> process1(cur, rest)֮ǰû�����
		// dp[cur][rest] != -1 -> process1(cur, rest)֮ǰ���������ֵ��dp[cur][rest]
		// N+1 * K+1
		return go(start, K, aim, N, dp);
	}

	// cur ��: 1 ~ N
	// rest ����0 ~ K
	public static int go(int cur, int rest, int aim, int N, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		// ֮ǰû�����
		int ans = 0;
		if (rest == 0) {
			ans = cur == aim ? 1 : 0;
		} else if (cur == 1) {
			ans = go(2, rest - 1, aim, N, dp);
		} else if (cur == N) {
			ans = go(N - 1, rest - 1, aim, N, dp);
		} else {
			ans = go(cur - 1, rest - 1, aim, N, dp) + go(cur + 1, rest - 1, aim, N, dp);
		}
		dp[cur][rest] = ans; // �����ڷ���֮ǰ�ŵ�������
		return ans;

	}

	@Override
	public Integer drawCardGame(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fmap[i][j] = -1;
				gmap[i][j] = -1;
			}
		}
		int first = f2(arr, 0, arr.length - 1, fmap, gmap);
		int second = g2(arr, 0, arr.length - 1, fmap, gmap);
		return Math.max(first, second);
	}

	// arr[L..R]�����ֻ�õ���÷�������
	public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (fmap[L][R] != -1) {
			return fmap[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = arr[L];
		} else {
			int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
			int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
			ans = Math.max(p1, p2);
		}
		fmap[L][R] = ans;
		return ans;
	}

	// // arr[L..R]�����ֻ�õ���÷�������
	public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (gmap[L][R] != -1) {
			return gmap[L][R];
		}
		int ans = 0;
		if (L != R) {
			int p1 = f2(arr, L + 1, R, fmap, gmap); // ����������Lλ�õ���
			int p2 = f2(arr, L, R - 1, fmap, gmap); // ����������Rλ�õ���
			ans = Math.min(p1, p2);
		}
		gmap[L][R] = ans;
		return ans;
	}

	@Override
	public int knapsackMaxValue(int[] w, int[] v, int bag) {
		return 0;
	}

	@Override
	public int convertNumToLetter(String str) {
		return 0;
	}

	@Override
	public int minStickersToSpellWords(String[] stickers, String target) {
		int N = stickers.length;
		// �ؼ��Ż�(�ô�Ƶ2ά����������ֽ����)
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		Map<String, Integer> cache = new HashMap<>();
		cache.put("", 0);
		int ans = process(counts, target, cache);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// stickers[i] ���飬����i����ֽ���ַ�ͳ�� int[][] stickers -> ���е���ֽ
	// ÿһ����ֽ����������
	// ���ظ㶨target����������
	// ��������
	private int process(int[][] stickers, String t, Map<String, Integer> cache) {
		if (cache.containsKey(t)) {
			return cache.get(t);
		}
		// target������Ƶͳ��
		// target aabbc 2 2 1..
		// 0 1 2..
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			// ���Ե�һ����ֽ��˭
			int[] sticker = stickers[i];

			// ��ؼ����Ż�(��Ҫ�ļ�֦!��һ��Ҳ��̰��!)
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process(stickers, rest, cache));
			}
		}
		int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
		cache.put(t, ans);
		return ans;
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
		int n = str.length;
		int[][] dp = new int[n][n]; // L <= R, ��˱������û��.
		dp[n - 1][n - 1] = 1;

		for (int i = 0; i < n - 1; i++) {
			dp[i][i] = 1; // base case 1: l = r => 1 �Խ��� 1
			dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1; // base case 2
		}

		for (int L = n - 3; L >= 0; L--) {
			for (int R = L + 2; R < n; R++) {
				int p1 = dp[L + 1][R - 1];
				int p2 = dp[L][R - 1];
				int p3 = dp[L + 1][R];
				int p4 = str[L] != str[R] ? 0 : (2 + dp[L + 1][R - 1]);
				dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
			}
		}
		return dp[0][n - 1];
	}

	@Override
	public int horseJumpMethods(int a, int b, int k) {
		return 0;
	}

	@Override
	public int minCoffeeTime(int[] arr, int n, int a, int b) {
		return 0;
	}

	/**
	 * dp[][] = �ۼӺ�����
	 */
	@Override
	public int minPathSum(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];

		// �����0�е�ֵ
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}

		// �����0�е�ֵ
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}

		// �����м�� = Min(Up,Left)
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1]; // �������½ǵ�ֵ
	}

	@Override
	public int coinWays(int[] coins, int target) {
		return 0;
	}

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
				int ways = 0;

				// ��дÿһ�����Ӿ�Ȼ��Ҫдһ��forѭ�� = ö�٣� �ӵ�
				for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
					ways += dp[index + 1][rest - (zhang * coins[index])];
				}
				dp[index][rest] = ways;
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
				int ways = 0;
				for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
					ways += dp[index + 1][rest - (zhang * coins[index])];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][target];
	}

	@Override
	public double chessBoardSurvive(int row, int col, int k, int N, int M) {
		return 0;
	}

	@Override
	public double killMonster(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) { // ���ж��ٵ����Կ�
			dp[times][0] = (long) Math.pow(M + 1, times); // �Ѿ� <=0 Ѫ��ʣ�¿������ڱ�ʬ������չ��time��
			for (int hp = 1; hp <= N; hp++) {
				long ways = 0;
				for (int i = 0; i <= M; i++) {
					if (hp - i >= 0) {
						ways += dp[times - 1][hp - i];
					} else {
						ways += (long) Math.pow(M + 1, times - 1); // ����Ѫ��< 0 ���ǻ�ʣtimes-1�Σ�ֱ�ӻ�� M+1^times-1�����
					}
				}
				dp[times][hp] = ways;
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
				int ways = 0;
				for (int first = pre; first <= rest; first++) {
					ways += dp[first][rest - first];
				}
				dp[pre][rest] = ways;
			}
		}
		return dp[1][n];
	}

	@Override
	public int splitArrSum(int[] arr) {
		return 0;
	}

	@Override
	public int splitArrSumSizeHalf(int[] arr) {
		return 0;
	}
}
