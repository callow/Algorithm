package com.algo.util.huffman_tree;

import java.util.HashMap;

/**
 * 
 * ��������/���룺�����ַ���ѹ��
 * <br>
 * һƪ���£�a����3�Σ�b����29��, c����5�Σ�ͳ��ÿ���ַ����ֵ�Ƶ�ʣ�ͳ�ƺ����ɹ��������󣬽�ԭʼ��ͨ��0/1���byte�ļ�������������
 * 
 * a:10, b:11, c:00, d:010, e: 011 -> �����ֵ�������
 *     ���룺ԭʼ���£�bcadc... = 11001001000...
       ���룺 �����ϻ�����һ�����׾ͽ��һ���ַ�
       
         ע:���һ�������ʾ���һ���ַ���λ��Ч��......101003 = ���ֻ��3λ��Ч
 *  
 *
 */
public class HuffmanTreeUtil {
	
	public static void example() {
		
		// ԭʼ�ַ���
		String input = "CBBBAABBACAABDDEFBA";
		System.out.println(input);
		
		// countMap�Ǹ���ԭʼ�ַ��������Ĵ�Ƶ��
		HashMap<Character, Integer> countMap = HuffmanTree.countMap(input);
		
		// hf�Ǹ���countMap���ɵĹ����������
		HashMap<Character, String> hf = HuffmanTree.huffmanForm(countMap);
		
		// huffmanEncode��ԭʼ�ַ���ת���Ĺ���������
		String huffmanEncode = HuffmanTree.huffmanEncode(input, hf);
		System.out.println(huffmanEncode);
		
		// huffmanDecode�ǹ��������뻹ԭ�ɵ�ԭʼ�ַ���
		String huffmanDecode = HuffmanTree.huffmanDecode(huffmanEncode, hf);
		System.out.println(huffmanDecode);
		
		
		
		
	}
}
