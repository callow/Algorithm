package com.algo.util.dp;

import java.util.List;
import java.util.Stack;

import com.algo.util.dp.impl.DPCache;
import com.algo.util.dp.impl.DPGrid;
import com.algo.util.dp.impl.DPRecursive;
import com.algo.util.stack.StackUtil;

/**
 * 1. 可变参数不可以超越整形： f(int[] arr, int i) <br>
 * 2. 如果违反，只能是1维： f(String input) - 贴纸问题 <br>
 * 3. 2的最优解只能是记忆化搜索 <br>
 * 
 * 提醒作用的4种模型： <br>
 * 
 * - 从左往右尝试模型：要和不要 <br>
 * - 范围尝试模型 <br>
 * - 多样本位置全对应的尝试模型 <br>
 * - 寻找业务限制的尝试模型：<br>
 * 
 * 
 *
 */
public class DPUtil {

	/**
	 * 汉诺塔问题, O(2^N - 1)
	 */

	public static void hanoi(int n) {
		new DPRecursive().hanoi(n);
	}

	/**
	 * 打印String的全部子序列,子序列不需要连续
	 */

	public static List<String> getAllSubsequence(String s) {
		return new DPRecursive().subsequence(s);
	}

	/**
	 * 打印String的全排列 = 排列组合 = 顺序不同算一种
	 */

	public static List<String> getAllPermutation(String s) {
		return new DPRecursive().permutation(s);
	}

	/**
	 * 逆序栈，但是不能用额外的数据结构
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
	 * 打印n对所有有效括号组合,lc22
	 */
	public static List<String> getAllBrackets(int n) {
		return new DPRecursive().getAllBrackets(n);
	}

	/**
	 * 
	 * m * n 棋盘，从左上角去右下角有多少条路
	 * 
	 */
	public static int uniquePaths(int m, int n) {
		return new DPGrid().uniquePaths(m, n);
	}

	/**
	 * 一维一共有n个位置 机器人从start*只走k步到aim~有多少方法 1 2 3 4 5 6 7 = N * ~
	 */
	public static int uniqueWays(int n, int start, int aim, int k) {
		return new DPGrid().uniqueWays(n, start, aim, k);
	}

	/**
	 * 一堆扑克，N个位置，2人先后两端拿牌 总数大的赢。返回获胜者的分数？ 范围尝试模型，L~R范围
	 */

	public static int drawCardGame(int[] arr) {
		return new DPGrid().drawCardGame(arr);
	}

	/**
	 * 背包问题： w : 货物重量， v : 货物价值， bag: 背包容量 从左往右，[要 or 不要]
	 */

	public static int knapsackMaxValue(int[] w, int[] v, int bag) {
		return new DPGrid().knapsackMaxValue(w, v, bag);
	}

	/**
	 * 
	 * str只含有数字字符0~9, 0=A,2=B... 25=Z 返回多少种转化方案
	 */
	public static int convertNumToLetter(String str) {
		return new DPGrid().convertNumToLetter(str);
	}

	/**
	 * 有一些贴纸，无穷张，可以剪切，最少需要多少张，可以拼出target
	 */
	public static int findMinStickerstoSpellTarget(String[] stickers, String target) {
		return new DPCache().minStickersToSpellWords(stickers, target);
	}

	/**
	 * 最长公共子序列.
	 * 
	 * 样本对应模型 ： 以最后一个字符作为考量
	 */

	public static int longestCommonSubsequence(String str1, String str2) {
		return new DPGrid().longestCommonSubsequence(str1, str2);
	}

	/**
	 * 最长回文子序列. = longestCommonSubsequence(str1,reverse(str))
	 * 
	 * 范围尝试模型 ： 在乎讨论开头 和 结尾
	 */
	public static int longestPalindromeSubsequence(String input) {
		return new DPGrid().longestPalindromeSubsequence(input);
	}

	/**
	 * 10 * 9 棋盘，象棋马 从(0,0)位置 跳k步 到(a,b)位置 有多少种方法？
	 *
	 */
	public static int horseJumpMethods(int a, int b, int k) {
		return new DPGrid().horseJumpMethods(a, b, k);
	}

	/**
	 * arr[1,3,4,..] : 代表每一个咖啡机冲一杯咖啡的时间. n个人需要喝咖啡，只能用咖啡机来制造咖啡. 喝咖啡用0s. 一台洗杯机
	 * a秒洗完，自然挥发要b秒。所有人喝完又洗完至少多久？
	 * 
	 * 业务限制模型：可变参数不可以直接得到变化范围,人为估出来，e.g : free
	 */

