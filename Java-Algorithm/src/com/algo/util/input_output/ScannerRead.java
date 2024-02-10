package com.algo.util.input_output;

import java.util.Scanner;

/**
 * 最基本的读取方式，但是效率不是很高
 * 
 * 注意： 
 * 
 * nextLine() returns the entire text up to the return line
 * 
 * next() reads a tokenized text based on a given delimiter
 */
public class ScannerRead {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		 // 先读取一个Int
		int i = scanner.nextInt();
		
		 // 再读取一个Double, 注意 nextInt， nextDouble，等都又一个空行的尾流，需要再调用一次消除掉
        double d = scanner.nextDouble();
        scanner.nextLine();
        
        // 再读取一个Sentence String
        String s = scanner.nextLine();

        scanner.close();
        
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
	}
}
