package com.algo.util.resource;

import java.util.List;
import java.util.PriorityQueue;

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
		return status;
	}

	/**
	 * 32位无符号整数[0,42亿]，有一个包含40亿无符号整数大文件，使用3KB内存，找出1个未出现的数？
	 */

	public static long geMissingNumsIn3KBMemory(long fourBillion) {
		// for loop
		return 0;
	}

	/**
	 * 一个100亿URL的大文件，每个URL = 64B, 找出其中所有重复的URL, 允许失误率
	 */

	public static List<String> findDuplicateURLsFrom10Billion(List<String> input) {
		// 布隆过滤器
		return input;
	}

	/**
	 * 无符号Int， 42亿， 现在有40亿个无符号整数，最多使用3KB内存，如何找到40亿个数的中位数？
	 */
	public static int findMedianFrom4Billion(long input) {
		// 3KB能装512个数
		int[] container = new int[512];

		// 每个container里面统计固定的词频数字：
		// i= 1 => 统计 1 - 1亿
		// i= 2 => 统计 1亿 - 2亿
		// 中位数则位于第20亿的区间， 然后在那个区间找中位数
		long counter = 0;
		int medianLoc = 0;
		for (int i : container) {
			if (counter == 200000000L) {
				medianLoc = i;
			}
		}

		return medianLoc;
	}

	/**
	 * 10G的数字文件，给5G内存，将其排序
	 */
	public static int sortBigFile(long file) {

		// 大根堆统计最小数的词频
		PriorityQueue<Integer> min3 = new PriorityQueue<>();

		// 遍历min3,然后输出到新文件，

		// 继续遍历原10G文件继续找最小的3个 >= min3

		return min3.size();
	}

}
