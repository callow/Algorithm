package com.algo.util.math;

import com.algo.util.math.impl.GCDUtil;

public class MathUtil {

	/**
	 * ���Լ�� O(log(a)^3) ��50��30 -> 10
	 */
	public static long gcd(long a, long b) {
		return GCDUtil.gcd(a, b);
	}
	
	/**
	 * ��С������: 50,30 -> 150
	 */
	public static long lcm(long a, long b) {
		return GCDUtil.lcm(a, b);
	}
	
	/**
	 * һ������������ܱ� a �� b ��������ô��������ġ������������� n , a , b �����ص� n ����������֡�
		https://leetcode.cn/problems/nth-magical-number/
	 */
	public static int nthMagicalNumber(int n, int a, int b) {
		return GCDUtil.nthMagicalNumber(n, a, b);
	}
	
	
	 /**
	  * �����Ƿ��ཻ��rec1 or rec2 �������� �� �������� -> rec1 = [0,0,2,2], rec2 = [1,1,3,3]
	  * 
	  * ˼·������н������򽻼�Ҳһ���Ǿ��Σ���������������x���ͶӰ �� ��y���ͶӰ�� = һά�߶��Ƿ��н��������� 
	  * 	# rec1 �� rec2 ��ˮƽ��ͶӰ�� x ���ϵ��߶ηֱ�Ϊ (rec1[0], rec1[2]) �� (rec2[0], rec2[2])
	  * 	  �� min(rec1[2], rec2[2]) > max(rec1[0], rec2[0]) ʱ���������߶��н���
	  *     # y��ͬ��
	  * 
	  * https://leetcode.cn/problems/rectangle-overlap/description/
	  */
	 public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		 return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])  // x �����ͶӰ
				 && 
	             Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1])); // y �����ͶӰ

	 }
	 
	 
	 
	   /**
	    * ��֧�㣺 �������У� ��(1-x) = ��(x~n), ��x? https://leetcode.com/problems/find-the-pivot-integer/description/
	     sum(1~x) = sum(x~n)
	
	     �������� �õȲ�������͹�ʽ ��n(a1+an) / 2
	         sum(1~x) = x(x+1) / 2
	         sum(x~n) = (x+n)(n-x+1) / 2 where ����n= n-x+1
	
	         x = (n^2 + n) / 2
	  */
	 public int pivotInteger(int n) {
	     
	     int sum = (n * (n + 1) / 2);
	     int pivot = (int) Math.sqrt(sum);
	     return pivot * pivot == sum ? pivot : -1;
	 }
	 
	 
	 /**
	  * �������� https://leetcode.com/problems/happy-number/
	  * 
	  * �⣺ ͨ����ٹ۲쵽��ʮ�����зǿ������Ǵ���һ��ѭ���У�4��16��37��58��89��145��42��20��4 �������滻��������������һ����ֱ��return false
	  */
	 public static boolean isHappy(int n) {
	      while(n != 1) {
	    	  if (n == 4|| n == 16 || n ==37|| n==58 || n==89 || n == 145 || n == 42 || n ==20) {
	    		  return false;
	    	  }
	    	  n = split(n);
	      }
	      return true;
	 }
	 private static int split(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
	     return totalSum;
	 }
	
	
	
}
