package com.algo.util.math;

import com.algo.util.math.impl.GCDUtil;

public class MathUtil {

	/**
	 * 最大公约数 O(log(a)^3) ：50，30 -> 10
	 */
	public static long gcd(long a, long b) {
		return GCDUtil.gcd(a, b);
	}
	
	/**
	 * 最小公倍数: 50,30 -> 150
	 */
	public static long lcm(long a, long b) {
		return GCDUtil.lcm(a, b);
	}
	
	/**
	 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。给定三个整数 n , a , b ，返回第 n 个神奇的数字。
		https://leetcode.cn/problems/nth-magical-number/
	 */
	public static int nthMagicalNumber(int n, int a, int b) {
		return GCDUtil.nthMagicalNumber(n, a, b);
	}
	
	
	 /**
	  * 矩形是否相交：rec1 or rec2 给出左下 和 右上坐标 -> rec1 = [0,0,2,2], rec2 = [1,1,3,3]
	  * 
	  * 思路：如果有交集，则交集也一定是矩形，即：交集存在向x轴的投影 和 向y轴的投影。 = 一维线段是否有交集的问题 
	  * 	# rec1 和 rec2 的水平边投影到 x 轴上的线段分别为 (rec1[0], rec1[2]) 和 (rec2[0], rec2[2])
	  * 	  当 min(rec1[2], rec2[2]) > max(rec1[0], rec2[0]) 时，这两条线段有交集
	  *     # y轴同理
	  * 
	  * https://leetcode.cn/problems/rectangle-overlap/description/
	  */
	 public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		 return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])  // x 轴存在投影
				 && 
	             Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1])); // y 轴存在投影

	 }
	 
	 
	 
	   /**
	    * 求支点： 连续数列： ∑(1-x) = ∑(x~n), 求x? https://leetcode.com/problems/find-the-pivot-integer/description/
	     sum(1~x) = sum(x~n)
	
	     连续数列 用等差数列求和公式 ：n(a1+an) / 2
	         sum(1~x) = x(x+1) / 2
	         sum(x~n) = (x+n)(n-x+1) / 2 where 项数n= n-x+1
	
	         x = (n^2 + n) / 2
	  */
	 public int pivotInteger(int n) {
	     
	     int sum = (n * (n + 1) / 2);
	     int pivot = (int) Math.sqrt(sum);
	     return pivot * pivot == sum ? pivot : -1;
	 }
	
	
	
}
