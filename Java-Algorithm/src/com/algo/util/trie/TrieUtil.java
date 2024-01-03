package com.algo.util.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import com.algo.util.trie.model.PrefixTree;
import com.algo.util.trie.model.PrefixTree3;
import com.algo.util.trie.model.PrefixTree4;

/**
 * ��**��ͷ / ��**��β ���ּ���
 */
public class TrieUtil {

	public static final String EMPTY = "";

	/**
	 * ��һ��Prefix��ͷ�ַ�������
	 */
	public static int countPrefixStr(List<String> strs, String prefix) {
		PrefixTree tree = new PrefixTree();
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
			PrefixTree3.build();
			m = Integer.valueOf(line);
			for (int i = 1; i <= m; i++) {
				splits = in.readLine().split(" ");
				op = Integer.valueOf(splits[0]);
				if (op == 1) {
					PrefixTree3.insert(splits[1]);
				} else if (op == 2) {
					PrefixTree3.delete(splits[1]);
				} else if (op == 3) {
					out.println(PrefixTree3.search(splits[1]) > 0 ? "YES" : "NO");
				} else if (op == 4) {
					out.println(PrefixTree3.prefixNumber(splits[1]));
				}
			}
			PrefixTree3.clear(); // һ��test case��ɣ�ȫ�����
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
		PrefixTree4.build(); 
		StringBuilder builder = new StringBuilder();
		// [3,6,50,10] -> "3#44#-40#"
		for (int[] nums : a) {
			builder.setLength(0);
			for (int i = 1; i < nums.length; i++) {
				builder.append(String.valueOf(nums[i] - nums[i - 1]) + "#");
			}
			PrefixTree4.insert(builder.toString());
		}
		int[] ans = new int[b.length];
		for (int i = 0; i < b.length; i++) {
			builder.setLength(0);
			int[] nums = b[i];
			for (int j = 1; j < nums.length; j++) {
				builder.append(String.valueOf(nums[j] - nums[j - 1]) + "#");
			}
			ans[i] = PrefixTree4.count(builder.toString());
		}
		PrefixTree4.clear();
		return ans;
	}
	
	
}
