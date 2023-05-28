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
 * �ϸ��ṹ��dp[][] ���� ɵ�����ȫ����С
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
			dp[1][rest] = dp[2][rest - 1]; // ���һ��
			for (int cur = 2; cur < N; cur++) {
				dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1]; // ���м���
			}
			dp[N][rest] = dp[N - 1][rest - 1]; // �����һ��
		}
		return dp[start][K];
	}

	// �����ݹ��д
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
	 * �ݹ��У�i λ������i+1��i+2λ�ã���˴���������dp��
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
				// int p1 = dp[L + 1][R - 1]; ��Ϊ p2 > p1, p3 > p1 ��� p1�Ͳ���Ҫ��max��
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
		if (x < 0 || x > 9 || y < 0 || y > 8) { // Խ��ͷ���0�������ȥ��
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
					break; // ��Ϊ�����Ҳ����������
				}
				// index�ű��� ����ϴ
				int restClean1 = dp[index + 1][selfClean1];
				int p1 = Math.max(selfClean1, restClean1);
				// index�ű��� �����ӷ�
				int selfClean2 = drinks[index] + air;
				int restClean2 = dp[index + 1][free];
				int p2 = Math.max(selfClean2, restClean2);
				dp[index][free] = Math.min(p1, p2);
			}
		}
		return dp[0][0];
	}

	/**
	 * ����һ����ά���� dp[][] �е��˷ѣ���ʵ������һά����dp[] = ����ѹ�� <br>
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

		// �����0�е�ֵ
		for (int j = 1; j < col; j++) {
			dp[j] = dp[j - 1] + m[0][j];
		}

		// ����dp[] ���Ҹ��£�
		for (int i = 1; i < row; i++) {
			dp[0] += m[i][0]; // dp[0] -> ��dp[��һ��][0] -> ����ȥ dp[��һ��][0]

			for (int j = 1; j < col; j++) {
				int left = dp[j - 1]; // dp[��һ��][j-1] ����ֵ
				int up = dp[j]; // dp[��һ��][j] �ϲ��ֵ

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

		// index ������index+1λ�ã�����Ǵ�������
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
	 * [^] = [d] + [c] + [b] + [a] �Ż��� [^] = [*] + [a] ������Ҫ������
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
				dp[index][rest] = dp[index + 1][rest]; // ���·�����
				if (rest - coins[index] >= 0) {
					dp[index][rest] += dp[index][rest - coins[index]]; // ���Լ������
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
	 * ��Χ�Ż��۲���ӹ������գ�<br>
	 * �� dp[5][10] = dp[4][10~3] <br>
	 * �� dp[4][11~4] = dp[5][10] + dp[4][11] - dp[4][3] <br>
	 * �� dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1-M]
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
				// ������1����ʹ��arr[i]
				int p1 = dp[i + 1][rest];
				// ������2��Ҫʹ��arr[i]
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
	 * 3���ɱ���� ��һ��3ά��
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
					// ����Ҫʹ��arr[i]�����
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
	 * DPRecursive��DPCache��2�ֵݹ�ķ�ʽ��<br>
	 * -DPRecursive : dp[i][j], i �ǵ�ǰ����index��j�Ѿ��γɵ�����,�����ͨ���Ժ����еĹ������ٻ�����Ǯ<br>
	 * -DPCache : dp[i][j], i �ǵ�ǰ����index���ϸ�jԪ�����γɵ��������<br>
	 * 
	 * ����������Ǯ��˭�ļ���(��ģ)Сѡ˭. <br>
	 * - �����¸���޵���Ǯ��Ⱥܴ󣬲�������DPRecursiveֻ����DPCache����Ϊ���Ӷȳ�����10^8<br>
	 * - �����¸������Ǯ��Ȳ��� ����1-10���ڣ���ô�Ϳ�����DPRecursive
	 * 
	 * ���ɣ������������½ⷨ
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
				// ������������������ô���hp��Ȼ�ǵݹ�����в�����ֵ�״̬
				// ��Ȼ��̬�滮�ǳ��Թ��̵��Ż������Թ�����������״̬�����ؼ���
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
	 * �����Ǿ��䱳����̬�滮���������Ż��㣺
	 * 
	 * �Ż���1�� �������Ϊarr�ж��ǷǸ���,��Ϊ������arr���и���������[3,-4,2]
	 * 	��Ϊ������ÿ����ǰ����+����-��,����[3,-4,2]��ʵ��[3,4,2]���һ����Ч��,��ô���Ǿ�ȫ��arr��ɷǸ���������Ӱ������.
	 * 
	 * �Ż���2�����arr���ǷǸ������������������ۼӺ���sum,��ô���target<sum��������û���κη������Դﵽtarget������ֱ�ӷ���0
	 * 
	 * �Ż���3��arr�ڲ������飬������ô+��-�����յĽ����һ������ı���ż��,���ԣ�������������ۼӺ���sum��
	 * 	������target����ż�Բ�һ����û���κη������Դﵽtarget������ֱ�ӷ���0
	 * 
	 * �Ż���4��ƫ����
	 * 	// ����˵����һ������, arr = [1, 2, 3, 4, 5] ���� target = 3
		 ����һ�������� : +1 -2 +3 -4 +5 = 3
		 �÷�����ȡ�����ļ���ΪP = {1��3��5}
		 �÷�����ȡ�˸��ļ���ΪN = {2��4}
		 �����κ�һ�ַ�������һ���� sum(P) - sum(N) = target
		 ��������������һ�������ʽ�����������߶�����sum(P) + sum(N)����ô�ͻ������£�
		 sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
		 2 * sum(P) = target + �������������ۼӺ�
		 sum(P) = (target + �������������ۼӺ�) / 2
		 Ҳ����˵���κ�һ�����ϣ�ֻҪ�ۼӺ���(target + �������������ۼӺ�) / 2
		 ��ô��һ����Ӧһ��target�ķ�ʽ
		 Ҳ����˵������Ǹ�����arr��target = 7, ���������ۼӺ���11
		 ���ж��ٷ������7����ʵ�������ж����ִﵽ�ۼӺ�(7+11)/2=9�ķ���
	 * 
	 * �Ż���5����ά��̬�滮�ռ�ѹ�����ɣ�һ��һά�������ȥ
	 * 
	 */
	@Override
	public int assembleTargetSum(int[] arr, int target) {
		int sum = 0;
		// ������֪ȫ������������˿���ֱ�Ӽӳ�sum������Ҫ������- �Ż���1
		for (int n : arr) {
			sum += n; 
		}
		
		return sum < target // �Ż���2
				||  BitUtil.isSameParity(target, sum) ? 0 : // �Ż���3
				assemble(arr, (target + sum) >> 1); // �Ż���4��5
	}
	
	/**
	 * ��Ǹ�����nums�ж��ٸ��Ӽ����ۼӺ���s => �������� 
	 * 		=> dp[i][j] // 0~i������ѡ�񣬸���ۼӺ�j�ķ�����
	 * ��ά��̬�滮�ÿռ�ѹ��:
	 * 	���ľ���forѭ������ģ�for (int i = s; i >= n; i--) {
		 Ϊɶ��ö�����п��ܵ��ۼӺͣ�ֻö�� n...s ��Щ�ۼӺͣ�
		 ��Ϊ��� i - n < 0��dp[i]��ô���£�����һ����dp[i]һ�������Բ��ø���
		 ��� i - n >= 0��dp[i]��ô���£���һ����dp[i] + ��һ��dp[i - n]��ֵ�������Ҫ����
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
