package com.algo.util.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.algo.util.trie.model.DfsPrefixTree;
import com.algo.util.trie.model.MapLetterPrefixTree;
import com.algo.util.trie.model.StaticLetterPrefixTree;
import com.algo.util.trie.model.NumberPrefixTree;
import com.algo.util.trie.model.XorPrefixTree;

/**
 * 以**开头 / 以**结尾 出现几次
 */
public class TrieUtil {

	public static final String EMPTY = "";

	/**
	 * 数一下Prefix开头字符串个数
	 */
	public static int countPrefixStr(List<String> strs, String prefix) {
		MapLetterPrefixTree tree = new MapLetterPrefixTree();
		for (String s : strs) {
			tree.insert(s);
		}
		return tree.countPrefix(prefix);
	}

	/**
	 * 
	 * 统计String中每种字符出现的频率,字符必修是小写
	 */
	public static int[] getLetterFrequency(String str) {
		char[] s1 = str.toCharArray();
		int[] count = new int[26];
		for (char cha : s1) {
			count[cha - 'a']++;
		}
		return count;
	}

	/**
	 * 寻找最长公共前缀 = 字典树最深处
	 */

	public static String getLongestCommontPrefix(String[] strs) {
		if (strs.length == 0) {
			return EMPTY;
		}
		String prefix = strs[0]; // first element
		for (int i = 1; i < strs.length; i++) {

			while (strs[i].indexOf(prefix) != 0) { // 当符合时，继续判断prefix, prefix不合格时退出
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return EMPTY;
				}
			}
		}
		return prefix;

	}
	
	/**
	 * 流式识别前缀树
	 * https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
	 * 输入：
			7
			1 qwer
			1 qwe
			3 qwer
			4 q
			2 qwer
			3 qwer
			4 q
		输出：
			YES
			2
			NO
			1
	 */
	public static int m, op;
	public static String[] splits;
	public static void streamTrie() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		String line = null;
		while ((line = in.readLine()) != null) {
			StaticLetterPrefixTree.build();
			m = Integer.valueOf(line);
			for (int i = 1; i <= m; i++) {
				splits = in.readLine().split(" ");
				op = Integer.valueOf(splits[0]);
				if (op == 1) {
					StaticLetterPrefixTree.insert(splits[1]);
				} else if (op == 2) {
					StaticLetterPrefixTree.delete(splits[1]);
				} else if (op == 3) {
					out.println(StaticLetterPrefixTree.search(splits[1]) > 0 ? "YES" : "NO");
				} else if (op == 4) {
					out.println(StaticLetterPrefixTree.prefixNumber(splits[1]));
				}
			}
			StaticLetterPrefixTree.clear(); // 一个test case完成，全部清空
		}
		out.flush();
		in.close();
		out.close();
	}
	
	/**
	 * 2套密钥，比对前缀 来确认匹配次数
	 * 
	 * https://www.nowcoder.com/practice/c552d3b4dfda49ccb883a6371d9a6932
	 * 
	 */
	public static int[] countConsistentKeys(int[][] b, int[][] a) {
		NumberPrefixTree.build(); 
		StringBuilder builder = new StringBuilder();
		// [3,6,50,10] -> "3#44#-40#"
		for (int[] nums : a) {
			builder.setLength(0);
			for (int i = 1; i < nums.length; i++) {
				builder.append(String.valueOf(nums[i] - nums[i - 1]) + "#");
			}
			NumberPrefixTree.insert(builder.toString());
		}
		int[] ans = new int[b.length];
		for (int i = 0; i < b.length; i++) {
			builder.setLength(0);
			int[] nums = b[i];
			for (int j = 1; j < nums.length; j++) {
				builder.append(String.valueOf(nums[j] - nums[j - 1]) + "#");
			}
			ans[i] = NumberPrefixTree.count(builder.toString());
		}
		NumberPrefixTree.clear();
		return ans;
	}
	
	/**
	 *  求数组中，2数最大的异或和？
	 *  如果一个数字确定，则找另一个数就是紧着高位变1，这个找高位变1的过程就是在前缀树上搜索的过程
	 *  
	 *  https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/
	 */
	public static int findMaximumXOR(int[] nums) { 
		XorPrefixTree.build(nums);
		int ans = 0;
		for (int num : nums) {
			ans = Math.max(ans, XorPrefixTree.maxXor(num));
		}
		XorPrefixTree.clear();
		return ans;
	}
	
	/**
	 * 在一个2维矩阵中，搜索字典中的单词
	 * 
	 * https://leetcode.cn/problems/word-search-ii/
	 * 
	 * DFS: 一条路走到黑 = 递归
	 * BFS: 探寻一下周围有多少路
	 * 
	 * 剪枝1: matrix走过的cell标记为0，如果确确实实搜到一个结果后则恢复现场，防止走重复/回头路
	 * 剪枝2: 将字典建成前缀树，可以在matrix走格子时候避免没用的path/cell（以...开头）
	 * 技巧：word - 收集的单词直接同 pass, end一起放入前缀树节点，可以直接收集答案，无需再回溯收集单词 e.g “ab”
	 * 		o word = null, pass = 1, end = 0
	 * 	   /a
	 *    o  word = null, pass = 1, end = 0
	 *     \b
	 *      o word = 'ab', pass = 1, end = 1
	 * 剪枝3: 前缀树search时候 每搜到一个单词，pass - 1, 当当前节点下面所有叶节点都是0时，自己也变0. 当下一次搜别的词时，直接跳过pass = 0 的路，直接返回
	 * 	（因为pass = 0 说明下面的词已经被用过了，要不就搜出重复答案）
	 * 
	 */
	public List<String> findWords(char[][] board, String[] words) {
		DfsPrefixTree.build(words);
		List<String> ans = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				DfsPrefixTree.dfs(board, i, j, 1, ans);
			}
		}
		DfsPrefixTree.clear();
		return ans;
    }
	
	
}
