package com.algo.util.recursive;

import java.util.List;

import com.algo.util.recursive.model.Combinations;
import com.algo.util.recursive.model.FullSubsequencePermutation;

/**
 * 所有递归 = DFS
 * 
 *  - 带路径递归
 *  - 不带路径递归 = dp
 */
public class RecursionUtil {

	/**
		返回字符串的全部子序列-去重
			- 每个位置要/不要， 不要的那条路要恢复现场
				a
			/要c   \不要c
		   ac	   a
	 */
	public String[] getSubsequence(String s) {
		return FullSubsequencePermutation.permutation(s);
				
	}
	
	/**
	 * 返回数组所有的组合： nums = [1,2,2] => 输出 [[],[1],[1,2],[1,2,2],[2],[2,2]]
	 * 
	 */
	public List<List<Integer>> getAllCombinations(int[] nums){
		return Combinations.subsetsWithDup(nums);
	}
	
	
	
	
	
}
