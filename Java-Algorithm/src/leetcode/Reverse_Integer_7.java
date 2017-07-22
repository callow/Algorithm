package leetcode;
/**
 * Example1: x = 123, return 321
   Example2: x = -123, return -321
 * 
 *  If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 *  Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. 
 *  How should you handle such cases?
 */
public class Reverse_Integer_7 {
	public static void main(String[] args) {
		//System.out.println(reverse1(542000)); // ������������ û�п��� 000
		System.out.println(reverse2(1012034));
	}
	
	 /**
	  * 
	  * method 1
	  */
	 public static int reverse1(int x) {
		if (Integer.valueOf(x).toString().toCharArray().length > 9 ) {
			return 0;
		}
	    char[] temp  = removeTail0(Integer.valueOf(x).toString()).toCharArray();
	    String result = "";
	    for (int a = temp.length -1; a >= 0; a--) { // ���ű���
	    	result += temp[a];
	    }
	    return Integer.valueOf(result);
	 }
	 
	 // ȥ��ĩβ 0 
	 public static String removeTail0(String str) {
	      if(!str.substring(str.length() -1).equals("0")) {  
	         return str;  
	      } 
	         return removeTail0(str.substring(0, str.length() -1)); 
	 }
	 
	 /**
	  * method 2 �������㷨
	  */
	 public static int reverse2(int x) {
	     int result = 0;

	     while (x != 0) {
	    	 
	         int tail = x % 10; // �㷨��ͣ�����λ = ������
	         int newResult = result * 10 + tail; // ����һ����һλ4 34 234 ... 
	         
	         if ((newResult - tail) / 10 != result) { 
	        	 return 0; 
	         }
	         result = newResult;
	         x = x / 10; // �� /10 �͵��� ���ż�һλ�� ����
	     }
	     return result;
	 }
	  
}