	public static int minCoffeeTime(int[] arr, int n, int a, int b) {
		return new DPGrid().minCoffeeTime(arr, n, a, b);
	}

	/**
	 * 机器人从Matrix 左上 -> 右下，返回路径最小和。<br>
	 * 
	 * 数组压缩技巧！<br>
	 * 
	 * 数组压缩适用范围： 任何位置依赖left和up || 任何位置依赖leftup和up || 任何位置依赖left和leftup和up <br>
	 * <br>
	 * 数组压缩的dp[]选择： 谁短选谁 -> 100W行4列准备dp[4]从上往下更新， 4行100W列准备dp[4]从左往右更新
	 * 
	 */

	public static int minPathSum(int[][] matrix) {
		return new DPGrid().minPathSum(matrix);
	}

	/**
	 * 用手头的coins组成target有多少种方式, 面值可以相同，面值相同也算是不同? [1,1,1,2,2,2] -> 3
	 */
	public static int coinWays(int[] coins, int target) {
		return new DPGrid().coinWays(coins, target);
	}

	/**
	 * 用手头的coins组成target有多少种方式, 每个面值不同，每个面值无数（infinite）张 [1,2,4,6,8,9] -> 113
	 */
	public static int coinWaysNoLimit(int[] coins, int target) {
		return new DPGrid().coinWays(coins, target);
	}

	/**
	 * 用手头的coins组成target有多少种方式, 面值可以相同，面值相同认为是相同? [1,1,1,2,2,2] = 3张1元，3张2元->
	 * Target: 25
	 */
	public static int coinWaysSameValue(int[] coins, int target) {
		return new DPGrid().coinWaysSameValue(coins, target);
	}

	/**
	 * 
	 * N * M 的棋盘，醉汉在 (row,col) 处要走k步，走出棋盘即死亡，返回k步后生存的概率？
	 * 
	 * 总样本： 4^k <br>
	 * 生存概率： 生存点 / 总样本 <br>
	 * 象棋跳马问题3维dp
	 */
	public static double chessBoardSurvive(int row, int col, int k, int N, int M) {
		return new DPGrid().chessBoardSurvive(row, col, k, N, M);
	}

	/**
	 * 怪兽N滴血，英雄每次砍[0~M]滴血，求K次砍之后砍死的概率？
	 * 
	 * 总可能性：(M+1)^k <br>
	 * 砍死概率：砍死次数 / 总可能性 <br>
	 * 斜率优化问题：动态规划带枚举的,优化掉枚举
	 */

	public static double killMonster(int N, int M, int K) {
		return new DPGrid().killMonster(N, M, K);
	}

	/**
	 * 拉马努金-整数拆分问题：3 裂开，后面不能小于前面的：1+1+1， 1+2 不可以：2+1 不可以降序拆分<br>
	 * 斜率优化
	 */
	public static int splitNumberWays(int n) {
		return new DPGrid().splitNumberWays(n);
	}

	/**
	 * 拆分arr成2个，使其累加和最接近
	 */
	public static int splitArrSum(int[] arr) {
		return new DPGrid().splitArrSum(arr);
	}

	/**
	 * 拆分arr成2个，使其累加和最接近, 总数是偶数：均分，总数是基数：相差1
	 */
	public static int splitArrSumSizeHalf(int[] arr) {
		return new DPGrid().splitArrSumSizeHalf(arr);
	}

	/**
	 * 
	 * N皇后问题，N*N的棋盘上摆放N个Queen, 横竖斜不能共线，给一个N= 1，2，3，4， ？, 求几种摆法? <br>
	 * 
	 * 解： 规定一行只填一个皇后 => 无需检查皇后共行了 <br>
	 * 
	 * A(x,y), B(d,q)-> y=q // 共列 , |d-x| = |q-y| // 共斜线
	 * 
	 * 
	 */
	public static int nQueens(int n) {
		return new DPCache().nQueens(n);
	}

	/**
	 * 贿赂怪兽问题：ability 怪兽能力，price 怪兽价格
	 */
	public static long bribeMonster(int[] ability, int[] price) {
		return new DPCache().bribeMonster(ability, price);
	}
	
	/**
	 * 一个数组，给每个数前加+ - 符号，要求和= sum 
	 * 
	 */
	
	public static int assembleTargetSum(int[] arr, int sum) {
		return new DPGrid().assembleTargetSum(arr, sum);
	}
	
	/**
	 * 二维数组，从任意点出发，求最长递增连的长度？
	 * 
	 */
	public static int longestIncreasingPath(int[][] matrix) {
		return new DPCache().longestIncreasingPath(matrix);
	}
	

}
