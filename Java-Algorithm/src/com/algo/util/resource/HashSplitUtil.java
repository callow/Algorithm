package com.algo.util.resource;

import java.util.List;
import java.util.PriorityQueue;

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
	 * ʹ��λͼbitmap��8 bits = 1Byte, 1 ���ֹ��� 0 û���ֹ�
	 * 
	 */
	public static long geMissingNumsIn1GMemory(long fourBillion) {

		// ����׼��һ������Ϊ10��int����
		int[] arr = new int[10]; // 32bits * 10 = 320bits

		// �����ith bitλ����106
		int i = 106;

		// ��106th bit��int[] �ֵ�λ����3��arr[3]
		int arrLoc = i / 32;

		// �������106th bit��arr[3]��10λ ��
		int bitLoc = i % 32;

		// ��ȡ����һλ�ϵ�״̬
		int status = (arr[arrLoc] & (1 << bitLoc)) == 0 ? 0 : 1;

		// ��������ֶ�������0��λ�þ���û���ֵ�����
		return status;
	}

	/**
	 * 32λ�޷�������[0,42��]����һ������40���޷����������ļ���ʹ��3KB�ڴ棬�ҳ�1��δ���ֵ�����
	 */

	public static long geMissingNumsIn3KBMemory(long fourBillion) {
		// for loop
		return 0;
	}

	/**
	 * һ��100��URL�Ĵ��ļ���ÿ��URL = 64B, �ҳ����������ظ���URL, ����ʧ����
	 */

	public static List<String> findDuplicateURLsFrom10Billion(List<String> input) {
		// ��¡������
		return input;
	}

	/**
	 * �޷���Int�� 42�ڣ� ������40�ڸ��޷������������ʹ��3KB�ڴ棬����ҵ�40�ڸ�������λ����
	 */
	public static int findMedianFrom4Billion(long input) {
		// 3KB��װ512����
		int[] container = new int[512];

		// ÿ��container����ͳ�ƹ̶��Ĵ�Ƶ���֣�
		// i= 1 => ͳ�� 1 - 1��
		// i= 2 => ͳ�� 1�� - 2��
		// ��λ����λ�ڵ�20�ڵ����䣬 Ȼ�����Ǹ���������λ��
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
	 * 10G�������ļ�����5G�ڴ棬��������
	 */
	public static int sortBigFile(long file) {

		// �����ͳ����С���Ĵ�Ƶ
		PriorityQueue<Integer> min3 = new PriorityQueue<>();

		// ����min3,Ȼ����������ļ���

		// ��������ԭ10G�ļ���������С��3�� >= min3

		return min3.size();
	}

}
