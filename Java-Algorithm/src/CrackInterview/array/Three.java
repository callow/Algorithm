package CrackInterview.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Three {
	// 可以用linkedHashSet去遍历，也可以用java8自动获得
	public static void main(String[] args) {
		String input = "ascccd0s";
		removeDuplicate(input);
	}
	
	public static void removeDuplicate(String input) {
		
		String[] ary = input.split(""); // 把String换成StringArray
		
		Arrays.asList(ary).stream().distinct().forEach(System.out::print);
		
	}
}
