package CrackInterview.array;

import java.util.Arrays;

public class Three {
	// ������linkedHashSetȥ������Ҳ������java8�Զ����
	public static void main(String[] args) {
		removeDuplicate("ascccd0s");
	}
	
	public static void removeDuplicate(String input) {
		
		String[] ary = input.split(""); // ��String����String[]
		
		Arrays.asList(ary).stream().distinct().forEach(System.out::print);
		
	}
}
