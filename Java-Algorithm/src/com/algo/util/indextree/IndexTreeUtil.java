package com.algo.util.indextree;

import com.algo.util.indextree.model.IndexTree2D;

/**
 * ʹ��ǰ׺�����飬����O��1���ĵ�L ~ R�Ħ���������������ָĶ���ǰ׺�������������¡����ʹ��Segment Tree���ԣ�����IndexTree�����Ƶ�2ά 3ά���߶������С�<br>
 * 
 * ����ֻ֧�ֵ������. help����������ȥǰ���Һ��Լ�Sizeһ���Ľ�����ϣ� <br>
 * 
 * orgin[] -> [1 2 3 4 5 6 7 8 9]
 * 
 * help[] -> [��1, ��1&2, ��3, ��1&2&3&4, ��5, ��5&6, ��7, ��1~8, ��9] <br>
 * 
 * help�����е���������Щ�� => help����λ��index������ȥ��ĩβ��1, Ȼ�� + 1 ~ index�Լ� <br>
 * 100001100 -> 100001001 ~ 100001100
 * 
 * origin[] ��1 ��index��ǰ׺�ͣ� => help������indexλ�ò�ͣ��Ĩȥĩβ1ֱ��0�ĺ�
 * 01110 -> 01110 + 01100 + 01000+ 00000
 *
 */
public class IndexTreeUtil {
	
	
	
	/**
	 * �� ��άMatrix�� (3,3) ~ (4,4) Χ�ɵķ��ε��ۼӺͣ�<br>
	 * Index Tree 2D Ĭ���Ǵ�1 ~ ...���ۼӺͣ���ô����������м� ��<br>
	 * 
	 * help[4,4] - help[4,2] - help[2,4] + help[2,2]
	 * 
	 */
	public static int sum(int[][] matrix) {
		IndexTree2D indexTree = new IndexTree2D(matrix);
		return indexTree.sumRegion(3, 3, 4, 4);
	}
}
