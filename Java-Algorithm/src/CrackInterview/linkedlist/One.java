package CrackInterview.linkedlist;

import java.util.LinkedList;

public class One {
	public static void main(String[] args) {
		LinkedList<Integer> chain = new LinkedList<>();
		chain.add(1);
		chain.add(2);
		chain.add(1);
		chain.add(3);
		chain.add(1);
		
		chain.stream().distinct().forEach(System.out::println);
	}
}
