package com.algo.util.catalan_number;

import java.math.BigInteger;

/**
 * ���������������ѧ�ֵ�����<br>
 * 3��ͨ�ʽ����֪ k(0) = 0, k(1) = 1<br>
 * - k(n) = k(0)*k(n-1) + k(1)*k(n-2) + .... + k(n-1)*k(0)<br>
 * - k(n) = c(2n,n) - c(2n,n-1)<br>
 * - k(n) = c(2n,n) / n+1 <br>
 * 
 * ����ж�2������Ԫ��������ȣ� - A -> B && A <- B
 */
public class CatalanUtil {

	/**
	 * ���ۣ� <br>
	 * 1. ������ż����һ����ġ� ÿ������*2 -> һ��ż�� && ÿ��ż�� / 2 -> һ������ <br>
	 * 2. 5��ֱ�ߺ�10��ֱ���ֵ���һ���ࡣ 5�����κ�һ�㻭һ��ֱ�� -> ��Ӧ10����һ�㣬 ��֮.
	 * 
	 */

	/**
	 * 
	 * n���� n�������� �������ʱ��������� �ж��ٺϷ���ϣ�<br>
	 * 
	 * �⣺����� ���Ϸ��ķ���A���ϣ�B������n+1�������ź�n-1��������������γɵ��������ӡ�<br>
	 * 
	 * �Ϸ����� ÿ��ǰ׺������ < ������. <br>
	 * ���Ϸ����� ����һ�����ǰ׺����������= ������+1.<br>
	 * 
	 * �ܷ�������C(2n,n) // һ��2n��λ�ã�ѡn��λ�÷������ţ�ʣ��n��λ�÷�������<br>
	 * 
	 * �Ϸ��� = �ܷ����� - ���Ϸ���<br>
	 * 
	 * ���Ϸ���: ������̲��Ϸ���(�� = ��+1),�����Ϸ��Ҳ෴תһ��(�� = ��+1)��=> �� = �� + 2<br>
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
	 * ����������n��
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
