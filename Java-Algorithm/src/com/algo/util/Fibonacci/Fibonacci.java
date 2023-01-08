package com.algo.util.Fibonacci;

/**
 * 
 * Fibonacci的行列式关系： <br>
 * 	                   (a,b)
 * |F3,F2| = |F2,F1| * (c,d)   
 * 
 * 然后求出 a,b,c,d !<br>
 * 
 * 通项公式： 
 * 
 * 	                                        (1,1)
 * |Fn,Fn-1| = |F2,F1| * (A)^ n-2 where A = (1,0)
 * 
 */
public class Fibonacci {
	/**
	 * 
	 * F(n) : 求Fib第n项? <br>
	 *                             (1,1)^ n-2           (x,y)
	 * ∵ |F(n),F(n-1)| = |F2,F1| * (1,0)      = |1,1| * (z,t) = |x+z, y+t| <br>
	 * 
	 *                                                    (x,y)
	 * ∴ F(n) = x + z = res[0][0] + res[1][0] <b>Where</b> res = (z,t)
	 * 				          
	 */
	public static int Fn(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		// [ 1 ,1 ]
		// [ 1, 0 ]
		int[][] base = {{ 1, 1 }, { 1, 0 }};
		
		int[][] res = QuickExponential.run(base, n - 2); // 求 base ^ n-2
		return res[0][0] + res[1][0];  
	}
	
	/**
	 * S(n) = ∑Fi: 求Fib前n项和? <br><br>
	 * 
	 * 通项公式： Sn = 1/√5 * ([(1+√5)/2] ^ n - [(1-√5)/2] ^ n)  = F(n+2) - 1 <br>
	 * 
	 * 利用2个等比数列相减互相抵消，最终得到： Sn = F(n+2) - 1
	 * O(Log(N))
	 */
	
	public static int Sn(int n) {
		return Fn(n+2) - 1;
	}
	
}
