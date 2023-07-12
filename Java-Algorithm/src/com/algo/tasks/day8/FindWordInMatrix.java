package com.algo.tasks.day8;

/**
	 * ����һ��char[][] matrix��Ҳ����char���͵Ķ�ά���飬�ٸ���һ���ַ���word��
	 * ���Դ��κ�һ��ĳ��λ�ó������������������ң��ܲ����ҵ�word��
	 * ���磺
	 * char[][] m = { 
	 *     { 'a', 'b', 'z' }, 
	 *     { 'c', 'd', 'o' }, 
	 *     { 'f', 'e', 'o' }, 
	 * };
	 * word = "zooe"
	 * �ǿ����ҵ���
	 * 
	 * �趨1���������ظ�·������£������ܲ����ҵ�
	 * ���磬word = "zoooz"���ǿ����ҵ��ģ�z -> o -> o -> o -> z����Ϊ������һ��·�����Ѿ��߹����ַ�
	 * 
	 * �趨2�����������ظ�·������£������ܲ����ҵ�
	 * ���磬word = "zoooz"���ǲ������ҵ��ģ���Ϊ������һ��·�����Ѿ��߹����ַ������ظ���
	 * 
	 * д�������趨�µ�code
 * 
 * */
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
	
	
	
	
	
	/**
	 * 
		�������ظ�·: ���仯���� -> dp[i][j][k]��ʾ��������m[i][j]����ַ���β������£��ܲ����ҵ�w[0...k]���ǰ׺��
	 */
	public static boolean findWord1(char[][] m, String word) {
		if (word == null || word.equals("")) {
			return true;
		}
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		char[] w = word.toCharArray();
		int N = m.length;
		int M = m[0].length;
		int len = w.length;
		// dp[i][j][k]��ʾ��������m[i][j]����ַ���β������£��ܲ����ҵ�w[0...k]���ǰ׺��
		boolean[][][] dp = new boolean[N][M][len];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dp[i][j][0] = m[i][j] == w[0];
			}
		}
		for (int k = 1; k < len; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					dp[i][j][k] = (m[i][j] == w[k] && checkPrevious(dp, i, j, k));
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dp[i][j][len - 1]) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean checkPrevious(boolean[][][] dp, int i, int j, int k) {
		boolean up = i > 0 ? (dp[i - 1][j][k - 1]) : false;
		boolean down = i < dp.length - 1 ? (dp[i + 1][j][k - 1]) : false;
		boolean left = j > 0 ? (dp[i][j - 1][k - 1]) : false;
		boolean right = j < dp[0].length - 1 ? (dp[i][j + 1][k - 1]) : false;
		return up || down || left || right;
	}

}
