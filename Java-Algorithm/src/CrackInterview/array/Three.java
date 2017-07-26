package CrackInterview.array;

import java.util.Arrays;

public class Three {
	// 可以用linkedHashSet去遍历，也可以用java8自动获得
	public static void main(String[] args) {
		removeDuplicate("ascccd0s");
	}
	
	public static void removeDuplicate(String input) {
		
		String[] ary = input.split(""); // 把String换成String[]
		
		Arrays.asList(ary).stream().distinct().forEach(System.out::print);
		
	}
}
