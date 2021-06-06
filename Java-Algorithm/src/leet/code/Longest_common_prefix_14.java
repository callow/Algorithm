package leet.code;

/**
 * 	�������ַ����������ǰ׺��������������ַ������������ǰ׺
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
	    while(i < strs.length) { // ��������Ԫ��,�ӵڶ�����ʼ����
	        while(strs[i].indexOf(pre) != 0) { // ��� pre ����λ������Ԫ�ص�ǰ׺λ��
	            pre = pre.substring(0,pre.length()-1); // ��  pre Ҫ��һ��Ȼ������ж�
	        }
	        i++; // ��һ��Ԫ��
	    }
	    return pre;
	}

}
