package com.algo.leecode.L1To50;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
   Input: n = 3
   ["((()))","(()())","(())()","()(())","()()()"]
 *
 */
public class GenerateParentheses_22 {
	
	public static void main(String[] args) {
		System.out.println(generateParenthesis(2));
	}
	
	
    public static final List<String> generateParenthesis(int n) {
    	List<String> result = new ArrayList<>();
    	generate(result,"", n, n);
        return result;
    }
    
    /**
     * 
     * @param curr: ��ǰ��Ͻ��
     * @param left : �����Ż�ʣ����
     * @param right�� �����Ż�ʣ����
     */
    public static void generate(List<String> result, String curr, int left, int right) {
    	if(left == 0 && right == 0) { // ����
    		result.add(curr);
    		return;
    	}
    	if (left > 0) { // ��������
    		generate(result, curr + "(", left - 1,right);
    	}
    	
    	if (right > left) { // ��������
    		generate(result, curr + ")", left,right -1);
    	}
    }
}
