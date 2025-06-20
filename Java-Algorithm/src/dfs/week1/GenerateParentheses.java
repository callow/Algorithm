package dfs.week1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 类型： 带路径递归 需要剪枝，左右括号平衡, 回溯
 */
public class GenerateParentheses {

	public static void main(String[] args) {
		
		int n = 3; // 3对括号 = 3个左 + 3个右
		List<String> result = generateParenthesis(n);
		System.out.println(result);
	}
	
	 public static List<String> generateParenthesis(int n) {
		 Deque<String> path = new ArrayDeque<>();
	     dfs(n, path, 0 , 0);
	     return result;
	 }
	 
	 /**
	  *  继续： left > right, 否则就继续+left (
	  *  终止条件： left = right = 3
	  */
	 static List<String> result = new ArrayList<>();
	 public static void dfs(int n, Deque<String> cur, int leftNo, int rightNo) {
		 if (leftNo == n && rightNo == n) {
			 result.add(String.join("", cur));
		 }
		 if (leftNo < n) {
			 cur.add("("); // 尝试+ (
			 dfs(n, cur, leftNo + 1, rightNo);
			 cur.removeLast();
		 }
		 
		 if (leftNo > rightNo) {
			 cur.add(")"); // 尝试+ )
			 dfs(n, cur, leftNo, rightNo + 1);
			 cur.removeLast();
		 }
	 }
}
