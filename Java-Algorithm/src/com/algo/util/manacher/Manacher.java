package com.algo.util.manacher;

import com.algo.util.common.CommonStringUtil;

/**
 * 
 * ��ṹ�㷨���� O(N) <br>
 * 
 * 1. �ߵ�ÿ��λ��Ȼ�����������һ��ĵ�������Ϊֹ�� ǰ��������Ϊ����ٺ���������Ϊ <br>
 * 2. 12321 -> ����ֱ����5 �����İ뾶��3 <br>
 * 3. 1221  -> ����ֱ����4 �����İ뾶��2 <br>
 * 4. ���İ뾶Array: ��¼ÿ��λ�û��İ뾶��Int[] <br>
 * 5. ���һ��ı߽�R�� ���ܴ������ģ�ֻҪ���ı߽�������ˣ��͸�ֵ��R ���� <br>
 * 6. ȡ��Rʱ������c�� ȡ�����һ����б߽�Ļ����������� �͸�ֵ��c ���� <br>
 * 7. ��iû��R��ס�޷��Ż�����i��R��ס�����Ż�����Ӧ��i'(i����c�ĶԳƵ�)�ش��� L(R��c�ĶԳƵ�) �ش��� Topo:[L i' c i R] <br><br>
 * 
 * cases: <br>
 * 	1. ��i'�������Ļ�����[L,R]��, ��i�Ļ�����i'һ���� O��1��. ���İ뾶��  = i'��<br>
 *  2. ��i'�������Ļ�����[L,R]��, ��i~R�ľ��� = i�Ļ��İ뾶 O(1). ���İ뾶�� R - i<br>
 *  3. ��i'����Ļ�����߽���L�غ�, �� ����i~RΪi�Ļ��İ뾶��Ȼ��������������Ҹ��� O(N). ���İ뾶��  >= i'�� 
 *   
 */
public class Manacher {

	public static int maxLen(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// "12132" -> "#1#2#1#3#2#"
		char[] str = CommonStringUtil.manacherString(s);
		// ���İ뾶���� : ���İ뾶�����е����ֵ /2 ���Ǵ𰸣���Ϊ����(#)���� * 2��
		int[] pArr = new int[str.length];
		// ���ĵ�
		int C = -1;
		// R�������ҵ����ɹ���λ�á�coding�����ҵ����ɹ�λ�õģ�����һ��λ��
		int R = -1;
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < str.length; i++) { // 0 1 2�� ÿ��λ��ȥ��
			/**
			 * R > i (���� i��R��ס)�� 3��case
			 * 	 - pArr[2 * C - i] �� �������i'
			 *   - R- i : ����� i~R ����
			 *   - min(..,..) : �����ĸ������ǲ�����ģ�ֱ��֪�����İ뾶
			 */
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
		
			
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				/**
				 * str[i + pArr[i]] �� �����ҵ��ַ�
				 * str[i - pArr[i]]) �� ��������ַ�
				 * �����ҵ��ַ� = ��������ַ� => ���İ뾶++
				 * ������Ĳ���һ��while��break
				 */
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			
			// ��ʱ��i�Ļ��İ뾶ȫ������������R�Ƶĸ������ˣ�����R�� ����C
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			// ÿһ���Ļ��İ뾶���ֵ����
			max = Math.max(max, pArr[i]);
		}
		// ��Ϊ���� ��ԭ������������˰뾶max -1 ����ԭʼ�����������ִ��Ļ���ֱ���ĳ���
		return max - 1;
	}
	
	
	
}
