package com.algo.tasks.day6;

/**
 * һ������arr[] , ���ֺͺ�����������Щ�����ü����֣�˭���Ȱ����һ���õ�0��Ӯ��
 * 
 *  ���� <> 0 => ����Ӯ
 *  ���� == 0 => ����Ӯ
 */
public class Nim {

	// ��֤arr����������
	public static void win(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		if (eor == 0) {
			System.out.println("����Ӯ");
		} else {
			System.out.println("����Ӯ");
		}
	}
}
