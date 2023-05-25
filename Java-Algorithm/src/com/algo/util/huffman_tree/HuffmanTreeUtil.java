package com.algo.util.huffman_tree;

import java.util.HashMap;

/**
 * 
 * 哈夫曼树/编码：用于字符串压缩
 * <br>
 * 一篇文章，a出现3次，b出现29次, c出现5次，统计每个字符出现的频率，统计后生成哈夫曼树后，将原始串通过0/1编成byte文件，传输代价最低
 * 
 * a:10, b:11, c:00, d:010, e: 011 -> 生成字典树样子
 *     编码：原始文章：bcadc... = 11001001000...
       解码： 在树上滑动，一旦触底就结出一个字符
       
         注:最后一个编码表示最后一个字符几位有效：......101003 = 最后只有3位有效
 *  
 *
 */
public class HuffmanTreeUtil {
	
	public static void example() {
		
		// 原始字符串
		String input = "CBBBAABBACAABDDEFBA";
		System.out.println(input);
		
		// countMap是根据原始字符串建立的词频表
		HashMap<Character, Integer> countMap = HuffmanTree.countMap(input);
		
		// hf是根据countMap生成的哈夫曼编码表
		HashMap<Character, String> hf = HuffmanTree.huffmanForm(countMap);
		
		// huffmanEncode是原始字符串转译后的哈夫曼编码
		String huffmanEncode = HuffmanTree.huffmanEncode(input, hf);
		System.out.println(huffmanEncode);
		
		// huffmanDecode是哈夫曼编码还原成的原始字符串
		String huffmanDecode = HuffmanTree.huffmanDecode(huffmanEncode, hf);
		System.out.println(huffmanDecode);
		
		
		
		
	}
}
