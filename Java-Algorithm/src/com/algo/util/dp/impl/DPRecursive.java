package com.algo.util.dp.impl;

import java.util.ArrayList;
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
 * 递归 = 抽象化
 */
public class DPRecursive implements DPService {

	@Override
	public void hanoi(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}

	/**
	 * 总目标： 1 ~ N 在 from 去 to , 另一个 other 1 2 N --- --- --- from to other
	 */
	private void func(int N, String from, String to, String other) {
		if (N == 1) { // base case
			System.out.println("Move 1 from " + from + " to " + to);
		} else {
			func(N - 1, from, other, to); // 1 ~ (n - 1)层圆盘从from 去 other, to 是另外一个
			System.out.println("Move " + N + " from " + from + " to " + to); // 单独的n 从 from 移动到 to 打印
			func(N - 1, other, to, from); // 1 ~ (n - 1)层圆盘从other 挪回 to 完事
		}
	}

	@Override
	public List<String> subsequence(String s) {
		char[] str = s.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process(str, 0, ans, path);
		return ans;
	}

	// str 固定参数
	// 来到了str[index]字符，index是位置
	// str[0..index-1]已经走过了！之前的决定，都在path上
	// 之前的决定已经不能改变了，就是path
	// str[index....]还能决定，之前已经确定，而后面还能自由选择的话，把所有生成的子序列，放入到ans里去
	public static void process(char[] str, int index, List<String> ans, String path) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		// 没有要index位置的字符 = path没变
		process(str, index + 1, ans, path);
		// 要了index位置的字符 = path 加了此时的字符
		process(str, index + 1, ans, path + String.valueOf(str[index]));
	}

	@Override
	public List<String> permutation(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g(str, 0, ans);
		return ans;
	}

	private static void g(char[] str, int index, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
			boolean[] visited = new boolean[256]; // assci 码最多255个
			for (int i = index; i < str.length; i++) { // i跟index位置的字符要交换
				if (!visited[str[i]]) { // 去重，注释掉就是不去重
					visited[str[i]] = true;
					CommonArrayUtil.swap(str, index, i);
					g(str, index + 1, ans); // 去跑所有支路
					CommonArrayUtil.swap(str, index, i); // 跑下一个前 恢复现场
				}
			}
		}
	}

	@Override
	public List<String> getAllBrackets(int n) {
		List<String> result = new ArrayList<>();
		generate(result, "", n, n);
		return result;
	}

	/**
	 * 
	 * @param curr:  当前组合结果
	 * @param left   : 左括号还剩几个
	 * @param right： 右括号还剩几个
	 */
	public static void generate(List<String> result, String curr, int left, int right) {
		if (left == 0 && right == 0) { // 结束
			result.add(curr);
			return;
		}

		if (left > 0) { // 加左括号
			generate(result, curr + "(", left - 1, right);
		}

		if (right > left) { // 加右括号
			generate(result, curr + ")", left, right - 1);
		}
	}

	@Override
	public Integer uniquePaths(int row, int col) {
		if (row == 1 || col == 1) {
			return 1;
		}
		return uniquePaths(row - 1, col) + uniquePaths(row, col - 1); // move down + move right
	}

	@Override
	public Integer uniqueWays(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		return go(start, K, aim, N);
	}

	// (cur,rest)
	private static int go(int cur, int rest, int aim, int N) {
		if (rest == 0) { // 如果已经不需要走了，走完了！
			return cur == aim ? 1 : 0;
		}
		if (cur == 1) { // 1 -> 2 , 最左侧
			return go(2, rest - 1, aim, N);
		}
		if (cur == N) { // N-1 <- N， 最右侧
			return go(N - 1, rest - 1, aim, N);
		}
		return go(cur - 1, rest - 1, aim, N) + go(cur + 1, rest - 1, aim, N); // 中间，既可以往左 又可以往右
	}

	@Override
	public Integer drawCardGame(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int first = f1(arr, 0, arr.length - 1);
		int second = g1(arr, 0, arr.length - 1);
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f1(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int p1 = arr[L] + g1(arr, L + 1, R);
		int p2 = arr[R] + g1(arr, L, R - 1);
		return Math.max(p1, p2);
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g1(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
		int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
		return Math.min(p1, p2);
	}

	@Override
	public int knapsackMaxValue(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length == 0 || v.length != w.length) {
			return 0;
		}

		return process(w, v, 0, bag); // 尝试函数
	}

	// 从index往后自由选择， 返回最大的价值
	private int process(int[] w, int[] v, int index, int bag) {
		if (bag < 0) {
			return -1; // 无效的货物选择
		}
		if (index == w.length) {
			return 0;
		}
		int no = process(w, v, index + 1, bag); // 不要当前货

		int yes = 0;// 要当前货
		int check = process(w, v, index + 1, bag - w[index]); // 检查背包是否爆炸
		if (check != -1) {
			yes = v[index] + check;
		}

		return Math.max(no, yes);

	}

	@Override
	public int convertNumToLetter(String str) {
		if (CommonStringUtil.isEmpty(str)) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// str[0..i-1]转化无需过问, str[i.....]去转化，返回有多少种转化方法，一个可变参数，一维表
	private int process(char[] str, int i) {
		if (i == str.length) {
			return 1;
		}
		// 之前的决定有问题
		if (str[i] == '0') {

		}

		// 可能性1：i单转， 去i+1继续
		int ways = process(str, i + 1);

		// 可能性2：i 与 i+1 共同转，去i+2继续
		if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			ways += process(str, i + 2);
		}

		return ways;
	}

	@Override
	public int minStickersToSpellWords(String[] stickers, String target) {
		int ans = process(stickers, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	private int process(String[] stickers, String target) {
		if (CommonStringUtil.isEmpty(target)) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (String first : stickers) {
			String rest = minus(target, first); // 使用一张贴纸 剪完后剩下的target
			if (rest.length() != target.length()) { // 如果剪完没有任何变化，= 使用当前贴纸没有减少任何字符，继续
				min = process(stickers, rest);
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1); // + 1 加上first
	}

	private String minus(String target, String sticker) {
		char[] s1 = target.toCharArray();
		char[] s2 = sticker.toCharArray();
		int[] count = new int[26];
		for (char cha : s1) {
			count[cha - 'a']++; // 统计String中每种字符出现的频率
		}
		for (char cha : s2) {
			count[cha - 'a']--; // s2中再去减
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				for (int j = 0; j < count[i]; j++) {
					builder.append((char) (i + 'a'));
				}
			}
		}
		return builder.toString();
	}

	@Override
	public int longestCommonSubsequence(String s1, String s2) {
		if (CommonStringUtil.isEmpty(s1) || CommonStringUtil.isEmpty(s2)) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		// 尝试
		return process(str1, str2, str1.length - 1, str2.length - 1);
	}

	// str1[0...i]和str2[0...j]，这个范围上最长公共子序列长度是多少？
	// 可能性分类:
	// a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
	// b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
	// c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
	// d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
	// 注意：a)、b)、c)、d)并不是完全互斥的，他们可能会有重叠的情况
	// 但是可以肯定，答案不会超过这四种可能性的范围
	// 那么我们分别来看一下，这几种可能性怎么调用后续的递归。
	// a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
	// 如果是这种情况，那么有没有str1[i]和str2[j]就根本不重要了，因为这两个字符一定没用啊
	// 所以砍掉这两个字符，最长公共子序列 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归)
	// b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
	// 如果是这种情况，那么我们可以确定str2[j]一定没有用，要砍掉；但是str1[i]可能有用，所以要保留
	// 所以，最长公共子序列 = str1[0...i]与str2[0...j-1]的最长公共子序列长度(后续递归)
	// c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
	// 跟上面分析过程类似，最长公共子序列 = str1[0...i-1]与str2[0...j]的最长公共子序列长度(后续递归)
	// d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
	// 同时可以看到，可能性d)存在的条件，一定是在str1[i] == str2[j]的情况下，才成立的
	// 所以，最长公共子序列总长度 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归) + 1(共同的结尾)
	// 综上，四种情况已经穷尽了所有可能性。四种情况中取最大即可
	// 其中b)、c)一定参与最大值的比较，
	// 当str1[i] == str2[j]时，a)一定比d)小，所以d)参与
	// 当str1[i] != str2[j]时，d)压根不存在，所以a)参与
	// 但是再次注意了！
	// a)是：str1[0...i-1]与str2[0...j-1]的最长公共子序列长度
	// b)是：str1[0...i]与str2[0...j-1]的最长公共子序列长度
	// c)是：str1[0...i-1]与str2[0...j]的最长公共子序列长度
	// a)中str1的范围 < b)中str1的范围，a)中str2的范围 == b)中str2的范围
	// 所以a)不用求也知道，它比不过b)啊，因为有一个样本的范围比b)小啊！
	// a)中str1的范围 == c)中str1的范围，a)中str2的范围 < c)中str2的范围
	// 所以a)不用求也知道，它比不过c)啊，因为有一个样本的范围比c)小啊！
	// 至此，可以知道，a)就是个垃圾，有它没它，都不影响最大值的决策
	// 所以，当str1[i] == str2[j]时，b)、c)、d)中选出最大值
	// 当str1[i] != str2[j]时，b)、c)中选出最大值
	public static int process(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {
			// str1[0..0]和str2[0..0]，都只剩一个字符了
			// 那如果字符相等，公共子序列长度就是1，不相等就是0
			// 这显而易见
			return str1[i] == str2[j] ? 1 : 0;
		} else if (i == 0) {
			// 这里的情况为：
			// str1[0...0]和str2[0...j]，str1只剩1个字符了，但是str2不只一个字符
			// 因为str1只剩一个字符了，所以str1[0...0]和str2[0...j]公共子序列最多长度为1
			// 如果str1[0] == str2[j]，那么此时相等已经找到了！公共子序列长度就是1，也不可能更大了
			// 如果str1[0] != str2[j]，只是此时不相等而已，
			// 那么str2[0...j-1]上有没有字符等于str1[0]呢？不知道，所以递归继续找
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process(str1, str2, i, j - 1);
			}
		} else if (j == 0) {
			// 和上面的else if同理
			// str1[0...i]和str2[0...0]，str2只剩1个字符了，但是str1不只一个字符
			// 因为str2只剩一个字符了，所以str1[0...i]和str2[0...0]公共子序列最多长度为1
			// 如果str1[i] == str2[0]，那么此时相等已经找到了！公共子序列长度就是1，也不可能更大了
			// 如果str1[i] != str2[0]，只是此时不相等而已，
			// 那么str1[0...i-1]上有没有字符等于str2[0]呢？不知道，所以递归继续找
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process(str1, str2, i - 1, j);
			}
		} else { // i != 0 && j != 0
			// 这里的情况为：
			// str1[0...i]和str2[0...i]，str1和str2都不只一个字符
			// 看函数开始之前的注释部分
			// p1就是可能性c)
			int p1 = process(str1, str2, i - 1, j);
			// p2就是可能性b)
			int p2 = process(str1, str2, i, j - 1);
			// p3就是可能性d)，如果可能性d)存在，即str1[i] == str2[j]，那么p3就求出来，参与pk
			// 如果可能性d)不存在，即str1[i] != str2[j]，那么让p3等于0，然后去参与pk，反正不影响
			int p3 = str1[i] == str2[j] ? (1 + process(str1, str2, i - 1, j - 1)) : 0;
			return Math.max(p1, Math.max(p2, p3));
		}
	}

	@Override
	public int longestPalindromeSubsequence(String s) {
		if (CommonStringUtil.isEmpty(s)) {
			return 0;
		}
		char[] str = s.toCharArray();
		return process(str, 0, str.length - 1);
	}

	// str[L..R]最长回文子序列长度返回
	public static int process(char[] str, int L, int R) {
		if (L == R) {
			return 1;
		}
		if (L == R - 1) {
			return str[L] == str[R] ? 2 : 1;
		}
		int p1 = process(str, L + 1, R - 1); // 最长回文子序列，即不以L开头，也不以R结尾 => 两边缩小范围
		int p2 = process(str, L, R - 1); // 最长回文子序列，以L开头，不以R结尾 => 右边缩小范围
		int p3 = process(str, L + 1, R); // 最长回文子序列，不以L开头，以R结尾 => 左边缩小范围
		int p4 = str[L] != str[R] ? 0 : (2 + process(str, L + 1, R - 1)); // 最长回文子序列，即以L开头，又以R结尾 =》 L == R才可以
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}

	@Override
	public int horseJumpMethods(int a, int b, int k) {
		return jump(0, 0, k, a, b);
	}

	// 当前在(x,y) 还剩 rest 步，目标(a,b)
	public static int jump(int x, int y, int rest, int a, int b) {
		if (x < 0 || x > 9 || y < 0 || y > 8) { // 跳出棋盘了
			return 0;
		}
		if (rest == 0) { // 完成了
			return (x == a && y == b) ? 1 : 0;
		}

		// 任何点都有8个方向的走法
		int ways = jump(x + 2, y + 1, rest - 1, a, b);
		ways += jump(x + 1, y + 2, rest - 1, a, b);
		ways += jump(x - 1, y + 2, rest - 1, a, b);
		ways += jump(x - 2, y + 1, rest - 1, a, b);
		ways += jump(x - 2, y - 1, rest - 1, a, b);
		ways += jump(x - 1, y - 2, rest - 1, a, b);
		ways += jump(x + 1, y - 2, rest - 1, a, b);
		ways += jump(x + 2, y - 1, rest - 1, a, b);
		return ways;
	}

	@Override
	public int minCoffeeTime(int[] arr, int n, int a, int b) {
		PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new CoffeeMachineComparator()); // 小根堆
		for (int i = 0; i < arr.length; i++) {
			heap.add(new CoffeeMachine(0, arr[i]));
		}
		int[] drinks = new int[n]; // 员工喝完时间
		for (int i = 0; i < n; i++) {
			CoffeeMachine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur); // 更新小根堆
		}

		return bestTime(drinks, a, b, 0, 0);
	}

	// drinks 所有杯子可以开始洗的时间
	// wash 单杯洗干净的时间（串行）
	// air 挥发干净的时间(并行)
	// free 洗的机器什么时候可用/空闲 -可变参数
	// drinks[index.....]都变干净，最早的结束时间（返回）
	public int bestTime(int[] drinks, int wash, int evaporate, int index, int free) {
		if (index == drinks.length) {
			return 0;
		}
		// index号杯子 去wash
		int selfWashTimePoint = Math.max(drinks[index], free) + wash; // 杯子喝完时间，和咖啡机空余的时间谁大谁决定什么时间开始洗 => max
		int restWashTimePoint = bestTime(drinks, wash, evaporate, index + 1, selfWashTimePoint);
		int p1 = Math.max(selfWashTimePoint, restWashTimePoint);

		// index号杯子 去evaporate
		int selfEvaporateTimePoint = drinks[index] + evaporate;
		int restEvaporateTimePoint = bestTime(drinks, wash, evaporate, index + 1, free);
		int p2 = Math.max(selfEvaporateTimePoint, restEvaporateTimePoint);
		return Math.min(p1, p2);
	}

	@Override
	public int minPathSum(int[][] matrix) {
		return 0;
	}

	@Override
	public int coinWays(int[] coins, int target) {
		return process(coins, 0, target);
	}

	// index -> 0 ~ coins
	// rest -> 0 ~ target
	public int process(int[] coins, int index, int rest) {
		if (rest < 0) {
			return 0;
		}
		if (index == coins.length) { // 没钱了！
			return rest == 0 ? 1 : 0;
		} else {
			int withoutIndex = process(coins, index + 1, rest); // 没要index位置的钱方法数
			int withIndex = process(coins, index + 1, rest - coins[index]); // 要index位置的钱方法数
			return withoutIndex + withIndex;
		}
	}

	@Override
	public int coinWaysNoLimit(int[] coins, int target) {
		if (CommonArrayUtil.isEmpty(coins)) {
			return 0;
		}
		return go(coins, 0, target);
	}

	// arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
	public static int go(int[] coins, int index, int rest) {
		if (index == coins.length) { // 没钱了
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
			ways += go(coins, index + 1, rest - (zhang * coins[index]));
		}
		return ways;
	}

	@Override
	public int coinWaysSameValue(int[] coins, int target) {
		if (CommonArrayUtil.isEmpty(coins) || target < 0) {
			return 0;
		}
		Info info = Info.getInfo(coins);
		return run(info.coins, info.zhangs, 0, target);
	}

	// coins 面值数组，正数且去重
	// zhangs 每种面值对应的张数
	public int run(int[] coins, int[] zhangs, int index, int rest) {
		if (index == coins.length) {
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
			ways += run(coins, zhangs, index + 1, rest - (zhang * coins[index]));
		}
		return ways;
	}

	/**
	 * 走完一步如果还在棋盘中就获得一点生存点，返回总的生存点数
	 */

	@Override
	public double chessBoardSurvive(int row, int col, int k, int N, int M) {
		return process(row, col, k, N, M) / Math.pow(4, k);
	}

	private long process(int row, int col, int rest, int N, int M) {

		// 越界直接死
		if (row < 0 || row == N || col < 0 || col == M) {
			return 0;
		}
		// 还在棋盘中！
		if (rest == 0) {
			return 1;
		}
		// 还在棋盘中！还有步数要走
		long up = process(row - 1, col, rest - 1, N, M);
		long down = process(row + 1, col, rest - 1, N, M);
		long left = process(row, col - 1, rest - 1, N, M);
		long right = process(row, col + 1, rest - 1, N, M);
		return up + down + left + right;
	}

	@Override
	public double killMonster(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long kill = chop(K, M, N);
		return (double) kill / (double) all;
	}

	// 怪兽还剩hp点血
	// 每次的伤害在[0~M]范围上
	// 还有times次可以砍
	// 返回砍死的情况数！
	public static long chop(int ktimes, int M, int hp) {
		if (ktimes == 0) {
			return hp <= 0 ? 1 : 0;
		}
		if (hp <= 0) {
			return (long) Math.pow(M + 1, ktimes);
		}
		long ways = 0;
		for (int i = 0; i <= M; i++) { // 砍掉1，2，3，4 M 滴血全都试一遍
			int rest = hp - i; // 剩余血量
			ways += chop(ktimes - 1, M, rest);
		}
		return ways;
	}

	@Override
	public int splitNumberWays(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 1) {
			return 1; // 1种拆分方式
		}
		return process(1, n);
	}

	// 上一个拆出来的数是pre
	// 还剩rest需要去拆
	// 返回拆解的方法数
	public static int process(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (pre > rest) { // 3,2 走不通
			return 0;
		}
		int ways = 0;
		for (int first = pre; first <= rest; first++) {
			ways += process(first, rest - first);
		}
		return ways;
	}

	@Override
	public int splitArrSum(int[] arr) {

		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return 0;
		}
		int sum = 0; // 累加和
		for (int num : arr) {
			sum += num;
		}
		return trigger(arr, 0, sum / 2);
	}

	// arr[i...]可以自由选择，请返回累加和(较小的那个集合)尽量接近rest，但不能超过rest的情况下，最接近的累加和是多少？
	public static int trigger(int[] arr, int i, int rest) {
		if (i == arr.length) {
			return 0;
		} else { // 还有数，arr[i]这个数
			// 可能性1，不使用arr[i]
			int p1 = trigger(arr, i + 1, rest);
			// 可能性2，要使用arr[i]
			int p2 = 0;
			if (arr[i] <= rest) {
				p2 = arr[i] + trigger(arr, i + 1, rest - arr[i]);
			}
			return Math.max(p1, p2); // 可能性1和2谁更大 = 谁更加接近rest ， 就选谁
		}
	}

	@Override
	public int splitArrSumSizeHalf(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		if ((arr.length & 1) == 0) { // 如果是偶数
			return trigger(arr, 0, arr.length / 2, sum / 2);
		} else { // 如果是基数：2种选择，返回最接近的Max
			return Math.max(trigger(arr, 0, arr.length / 2, sum / 2), trigger(arr, 0, arr.length / 2 + 1, sum / 2));
		}
	}

	// arr[i....]自由选择，挑选的个数一定要是picks个，累加和<=rest, 离rest最近的返回
	public static int trigger(int[] arr, int i, int picks, int rest) {
		if (i == arr.length) { // 没数的时候，正好剩余挑的格式收0个才有效
			return picks == 0 ? 0 : -1;
		} else {

			// 不使用arr[i]这个数
			int p1 = trigger(arr, i + 1, picks, rest);

			// 就是要使用arr[i]这个数
			int p2 = -1;
			int next = -1;
			if (arr[i] <= rest) {
				next = trigger(arr, i + 1, picks - 1, rest - arr[i]);
			}
			if (next != -1) {
				p2 = arr[i] + next;
			}
			return Math.max(p1, p2); // 可能性1和2谁更大 = 谁更加接近rest ， 就选谁
		}
	}

	@Override
	public int nQueens(int n) {
		if (n < 1) {
			return 0;
		}
		int[] record = new int[n];
		return put(0, record, n);
	}

	/**
	 * 共N-1行,当前来到i行,在此行尝试所有列放quene保证所有皇后不打架。<br>
	 * 之前的皇后存在record, record[x] = y // 之前的第x行的皇后放在y列<br>
	 * 返回：不关心i行以上，i..后续有多少合理的方法数
	 */

	public int put(int i, int[] record, int n) {
		if (i == n) { // 终止了，之前做过的决定 就发现了一个有效方法
			return 1;
		}
		int res = 0;
		// i行的皇后，放哪一列呢？j列，
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;
				res += put(i + 1, record, n);
			}
		}
		return res;
	}

	// A(x,y), B(d,q)-> y=q // 共列 , |d-x| = |q-y| // 共斜线

	/**
	 * A(x,y), B(d,q) <br>
	 * y = q // 共列 <br>
	 * |d-x| = |q-y| // 共斜线
	 */
	boolean isValid(int[] record, int i, int j) {
		// 0..i-1
		for (int k = 0; k < i; k++) {
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public long bribeMonster(int[] ability, int[] price) {
		return choose(ability, price, 0, 0);

	}

	/**
	 * 目前，你的能力是ability，你来到了index号怪兽的面前，如果要通过后续所有的怪兽，请返回需要花的最少钱数
	 */
	public long choose(int[] ability, int[] price, int currentAbility, int index) {
		if (index == ability.length) {
			return 0;
		}
		if (currentAbility < ability[index]) { // 必须贿赂
			return price[index] + choose(ability, price, currentAbility + ability[index], index + 1);
		} else { // ability >= d[index] 可以贿赂，也可以不贿赂
			return Math.min( // 哪个花钱最少

					price[index] + choose(ability, price, currentAbility + ability[index], index + 1), // 贿赂

					0 + choose(ability, price, currentAbility, index + 1)); // 不贿赂
		}
	}

	@Override
	public int assembleTargetSum(int[] arr, int sum) {
		return assemble(arr, 0, sum);
	}
	
	/**
	 * 可以使用arr[index...]往后所有数字,加出rest，方法数多少
	 */
	private int assemble(int[] arr, int index, int rest) {
		if (index == arr.length) { // 没数了
			return rest == 0 ? 1 : 0;
		}
		
		int plus = process(arr,index + 1, rest + arr[index]);
		int minus = process(arr,index + 1, rest - arr[index]);
		
		return plus + minus; 
	}

	@Override
	public int longestIncreasingPath(int[][] matrix) {
		int ans = 0;
		int N = matrix.length;
		int M = matrix[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans = Math.max(ans, walk(matrix, i, j));
			}
		}
		return ans;
	}
	
	// 从m[i][j]开始走，走出来的最长递增链，返回！
	public int walk(int[][] m, int i, int j) {
		boolean canWalkUp = i > 0 && m[i][j] < m[i - 1][j];
		int up = canWalkUp ? walk(m, i - 1, j) : 0;
		
		boolean canWalkDown = i < (m.length - 1) && m[i][j] < m[i + 1][j];
		int down = canWalkDown ? walk(m, i + 1, j) : 0;
		
		boolean canWalkLeft = j > 0 && m[i][j] < m[i][j - 1];
		int left = canWalkLeft ? walk(m, i, j - 1) : 0;
		
		boolean canWalkright = j < (m[0].length - 1) && m[i][j] < m[i][j + 1];
		int right = canWalkright ? walk(m, i, j + 1) : 0;
		
		return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
	}

	@Override
	public int maxDriverIncome(int[][] income) {
		
		if (income == null || income.length < 2 || (income.length & 1) != 0) {
			return 0;
		}
		
		int drivers = income.length; // 司机总数
		int A = drivers / 2; // 要去A的
		
		return go(income,0,A);
	}
	
	/**
	 * index..往后所有司机往A,B分配，A区域还有rest个名额
	 * 返回把index...	司机分配完，且A和B同样多时，index..司机整体收入最大多少
	 */
	private int go(int[][] income, int index, int ARest) {
		
		if (income.length == index) {
			return 0;
		}
		
		 // (剩下的司机数 = A的名额) => 必须全去A，B已满
		if (income.length - index  == ARest) {
			return income[index][0] + go(income,index+1,ARest-1);
		}
		
		// A没有名额了 => 必须全去B，A已满
		if (ARest == 0) {
			return income[index][1] + go(income,index+1,ARest);
		}
		
		// 可以去A也可以去B
		int p1 = income[index][0] + go(income,index+1,ARest-1);
		int p2 = income[index][1] + go(income,index+1,ARest);
		
		return Math.max(p1, p2);
	}

}
