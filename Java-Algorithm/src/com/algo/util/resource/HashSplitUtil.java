package com.algo.util.resource;

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
	public static int getMostFrequentNumIn1GMemory(long fourBillion) {

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
			System.out.println("Most frequent num: " + topFrequency);
		}
		return topFrequency;
	}

	/**
	 * 
	 * 32位无符号整数[0,42亿]，有一个包含40亿无符号整数大文件，使用1G内存，找出未出现的数？<br>
	 * 
	 * UInt = 4 bytes , Map<UInt,UInt> = 4 * 2 = 8 byte
	 * 
	 * 使用位图bitmap：8 bits = 1Byte, 1 出现过， 0 没出现过
	 * 
	 */
	public static long geMissingNumsIn1GMemory(long fourBillion) {

		// 比如准备一个长度为10的int数组
		int[] arr = new int[10]; // 32bits * 10 = 320bits

		// 如果第ith bit位置是106
		int i = 106;

		// 则106th bit在int[] 种的位置是3：arr[3]
		int arrLoc = i / 32;

		// 看看这个106th bit在arr[3]第10位 ：
		int bitLoc = i % 32;

		// 提取出那一位上的状态
		int status = (arr[arrLoc] & (1 << bitLoc)) == 0 ? 0 : 1;

		// 这个数组种二进制种0的位置就是没出现的数字
		return 0;
	}

}
