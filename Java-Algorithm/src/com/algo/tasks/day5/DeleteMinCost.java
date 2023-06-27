package com.algo.tasks.day5;
/**
 * 
 * �����ַ���str1��str2, str2����ɾ�����ַ����Ա��str1?
 * 
 * Note: ���׿�һ����Ƶ ��Ϊ�������滹��һ��ͬѧ�����Ž�
 *
 */
public class DeleteMinCost {
	
	/**
	 * 	ֻ����delete�ļ򻯱༭����⣬x = str2, y = str1, ��������ɾ�������ַ���
	 */
	public static int onlyDelete(char[] y, char[] x) {
		if (x.length < y.length) { // str2���� < str 1 , ��Զ���䲻��
			return Integer.MAX_VALUE;
		}
		int N = x.length;
		int M = y.length;
		int[][] dp = new int[N + 1][M + 1];
		// ���ȣ� Ĭ�ϳ�ʼ��ȫ��������
		for (int i = 0; i <= N; i++) { 
			for (int j = 0; j <= M; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;
		// dp[i][j]��ʾǰ׺����
		// ��0������ã�ȫ��str2ȫ��ɾ����ɿյ�str1: ""
		for (int i = 1; i <= N; i++) { 
			dp[i][0] = i;
		}
		
		// �ձ�λ��������dp[i][j]
		for (int xlen = 1; xlen <= N; xlen++) {
			for (int ylen = 1; ylen <= Math.min(M, xlen); ylen++) {
				if (dp[xlen - 1][ylen] != Integer.MAX_VALUE) { // ���x��ȥ���һ���ַ���ǰ��һ����һ����Ч��,����x[0~i-1]����ͨ��ɾ��ƴ��y
					dp[xlen][ylen] = dp[xlen - 1][ylen] + 1;
				}
				if (x[xlen - 1] == y[ylen - 1] // ����ַ����
						&& dp[xlen - 1][ylen - 1] != Integer.MAX_VALUE) {
					dp[xlen][ylen] = Math.min(dp[xlen][ylen], dp[xlen - 1][ylen - 1]);
				}
			}
		}
		return dp[N][M];
	}
}
