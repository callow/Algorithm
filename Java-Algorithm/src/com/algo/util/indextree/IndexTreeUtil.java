package com.algo.util.indextree;

import com.algo.util.indextree.model.IndexTree2D;

/**
 * 使用前缀和数组，可以O（1）的到L ~ R的Σ。但是如果把数字改动，前缀和数组会大量更新。因此使用Segment Tree可以，但是IndexTree可以推到2维 3维，线段树不行。<br>
 * 
 * 但是只支持单点更新. help数组的组成是去前面找和自己Size一样的进行组合： <br>
 * 
 * orgin[] -> [1 2 3 4 5 6 7 8 9]
 * 
 * help[] -> [Σ1, Σ1&2, Σ3, Σ1&2&3&4, Σ5, Σ5&6, Σ7, Σ1~8, Σ9] <br>
 * 
 * help数组中的数管了哪些？ => help数组位置index二进制去掉末尾的1, 然后 + 1 ~ index自己 <br>
 * 100001100 -> 100001001 ~ 100001100
 * 
 * origin[] 从1 到index的前缀和？ => help数组中index位置不停的抹去末尾1直到0的和
 * 01110 -> 01110 + 01100 + 01000+ 00000
 *
 */
public class IndexTreeUtil {
	
	
	
	/**
	 * 求 二维Matrix中 (3,3) ~ (4,4) 围成的方形的累加和：<br>
	 * Index Tree 2D 默认是从1 ~ ...的累加和，那么如果矩形在中间 则：<br>
	 * 
	 * help[4,4] - help[4,2] - help[2,4] + help[2,2]
	 * 
	 */
	public static int sum(int[][] matrix) {
		IndexTree2D indexTree = new IndexTree2D(matrix);
		return indexTree.sumRegion(3, 3, 4, 4);
	}
}
