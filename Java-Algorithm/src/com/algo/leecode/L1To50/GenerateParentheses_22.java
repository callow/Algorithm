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
     * @param curr: 当前组合结果
     * @param left : 左括号还剩几个
     * @param right： 右括号还剩几个
     */
    public static void generate(List<String> result, String curr, int left, int right) {
    	if(left == 0 && right == 0) { // 结束
    		result.add(curr);
    		return;
    	}
    	if (left > 0) { // 加左括号
    		generate(result, curr + "(", left - 1,right);
    	}
    	
    	if (right > left) { // 加右括号
    		generate(result, curr + ")", left,right -1);
    	}
    }
}
