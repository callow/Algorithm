package leet.code;
/**
 * ���������ж�ĳ�������Ƿ��ǻ�����, 505
 * 
 *
 */
public class Palindrome_Number_9 {
	public static void main(String[] args) {
		System.out.println(isPalindrome1(2147447412));
		System.out.println(isPalindrome2(2112));
	}
	 
	 // �Լ��ķ��� ,��ȡ�ַ���Ȼ��Ա�
	 public static boolean isPalindrome1(int x) {
	     char[] temp = Integer.valueOf(x).toString().toCharArray();
	     String result = "";
	     for (int i = temp.length -1; i >= 0  ; i-- ) {
	    	 result += temp[i];
	     }
	     if (result.equals(Integer.valueOf(x).toString())) {
	    	 return true;
	     }
	     return false;
	 }
	 
	 // ���ֵ�����
	 public static boolean isPalindrome2(int x) {
		    
		    if (x < 0) return false; // ���������ǻ���

		    int p = x; // 505
		    int q = 0; 
		    
		    while (p >= 10){
		        q *= 10; // 0
		        q += p%10; // ��һλ
		        p /= 10; // ����һλ
		    }
		    
		    return q == x / 10 && p == x % 10; // �Ա�ǰ��λ �� ���һλ ���գ�
		}
}
