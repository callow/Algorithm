package com.algo.util.difference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 
 * 
 * 2ά��֣���ά�����У����������²�����
 * ÿ���������޸�4���㣺�ã� a,b��-> (c,d) ͳһ��3����ô���ٴ����� ������������εõ�2ά������ÿ��λ�õ�ֵ��
 * 
 * 
 *  ���Ͻǣ�a,b��, ���½ǡ���c,d��2���� +3�� ���½ǡ������Ͻǡ���2���� -3
 *  
 *  0  0  0      0 0 0
 *  +3 0 -3      3 3 0
 *  0  0  0  =>  3 3 0
 *  -3 0 -3	     0 0 0 
 *  
 *  ��ʽ�� �� + �� - ���� + �Լ�
 *  
 *  ÿ���㶼����һ�£��ĳ�����matrix����2ά�������
 *  ��������ʱ�������ܰ���һȦ0�������˺ܶ�߽�����
 * 
 */
public class Difference2DUtil {
	
	/**
	 * 
	 * ��ά����Ʊ����
	 */
	public static boolean isFillStamp(int[][] matrix) {
		return false;
	}
	
}