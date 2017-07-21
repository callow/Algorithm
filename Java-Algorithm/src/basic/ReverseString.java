package basic;

/**
 * 
 * Given s = "the sky is blue", return "blue is sky the"
 *
 */
public class ReverseString {
	
	public static void main(String[] args) {	
		String[] inputArray = "the sky is blue".split(" ");
		
		for (int i = inputArray.length -1; i >= 0; i--) { // ·´×Å±éÀú
			System.out.println(inputArray[i]);	
		}
	}
}
