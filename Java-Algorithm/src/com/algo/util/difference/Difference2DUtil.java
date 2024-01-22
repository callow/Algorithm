package com.algo.util.difference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/50e1a93989df42efb0b1dec386fb4ccc
 * 
 * 2维拆分：二维数组中，批量做如下操作：
 * 每个操作都修改4个点：让（ a,b）-> (c,d) 统一加3，怎么快速处理？ 批量操作后，如何得到2维数组中每个位置的值？
 * 
 * 
 *  左上角（a,b）, 右下角‘（c,d）2个点 +3， 左下角’，右上角‘，2个点 -3
 *  
 *  0  0  0      0 0 0
 *  +3 0 -3      3 3 0
 *  0  0  0  =>  3 3 0
 *  -3 0 -3	     0 0 0 
 *  
 *  公式： 左 + 上 - 左上 + 自己
 *  
 *  每个点都操作一下，的出来都matrix就是2维拆分数组
 *  正常做的时候在四周包裹一圈0，避免了很多边界讨论
 * 
 */
public class Difference2DUtil {
	
	public static int MAXN = 1005;

	public static int MAXM = 1005;

	public static long[][] diff = new long[MAXN][MAXM];

	public static int n, m, q;

	public static void add(int a, int b, int c, int d, int k) {
		diff[a][b] += k;
		diff[c + 1][b] -= k;
		diff[a][d + 1] -= k;
		diff[c + 1][d + 1] += k;
	}

	public static void build() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
			}
		}
	}

	public static void clear() {
		for (int i = 1; i <= n + 1; i++) {
			for (int j = 1; j <= m + 1; j++) {
				diff[i][j] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			in.nextToken();
			q = (int) in.nval;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					in.nextToken();
					add(i, j, i, j, (int) in.nval);
				}
			}
			for (int i = 1, a, b, c, d, k; i <= q; i++) {
				in.nextToken();
				a = (int) in.nval;
				in.nextToken();
				b = (int) in.nval;
				in.nextToken();
				c = (int) in.nval;
				in.nextToken();
				d = (int) in.nval;
				in.nextToken();
				k = (int) in.nval;
				add(a, b, c, d, k);
			}
			build();
			for (int i = 1; i <= n; i++) {
				out.print(diff[i][1]);
				for (int j = 2; j <= m; j++) {
					out.print(" " + diff[i][j]);
				}
				out.println();
			}
			clear();
		}
		out.flush();
		out.close();
		br.close();
	}

}
