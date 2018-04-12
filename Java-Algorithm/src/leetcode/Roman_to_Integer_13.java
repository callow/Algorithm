package leetcode;
/**
 * 
 * Given a roman numeral, convert it to an integer.
   Input is guaranteed to be within the range from 1 to 3999.
 *
 */
public class Roman_to_Integer_13 {

	public static void main(String[] args) {
		System.out.println(romanToInt("MCMXCVI"));
	}
	
	// 自己的解法
	 public static int romanToInt(String s) {
	   char[] temp =  s.toCharArray();
	   int ICounter = 0,VCounter = 0,XCounter = 0,LCounter = 0,CCounter = 0,DCounter = 0,MCounter = 0;
	   for (int i = 0; i < temp.length; i++) {
		   if (temp[i] == 'I') ICounter++;
		   if (temp[i] == 'X') XCounter++;
		   if (temp[i] == 'V') VCounter++;
		   if (temp[i] == 'L') LCounter++;
		   if (temp[i] == 'C') CCounter++;
		   if (temp[i] == 'D') DCounter++;
		   if (temp[i] == 'M') MCounter++;
	   }
	    return 5*VCounter + 10*XCounter + 50*LCounter +100*CCounter +500*DCounter +1000*MCounter +ICounter;
	 }

}
