package dfs;

public class Util {

	// ¡ü ¡ú ¡ý ¡û Ë³Ê±Õë
	public static final int[] DIRECTION_MATRIX_4 = {-1, 0, 1, 0, -1};
	/**
	 * for (int d = 0; d < 4; d++) {
			int ni = i + DIRECTION_MATRIX_4[d];
	        int ni = j + DIRECTION_MATRIX_4[d + 1];
	 */
	
	// ¡ü ¨J ¡ú ¨K ¡ý ¨L ¡û ¨I Ë³Ê±Õë
	public static final int[] DIRECTION_MATRIX_8_X = {-1, -1, -1, 0, 1, 1, 1, 0};
	public static final int[] DIRECTION_MATRIX_8_Y = {-1,  0,  1, 1, 1, 0, -1, -1};
	/**
	 * for (int d = 0; d < 8; d++) {
        int ni = i + DIRECTION_MATRIX_8_X[d];
        int nj = j + DIRECTION_MATRIX_8_Y[d];
	 */
}
