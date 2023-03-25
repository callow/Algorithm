package com.algo.util.dp.impl;

import java.util.ArrayList;
import java.util.List;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.dp.DPService;

/**
 * 
 * 递归 = 抽象化
 */
public class DPRecursive implements DPService {

	@Override 
	public void hanoi(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}
	
	/**
	 * 总目标： 1 ~ N  在 from 去 to , 另一个 other
	 * 1        
	 * 2
	 * N
	 * ---      ---    ---
	 * from      to    other
	 */
	public void func(int N, String from, String to, String other) {
		if (N == 1) { // base case
			System.out.println("Move 1 from " + from + " to " + to);
		} else {
			func(N - 1, from, other, to); // 1 ~ (n - 1)层圆盘从from 去 other, to 是另外一个
			System.out.println("Move " + N + " from " + from + " to " + to); // 单独的n 从 from 移动到 to 打印
			func(N - 1, other, to, from); // 1 ~ (n - 1)层圆盘从other 挪回 to 完事
		}
	}

	
	@Override
	public List<String> subsequence(String s) {
		char[] str = s.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process(str, 0, ans, path);
		return ans;
	}

	// str 固定参数
	// 来到了str[index]字符，index是位置
	// str[0..index-1]已经走过了！之前的决定，都在path上
	// 之前的决定已经不能改变了，就是path
	// str[index....]还能决定，之前已经确定，而后面还能自由选择的话，把所有生成的子序列，放入到ans里去
	public static void process(char[] str, int index, List<String> ans, String path) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		// 没有要index位置的字符 = path没变
		process(str, index + 1, ans, path);
		// 要了index位置的字符 = path 加了此时的字符
		process(str, index + 1, ans, path + String.valueOf(str[index]));
	}

	@Override
	public List<String> permutation(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g(str, 0, ans);
		return ans;
	}
	
	public static void g(char[] str, int index, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
			boolean[] visited = new boolean[256]; // assci 码最多255个
			for (int i = index; i < str.length; i++) { // i跟index位置的字符要交换
				if (!visited[str[i]]) { // 去重，注释掉就是不去重
					visited[str[i]] = true;
					CommonArrayUtil.swap(str, index, i);
					g(str, index + 1, ans); // 去跑所有支路
					CommonArrayUtil.swap(str, index, i); // 跑下一个前 恢复现场
				}
			}
		}
	}

	@Override
	public List<String> getAllBrackets(int n) {
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

	@Override
	public Integer uniquePaths(int row, int col) {
		if (row == 1 || col == 1) {
		      return 1;
		}
		return uniquePaths(row - 1, col) + uniquePaths(row, col - 1); // move down + move right
	}

}
