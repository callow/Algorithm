package com.algo.util.validator;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.sort.SortUtil;

/**
 * �����������������Ϊ��������ǧ���������,���ܿ��Ƴ��� Ҳ���Կ��Ʒ�Χ
 * 
 * �����Ѿ��ѷ�����װ���ˣ�
 * 	
 *  CommonArrayUtil.generateRandomArray
 *  CommonArrayUtil.copyArray
 *  CommonArrayUtil.equal
 *  �ȵȵȵ�
 * 
 */
public class ValidatorUtil {

	public static void main(String[] args) {
		
		// ���������󳤶�
		int N = 200;
		// �������ÿ��ֵ����1~V֮��ȸ������
		int V = 1000;
		// testTimes : ���Դ���
		int testTimes = 50000;
		System.out.println("���Կ�ʼ");
		for (int i = 0; i < testTimes; i++) {
			
			// ����õ�һ�����ȣ�������[0~N-1]
			int n = (int) (Math.random() * N);
			// �õ��������
			int[] arr = CommonArrayUtil.generateRandomArray(n, V); 
			int[] arr1 = CommonArrayUtil.copyArray(arr);
			int[] arr2 = CommonArrayUtil.copyArray(arr);
			int[] arr3 = CommonArrayUtil.copyArray(arr);
			
			SortUtil.selectionSort(arr1);
			SortUtil.bubbleSort(arr2);
			SortUtil.insertSort(arr3);
			
			
			if (!CommonArrayUtil.equals(arr1, arr2) || !CommonArrayUtil.equals(arr1, arr3)) {
				System.out.println("������!");
				
				CommonArrayUtil.printArray(arr1);
				CommonArrayUtil.printArray(arr2);
				CommonArrayUtil.printArray(arr3);
				break;
				// ���д���
				// ��ӡ��ʲô���ӣ������
				// ��ӡ�������ܣ������������ʲô��
				// ����Ҫ�����Ӵ��룬ÿ��������ȥdebug��
			}
		}
		System.out.println("���Խ���");
	}
}
