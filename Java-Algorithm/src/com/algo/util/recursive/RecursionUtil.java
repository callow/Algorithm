package com.algo.util.recursive;

import java.util.List;

import com.algo.util.recursive.model.Combinations;
import com.algo.util.recursive.model.FullSubsequencePermutation;

/**
 * ���еݹ� = DFS
 * 
 *  - ��·���ݹ�
 *  - ����·���ݹ� = dp
 */
public class RecursionUtil {

	/**
		�����ַ�����ȫ��������-ȥ��
			- ÿ��λ��Ҫ/��Ҫ�� ��Ҫ������·Ҫ�ָ��ֳ�
				a
			/Ҫc   \��Ҫc
		   ac	   a
	 */
	public String[] getSubsequence(String s) {
		return FullSubsequencePermutation.permutation(s);
				
	}
	
	/**
	 * �����������е���ϣ� nums = [1,2,2] => ��� [[],[1],[1,2],[1,2,2],[2],[2,2]]
	 * 
	 */
	public List<List<Integer>> getAllCombinations(int[] nums){
		return Combinations.subsetsWithDup(nums);
	}
	
	
	
	
	
}
