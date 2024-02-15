package com.algo.util.sort.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 
 * 给你一个n代表有n个数字，然后你需要使用归并排序将这些数字从小到大排好。
 * 
 *  https://www.nowcoder.com/practice/bc25055fb97e4a0bb564cb4b214ffa92
 */

public class MergeSort {
	
	static int ESTIMATE= 501;
	
	public static int n;
	public static int[] arr = new int[ESTIMATE];
	public static int[] help = new int[ESTIMATE];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval; // 告诉你又n个数
			for (int i = 0; i < n; i++) { // 所有数都先收进来，无序的
				in.nextToken();
				arr[i] = (int) in.nval;
			}
			// mergeSort1为递归方法
			// mergeSort2为非递归方法
			// 用哪个都可以
			// mergeSort1(0, n - 1);
			mergeSort2();
			out.print(arr[0]);
			for (int i = 1; i < n; i++) {
				out.print(" " + arr[i]);
			}
			out.println();
		}
		out.flush();
		out.close();
	}
	
	// 定义变量步长，步长每次翻1倍：1 2 4 8 ..., 左侧和右侧每次拿步长个数
	static void mergeSort2() {
		
		// 步长为1，一组一组的去merge 
		// 步长为2，一组一组的去merge
		// 步长为4，一组一组的去merge
		
		for (int l, mid, r, step = 1; step < n; step *= 2) {
			l = 0;
			
			// 内部分组merge O(N)
			while (l < n) {
				mid = l + step - 1;
				
				// 没有右侧，只有左
				if (mid + 1 >= n) {
					break;
				}
				
				//有右侧，r位置有可能能凑够则位置是：l+2s -1, 有可能凑不够，则就用n - 1
				r = Math.min(l + (step * 2) - 1, n - 1);
				
				//             l..m | m+1...r
				//                           l...m | m+1...r
				//                                          l...m | m+1...r
				merge(l, mid, r);
				l = r + 1;
			}
		}
	}
	
	
// --------------------------------------------------------------------------------------------
	static void mergeSort1(int l, int r) {
		if (l == r) {
			return;
		}
		int mid = (l + r)  / 2;
		mergeSort1(0,mid);
		mergeSort1(mid + 1, r);
		merge(l,mid,r);
	}
	
	// 左右两侧都有序的前提下去merge: 使用辅助数组，2个指针（a,b），谁小拷贝谁
	static void merge(int l, int mid, int r) {
		int i = l;
		int a = l;
		int b = mid+1;
		
		while(a <= mid && b <= r) {
			if (arr[a] <= arr[b]) {
				help[i] = arr[a];
				a++;
				i++;
			} else {
				help[i] = arr[b];
				b++;
				i++;
			}
		}
		
		while (a <= mid) {
			help[i] = arr[a];
			a++;
			i++;
		}
		while (b <= r) {
			help[i] = arr[b];
			i++;
			b++;
		}
		// 拷贝回原数组
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
	}
	
	
	public static void print(int[] arr, int l, int m, int r) {
		StringBuilder builderLeft = new StringBuilder();
		for(int i = l; i < m; i++) {
			builderLeft.append(arr[i]).append(" ");
		}
		StringBuilder builderRight = new StringBuilder();
		for(int i = m+1; i < r; i++) {
			builderRight.append(arr[i]).append(" ");
		}
		System.out.println("左： " + builderLeft + " 右： " + builderRight);
	}
	
	
}
