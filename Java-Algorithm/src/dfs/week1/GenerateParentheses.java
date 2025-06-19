package dfs.week1;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型： 带路径递归 需要剪枝，左右括号平衡
 */
public class GenerateParentheses {

	public static void main(String[] args) {
		
		int n = 3; // 3对括号 = 3个左 + 3个右
		List<String> result = generateParenthesis(n);
		System.out.println(result);
	}
	
	 public static List<String> generateParenthesis(int n) {
		 List<String> result = new ArrayList<>();
	     dfs(n, result);
	     return result;
	 }
	 
	 public static void dfs(int n, List<String> result) {
		 
	 }
}
