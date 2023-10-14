package com.algo.util.graph.bfs_dfs;
//��ͼ����
//������������һ�ݴ�СΪ n x n �� ���� grid
//�����ÿ�� ��Ԫ�� ���� 0 �� 1 ��Ǻ������� 0 ������1 ����½�ء�
//�����ҳ�һ������Ԫ���������Ԫ�����������½�ص�Ԫ��ľ���������
//�����ظþ��롣���������ֻ��½�ػ��ߺ����뷵�� -1��
//��������˵�ľ����ǡ������پ��롹�� Manhattan Distance����
//(x0, y0) �� (x1, y1) ��������Ԫ��֮��ľ����� |x0 - x1| + |y0 - y1| ��
//�������� : https://leetcode.cn/problems/as-far-from-land-as-possible/
public class AsFarFromLandAsPossible {

	public static int MAXN = 101;

	public static int MAXM = 101;

	public static int[][] queue = new int[MAXN * MAXM][2];

	// ���������ݷ���rλ�ú�Ȼ��r++
	// �Ӷ������õ���lλ�õ�����Ȼ��l++
	public static int l, r;

	public static boolean[][] visited = new boolean[MAXN][MAXM];

	// 0:�ϣ�1:�ң�2:�£�3:��
	public static int[] move = new int[] { -1, 0, 1, 0, -1 };
	//                                      0  1  2  3   4
	//                                               i
	// (x,y)  i����0λ�� : x + move[i], y + move[i+1] -> x - 1, y
	// (x,y)  i����1λ�� : x + move[i], y + move[i+1] -> x, y + 1
	// (x,y)  i����2λ�� : x + move[i], y + move[i+1] -> x + 1, y
	// (x,y)  i����3λ�� : x + move[i], y + move[i+1] -> x, y - 1

	public static int maxDistance(int[][] grid) {
		l = r = 0;
		int n = grid.length;
		int m = grid[0].length;
		int seas = 0; // �����˶��ٸ����������
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					visited[i][j] = true;
					queue[r][0] = i;
					queue[r++][1] = j;
				} else {
					visited[i][j] = false;
					seas++;
				}
			}
		}
		if (seas == 0 || seas == n * m) {
			return -1;
		}
		int level = 0;
		while (l < r) {
			level++;
			int size = r - l; // ��ǰ�������м���Ԫ��
			for (int k = 0, x, y, nx, ny; k < size; k++) {
				x = queue[l][0];
				y = queue[l++][1];
				for (int i = 0; i < 4; i++) {
					// �ϡ��ҡ��¡���˳��չ��
					nx = x + move[i];
					ny = y + move[i + 1];
					if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
						visited[nx][ny] = true;
						queue[r][0] = nx;
						queue[r++][1] = ny;
					}
				}
			}
		}
		return level - 1;
	}

}
