package com.algo.util.difference;

import com.algo.util.common.MatrixUtil;

/**
 * 
 * 
 * 2ά��֣���ά�����У����������²�����
 * ÿ���������޸�4���㣺�ã� a,b��-> (c,d) ͳһ��3����ô���ٴ��� ������������εõ�2ά������ÿ��λ�õ�ֵ��
 * 
 * 
 *  ���Ͻǣ�a,b��, ���½ǡ���c,d��2���� +3�� ���½ǡ������Ͻǡ���2���� -3
 *  
 *  0  0  0      0 0 0
 *  +3 0 -3      3 3 0
 *  0  0  0  =>  3 3 0
 *  -3 0 -3	     0 0 0 
 *  
 *  ��ʽ�� �� + �� - ���� + �Լ�
 *  
 *  ÿ���㶼����һ�£��ĳ�����matrix����2ά�������
 *  ��������ʱ�������ܰ���һȦ0�������˺ܶ�߽����� ������Ƿ�Χfor��i = 1��
 * 
 */
public class Difference2DUtil {
	
	/**
	 * 
	 * ��ά����Ʊ����
	 */
	public static boolean isFillStamp(int[][] matrix) {
		return false;
	}
	

	/**
	 * 
		�������diff:(a,b) ->  (c,d) ��+k, 
			ֻ��Ҫ��4�������һ�¼���
	 */
	static void add(int[][] diff, int a, int b, int c, int d, int k) {
		diff[a][b] += k;
		diff[c+1][b] -= k;
		diff[a][d+1] -= k;
		diff[c+1][d+1] += k;
	}
	
	/**
	 * 1. ����4��λ��add add add...
	   2. ���buildһ��
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
