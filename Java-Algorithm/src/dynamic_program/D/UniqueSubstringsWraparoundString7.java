package dynamic_program.D;
/**
 * Base: "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串
 * 给你一个字符串s ，请你统计 s 中有多少 不同非空子串 也在 Base 中出现
 * 
 * https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 */
public class UniqueSubstringsWraparoundString7 {

		// 维护一个字符结尾的最大长度表： dp[0]=s中必须以'a'的子串，最大延伸长度是多少，延伸一定要跟据base串的规则
	
		/**
		 * 只需要看每一个字符在s种，他在base串种往左延申的最大长度（自带去重功能哈哈）
		 * s: "zabpxyzab" 
		 * 
		 * a: 4
		 * b: 5
		 * p: 1
		 * z: 3
		 * y: 2
		 * x: 1
		 * sum = 16
		 * 一个变量往下走就可以，走不通就=1
		 * 
		 */
	
	
		// 时间复杂度O(n)，n是字符串s的长度，字符串base长度为正无穷
		public static int findSubstringInWraproundString(String str) {
			int n = str.length();
			int[] s = new int[n];
			// abcde...z -> 0, 1, 2, 3, 4....25 将字符转换成数组
			for (int i = 0; i < n; i++) {
				s[i] = str.charAt(i) - 'a';
			}
			
			
			
			// dp[0] : s中必须以'a'的子串，最大延伸长度是多少，延伸一定要跟据base串的规则
			int[] dp = new int[26];
			// s : c d e....
			//     2 3 4
			dp[s[0]] = 1;
			for (int i = 1, cur, pre, len = 1; i < n; i++) {
				cur = s[i];
				pre = s[i - 1];
				// pre cur
				if ((pre == 25 && cur == 0) || pre + 1 == cur) {
					// (前一个字符是'z' && 当前字符是'a') || 前一个字符比当前字符的ascii码少1
					len++;
				} else {
					len = 1;
				}
				dp[cur] = Math.max(dp[cur], len);
			}
			int ans = 0;
			for (int i = 0; i < 26; i++) {
				ans += dp[i];
			}
			return ans;
		}
}
