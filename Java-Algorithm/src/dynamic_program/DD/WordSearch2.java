package dynamic_program.DD;
/**
 * ��ά�����ҵ���(exist)
 * 
 * https://leetcode.cn/problems/word-search/
 * 
 * �����Ǿ������⣺��·���ĵݹ��޷��Ż�����Ϊ��������ֵ�Ĳ�ֻ��i j k,����һ����ά����char[][] b, �仯״̬̫����
 * ��·���ĵݹ飺�߹��˸ĳ�0����ֹ�ظ��ߣ�����Ҫ�ָ��ֳ�
 */
public class WordSearch2 {

	// �����ݹ�
	public static boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (f(board, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	// ��Ϊboard������е��ַ�
	// ���������Щ�ַ��޷�����
	// ��·���ĵݹ��޷��ĳɶ�̬�滮����˵û��Ҫ
	// ��(i,j)����������kλ�ã����ʺ����ܲ��ܰ�word�߳��� =>w[..k...]
	public static boolean f(char[][] b, int i, int j, char[] w, int k) {
		if (k == w.length) { // �ɹ�
			return true;
		}
		if (i < 0 || i == b.length || j < 0 || j == b[0].length // Խ��
				|| b[i][j] != w[k]) { // ��ƥ��
			return false;
		}
		
		// ��Խ�磬b[i][j] == w[k]
		char tmp = b[i][j];
		b[i][j] = 0; // �߹��˸ĳ�0����ֹ�ظ��� = ��·���Ķ�̬�滮
		boolean ans = f(b, i - 1, j, w, k + 1)  // ����������
				|| f(b, i + 1, j, w, k + 1) 
				|| f(b, i, j - 1, w, k + 1)
				|| f(b, i, j + 1, w, k + 1);
		b[i][j] = tmp; // �ָ��ֳ�
		return ans;
	}
}
