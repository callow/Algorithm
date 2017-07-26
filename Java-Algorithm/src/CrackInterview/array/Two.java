package CrackInterview.array;

public class Two {

	public static void main(String[] args) {
		
		reverse("abc");
	}
	
	public static void reverse(String input) {
		char[] inputC = input.toCharArray();
		StringBuilder builder = new StringBuilder("null");
		for (int i = inputC.length-1; i>=0; i--) {
			builder.append(inputC[i]);
		}
		System.out.println(builder.toString());
	}

}
