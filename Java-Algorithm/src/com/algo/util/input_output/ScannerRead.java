package com.algo.util.input_output;

import java.util.Scanner;

/**
 * ������Ķ�ȡ��ʽ������Ч�ʲ��Ǻܸ�
 * 
 * ע�⣺ 
 * 
 * nextLine() returns the entire text up to the return line
 * 
 * next() reads a tokenized text based on a given delimiter
 */
public class ScannerRead {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		 // �ȶ�ȡһ��Int
		int i = scanner.nextInt();
		
		 // �ٶ�ȡһ��Double, ע�� nextInt�� nextDouble���ȶ���һ�����е�β������Ҫ�ٵ���һ��������
        double d = scanner.nextDouble();
        scanner.nextLine();
        
        // �ٶ�ȡһ��Sentence String
        String s = scanner.nextLine();

        scanner.close();
        
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
	}
}
