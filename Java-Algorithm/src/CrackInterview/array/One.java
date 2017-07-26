package CrackInterview.array;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class One {
	
	public static void main(String[] args) {
		isAllUnique("abcdeddcc");
		statisticDuplicate("abcdeddcc");
	}
	
	public static  boolean isAllUnique(String input) {
		int distinctNumber = (int)input.chars().distinct().count(); // 研究一下这个方法 做个博客
		if (distinctNumber != input.length()) {
			System.out.println("duplicated");
			return false;
		}
		System.out.println("not duplicated");
		return true;
	}
	
	public static void statisticDuplicate(String input) {
		List<String> items = Arrays.asList(input.split("")); // 统计每一个字母出现的次数,注意 只有String[] 才能转换成List
		 Map<String, Long> result = items.stream().collect(
				Collectors.groupingBy(
						Function.identity(),
						Collectors.counting()));
		 System.out.println(result);
	}
}
