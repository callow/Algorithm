package com.algo.tasks.day3;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
	 * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
	 * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
 *
 */
public class HowManyTypes {

	/**
	 * 技巧： 使用int 替代boolean[] 来表示每个字符是否出现过
	 */
	public static int types(String[] arr) {
		Set<Integer> types = new HashSet<>();
		for (String str : arr) {
			char[] chs = str.toCharArray();
			int key = 0;
			for(int i = 0 ; i < chs.length;i++) {
				key |= (1 << (chs[i] - 'a')); // 技巧 1：位运算替代boolean技巧 |= 
			}
			types.add(key);
		}
		return types.size();
	}
}
