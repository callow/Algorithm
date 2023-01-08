package com.algo.util.Fibonacci;

/**
 * 
 * 斐波那契数列(严格递推式)： F(n) = F(n-1) + F(n-2)<br><br>
 * 严格递推式： Recurrence Formula => O(Log(N)),即：只要是严格递推式就有这复杂度 <br>
 * 严格： 不是条件递推式 <br>
 * 推广(任何系数)： 若： F(N) = 6F(N-1) + 3F(N-5) 则满足行列式：  <br><br>
 * 
 * |F(N)， F(N-1)， F(N-2)， F(N-3)， F(N-4)| = |F(5)， F(4)， F(3)， F(2)， F(1)| * (5*5矩阵)^ n-5
 */
public class RecurrenceFormulaUtil {
	
	/**
	 * N年后的多少牛？ / 兔子生兔子有多少兔子/ 等等 <br><br>
	 * 
	 * 第一年有一头母球A， 以后每年每只母牛都会生一直母牛，每只小牛会3年成熟，母牛不会死，求N年后有多少牛？<br>
	 * 
	 * 解： F(n) = F(N-1) + F(n-3), 今年的牛 = 去年留下的牛 + 3年前出生的牛现在可以生小牛了 <br>
	 * 
	 * ∴ |F(4), F(3), F(2)| = |F(3), F(2), F(1)|  * (3*3矩阵)^ n-3 <br>
	 * 
	 * ∴ 通过枚举前几项然后求出 (3*3矩阵)的 base
	 * 
	 * 																	 (X..
	 * ∵ |F(N),F(N-1),F(N-2)| = |3,2,1| * (3*3矩阵)^ n-3 Where (3*3矩阵) = (Y..
	 * 																	 (Z..			
	 * ∴ F(N) = 3X + 2Y + Z = 3 * res[0][0] + 2 * res[1][0] + res[2][0]
	 * 
	 *  O(Log(N))
	 */
	
	public static int numOfCows(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int[][] base = {
				{ 1, 1, 0 }, 
				{ 0, 0, 1 }, 
				{ 1, 0, 0 } };
		
		int[][] res = QuickExponential.run(base, n - 3);
		return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
	}
	
	
	/**
	 *  达标字符串的数量 ： 一个数N， 一个0/1组成的Str, 一个长度N的Str中， 0左边紧挨着1 / 没有0 = <b>达标</b> , 有多少达标Str? <br><br>
	 *  通过观察： 
	 *  N：1，2，3，4，5，6 
	 *    1，2，3，4，5，13 // 初始项是1，2的Fibonacci(原生的Fib: 1、1、2、3、5、8、13),因此矩阵有些许区别
	 *  解： F(N) = F(N-1) + F(N-2)
	 *  
	 */
	
	public static int qualifiedStr(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		int[][] base = {{ 1, 1 }, { 1, 0 }};
		int[][] res = QuickExponential.run(base, n - 2); // 求 base ^ n-2
		return 2 * res[0][0] + res[1][0];  
	}
}
