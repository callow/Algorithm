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
}
