package leet.code;

/**
 * 	求所有字符串的最长公共前缀，即数组的所有字符串都包含这个前缀
 * 
 *
 */
public class Longest_common_prefix_14 {

	public static void main(String[] args) {
		String[] input = {"ca", "cc", "ccac", "cccq", "ccd"};
		System.out.println(longestCommonPrefix(input));
	}
	
	public static String longestCommonPrefix(String[] strs) {
	    if(strs == null || strs.length == 0)    return "";
	    String pre = strs[0];
	    int i = 1;
	    while(i < strs.length) { // 遍历所有元素,从第二个开始遍历
	        while(strs[i].indexOf(pre) != 0) { // 如果 pre 不是位于其他元素的前缀位置
	            pre = pre.substring(0,pre.length()-1); // 则  pre 要缩一下然后继续判断
	        }
	        i++; // 下一个元素
	    }
	    return pre;
	}

}
