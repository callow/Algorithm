package com.algo.util.dp.impl;

import java.util.ArrayList;
import java.util.List;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.dp.DPService;

/**
 * 
 * �ݹ� = ����
 */
public class DPRecursive implements DPService {

	@Override 
	public void hanoi(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}
	
	/**
	 * ��Ŀ�꣺ 1 ~ N  �� from ȥ to , ��һ�� other
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
			func(N - 1, from, other, to); // 1 ~ (n - 1)��Բ�̴�from ȥ other, to ������һ��
			System.out.println("Move " + N + " from " + from + " to " + to); // ������n �� from �ƶ��� to ��ӡ
			func(N - 1, other, to, from); // 1 ~ (n - 1)��Բ�̴�other Ų�� to ����
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

	// str �̶�����
	// ������str[index]�ַ���index��λ��
	// str[0..index-1]�Ѿ��߹��ˣ�֮ǰ�ľ���������path��
	// ֮ǰ�ľ����Ѿ����ܸı��ˣ�����path
	// str[index....]���ܾ�����֮ǰ�Ѿ�ȷ���������滹������ѡ��Ļ������������ɵ������У����뵽ans��ȥ
	public static void process(char[] str, int index, List<String> ans, String path) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		// û��Ҫindexλ�õ��ַ� = pathû��
		process(str, index + 1, ans, path);
		// Ҫ��indexλ�õ��ַ� = path ���˴�ʱ���ַ�
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
			boolean[] visited = new boolean[256]; // assci �����255��
			for (int i = index; i < str.length; i++) { // i��indexλ�õ��ַ�Ҫ����
				if (!visited[str[i]]) { // ȥ�أ�ע�͵����ǲ�ȥ��
					visited[str[i]] = true;
					CommonArrayUtil.swap(str, index, i);
					g(str, index + 1, ans); // ȥ������֧·
					CommonArrayUtil.swap(str, index, i); // ����һ��ǰ �ָ��ֳ�
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
		if (rest == 0) { // ����Ѿ�����Ҫ���ˣ������ˣ�
			return cur == aim ? 1 : 0;
		}
		if (cur == 1) { // 1 -> 2 , �����
			return go(2, rest - 1, aim, N);
		}
		if (cur == N) { // N-1 <- N�� ���Ҳ�
			return go(N - 1, rest - 1, aim, N);
		}
		return go(cur - 1, rest - 1, aim, N) + go(cur + 1, rest - 1, aim, N); //�м䣬�ȿ������� �ֿ�������
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
	
	// arr[L..R]�����ֻ�õ���÷�������
	public static int f1(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int p1 = arr[L] + g1(arr, L + 1, R);
		int p2 = arr[R] + g1(arr, L, R - 1);
		return Math.max(p1, p2);
	}

	// // arr[L..R]�����ֻ�õ���÷�������
	public static int g1(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int p1 = f1(arr, L + 1, R); // ����������Lλ�õ���
		int p2 = f1(arr, L, R - 1); // ����������Rλ�õ���
		return Math.min(p1, p2);
	}

	@Override
	public int knapsackMaxValue(int[] w, int[] v, int bag) {
		if ( w == null || v == null || w.length == 0 || v.length != w.length) {
			return 0;
		}
		
		return process(w, v, 0, bag); // ���Ժ���
	}
	// ��index��������ѡ�� �������ļ�ֵ
	private int process(int[] w, int[] v, int index, int bag) {
		if (bag < 0) { 
			return -1; // ��Ч�Ļ���ѡ��
		}
		if(index == w.length) {
			return 0;
		}
		int no = process(w,v,index+1,bag); // ��Ҫ��ǰ��
		
		int yes = 0;// Ҫ��ǰ��
		int check = process(w,v,index+1, bag-w[index]);  // ��鱳���Ƿ�ը
		if (check != -1) {
			yes = v[index] + check;
		}
		
		return Math.max(no, yes);
		
	}


}
