package com.algo.util.resource;

import java.io.IOException;

/**
 * Hash分流文件，Map Reduce
 *
 */
public class HashSplitUtil {

	/**
	 * 
	 * 32位无符号整数[0,42亿]，有一个包含40亿无符号整数大文件，使用1G内存，找出出现次数最多的数？<br>
	 * 
	 * UInt = 4 bytes , Map<UInt,UInt> = 4 * 2 = 8 byte
	 * 
	 */
	public static int getMostFrequentNumIn1GMemory(long fourBillion) throws IOException {

		// 1G / 8 byte ≈ 装1亿条记录
		long record1GMemory = 1000000000;

		// 40亿 / 1 亿 = 40 个文件
		long files = fourBillion / record1GMemory;

		// 将大文件数字分散到40个小文件， Map
		for (int i = 0; i < fourBillion; i++) {
			System.out.println("Send to file : " + i % files);
		}

		// 在每个文件中依次求词频最高的。< 1G内存,每次算完文件清空内存 Reduce
		int topFrequency = 0;
		for (int i = 0; i < files; i++) {
			topFrequency = Math.max(topFrequency, i);
			System.out.println("Most frequenct num: " + topFrequency);
		}

		return topFrequency;
	}
}
