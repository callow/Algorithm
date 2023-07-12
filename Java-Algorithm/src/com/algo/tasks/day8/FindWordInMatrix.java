package com.algo.tasks.day8;

public class FindWordInMatrix {
	
	public static boolean findWordcanLoop(char[][] m, String word) {
		if (word == null || word.equals("")) {
			return true;
		}
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		char[] w = word.toCharArray();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (canLoop(m, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * �������ظ�· : ��m[i][j]����ַ��������ܲ����ҵ�word[k...]�����׺��
	 */
	public static boolean canLoop(char[][] m, int i, int j, char[] word, int k) {
		
		// ����word��β�ˣ� �㶨�� ����
		if (k == word.length) { 
			return true;
		}
		
		// ��ǰ�ַ���kλ�ò�һ�����㲻��
		if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != word[k]) {
			return false;
		}
		// ��Խ�磡m[i][j] == str[k] �Ե��ϵģ���������ȫ����һ��
		// word[k+1....]
		boolean ans = false;
		if (canLoop(m, i + 1, j, word, k + 1) || canLoop(m, i - 1, j, word, k + 1) || canLoop(m, i, j + 1, word, k + 1)
				|| canLoop(m, i, j - 1, word, k + 1)) {
			ans = true;
		}
		return ans;
	}
	
	
	
	public static boolean findWordNoLoop(char[][] m, String word) {
		if (word == null || word.equals("")) {
			return true;
		}
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		char[] w = word.toCharArray();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (noLoop(m, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * ���������ظ�· : ��m[i][j]����ַ��������ܲ����ҵ�word[k...]�����׺��
	 * 
	 * �߹��Ƴ����� �߹���λ�ã�ascci���0
	 */
	public static boolean noLoop(char[][] m, int i, int j, char[] word, int k) {
		if (k == word.length) {
			return true;
		}
		if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != word[k] || m[i][j] != 0) {
			return false;
		}
		// ��Խ�磡Ҳ���ǻ�ͷ·��m[i][j] == str[k] Ҳ�Ե��ϣ�
		char original = m[i][j];
		m[i][j] = 0; // ��0
		boolean ans = false;
		if (noLoop(m, i + 1, j, word, k + 1) || noLoop(m, i - 1, j, word, k + 1) || noLoop(m, i, j + 1, word, k + 1)
				|| noLoop(m, i, j - 1, word, k + 1)) {
			ans = true;
		}
		m[i][j] = original; // �ָ��ֳ�
		return ans;
	}

}
