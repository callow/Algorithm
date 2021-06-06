package leet.code;
/*Implement int sqrt(int x).

Compute and return the square root of x.

x is guaranteed to be a non-negative integer.


Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.*/

public class Sqrt {

	public static void main(String[] args) {
		System.out.println(mySqrt(8));
	}
	
	// 牛顿求根算法，就是while循环那部分
	 public static int mySqrt(int x) {
		    long x1 = x;
		    while (x1 * x1 > x) { // x1 * x1  不能大于原数 否则就一直递归赋值 直到<x为止
		    	x1 = (x1 + x/x1) / 2;
		    } 	
		    return (int) x1;
	 }

}
