package com.algo.util.PrefixSum;

import com.algo.util.PrefixSum.model.MatrixSumArray;

/**
 * ��ά�����ǰ׺������Sum (MatrixSum)��  ����+�ϣ� - ���� + �Լ�  // �ݳ�ԭ�� = �������һ���ټ�ȥ
 * 
 * ����a,b������c,d��������ε��ۼӺ� ��ʽ���ǣ�
 * 
 * 	sum[c][d] - sum[c][b-1] - sum[a-1][d] + sum[a-1][b-1]
 * 
 * O(1)
 */
public class PrefixSum2DUtil {
	
	/**
	 * ��2ά�����ĳ�����κͣ�https://leetcode.cn/problems/range-sum-query-2d-immutable/
	 */
	public static int sumRegion(int[][] matrix, int a, int b, int c, int d) {
		MatrixSumArray prefixSum = new MatrixSumArray(matrix);
		return prefixSum.sumRegion(a, b, c, d);
	}
	
	/**
	 * ��0/1�����У�Ѱ�ұ߿�Ϊ1����������� https://leetcode.cn/problems/largest-1-bordered-square/
	 * 
	 * ��֤�߿�ȫ1��˼·��
	 * 	����a,b��-> (c,d) ���ۼӺ�Sum1
	 *  ���� (e,f��-> (g,h) ������������ۺ�Sum2
	 *  ��һ���Ƿ� Sum1 - Sum2 = ��a,b��-> (c,d) ���ܳ�?
	 *  
	 *  �ܳ��㷨�� ��k=4������һ��4*4�������Σ��ܳ� = (k-1) * 4 = 12 or  (k - 1) << 2
	 *  
	 * ö�����������Σ� O(N * M * MIN(N,M))
	 */
	public static int largest1BorderedSquare(int[][] g) {
		int n = g.length;
		int m = g[0].length;
		MatrixSumArray.build(n, m, g); // ����ǰ׺�;���
		if (MatrixSumArray.sum(g, 0, 0, n - 1, m - 1) == 0) { // ������û��1��û��1ֱ�ӷ���0
			return 0;
		}
		// �ҵ������Ϸ������εı߳�
		int ans = 1;
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < m; b++) {
				// ö������(a,b)�������Ͻǵ�
				//     ö������(c,d)����߳������½ǵ㣬k�ǵ�ǰ���Եı߳�
				// e.g�� ��5��7�� ��2 * 2�� ���½� ��6��8��
				// k �Ǳ߳�
				for (int c = a + ans, d = b + ans, k = ans + 1; c < n && d < m; c++, d++, k++) {
					if (MatrixSumArray.sum(g, a, b, c, d) // ����������
							- MatrixSumArray.sum(g, a + 1, b + 1, c - 1, d - 1) //�ڲ�������
							== (k - 1) << 2) { // �ܳ�
						ans = k;
					}
				}
			}
		}
		return ans * ans; // �߳� * �߳� = ���
	}
	
	
}
