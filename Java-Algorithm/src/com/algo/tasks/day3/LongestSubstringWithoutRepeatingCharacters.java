package com.algo.tasks.day3;

import com.algo.util.common.CommonStringUtil;

/**
 * ���ַ���������ظ��ִ����ȣ�
 * 
	https://leetcode.com/problems/longest-substring-without-repeating-characters/
	
	1. �����ִ��������뵽��i��β������ʲô(����/�����ƶ�Զ)
	2. ��Ϊ�Ǵ���������i��β���Ƿ������i-1�Ĵ𰸼���
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
	
	public static int lengthOfLongestSubstring(String s) {
		if (CommonStringUtil.isEmpty(s)) { 
			return 0;
		}
		char[] str = s.toCharArray();
		
		int[] map = new int[256]; // map[i] = v , iλ������ַ���ascci�ϴγ�����vλ��
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		map[str[0]] = 0; 
		int N = str.length;
		int ans = 1;
		int pre = 1; // ��һ��λ��i-1�����ƶ೤
		for (int i = 1; i < N; i++) {
			
			int p1 = i - map[str[i]]; // case 1: �ϴγ��ִ��ַ���λ�����ƶ�Զ
			int p2 = pre + 1; // case 2: ��һ��λ�� + 1
			int cur = Math.min(p1, p2);
			ans = Math.max(ans, pre);
			pre = cur;
			map[str[i]] = i;
		}
		return ans;
	}
}
