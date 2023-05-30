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
 * �ݹ� = ����
 */
public class DPRecursive implements DPService {

	@Override
	public void hanoi(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}

	/**
	 * ��Ŀ�꣺ 1 ~ N �� from ȥ to , ��һ�� other 1 2 N --- --- --- from to other
	 */
	private void func(int N, String from, String to, String other) {
		if (N == 1) { // base case
			System.out.println("Move 1 from " + from + " to " + to);
		} else {
			func(N - 1, from, other, to); // 1 ~ (n - 1)��Բ�̴�from ȥ other, to ������һ��
			System.out.println("Move " + N + " from " + from + " to " + to); // ������n �� from �ƶ��� to ��ӡ
			func(N - 1, other, to, from); // 1 ~ (n - 1)��Բ�̴�other Ų�� to ����
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

	// str �̶�����
	// ������str[index]�ַ���index��λ��
	// str[0..index-1]�Ѿ��߹��ˣ�֮ǰ�ľ���������path��
	// ֮ǰ�ľ����Ѿ����ܸı��ˣ�����path
	// str[index....]���ܾ�����֮ǰ�Ѿ�ȷ���������滹������ѡ��Ļ������������ɵ������У����뵽ans��ȥ
	public static void process(char[] str, int index, List<String> ans, String path) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		// û��Ҫindexλ�õ��ַ� = pathû��
		process(str, index + 1, ans, path);
		// Ҫ��indexλ�õ��ַ� = path ���˴�ʱ���ַ�
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
			boolean[] visited = new boolean[256]; // assci �����255��
			for (int i = index; i < str.length; i++) { // i��indexλ�õ��ַ�Ҫ����
				if (!visited[str[i]]) { // ȥ�أ�ע�͵����ǲ�ȥ��
					visited[str[i]] = true;
					CommonArrayUtil.swap(str, index, i);
					g(str, index + 1, ans); // ȥ������֧·
					CommonArrayUtil.swap(str, index, i); // ����һ��ǰ �ָ��ֳ�
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
	 * @param curr:  ��ǰ��Ͻ��
	 * @param left   : �����Ż�ʣ����
	 * @param right�� �����Ż�ʣ����
	 */
	public static void generate(List<String> result, String curr, int left, int right) {
		if (left == 0 && right == 0) { // ����
			result.add(curr);
			return;
		}

		if (left > 0) { // ��������
			generate(result, curr + "(", left - 1, right);
		}

		if (right > left) { // ��������
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
		if (rest == 0) { // ����Ѿ�����Ҫ���ˣ������ˣ�
			return cur == aim ? 1 : 0;
		}
		if (cur == 1) { // 1 -> 2 , �����
			return go(2, rest - 1, aim, N);
		}
		if (cur == N) { // N-1 <- N�� ���Ҳ�
			return go(N - 1, rest - 1, aim, N);
		}
		return go(cur - 1, rest - 1, aim, N) + go(cur + 1, rest - 1, aim, N); // �м䣬�ȿ������� �ֿ�������
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

	// arr[L..R]�����ֻ�õ���÷�������
	public static int f1(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int p1 = arr[L] + g1(arr, L + 1, R);
		int p2 = arr[R] + g1(arr, L, R - 1);
		return Math.max(p1, p2);
	}

	// // arr[L..R]�����ֻ�õ���÷�������
	public static int g1(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int p1 = f1(arr, L + 1, R); // ����������Lλ�õ���
		int p2 = f1(arr, L, R - 1); // ����������Rλ�õ���
		return Math.min(p1, p2);
	}

	@Override
	public int knapsackMaxValue(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length == 0 || v.length != w.length) {
			return 0;
		}

		return process(w, v, 0, bag); // ���Ժ���
	}

	// ��index��������ѡ�� �������ļ�ֵ
	private int process(int[] w, int[] v, int index, int bag) {
		if (bag < 0) {
			return -1; // ��Ч�Ļ���ѡ��
		}
		if (index == w.length) {
			return 0;
		}
		int no = process(w, v, index + 1, bag); // ��Ҫ��ǰ��

		int yes = 0;// Ҫ��ǰ��
		int check = process(w, v, index + 1, bag - w[index]); // ��鱳���Ƿ�ը
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

	// str[0..i-1]ת���������, str[i.....]ȥת���������ж�����ת��������һ���ɱ������һά��
	private int process(char[] str, int i) {
		if (i == str.length) {
			return 1;
		}
		// ֮ǰ�ľ���������
		if (str[i] == '0') {

		}

		// ������1��i��ת�� ȥi+1����
		int ways = process(str, i + 1);

		// ������2��i �� i+1 ��ͬת��ȥi+2����
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
			String rest = minus(target, first); // ʹ��һ����ֽ �����ʣ�µ�target
			if (rest.length() != target.length()) { // �������û���κα仯��= ʹ�õ�ǰ��ֽû�м����κ��ַ�������
				min = process(stickers, rest);
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1); // + 1 ����first
	}

	private String minus(String target, String sticker) {
		char[] s1 = target.toCharArray();
		char[] s2 = sticker.toCharArray();
		int[] count = new int[26];
		for (char cha : s1) {
			count[cha - 'a']++; // ͳ��String��ÿ���ַ����ֵ�Ƶ��
		}
		for (char cha : s2) {
			count[cha - 'a']--; // s2����ȥ��
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
		// ����
		return process(str1, str2, str1.length - 1, str2.length - 1);
	}

	// str1[0...i]��str2[0...j]�������Χ������������г����Ƕ��٣�
	// �����Է���:
	// a) ����������У�һ������str1[i]�ַ���β��Ҳһ������str2[j]�ַ���β
	// b) ����������У�������str1[i]�ַ���β������һ������str2[j]�ַ���β
	// c) ����������У�һ������str1[i]�ַ���β�����ǿ�����str2[j]�ַ���β
	// d) ����������У�������str1[i]�ַ���β��Ҳ������str2[j]�ַ���β
	// ע�⣺a)��b)��c)��d)��������ȫ����ģ����ǿ��ܻ����ص������
	// ���ǿ��Կ϶����𰸲��ᳬ�������ֿ����Եķ�Χ
	// ��ô���Ƿֱ�����һ�£��⼸�ֿ�������ô���ú����ĵݹ顣
	// a) ����������У�һ������str1[i]�ַ���β��Ҳһ������str2[j]�ַ���β
	// ����������������ô��û��str1[i]��str2[j]�͸�������Ҫ�ˣ���Ϊ�������ַ�һ��û�ð�
	// ���Կ����������ַ�������������� = str1[0...i-1]��str2[0...j-1]������������г���(�����ݹ�)
	// b) ����������У�������str1[i]�ַ���β������һ������str2[j]�ַ���β
	// ����������������ô���ǿ���ȷ��str2[j]һ��û���ã�Ҫ����������str1[i]�������ã�����Ҫ����
	// ���ԣ������������ = str1[0...i]��str2[0...j-1]������������г���(�����ݹ�)
	// c) ����������У�һ������str1[i]�ַ���β�����ǿ�����str2[j]�ַ���β
	// ����������������ƣ������������ = str1[0...i-1]��str2[0...j]������������г���(�����ݹ�)
	// d) ����������У�������str1[i]�ַ���β��Ҳ������str2[j]�ַ���β
	// ͬʱ���Կ�����������d)���ڵ�������һ������str1[i] == str2[j]������£��ų�����
	// ���ԣ�������������ܳ��� = str1[0...i-1]��str2[0...j-1]������������г���(�����ݹ�) + 1(��ͬ�Ľ�β)
	// ���ϣ���������Ѿ�������п����ԡ����������ȡ��󼴿�
	// ����b)��c)һ���������ֵ�ıȽϣ�
	// ��str1[i] == str2[j]ʱ��a)һ����d)С������d)����
	// ��str1[i] != str2[j]ʱ��d)ѹ�������ڣ�����a)����
	// �����ٴ�ע���ˣ�
	// a)�ǣ�str1[0...i-1]��str2[0...j-1]������������г���
	// b)�ǣ�str1[0...i]��str2[0...j-1]������������г���
	// c)�ǣ�str1[0...i-1]��str2[0...j]������������г���
	// a)��str1�ķ�Χ < b)��str1�ķ�Χ��a)��str2�ķ�Χ == b)��str2�ķ�Χ
	// ����a)������Ҳ֪�������Ȳ���b)������Ϊ��һ�������ķ�Χ��b)С����
	// a)��str1�ķ�Χ == c)��str1�ķ�Χ��a)��str2�ķ�Χ < c)��str2�ķ�Χ
	// ����a)������Ҳ֪�������Ȳ���c)������Ϊ��һ�������ķ�Χ��c)С����
	// ���ˣ�����֪����a)���Ǹ�����������û��������Ӱ�����ֵ�ľ���
	// ���ԣ���str1[i] == str2[j]ʱ��b)��c)��d)��ѡ�����ֵ
	// ��str1[i] != str2[j]ʱ��b)��c)��ѡ�����ֵ
	public static int process(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {
			// str1[0..0]��str2[0..0]����ֻʣһ���ַ���
			// ������ַ���ȣ����������г��Ⱦ���1������Ⱦ���0
			// ���Զ��׼�
			return str1[i] == str2[j] ? 1 : 0;
		} else if (i == 0) {
			// ��������Ϊ��
			// str1[0...0]��str2[0...j]��str1ֻʣ1���ַ��ˣ�����str2��ֻһ���ַ�
			// ��Ϊstr1ֻʣһ���ַ��ˣ�����str1[0...0]��str2[0...j]������������೤��Ϊ1
			// ���str1[0] == str2[j]����ô��ʱ����Ѿ��ҵ��ˣ����������г��Ⱦ���1��Ҳ�����ܸ�����
			// ���str1[0] != str2[j]��ֻ�Ǵ�ʱ����ȶ��ѣ�
			// ��ôstr2[0...j-1]����û���ַ�����str1[0]�أ���֪�������Եݹ������
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process(str1, str2, i, j - 1);
			}
		} else if (j == 0) {
			// �������else ifͬ��
			// str1[0...i]��str2[0...0]��str2ֻʣ1���ַ��ˣ�����str1��ֻһ���ַ�
			// ��Ϊstr2ֻʣһ���ַ��ˣ�����str1[0...i]��str2[0...0]������������೤��Ϊ1
			// ���str1[i] == str2[0]����ô��ʱ����Ѿ��ҵ��ˣ����������г��Ⱦ���1��Ҳ�����ܸ�����
			// ���str1[i] != str2[0]��ֻ�Ǵ�ʱ����ȶ��ѣ�
			// ��ôstr1[0...i-1]����û���ַ�����str2[0]�أ���֪�������Եݹ������
			if (str1[i] == str2[j]) {
				return 1;
			} else {
				return process(str1, str2, i - 1, j);
			}
		} else { // i != 0 && j != 0
			// ��������Ϊ��
			// str1[0...i]��str2[0...i]��str1��str2����ֻһ���ַ�
			// ��������ʼ֮ǰ��ע�Ͳ���
			// p1���ǿ�����c)
			int p1 = process(str1, str2, i - 1, j);
			// p2���ǿ�����b)
			int p2 = process(str1, str2, i, j - 1);
			// p3���ǿ�����d)�����������d)���ڣ���str1[i] == str2[j]����ôp3�������������pk
			// ���������d)�����ڣ���str1[i] != str2[j]����ô��p3����0��Ȼ��ȥ����pk��������Ӱ��
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

	// str[L..R]����������г��ȷ���
	public static int process(char[] str, int L, int R) {
		if (L == R) {
			return 1;
		}
		if (L == R - 1) {
			return str[L] == str[R] ? 2 : 1;
		}
		int p1 = process(str, L + 1, R - 1); // ����������У�������L��ͷ��Ҳ����R��β => ������С��Χ
		int p2 = process(str, L, R - 1); // ����������У���L��ͷ������R��β => �ұ���С��Χ
		int p3 = process(str, L + 1, R); // ����������У�����L��ͷ����R��β => �����С��Χ
		int p4 = str[L] != str[R] ? 0 : (2 + process(str, L + 1, R - 1)); // ����������У�����L��ͷ������R��β =�� L == R�ſ���
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}

	@Override
	public int horseJumpMethods(int a, int b, int k) {
		return jump(0, 0, k, a, b);
	}

	// ��ǰ��(x,y) ��ʣ rest ����Ŀ��(a,b)
	public static int jump(int x, int y, int rest, int a, int b) {
		if (x < 0 || x > 9 || y < 0 || y > 8) { // ����������
			return 0;
		}
		if (rest == 0) { // �����
			return (x == a && y == b) ? 1 : 0;
		}

		// �κε㶼��8��������߷�
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
		PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new CoffeeMachineComparator()); // С����
		for (int i = 0; i < arr.length; i++) {
			heap.add(new CoffeeMachine(0, arr[i]));
		}
		int[] drinks = new int[n]; // Ա������ʱ��
		for (int i = 0; i < n; i++) {
			CoffeeMachine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur); // ����С����
		}

		return bestTime(drinks, a, b, 0, 0);
	}

	// drinks ���б��ӿ��Կ�ʼϴ��ʱ��
	// wash ����ϴ�ɾ���ʱ�䣨���У�
	// air �ӷ��ɾ���ʱ��(����)
	// free ϴ�Ļ���ʲôʱ�����/���� -�ɱ����
	// drinks[index.....]����ɾ�������Ľ���ʱ�䣨���أ�
	public int bestTime(int[] drinks, int wash, int evaporate, int index, int free) {
		if (index == drinks.length) {
			return 0;
		}
		// index�ű��� ȥwash
		int selfWashTimePoint = Math.max(drinks[index], free) + wash; // ���Ӻ���ʱ�䣬�Ϳ��Ȼ������ʱ��˭��˭����ʲôʱ�俪ʼϴ => max
		int restWashTimePoint = bestTime(drinks, wash, evaporate, index + 1, selfWashTimePoint);
		int p1 = Math.max(selfWashTimePoint, restWashTimePoint);

		// index�ű��� ȥevaporate
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
		if (index == coins.length) { // ûǮ�ˣ�
			return rest == 0 ? 1 : 0;
		} else {
			int withoutIndex = process(coins, index + 1, rest); // ûҪindexλ�õ�Ǯ������
			int withIndex = process(coins, index + 1, rest - coins[index]); // Ҫindexλ�õ�Ǯ������
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

	// arr[index....] ���е���ֵ��ÿһ����ֵ����������ѡ���������������rest��ô��Ǯ�����������٣�
	public static int go(int[] coins, int index, int rest) {
		if (index == coins.length) { // ûǮ��
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

	// coins ��ֵ���飬������ȥ��
	// zhangs ÿ����ֵ��Ӧ������
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
	 * ����һ��������������оͻ��һ������㣬�����ܵ��������
	 */

	@Override
	public double chessBoardSurvive(int row, int col, int k, int N, int M) {
		return process(row, col, k, N, M) / Math.pow(4, k);
	}

	private long process(int row, int col, int rest, int N, int M) {

		// Խ��ֱ����
		if (row < 0 || row == N || col < 0 || col == M) {
			return 0;
		}
		// ���������У�
		if (rest == 0) {
			return 1;
		}
		// ���������У����в���Ҫ��
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

	// ���޻�ʣhp��Ѫ
	// ÿ�ε��˺���[0~M]��Χ��
	// ����times�ο��Կ�
	// ���ؿ������������
	public static long chop(int ktimes, int M, int hp) {
		if (ktimes == 0) {
			return hp <= 0 ? 1 : 0;
		}
		if (hp <= 0) {
			return (long) Math.pow(M + 1, ktimes);
		}
		long ways = 0;
		for (int i = 0; i <= M; i++) { // ����1��2��3��4 M ��Ѫȫ����һ��
			int rest = hp - i; // ʣ��Ѫ��
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
			return 1; // 1�ֲ�ַ�ʽ
		}
		return process(1, n);
	}

	// ��һ�������������pre
	// ��ʣrest��Ҫȥ��
	// ���ز��ķ�����
	public static int process(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (pre > rest) { // 3,2 �߲�ͨ
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
		int sum = 0; // �ۼӺ�
		for (int num : arr) {
			sum += num;
		}
		return trigger(arr, 0, sum / 2);
	}

	// arr[i...]��������ѡ���뷵���ۼӺ�(��С���Ǹ�����)�����ӽ�rest�������ܳ���rest������£���ӽ����ۼӺ��Ƕ��٣�
	public static int trigger(int[] arr, int i, int rest) {
		if (i == arr.length) {
			return 0;
		} else { // ��������arr[i]�����
			// ������1����ʹ��arr[i]
			int p1 = trigger(arr, i + 1, rest);
			// ������2��Ҫʹ��arr[i]
			int p2 = 0;
			if (arr[i] <= rest) {
				p2 = arr[i] + trigger(arr, i + 1, rest - arr[i]);
			}
			return Math.max(p1, p2); // ������1��2˭���� = ˭���ӽӽ�rest �� ��ѡ˭
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
		if ((arr.length & 1) == 0) { // �����ż��
			return trigger(arr, 0, arr.length / 2, sum / 2);
		} else { // ����ǻ�����2��ѡ�񣬷�����ӽ���Max
			return Math.max(trigger(arr, 0, arr.length / 2, sum / 2), trigger(arr, 0, arr.length / 2 + 1, sum / 2));
		}
	}

	// arr[i....]����ѡ����ѡ�ĸ���һ��Ҫ��picks�����ۼӺ�<=rest, ��rest����ķ���
	public static int trigger(int[] arr, int i, int picks, int rest) {
		if (i == arr.length) { // û����ʱ������ʣ�����ĸ�ʽ��0������Ч
			return picks == 0 ? 0 : -1;
		} else {

			// ��ʹ��arr[i]�����
			int p1 = trigger(arr, i + 1, picks, rest);

			// ����Ҫʹ��arr[i]�����
			int p2 = -1;
			int next = -1;
			if (arr[i] <= rest) {
				next = trigger(arr, i + 1, picks - 1, rest - arr[i]);
			}
			if (next != -1) {
				p2 = arr[i] + next;
			}
			return Math.max(p1, p2); // ������1��2˭���� = ˭���ӽӽ�rest �� ��ѡ˭
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
	 * ��N-1��,��ǰ����i��,�ڴ��г��������з�quene��֤���лʺ󲻴�ܡ�<br>
	 * ֮ǰ�Ļʺ����record, record[x] = y // ֮ǰ�ĵ�x�еĻʺ����y��<br>
	 * ���أ�������i�����ϣ�i..�����ж��ٺ���ķ�����
	 */

	public int put(int i, int[] record, int n) {
		if (i == n) { // ��ֹ�ˣ�֮ǰ�����ľ��� �ͷ�����һ����Ч����
			return 1;
		}
		int res = 0;
		// i�еĻʺ󣬷���һ���أ�j�У�
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;
				res += put(i + 1, record, n);
			}
		}
		return res;
	}

	// A(x,y), B(d,q)-> y=q // ���� , |d-x| = |q-y| // ��б��

	/**
	 * A(x,y), B(d,q) <br>
	 * y = q // ���� <br>
	 * |d-x| = |q-y| // ��б��
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
	 * Ŀǰ�����������ability����������index�Ź��޵���ǰ�����Ҫͨ���������еĹ��ޣ��뷵����Ҫ��������Ǯ��
	 */
	public long choose(int[] ability, int[] price, int currentAbility, int index) {
		if (index == ability.length) {
			return 0;
		}
		if (currentAbility < ability[index]) { // �����¸
			return price[index] + choose(ability, price, currentAbility + ability[index], index + 1);
		} else { // ability >= d[index] ���Ի�¸��Ҳ���Բ���¸
			return Math.min( // �ĸ���Ǯ����

					price[index] + choose(ability, price, currentAbility + ability[index], index + 1), // ��¸

					0 + choose(ability, price, currentAbility, index + 1)); // ����¸
		}
	}

	@Override
	public int assembleTargetSum(int[] arr, int sum) {
		return assemble(arr, 0, sum);
	}
	
	/**
	 * ����ʹ��arr[index...]������������,�ӳ�rest������������
	 */
	private int assemble(int[] arr, int index, int rest) {
		if (index == arr.length) { // û����
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
	
	// ��m[i][j]��ʼ�ߣ��߳�����������������أ�
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
		
		int drivers = income.length; // ˾������
		int A = drivers / 2; // ҪȥA��
		
		return go(income,0,A);
	}
	
	/**
	 * index..��������˾����A,B���䣬A������rest������
	 * ���ذ�index...	˾�������꣬��A��Bͬ����ʱ��index..˾����������������
	 */
	private int go(int[][] income, int index, int ARest) {
		
		if (income.length == index) {
			return 0;
		}
		
		 // (ʣ�µ�˾���� = A������) => ����ȫȥA��B����
		if (income.length - index  == ARest) {
			return income[index][0] + go(income,index+1,ARest-1);
		}
		
		// Aû�������� => ����ȫȥB��A����
		if (ARest == 0) {
			return income[index][1] + go(income,index+1,ARest);
		}
		
		// ����ȥAҲ����ȥB
		int p1 = income[index][0] + go(income,index+1,ARest-1);
		int p2 = income[index][1] + go(income,index+1,ARest);
		
		return Math.max(p1, p2);
	}

}
