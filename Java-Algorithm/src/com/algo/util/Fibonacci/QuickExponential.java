package com.algo.util.Fibonacci;

import com.algo.util.bit.BitUtil;
import com.algo.util.common.CommonNumberUtil;

/**
 * 
 * 快速幂：<br><br>
 * 将幂分解成2进制，10^75 = 10 ^(64+8+2+1) = 10 ^(1001011) <br>
 * 设 t = 10^1, 10^2, 10^4, 10^8, 10^16, 10^32, 10^64 每次乘自己<br>
 *  ∵  64,32,16,8,4,2,1<br>
 * 75:  1 0  0  1 0 1 1<br>
 	t: ✔ ✘  ✘ ✔ ✘ ✔✔ <br>
 * ∴ 10^75 = 10 ^(1001011) = 10^1 * 10^2 * 10^8 * 10^64 <br>
 *  O(Log(n))
 */
public class QuickExponential {
	
	/**
	 * 1维矩阵/一个数，快速幂： <br>
	 * 
	 * m = 5 , p = 100  => 5^100<br>
	 * 
	 */
	
	public static int run(int m, int p) {
		// 生成单位阵： 1
        int res = 1;
        
        int t = p;
        for (; p != 0; p >>= 1) {
        	if (BitUtil.hasOneAtEnd(p)) {
        		res = res * t;
        	}
        	t = t * t;
        }
        return res;
	}
	
	/**
	 * 2维矩阵，快速幂
	 */
	public static int[][] run(int[][] m, int p) {
		//           (1,0)
		// 生成单位阵: (0,1)
		int[][] res = new int[m.length][m[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		
		int[][] t = m; // 矩阵1次方
		for (; p != 0; p >>= 1) { // p右移，擦去末尾1
			if (BitUtil.hasOneAtEnd(p)) {
				res = CommonNumberUtil.matrixMultiply(res, t);
			}
			t = CommonNumberUtil.matrixMultiply(t, t);
		}
		return res;
	}
	
}
