package com.algo.util.manacher;

import com.algo.util.common.CommonStringUtil;

/**
 * 
 * 最长结构算法回文 O(N) <br>
 * 
 * 1. 走到每个位置然后向左右扩找回文到不能扩为止， 前面扩的行为会加速后面扩的行为 <br>
 * 2. 12321 -> 回文直径：5 ，回文半径：3 <br>
 * 3. 1221  -> 回文直径：4 ，回文半径：2 <br>
 * 4. 回文半径Array: 记录每个位置回文半径的Int[] <br>
 * 5. 最右回文边界R： 不管从哪扩的，只要回文边界更靠右了，就赋值给R 变量 <br>
 * 6. 取得R时的中心c： 取得最右回文有边界的回文中心在哪 就赋值给c 变量 <br>
 * 7. 若i没被R罩住无法优化，若i被R罩住可以优化，对应的i'(i对与c的对称点)必存在 L(R对c的对称点) 必存在 Topo:[L i' c i R] <br><br>
 * 
 * cases: <br>
 * 	1. 若i'自身扩的回文在[L,R]内, 则i的回文与i'一样长 O（1）. 回文半径：  = i'的<br>
 *  2. 若i'自身扩的回文在[L,R]外, 则i~R的距离 = i的回文半径 O(1). 回文半径： R - i<br>
 *  3. 若i'自身的回文左边界与L重合, 则 至少i~R为i的回文半径，然后继续向左右扩找更大 O(N). 回文半径：  >= i'的 
 *   
 */
public class Manacher {

	public static int maxPalindromicLen(String s) {
		if (CommonStringUtil.isEmpty(s)) {
			return 0;
		}
		// "12132" -> "#1#2#1#3#2#"
		char[] str = CommonStringUtil.manacherString(s);
		// 回文半径数组 : 回文半径数组中的最大值 /2 就是答案，因为处理串(#)长度 * 2了
		int[] pArr = new int[str.length];
		// 中心点
		int C = -1;
		// R代表最右的扩成功的位置。coding：最右的扩成功位置的，再下一个位置
		int R = -1;
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < str.length; i++) { // 0 1 2， 每个位置去扩
			/**
			 * R > i (就是 i被R罩住)： 3种case
			 * 	 - pArr[2 * C - i] ： 这个就是i'
			 *   - R- i : 这个是 i~R 距离
			 *   - min(..,..) : 返回哪个区域是不用验的，直接知道回文半径
			 */
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
		
			
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				/**
				 * str[i + pArr[i]] ： 再往右的字符
				 * str[i - pArr[i]]) ： 再往左的字符
				 * 再往右的字符 = 再往左的字符 => 回文半径++
				 * 不用验的部分一进while就break
				 */
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			
			// 这时候i的回文半径全部求出，如果把R推的更往右了，更新R， 更新C
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			// 每一步的回文半径最大值记下
			max = Math.max(max, pArr[i]);
		}
		// 因为处理串 是原串的两倍，因此半径max -1 就是原始串的最大回文字串的回文直径的长度
		return max - 1;
	}
	
	/**
	 * 原始串的结尾位置(x) = 处理串的结尾位置 - 1 / 2 <br>
	 * maxPalindromicLen 长度又已知<br>
	 * 因此subString()一下就知道了
	 */
	
	public static String maxPalindromicStr(String s) {
		if (CommonStringUtil.isEmpty(s)) { 
			return null;
		}
        StringBuilder stringBuilder =  CommonStringUtil.manacherString2(s);
        int R = 0;
        int C = 0;
        //求len中的最大
        int answer = 0;
        //answer最大时的中心
        int index = 0;
        int pArr[] = new int[stringBuilder.length() ];
        for (int i = 1; i < stringBuilder.length(); i++) {
        	pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < stringBuilder.length() && i - pArr[i] > -1) {
				if (stringBuilder.charAt(i + pArr[i]) == stringBuilder.charAt(i - pArr[i]))
					pArr[i]++;
				else {
					break;
				}
			}
            if(pArr[i] + i > R) {
                R = pArr[i] + i;
                C = i;
            }
            if(pArr[i] > answer) {
                answer = pArr[i];
                index = i;
            }
        }
        return stringBuilder.substring(index - answer + 1, index + answer).replace("#", "");
    }
	
	/**
	 * 在一个字符串末尾加字符串，让整体变成回文 / 必须包含最后一个字符时，最长回文串多长<br><br>
	 * 
	 * 思路： 把前面不是回文的部分逆序，添加到原字符串末尾即可。<br>
	 * 
	 */
	
	public static String makePalindromicStr(String s) {
		if (CommonStringUtil.isEmpty(s)) { 
			return null;
		}
		char[] str = CommonStringUtil.manacherString(s);;
		int[] pArr = new int[str.length];
		int C = -1;
		int R = -1;
		int maxContainsEnd = -1;
		for (int i = 0; i != str.length; i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			if (R == str.length) {
				maxContainsEnd = pArr[i];
				break;
			}
		}
		char[] res = new char[s.length() - maxContainsEnd + 1];
		for (int i = 0; i < res.length; i++) {
			res[res.length - 1 - i] = str[i * 2 + 1];
		}
		return String.valueOf(res);
	}
	
	
	
}
