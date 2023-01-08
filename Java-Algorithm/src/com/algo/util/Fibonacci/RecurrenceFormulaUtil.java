package com.algo.util.Fibonacci;

/**
 * 
 * 쳲���������(�ϸ����ʽ)�� F(n) = F(n-1) + F(n-2)<br><br>
 * �ϸ����ʽ�� Recurrence Formula => O(Log(N)),����ֻҪ���ϸ����ʽ�����⸴�Ӷ� <br>
 * �ϸ� ������������ʽ <br>
 * �ƹ�(�κ�ϵ��)�� ���� F(N) = 6F(N-1) + 3F(N-5) ����������ʽ��  <br><br>
 * 
 * |F(N)�� F(N-1)�� F(N-2)�� F(N-3)�� F(N-4)| = |F(5)�� F(4)�� F(3)�� F(2)�� F(1)| * (5*5����)^ n-5
 */
public class RecurrenceFormulaUtil {
	
	/**
	 * N���Ķ���ţ�� / �����������ж�������/ �ȵ� <br><br>
	 * 
	 * ��һ����һͷĸ��A�� �Ժ�ÿ��ÿֻĸţ������һֱĸţ��ÿֻСţ��3����죬ĸţ����������N����ж���ţ��<br>
	 * 
	 * �⣺ F(n) = F(N-1) + F(n-3), �����ţ = ȥ�����µ�ţ + 3��ǰ������ţ���ڿ�����Сţ�� <br>
	 * 
	 * �� |F(4), F(3), F(2)| = |F(3), F(2), F(1)|  * (3*3����)^ n-3 <br>
	 * 
	 * �� ͨ��ö��ǰ����Ȼ����� (3*3����)�� base
	 * 
	 * 																	 (X..
	 * �� |F(N),F(N-1),F(N-2)| = |3,2,1| * (3*3����)^ n-3 Where (3*3����) = (Y..
	 * 																	 (Z..			
	 * �� F(N) = 3X + 2Y + Z = 3 * res[0][0] + 2 * res[1][0] + res[2][0]
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
	 *  ����ַ��������� �� һ����N�� һ��0/1��ɵ�Str, һ������N��Str�У� 0��߽�����1 / û��0 = <b>���</b> , �ж��ٴ��Str? <br><br>
	 *  ͨ���۲죺 
	 *  N��1��2��3��4��5��6 
	 *    1��2��3��4��5��13 // ��ʼ����1��2��Fibonacci(ԭ����Fib: 1��1��2��3��5��8��13),��˾�����Щ������
	 *  �⣺ F(N) = F(N-1) + F(N-2)
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
		int[][] res = QuickExponential.run(base, n - 2); // �� base ^ n-2
		return 2 * res[0][0] + res[1][0];  
	}
}
