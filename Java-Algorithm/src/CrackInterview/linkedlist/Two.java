package CrackInterview.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class Two {
	public static void main(String[] args) {
		LinkedList<Integer> chain = new LinkedList<>();
		chain.add(1);
		chain.add(2);
		chain.add(1);
		chain.add(3);
		chain.add(1);
		
		findNToLast(chain);
	}
	
	public static void findNToLast(LinkedList<Integer> input) {
		List<Integer> result = input.subList(2, input.size());
		
		result.stream().forEach(System.out::println);
	}
}
