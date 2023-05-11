package com.algo.util.catalan_number;

import java.math.BigInteger;

/**
 * 卡特兰数：组合数学种的数列<br>
 * 3个通项公式：已知 k(0) = 0, k(1) = 1<br>
 * - k(n) = k(0)*k(n-1) + k(1)*k(n-2) + .... + k(n-1)*k(0)<br>
 * - k(n) = c(2n,n) - c(2n,n-1)<br>
 * - k(n) = c(2n,n) / n+1 <br>
 * 
 * 如何判断2个集合元素数量相等： - A -> B && A <- B
 */
public class CatalanUtil {

	/**
	 * 推论： <br>
	 * 1. 整数和偶数是一样多的。 每个整数*2 -> 一个偶数 && 每个偶数 / 2 -> 一个整数 <br>
	 * 2. 5米直线和10米直线种点数一样多。 5米中任何一点画一条直线 -> 对应10米中一点， 反之.
	 * 
	 */

	/**
	 * 
	 * n个左 n个右括号 数量相等时，自由组合 有多少合法组合？<br>
	 * 
	 * 解：结合中 不合法的放入A集合，B集合是n+1个左括号和n-1右括号随意组合形成的所有样子。<br>
	 * 
	 * 合法数： 每个前缀右括号 < 左括号. <br>
	 * 不合法数： 存在一个最短前缀导致右括号= 左括号+1.<br>
	 * 
	 * 总方法数：C(2n,n) // 一共2n个位置，选n个位置放左括号，剩下n个位置放右括号<br>
	 * 
	 * 合法数 = 总方法数 - 不合法数<br>
	 * 
	 * 不合法数: 遇到最短不合法后(右 = 左+1),将不合法右侧反转一下(右 = 左+1)，=> 右 = 左 + 2<br>
	 * c(2n,n-1)
	 * 
	 * A: ())(() -> B: ())))(
	 * 
	 */
	public static int bracketCombinationValidNum(int n) {
		return findCatalan(n).intValue();
	}

	/**
	 * 
	 * 求卡特兰数第n项
	 */
	public static BigInteger findCatalan(int n) {
		// using BigInteger to calculate large factorials
		BigInteger b = new BigInteger("1");

		// calculating n!
		for (int i = 1; i <= n; i++) {
			b = b.multiply(BigInteger.valueOf(i));
		}

		// calculating n! * n!
		b = b.multiply(b);

		BigInteger d = new BigInteger("1");

		// calculating (2n)!
		for (int i = 1; i <= 2 * n; i++) {
			d = d.multiply(BigInteger.valueOf(i));
		}

		// calculating (2n)! / (n! * n!)
		BigInteger ans = d.divide(b);

		// calculating (2n)! / ((n! * n!) * (n+1))
		ans = ans.divide(BigInteger.valueOf(n + 1));
		return ans;
	}

}
