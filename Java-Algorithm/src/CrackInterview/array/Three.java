package CrackInterview.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Three {
	// ������linkedHashSetȥ������Ҳ������java8�Զ����
	public static void main(String[] args) {
		String input = "ascccd0s";
		removeDuplicate(input);
	}
	
	public static void removeDuplicate(String input) {
		
		String[] ary = input.split(""); // ��String����StringArray
		
		Arrays.asList(ary).stream().distinct().forEach(System.out::print);
		
	}
}
