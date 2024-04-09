package com.algo.tasks.day3;

import com.algo.util.common.CommonStringUtil;

/**
 * ���ַ���������ظ��ִ����ȣ�
 * 
	https://leetcode.com/problems/longest-substring-without-repeating-characters/
	
	1. �����ִ��������뵽��i��β������ʲô(����/�����ƶ�Զ)
	2. ��Ϊ�Ǵ���������i��β���Ƿ������i-1�Ĵ𰸼���
	
	���16λ����������Զ���Ƶ�12�� ��17λ�������ƿ϶�Ҳ�Ʋ���12. ���i-1���Ը�i����
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
		int pre = 1; // ��һ��λ�ã���i-1λ�� �������˶೤
		for (int i = 1; i < N; i++) {
			
			/**
			 * a  s  k  b  c   b  a
			 * 11 12 13 14 15 16 17
			 * p			      i
			 * 
			 * i= 17�� i�� pre = 11, i-1= 16λ��ֻ�������Ƶ�15λ�ã�����Ҳֻ���Ƶ�15λ�ã�˭����i���Ҿ�ֻ���Ƶ�����				  
			 */
			
			int p1 = i - map[str[i]]; // case 1: �ϴγ��ִ��ַ���λ�����ƶ�Զ�� a(13)......a(17) = 17-13 = 4
			int p2 = pre + 1; // case 2: ��һ��λ�� + 1, ��ǰ��17λ�ã�����16λ����������8�ĳ��ȣ���8+1����17λ�������Ƶļ���
			int cur = Math.min(p1, p2);
			ans = Math.max(ans, cur);
			pre = cur;
			map[str[i]] = i;
		}
		return ans;
	}
}
