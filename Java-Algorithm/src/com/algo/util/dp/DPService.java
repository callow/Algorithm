package com.algo.util.dp;

import java.util.List;

public interface DPService {

	public void hanoi(int n); 
	public List<String> subsequence(String n);
	public List<String> permutation(String n);
	public List<String> getAllBrackets(int n);
	public Integer uniquePaths(int m, int n);
}
