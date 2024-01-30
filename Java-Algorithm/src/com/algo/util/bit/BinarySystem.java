package com.algo.util.bit;
/**
 * 
 * 1. 负数的二进制表达： (他正数的二进制 - 1) 后取反。 -2 => 2的二进制-1取反  => ~(0010 -1) = ~(0001) = 1110
 * 2. 二进制知道是负几： 整体取反 + 1.   1001 => 0110 + 1 = 0111 = 7 => -7
 * 3. 若有4位 ： 可表示 -8 ~ -1， 0 ~ 7 , 16个数字的状态
 * 4. 16进制： 0000 =0 0001=1 .....1001 = 9, 1010 =a, 1011 =b ,1100 = c, 1101 =d, 1110 = e, 1111 =f
 * 5. 开头： 0b开头表示下面是2进制， 0x开头表示下面是16进制  , 0b 0110 1111 = 0x6f
 * 6. | & ^ 运算符不会像 || && 一样短路，它们会全部都执行
 * 7. 左移 <<, 最右侧用0补。
 * 8. 右移 >>, 最左侧用符号位补。 >>> 最左侧用0补。
 */
public class BinarySystem {

	public static void main(String[] args) {
		
		// 非负数
		int a = 78;
		System.out.println(a);
		BitUtil.print(a);
		System.out.println("===a===");
		
		// 负数
		int b = -6;
		System.out.println(b);
		BitUtil.print(b);
		System.out.println("===b===");
		
		// 直接写二进制的形式定义变量
		int c = 0b1001110;
		System.out.println(c);
		BitUtil.print(c);
		
		// 直接写十六进制的形式定义变量
		// 0100 -> 4
		// 1110 -> e
		// 0x4e -> 01001110
		int d = 0x4e;
		System.out.println(d);
		BitUtil.print(d);
		System.out.println("===d===");
		
		// ~、相反数 = 取反+1
		System.out.println(a);
		BitUtil.print(a);
		BitUtil.print(~a);
		int e = ~a + 1;
		System.out.println(e);
		BitUtil.print(e);
		System.out.println("===e===");
		
		// int、long的最小值，取相反数、绝对值，都是自己
		int f = Integer.MIN_VALUE;
		System.out.println(f);
		BitUtil.print(f);
		System.out.println(-f);
		BitUtil.print(-f);
		System.out.println(~f + 1);
		BitUtil.print(~f + 1);
		System.out.println("===f===");
		
		//-------------------------------------------------------------------------------------
		
		// | & ^
		int g = 0b0001010;
		int h = 0b0001100;
		BitUtil.print(g | h);
		BitUtil.print(g & h);
		BitUtil.print(g ^ h);
		System.out.println("===g、h===");
		
		// <<
		int i = 0b0011010;
		BitUtil.print(i);
		BitUtil.print(i << 1);
		BitUtil.print(i << 2);
		BitUtil.print(i << 3);
		System.out.println("===i << ===");
		
		// 非负数 >> >>>，效果一样
		BitUtil.print(i);
		BitUtil.print(i >> 2);
		BitUtil.print(i >>> 2);
		System.out.println("===i >> >>>===");
		
		// 负数 >> >>>，效果不一样
		int j = 0b11110000000000000000000000000000;
		BitUtil.print(j);
		BitUtil.print(j >> 2);
		BitUtil.print(j >>> 2);
		System.out.println("===j >> >>>===");
		
		// 非负数 << 1，等同于乘以2
		// 非负数 << 2，等同于乘以4
		// 非负数 << 3，等同于乘以8
		// 非负数 << i，等同于乘以2的i次方
		// ...
		// 非负数 >> 1，等同于除以2
		// 非负数 >> 2，等同于除以4
		// 非负数 >> 3，等同于除以8
		// 非负数 >> i，等同于除以2的i次方
		// 只有非负数符合这个特征，负数不要用
		int k = 10;
		System.out.println(k);
		System.out.println(k << 1);
		System.out.println(k << 2);
		System.out.println(k << 3);
		System.out.println(k >> 1);
		System.out.println(k >> 2);
		System.out.println(k >> 3);
		System.out.println("===k===");
		
		
		
	}
}
