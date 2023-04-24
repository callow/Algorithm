package com.algo.util.dp;

import java.util.List;

public interface DPService {

	public void hanoi(int n);

	public List<String> subsequence(String n);

	public List<String> permutation(String n);

	public List<String> getAllBrackets(int n);

	public Integer uniquePaths(int m, int n);

	public Integer uniqueWays(int N, int start, int aim, int K);

	public Integer drawCardGame(int[] arr);

	public int knapsackMaxValue(int[] w, int[] v, int bag);

	public int convertNumToLetter(String str);

	public int minStickersToSpellWords(String[] stickers, String target);

	public int longestCommonSubsequence(String str1, String str2);

	public int longestPalindromeSubsequence(String input);

	public int horseJumpMethods(int a, int b, int k);

	public int minCoffeeTime(int[] arr, int n, int a, int b);

	public int minPathSum(int[][] matrix);

	public int coinWays(int[] coins, int target);

	public int coinWaysNoLimit(int[] coins, int target);

	public int coinWaysSameValue(int[] coins, int target);

	public double chessBoardSurvive(int row, int col, int k, int N, int M);

	public double killMonster(int N, int M, int K);
}
