package com.algo.util.resource;

/**
 * Hash�����ļ���Map Reduce
 *
 */
public class HashSplitUtil {

	/**
	 * 
	 * 32λ�޷�������[0,42��]����һ������40���޷����������ļ���ʹ��1G�ڴ棬�ҳ����ִ�����������<br>
	 * 
	 * UInt = 4 bytes , Map<UInt,UInt> = 4 * 2 = 8 byte
	 * 
	 */
	public static int getMostFrequentNumIn1GMemory(long fourBillion) {

		// 1G / 8 byte �� װ1������¼
		long record1GMemory = 1000000000;

		// 40�� / 1 �� = 40 ���ļ�
		long files = fourBillion / record1GMemory;

		// �����ļ����ַ�ɢ��40��С�ļ��� Map
		for (int i = 0; i < fourBillion; i++) {
			System.out.println("Send to file : " + i % files);
		}

		// ��ÿ���ļ����������Ƶ��ߵġ�< 1G�ڴ�,ÿ�������ļ�����ڴ� Reduce
		int topFrequency = 0;
		for (int i = 0; i < files; i++) {
			topFrequency = Math.max(topFrequency, i);
			System.out.println("Most frequent num: " + topFrequency);
		}
		return topFrequency;
	}

	/**
	 * 
	 * 32λ�޷�������[0,42��]����һ������40���޷����������ļ���ʹ��1G�ڴ棬�ҳ�δ���ֵ�����<br>
	 * 
	 * UInt = 4 bytes , Map<UInt,UInt> = 4 * 2 = 8 byte
	 * 
	 */
	public static long geMissingNumsIn1GMemory(long fourBillion) {
		return 0;
	}

}
