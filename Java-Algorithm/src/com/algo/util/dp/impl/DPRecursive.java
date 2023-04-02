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
	private void func(int N, String from, String to, String other) {
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
	
	private static void g(char[] str, int index, List<String> ans) {
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

	@Override
	public Integer uniqueWays(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		return go(start, K, aim, N);
	}
	//(cur,rest)
	private static int go(int cur, int rest, int aim, int N) {
		if (rest == 0) { // 如果已经不需要走了，走完了！
			return cur == aim ? 1 : 0;
		}
		if (cur == 1) { // 1 -> 2 , 最左侧
			return go(2, rest - 1, aim, N);
		}
		if (cur == N) { // N-1 <- N， 最右侧
			return go(N - 1, rest - 1, aim, N);
		}
		return go(cur - 1, rest - 1, aim, N) + go(cur + 1, rest - 1, aim, N); //中间，既可以往左 又可以往右
	}

	@Override
	public Integer drawCardGame(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int first = f1(arr, 0, arr.length - 1);
		int second = g1(arr, 0, arr.length - 1);
		return Math.max(first, second);
	}
	
	// arr[L..R]，先手获得的最好分数返回
	public static int f1(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int p1 = arr[L] + g1(arr, L + 1, R);
		int p2 = arr[R] + g1(arr, L, R - 1);
		return Math.max(p1, p2);
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g1(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
		int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
		return Math.min(p1, p2);
	}

	@Override
	public int knapsackMaxValue(int[] w, int[] v, int bag) {
		if ( w == null || v == null || w.length == 0 || v.length != w.length) {
			return 0;
		}
		
		return process(w, v, 0, bag); // 尝试函数
	}
	// 从index往后自由选择， 返回最大的价值
	private int process(int[] w, int[] v, int index, int bag) {
		if (bag < 0) { 
			return -1; // 无效的货物选择
		}
		if(index == w.length) {
			return 0;
		}
		int no = process(w,v,index+1,bag); // 不要当前货
		
		int yes = 0;// 要当前货
		int check = process(w,v,index+1, bag-w[index]);  // 检查背包是否爆炸
		if (check != -1) {
			yes = v[index] + check;
		}
		
		return Math.max(no, yes);
		
	}


}
