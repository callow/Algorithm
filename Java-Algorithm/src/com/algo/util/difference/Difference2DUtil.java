package com.algo.util.difference;

import com.algo.util.common.MatrixUtil;

/**
 * 
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
 *  正常做的时候在四周包裹一圈0，避免了很多边界讨论 因此真是范围for（i = 1）
 * 
 */
public class Difference2DUtil {
	
	/**
	 * 
	 * 二维贴邮票问题
	 */
	public static boolean isFillStamp(int[][] matrix) {
		return false;
	}
	

	/**
	 * 
		差分数组diff:(a,b) ->  (c,d) 都+k, 
			只需要在4个点操作一下即可
	 */
	static void add(int[][] diff, int a, int b, int c, int d, int k) {
		diff[a][b] += k;
		diff[c+1][b] -= k;
		diff[a][d+1] -= k;
		diff[c+1][d+1] += k;
	}
	
	/**
	 * 1. 首先4个位置add add add...
	   2. 最后build一下
	 */
	public static int[][] build(int n, int m, int[][] g) {
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				g[i][j] += MatrixUtil.get(g, i, j - 1) + MatrixUtil.get(g, i - 1, j) - MatrixUtil.get(g, i - 1, j - 1);
			}
		}
		return g;
	}
	
	
	
	
}
