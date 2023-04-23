package com.algo.util.dp;

import java.util.List;
import java.util.Stack;

import com.algo.util.dp.impl.DPCache;
import com.algo.util.dp.impl.DPGrid;
import com.algo.util.dp.impl.DPRecursive;
import com.algo.util.stack.StackUtil;

public class DPUtil {

	/**
	 * ��ŵ������, O(2^N - 1)
	 */

	public static void hanoi(int n) {
		new DPRecursive().hanoi(n);
	}

	/**
	 * ��ӡString��ȫ��������,�����в���Ҫ����
	 */

	public static List<String> getAllSubsequence(String s) {
		return new DPRecursive().subsequence(s);
	}

	/**
	 * ��ӡString��ȫ���� = ������� = ˳��ͬ��һ��
	 */

	public static List<String> getAllPermutation(String s) {
		return new DPRecursive().permutation(s);
	}

	/**
	 * ����ջ�����ǲ����ö�������ݽṹ
	 */

	public static void reverseStack(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = StackUtil.popBottom(stack);
		reverseStack(stack);
		stack.push(i);
		StackUtil.popBottom(stack);
	}

	/**
	 * ��ӡn��������Ч�������,lc22
	 */
	public static List<String> getAllBrackets(int n) {
		return new DPRecursive().getAllBrackets(n);
	}

	/**
	 * 
	 * m * n ���̣������Ͻ�ȥ���½��ж�����·
	 * 
	 */
	public static int uniquePaths(int m, int n) {
		return new DPGrid().uniquePaths(m, n);
	}

	/**
	 * һάһ����n��λ�� �����˴�start*ֻ��k����aim~�ж��ٷ��� 1 2 3 4 5 6 7 = N * ~
	 */
	public static int uniqueWays(int n, int start, int aim, int k) {
		return new DPGrid().uniqueWays(n, start, aim, k);
	}

	/**
	 * һ���˿ˣ�N��λ�ã�2���Ⱥ��������� �������Ӯ�����ػ�ʤ�ߵķ����� ��Χ����ģ�ͣ�L~R��Χ
	 */

	public static int drawCardGame(int[] arr) {
		return new DPGrid().drawCardGame(arr);
	}

	/**
	 * �������⣺ w : ���������� v : �����ֵ�� bag: �������� �������ң�[Ҫ or ��Ҫ]
	 */

	public static int knapsackMaxValue(int[] w, int[] v, int bag) {
		return new DPGrid().knapsackMaxValue(w, v, bag);
	}

	/**
	 * 
	 * strֻ���������ַ�0~9, 0=A,2=B... 25=Z ���ض�����ת������
	 */
	public static int convertNumToLetter(String str) {
		return new DPGrid().convertNumToLetter(str);
	}

	/**
	 * ��һЩ��ֽ�������ţ����Լ��У�������Ҫ�����ţ�����ƴ��target
	 */
	public static int findMinStickerstoSpellTarget(String[] stickers, String target) {
		return new DPCache().minStickersToSpellWords(stickers, target);
	}

	/**
	 * �����������.
	 * 
	 * ������Ӧģ�� �� �����һ���ַ���Ϊ����
	 */

	public static int longestCommonSubsequence(String str1, String str2) {
		return new DPGrid().longestCommonSubsequence(str1, str2);
	}

	/**
	 * �����������. = longestCommonSubsequence(str1,reverse(str))
	 * 
	 * ��Χ����ģ�� �� �ں����ۿ�ͷ �� ��β
	 */
	public static int longestPalindromeSubsequence(String input) {
		return new DPGrid().longestPalindromeSubsequence(input);
	}

	/**
	 * 10 * 9 ���̣������� ��(0,0)λ�� ��k�� ��(a,b)λ�� �ж����ַ�����
	 *
	 */
	public static int horseJumpMethods(int a, int b, int k) {
		return new DPGrid().horseJumpMethods(a, b, k);
	}

	/**
	 * arr[1,3,4,..] : ����ÿһ�����Ȼ���һ�����ȵ�ʱ��. n������Ҫ�ȿ��ȣ�ֻ���ÿ��Ȼ������쿧��. �ȿ�����0s. һ̨ϴ����
	 * a��ϴ�꣬��Ȼ�ӷ�Ҫb�롣�����˺�����ϴ�����ٶ�ã�
	 * 
	 * ҵ������ģ�ͣ��ɱ����������ֱ�ӵõ��仯��Χ,��Ϊ��������e.g : free
	 */

	public static int minCoffeeTime(int[] arr, int n, int a, int b) {
		return new DPGrid().minCoffeeTime(arr, n, a, b);
	}

	/**
	 * �����˴�Matrix ���� -> ���£�����·����С�͡�<br>
	 * 
	 * ����ѹ�����ɣ�<br>
	 * 
	 * ����ѹ�����÷�Χ�� �κ�λ������left��up || �κ�λ������leftup��up || �κ�λ������left��leftup��up <br>
	 * <br>
	 * ����ѹ����dp[]ѡ�� ˭��ѡ˭ -> 100W��4��׼��dp[4]�������¸��£� 4��100W��׼��dp[4]�������Ҹ���
	 * 
	 */

	public static int minPathSum(int[][] matrix) {
		return new DPGrid().minPathSum(matrix);
	}

	/**
	 * ����ͷ��coins���target�ж����ַ�ʽ, ��ֵ������ͬ����ֵ��ͬҲ���ǲ�ͬ? [1,1,1,2,2,2] -> 3
	 */
	public static int coinWays(int[] coins, int target) {
		return new DPGrid().coinWays(coins, target);
	}

	/**
	 * ����ͷ��coins���target�ж����ַ�ʽ, ÿ����ֵ��ͬ��ÿ����ֵ������ [1,2,4,6,8,9] -> 113
	 */
	public static int coinWaysNoLimit(int[] coins, int target) {
		return new DPGrid().coinWays(coins, target);
	}

}
