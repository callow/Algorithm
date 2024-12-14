package dynamic_program.DDD;

/**
 * һ����(��ά���ñ���:0/1����):����һ���������ַ������� strs("0011"��"001","01"...) ���������� m �� n �ҳ������� strs ������Ӽ��ĳ���(���Ӽ��� ��� �� m �� 0 �� n �� 1)
 * 0/1���� �� Ҫ/��Ҫ
 * �������⣺ ��n��Լ�����õ����⣬������2������Լ������.
 * 
 *  https://leetcode.cn/problems/ones-and-zeroes/
 */
public class OneAndZero {

	public static int zeros, ones;

	// ͳ��һ���ַ�����0��1������
	// 0��������ֵ��ȫ�ֱ���zeros
	// 1��������ֵ��ȫ�ֱ���ones
	public static void zerosAndOnes(String str) {
		zeros = 0;
		ones = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') {
				zeros++;
			} else {
				ones++;
			}
		}
	}
	
	public static int findMaxForm1(String[] strs, int m, int n) {
		return f1(strs, 0, m, n);
	}
	
	// strs[i....]����ѡ��ϣ���������������z��һ������������o
	// �����ݹ� �������ѡ���ٸ��ַ���
	public static int f1(String[] strs, int i, int z, int o) {
		if (i == strs.length) {
			// û���ַ�����
			return 0;
		}
		// ��ʹ�õ�ǰ��strs[i]�ַ���
		int p1 = f1(strs, i + 1, z, o);
		// ʹ�õ�ǰ��strs[i]�ַ���
		int p2 = 0;
		zerosAndOnes(strs[i]);
		if (zeros <= z && ones <= o) { // ��Ȼ��0/1���Կۣ���ֱ�ӿ۳�
			p2 = 1 + f1(strs, i + 1, z - zeros, o - ones);
		}
		return Math.max(p1, p2);
	}
	
	//---------------------------------------------------------------------
	
	// ���仯����
	public static int findMaxForm2(String[] strs, int m, int n) {
		int[][][] dp = new int[strs.length][m + 1][n + 1];
		for (int i = 0; i < strs.length; i++) {
			for (int z = 0; z <= m; z++) {
				for (int o = 0; o <= n; o++) {
					dp[i][z][o] = -1;
				}
			}
		}
		return f2(strs, 0, m, n, dp);
	}

	public static int f2(String[] strs, int i, int z, int o, int[][][] dp) {
		if (i == strs.length) {
			return 0;
		}
		if (dp[i][z][o] != -1) {
			return dp[i][z][o];
		}
		int p1 = f2(strs, i + 1, z, o, dp);
		int p2 = 0;
		zerosAndOnes(strs[i]);
		if (zeros <= z && ones <= o) {
			p2 = 1 + f2(strs, i + 1, z - zeros, o - ones, dp);
		}
		int ans = Math.max(p1, p2);
		dp[i][z][o] = ans;
		return ans;
	}
	
	//--------------------------------------------------------------
	// �ϸ�λ������
	public static int findMaxForm3(String[] strs, int m, int n) {
		int len = strs.length;
		int[][][] dp = new int[len + 1][m + 1][n + 1];
		for (int i = len - 1; i >= 0; i--) {
			zerosAndOnes(strs[i]);
			for (int z = 0, p1, p2; z <= m; z++) {
				for (int o = 0; o <= n; o++) {
					p1 = dp[i + 1][z][o];
					p2 = 0;
					if (zeros <= z && ones <= o) {
						p2 = 1 + dp[i + 1][z - zeros][o - ones];
					}
					dp[i][z][o] = Math.max(p1, p2);
				}
			}
		}
		return dp[0][m][n];
	}
	
	//-----------------------------------------------------------------
	
	//�ռ�ѹ���� ����
	public static int findMaxForm4(String[] strs, int m, int n) {
		// ����i == len
		int[][] dp = new int[m + 1][n + 1];
		for (String s : strs) {
			// ÿ���ַ����𽥱�������
			// ����ÿһ��ı�
			// ��֮ǰ�ı���û������
			zerosAndOnes(s);
			for (int z = m; z >= zeros; z--) {
				for (int o = n; o >= ones; o--) {
					dp[z][o] = Math.max(dp[z][o], 1 + dp[z - zeros][o - ones]);
				}
			}
		}
		return dp[m][n];
	}

	
}
