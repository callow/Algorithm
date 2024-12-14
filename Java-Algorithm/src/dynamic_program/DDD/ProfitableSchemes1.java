package dynamic_program.DDD;
/**
 * 
 * ӯ���ƻ�(��ά���ñ���)�� �������� n ��Ա�������ǿ�����ɸ��ָ����Ĺ�����������. �� i �ֹ�������� profit[i] ��������Ҫ�� group[i] ����Ա��ͬ����.
 *  �����Ա����������һ������Ͳ��ܲ�����һ����� ����>=minProfit, ��������Ϊn���ж����ּƻ���Ͽ���ѡ��
 *  
 *  https://leetcode.cn/problems/profitable-schemes/
 */
public class ProfitableSchemes1 {
	
	// n : Ա�������������ܳ�
	// p : ������󣬲�����
	// group[i] : i����Ŀ��Ҫ������
	// profit[i] : i����Ŀ����������
	// ����������Ա�����ܳ���n������������p�ļƻ��ж��ٸ�
	public static int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
		return f1(group, profit, 0, n, minProfit);
	}
	
	// i : ����i�Ź���
	// r : Ա����Ȼ���r�ˣ����r<=0˵���Ѿ�û����ѡ������ => ʣ������
	// s : ������s���ܴ�꣬���s<=0˵��֮ǰ��ѡ���Ѿ����������� => ʣ������
	// ���� : i.... r��s���ж����ַ���
	public static int f1(int[] g, int[] p, int i, int r, int s) {
		if (r <= 0) {
			// ���Ѿ��ľ��ˣ�֮ǰ����ѡ��һЩ����
			return s <= 0 ? 1 : 0;
		}
		// r > 0
		if (i == g.length) {
			// �����ľ��ˣ�֮ǰ����ѡ��һЩ����
			return s <= 0 ? 1 : 0;
		}
		// ��Ҫ��ǰ����
		int p1 = f1(g, p, i + 1, r, s);
		// Ҫ����ǰ����
		int p2 = 0;
		if (g[i] <= r) {
			p2 = f1(g, p, i + 1, r - g[i], s - p[i]);
		}
		return p1 + p2;
	}
	
	// ------------------------------------------------------
	
	// �����ɱ�������һ�����仯����
	public static int mod = 1000000007;

	public static int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
		int m = group.length;
		int[][][] dp = new int[m][n + 1][minProfit + 1];
		for (int a = 0; a < m; a++) {
			for (int b = 0; b <= n; b++) {
				for (int c = 0; c <= minProfit; c++) {
					dp[a][b][c] = -1;
				}
			}
		}
		return f2(group, profit, 0, n, minProfit, dp);
	}

	public static int f2(int[] g, int[] p, int i, int r, int s, int[][][] dp) {
		if (r <= 0) {
			return s == 0 ? 1 : 0;
		}
		if (i == g.length) {
			return s == 0 ? 1 : 0;
		}
		if (dp[i][r][s] != -1) {
			return dp[i][r][s];
		}
		int p1 = f2(g, p, i + 1, r, s, dp);
		int p2 = 0;
		if (g[i] <= r) {
			p2 = f2(g, p, i + 1, r - g[i], Math.max(0, s - p[i]), dp);
		}
		int ans = (p1 + p2) % mod;
		dp[i][r][s] = ans;
		return ans;
	}
	
	// --------------------------------------------------------
	// �ռ�ѹ��
	public static int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
		// i = û�й�����ʱ��i == g.length
		int[][] dp = new int[n + 1][minProfit + 1];
		for (int r = 0; r <= n; r++) {
			dp[r][0] = 1;
		}
		int m = group.length;
		for (int i = m - 1; i >= 0; i--) {
			for (int r = n; r >= 0; r--) {
				for (int s = minProfit; s >= 0; s--) {
					int p1 = dp[r][s];
					int p2 = group[i] <= r ? dp[r - group[i]][Math.max(0, s - profit[i])] : 0;
					dp[r][s] = (p1 + p2) % mod;
				}
			}
		}
		return dp[n][minProfit];
	}
	

}
