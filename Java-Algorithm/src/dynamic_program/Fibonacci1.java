package dynamic_program;
/**
 * F��i�� = F(i -1) + F(i-2)
 * 0 1 1 2 3 5 8
 * 
 * ��쳲��������е�n�
 * 
 * �ݹ麬�壺 iλ�õ�����
 */
public class Fibonacci1 {
	
	// O(2^n) �޼��仯����
	public static int f(int i) {
		if (i == 0) {
			return 0;
		}
		if(i == 1) {
			return 1;
		}
		return f(i - 1) + f(i - 2);
	}
	
	// O(n) ���仯���� = �Ӷ����׵ġ��Ӻ���ǰ��
	public static int f2(int i, int[] dp) {
		if (i == 0) {
			return 0;
		}
		if(i == 1) {
			return 1;
		}
		
		if(dp[i] != -1) {
			return dp[i];
		}
		int ans =  f2(i - 1, dp) + f2(i - 2, dp);
		dp[i] = ans;
		return ans;
	}
	
	// O(n) �ϸ�λ�������Ķ�̬�滮 = ��λ�����㣬���Ӻ��� = �ӵ׵����� = ��ǰ�����
	public static int f3(int n) {
		if (n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[1] = 1; // ��dp��
		for(int i  = 2; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		return dp[n];
	}
	
	// O(n) �������� �ռ��Ż�
	public static int f4(int n) {
		if (n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		int lastlast = 0, last = 1;
		for(int i = 2, cur; i <= n; i++) {
			cur = lastlast + last;
			lastlast = last;
			last = cur;
		}
		
		return last;
	}
}
