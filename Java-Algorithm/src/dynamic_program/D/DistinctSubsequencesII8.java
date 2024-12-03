package dynamic_program.D;
/**
 * 
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
 * 
 * https://leetcode.cn/problems/distinct-subsequences-ii/
 * 
 * 解法： 有多少个子序列是以i结尾的。
 * 
 * 算法：
 *  纯新增个数 = all - 当前character上次的记录
 *  当前character记录数 +=  纯新增个数
 *  all += 纯新增
 * 
 */
public class DistinctSubsequencesII8 {

	/**
	 * s: "abaab...."
	 * 	   01234
	 * 
	 *  新增子序列 = 遍历之前子序列然后每一个都concat当前字符 例子如下，
	 * Ø: {}
	 * 0: {} | {Ø+a} => {} {a}
	 * 1: {} {a} | {Ø+b} {a+b} => {} {a} {b} {ab}
	 * 2：{} {a} {b} {ab} | {Ø+a} {a+a} {b+a} {ab+a} => {} {a} {b} {ab} {aa} {ba} {aba}
	 * 3：....
	 */
	
	public static int distinctSubseqII(String s) {
		int mod = 1000000007;
		char[] str = s.toCharArray();
		int[] cnt = new int[26];
		int all = 1, newAdd;
		for (char x : str) {
			newAdd = (all - cnt[x - 'a'] + mod) % mod;
			cnt[x - 'a'] = (cnt[x - 'a'] + newAdd) % mod;
			all = (all + newAdd) % mod;
		}
		return (all - 1 + mod) % mod;
	}
}
