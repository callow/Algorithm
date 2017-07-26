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
		int distinctNumber = (int)input.chars().distinct().count(); // �о�һ��������� ��������
		if (distinctNumber != input.length()) {
			System.out.println("duplicated");
			return false;
		}
		System.out.println("not duplicated");
		return true;
	}
	
	public static void statisticDuplicate(String input) {
		List<String> items = Arrays.asList(input.split("")); // ͳ��ÿһ����ĸ���ֵĴ���,ע�� ֻ��String[] ����ת����List
		 Map<String, Long> result = items.stream().collect(
				Collectors.groupingBy(
						Function.identity(),
						Collectors.counting()));
		 System.out.println(result);
	}
}
