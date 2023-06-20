package com.algo.tasks.day3;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * ֻ��Сд��ĸ��a~z����ɵ�һ���ַ������������ַ����͵�����String[] arr�У�
	 * �������ĳ�����ַ����������е��ַ�������ȫһ�����ͽ������ַ�������һ�� ���磺baacba��bac������һ��
	 * ��Ȼ���Ȳ�һ�������������ַ���������ȫһ����a��b��c�� ����arr���ж����ࣿ
 *
 */
public class HowManyTypes {

	/**
	 * ���ɣ� ʹ��int ���boolean[] ����ʾÿ���ַ��Ƿ���ֹ�
	 */
	public static int types(String[] arr) {
		Set<Integer> types = new HashSet<>();
		for (String str : arr) {
			char[] chs = str.toCharArray();
			int key = 0;
			for(int i = 0 ; i < chs.length;i++) {
				key |= (1 << (chs[i] - 'a')); // ���� 1��λ�������boolean���� |= 
			}
			types.add(key);
		}
		return types.size();
	}
}
