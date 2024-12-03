package dynamic_program.D;
/**
 * 
 * ����һ��ֻ���� '(' �� ')' ���ַ��� �ҳ����Ч����ʽ��ȷ�������������Ӵ��ĳ���
 * 
 * https://leetcode.cn/problems/longest-valid-parentheses/
 * 
 * ֱ�������ݹ飬ֱ�Ӵ������ҵ��ƣ�DP[i]�����ִ�һ��Ҫ��iλ�ý�βʱ��������೤
 *  -> (  ֱ���� 0 ��dp[i] = 0
 *  -> )  ��i-1λ�ã�Ȼ������ƥ��λ��P�����pλ����(,�� dp[i] = 1 + dp[i-1] + 1   + dp[p-1]
 */
public class LongestValidParentheses6 {

	// ʱ�临�Ӷ�O(n)��n��str�ַ����ĳ���
	public static int longestValidParentheses(String str) {
		char[] s = str.toCharArray();
		// dp[0...n-1]
		// dp[i] : �Ӵ�������iλ�õ��ַ���β������£�����������Ч����󳤶�
		int[] dp = new int[s.length];
		int ans = 0;
		for (int i = 1, p; i < s.length; i++) {
			if (s[i] == ')') {
				p = i - dp[i - 1] - 1;
				//  ?         )
				//  p         i
				if (p >= 0 && s[p] == '(') {
					dp[i] = dp[i - 1] + 2 + (p - 1 >= 0 ? dp[p - 1] : 0);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

}
