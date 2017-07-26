package CrackInterview.array;

public class Five {

	public static void main(String[] args) {
		String input = "asa     dsd  sds dsd sfsf fdf sd ";
		//String newString = input.replaceAll(" ", "%20");
		//System.out.println(newString);
		
		String newString2 = input.replaceAll("\\s+", "%20"); // 空格出现一次或多次
		System.out.println(newString2);
	}
}
