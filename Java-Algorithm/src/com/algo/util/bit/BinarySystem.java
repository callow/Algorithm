package com.algo.util.bit;
/**
 * 
 * 1. �����Ķ����Ʊ� (�������Ķ����� - 1) ��ȡ���� -2 => 2�Ķ�����-1ȡ��  => ~(0010 -1) = ~(0001) = 1110
 * 2. ������֪���Ǹ����� ����ȡ�� + 1.   1001 => 0110 + 1 = 0111 = 7 => -7
 * 3. ����4λ �� �ɱ�ʾ -8 ~ -1�� 0 ~ 7 , 16�����ֵ�״̬
 * 4. 16���ƣ� 0000 =0 0001=1 .....1001 = 9, 1010 =a, 1011 =b ,1100 = c, 1101 =d, 1110 = e, 1111 =f
 * 5. ��ͷ�� 0b��ͷ��ʾ������2���ƣ� 0x��ͷ��ʾ������16����  , 0b 0110 1111 = 0x6f
 * 6. | & ^ ����������� || && һ����·�����ǻ�ȫ����ִ��
 * 7. ���� <<, ���Ҳ���0����
 * 8. ���� >>, ������÷���λ���� >>> �������0����
 */
public class BinarySystem {

	public static void main(String[] args) {
		
		// �Ǹ���
		int a = 78;
		System.out.println(a);
		BitUtil.print(a);
		System.out.println("===a===");
		
		// ����
		int b = -6;
		System.out.println(b);
		BitUtil.print(b);
		System.out.println("===b===");
		
		// ֱ��д�����Ƶ���ʽ�������
		int c = 0b1001110;
		System.out.println(c);
		BitUtil.print(c);
		
		// ֱ��дʮ�����Ƶ���ʽ�������
		// 0100 -> 4
		// 1110 -> e
		// 0x4e -> 01001110
		int d = 0x4e;
		System.out.println(d);
		BitUtil.print(d);
		System.out.println("===d===");
		
		// ~���෴�� = ȡ��+1
		System.out.println(a);
		BitUtil.print(a);
		BitUtil.print(~a);
		int e = ~a + 1;
		System.out.println(e);
		BitUtil.print(e);
		System.out.println("===e===");
		
		// int��long����Сֵ��ȡ�෴��������ֵ�������Լ�
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
		System.out.println("===g��h===");
		
		// <<
		int i = 0b0011010;
		BitUtil.print(i);
		BitUtil.print(i << 1);
		BitUtil.print(i << 2);
		BitUtil.print(i << 3);
		System.out.println("===i << ===");
		
		// �Ǹ��� >> >>>��Ч��һ��
		BitUtil.print(i);
		BitUtil.print(i >> 2);
		BitUtil.print(i >>> 2);
		System.out.println("===i >> >>>===");
		
		// ���� >> >>>��Ч����һ��
		int j = 0b11110000000000000000000000000000;
		BitUtil.print(j);
		BitUtil.print(j >> 2);
		BitUtil.print(j >>> 2);
		System.out.println("===j >> >>>===");
		
		// �Ǹ��� << 1����ͬ�ڳ���2
		// �Ǹ��� << 2����ͬ�ڳ���4
		// �Ǹ��� << 3����ͬ�ڳ���8
		// �Ǹ��� << i����ͬ�ڳ���2��i�η�
		// ...
		// �Ǹ��� >> 1����ͬ�ڳ���2
		// �Ǹ��� >> 2����ͬ�ڳ���4
		// �Ǹ��� >> 3����ͬ�ڳ���8
		// �Ǹ��� >> i����ͬ�ڳ���2��i�η�
		// ֻ�зǸ����������������������Ҫ��
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
