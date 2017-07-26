package CrackInterview.array;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Four {

	public static void main(String[] args) {
		System.out.println(isAnagram("apple", "papel"));
		System.out.println(isAnagram("apple", "paper"));
	}
	
	public static boolean isAnagram(String str1, String str2) {
		int a = (int) str1.chars().distinct().count();
		int b = (int) str1.chars().distinct().count();
		
		if (a == b) {
			List<String> input1 = Arrays.asList(str1.split(""));
			Map<String,Long> statistics  = input1.stream().collect(Collectors
					.groupingBy(Function.identity(),Collectors.counting()));  // 收集统计数字
			
			for (String item : statistics.keySet()) { // 遍历统计结果
				if (!str2.contains(item)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
