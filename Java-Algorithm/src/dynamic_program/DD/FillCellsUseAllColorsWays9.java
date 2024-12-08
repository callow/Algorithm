package dynamic_program.DD;

import java.util.Arrays;

/**
 * ��ЧͿɫ���⣺ ����n��m����������һ����n�����ӣ�ÿ�����ӿ���Ϳ��һ����ɫ����ɫ��m����ѡ ��Ϳ��n�����ӣ�����m����ɫ��ʹ���ˣ���һ����Ч���� ��һ���ж�������Ч��Ϳɫ����
 * 
 * ����Ƚϴ��� % 1000000007 ֮�󷵻�
 */
public class FillCellsUseAllColorsWays9 {

	/**
	 * dp[i][j] = i�����Ӵ�j����ɫ�ķ�������(6�����Ӵ���4����ɫ)
	 * 
	 * dp[i][j]�����ԣ�
	 *  1. �����������ɫ��
	 *      ��dp[5][3]������3����ɫ��,Ҫ����5�� ��������3����ɫ����4��ʱ���ֵ*3����Ϊ�������ɫ���¸��ӿ�����3����ɫ����һ�� = dp[4][3] * 3
	 *  	dp[i-1][j] * j
	 * 
	 *  2. �������ɫ��
	 *      ��ȱһ����ɫû�ã���ô������Ҫ��ĸ��Ӿ�Ҫ����
	 * 		dp[i-1][j-1] * (m-(j-1))
	 */
	// �����ݹ�(��·����)��Ϊ����֤�Ķ�������·����path
	public static int ways1(int n, int m) {
		return f(new int[n], new boolean[m + 1], 0, n, m);
	}
	
	public static int f(int[] path, boolean[] set, int i, int n, int m) {
		if (i == n) {
			Arrays.fill(set, false);
			int colors = 0;
			for (int c : path) {
				if (!set[c]) {
					set[c] = true;
					colors++;
				}
			}
			return colors == m ? 1 : 0;
		} else {
			int ans = 0;
			for (int j = 1; j <= m; j++) {
				path[i] = j;
				ans += f(path, set, i + 1, n, m);
			}
			return ans;
		}
	}
	
	public static int MAXN = 5001;
	public static int[][] dp = new int[MAXN][MAXN];
	public static int mod = 1000000007;
	public static int ways2(int n, int m) {
		// dp[i][j]:
		// һ����m����ɫ
		// ǰi������Ϳ��j����ɫ�ķ�����
		for (int i = 1; i <= n; i++) {
			dp[i][1] = m;
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= m; j++) {
				dp[i][j] = (int) (((long) dp[i - 1][j] * j) % mod);
				dp[i][j] = (int) ((((long) dp[i - 1][j - 1] * (m - j + 1)) + dp[i][j]) % mod);
			}
		}
		return dp[n][m];
	}
	
	public static void main(String[] args) {
		// ���Ե��������Ƚ�С
		// ������Ϊ���������ˣ���������������
		// ��������������㹻˵����ʽ��������ȷ��
		int N = 9;
		int M = 9;
		System.out.println("���ܲ��Կ�ʼ");
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				int ans1 = ways1(n, m);
				int ans2 = ways2(n, m);
				if (ans1 != ans2) {
					System.out.println("������!");
				}
			}
		}
		System.out.println("���ܲ��Խ���");

		System.out.println("���ܲ��Կ�ʼ");
		int n = 5000;
		int m = 4877;
		System.out.println("n : " + n);
		System.out.println("m : " + m);
		long start = System.currentTimeMillis();
		int ans = ways2(n, m);
		long end = System.currentTimeMillis();
		System.out.println("ȡģ֮��Ľ�� : " + ans);
		System.out.println("����ʱ�� : " + (end - start) + " ����");
		System.out.println("���ܲ��Խ���");
	}
	
	
}
