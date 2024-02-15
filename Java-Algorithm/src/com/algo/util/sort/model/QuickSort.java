package com.algo.util.sort.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/3385982ae71d4a1ca8bf3d03614c0325
 */
public class QuickSort {

	
	public static int MAXN = 1001;

	public static int[] arr = new int[MAXN];

	public static int n;

    public static int first, // = x区域的左边界
    	last; // = x区域的右边界


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            quickSort(0, n - 1); // 荷兰国旗问题
            out.print(arr[0]);
            for (int i = 1; i < n; i++) {
                out.print(" " + arr[i]);
            }
            out.println();
        }
        out.flush();
        out.close();
    }

    	public static void quickSort(int l, int r) {
            if (l >= r) {
                return;
            }
            int x = arr[l + (int) (Math.random() * (r - l + 1))];
            partition(l, r, x);
            // 为了防止底层的递归过程覆盖全局变量 这里用临时变量记录first、last
            int left = first;
            int right = last;
            quickSort(l, left - 1);
            quickSort(right + 1, r);
	}

    //荷兰国旗问题
    public static void partition(int l, int r, int x) {
            first = l;
            last = r;
            int i = l;
            while (i <= last) {
                if (arr[i] == x) {
                    i++;
                } else if (arr[i] < x) {
                    swap(first, i);
                    first++;
                    i++;
                } else {
                    swap(i, last);
                    last--;
                }
            }
	}
    public static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
