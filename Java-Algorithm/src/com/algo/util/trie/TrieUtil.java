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
 * ��**��ͷ / ��**��β ���ּ���
 */
public class TrieUtil {

	public static final String EMPTY = "";

	/**
	 * ��һ��Prefix��ͷ�ַ�������
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
	 * ͳ��String��ÿ���ַ����ֵ�Ƶ��,�ַ�������Сд
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
	 * Ѱ�������ǰ׺ = �ֵ������
	 */

	public static String getLongestCommontPrefix(String[] strs) {
		if (strs.length == 0) {
			return EMPTY;
		}
		String prefix = strs[0]; // first element
		for (int i = 1; i < strs.length; i++) {

			while (strs[i].indexOf(prefix) != 0) { // ������ʱ�������ж�prefix, prefix���ϸ�ʱ�˳�
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return EMPTY;
				}
			}
		}
		return prefix;

	}
	
	/**
	 * ��ʽʶ��ǰ׺��
	 * https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
	 * ���룺
			7
			1 qwer
			1 qwe
			3 qwer
			4 q
			2 qwer
			3 qwer
			4 q
		�����
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
			StaticLetterPrefixTree.clear(); // һ��test case��ɣ�ȫ�����
		}
		out.flush();
		in.close();
		out.close();
	}
	
	/**
	 * 2����Կ���ȶ�ǰ׺ ��ȷ��ƥ�����
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
	 *  �������У�2���������ͣ�
	 *  ���һ������ȷ����������һ�������ǽ��Ÿ�λ��1������Ҹ�λ��1�Ĺ��̾�����ǰ׺���������Ĺ���
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
	 * ��һ��2ά�����У������ֵ��еĵ���
	 * 
	 * https://leetcode.cn/problems/word-search-ii/
	 * 
	 * DFS: һ��·�ߵ��� = �ݹ�
	 * BFS: ̽Ѱһ����Χ�ж���·
	 * 
	 * ��֦1: matrix�߹���cell���Ϊ0�����ȷȷʵʵ�ѵ�һ���������ָ��ֳ�����ֹ���ظ�/��ͷ·
	 * ��֦2: ���ֵ佨��ǰ׺����������matrix�߸���ʱ�����û�õ�path/cell����...��ͷ��
	 * ���ɣ�word - �ռ��ĵ���ֱ��ͬ pass, endһ�����ǰ׺���ڵ㣬����ֱ���ռ��𰸣������ٻ����ռ����� e.g ��ab��
	 * 		o word = null, pass = 1, end = 0
	 * 	   /a
	 *    o  word = null, pass = 1, end = 0
	 *     \b
	 *      o word = 'ab', pass = 1, end = 1
	 * ��֦3: ǰ׺��searchʱ�� ÿ�ѵ�һ�����ʣ�pass - 1, ����ǰ�ڵ���������Ҷ�ڵ㶼��0ʱ���Լ�Ҳ��0. ����һ���ѱ�Ĵ�ʱ��ֱ������pass = 0 ��·��ֱ�ӷ���
	 * 	����Ϊpass = 0 ˵������Ĵ��Ѿ����ù��ˣ�Ҫ�����ѳ��ظ��𰸣�
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
