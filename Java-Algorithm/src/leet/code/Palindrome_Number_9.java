package leet.code;
/**
 * 回文数，判断某个数字是否是回文数, 505
 * 
 *
 */
public class Palindrome_Number_9 {
	public static void main(String[] args) {
		System.out.println(isPalindrome1(2147447412));
		System.out.println(isPalindrome2(2112));
	}
	 
	 // 自己的方法 ,截取字符串然后对比
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
	 
	 // 高手的作答
	 public static boolean isPalindrome2(int x) {
		    
		    if (x < 0) return false; // 负数都不是回文

		    int p = x; // 505
		    int q = 0; 
		    
		    while (p >= 10){
		        q *= 10; // 0
		        q += p%10; // 加一位
		        p /= 10; // 左移一位
		    }
		    
		    return q == x / 10 && p == x % 10; // 对比前三位 和 最后一位 妙哉！
		}
}
