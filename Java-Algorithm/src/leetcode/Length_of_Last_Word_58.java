package leetcode;

/*Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
*/


public class Length_of_Last_Word_58 {

	public static void main(String[] args) {
		System.out.println(lengthOfLastWord(" hh dd dds "));

	}

	  public  static int lengthOfLastWord(String input) {
		  input = input.trim();
		  if (input.length() == 0) { // ""
			  return 0;
		  }
		  if (!input.contains(" ")) {// " dd "
			  return input.length();
		  }
		  int location = input.lastIndexOf(" "); // " ss ss sss"
		  return input.substring(location).length()-1;
	 }

}
