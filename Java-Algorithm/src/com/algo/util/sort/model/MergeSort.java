package com.algo.util.sort.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 
 * ����һ��n������n�����֣�Ȼ������Ҫʹ�ù鲢������Щ���ִ�С�����źá�
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
			n = (int) in.nval; // ��������n����
			for (int i = 0; i < n; i++) { // �����������ս����������
				in.nextToken();
				arr[i] = (int) in.nval;
			}
			// mergeSort1Ϊ�ݹ鷽��
			// mergeSort2Ϊ�ǵݹ鷽��
			// ���ĸ�������
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
	
	// �����������������ÿ�η�1����1 2 4 8 ..., �����Ҳ�ÿ���ò�������
	static void mergeSort2() {
		
		// ����Ϊ1��һ��һ���ȥmerge 
		// ����Ϊ2��һ��һ���ȥmerge
		// ����Ϊ4��һ��һ���ȥmerge
		
		for (int l, mid, r, step = 1; step < n; step *= 2) {
			l = 0;
			
			// �ڲ�����merge O(N)
			while (l < n) {
				mid = l + step - 1;
				
				// û���Ҳֻ࣬����
				if (mid + 1 >= n) {
					break;
				}
				
				//���Ҳ࣬rλ���п����ܴչ���λ���ǣ�l+2s -1, �п��ܴղ����������n - 1
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
	
	// �������඼�����ǰ����ȥmerge: ʹ�ø������飬2��ָ�루a,b����˭С����˭
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
		// ������ԭ����
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
		System.out.println("�� " + builderLeft + " �ң� " + builderRight);
	}
	
	
}
