package com.algo.util.difference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

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
 *  正常做的时候在四周包裹一圈0，避免了很多边界讨论
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
	
}
