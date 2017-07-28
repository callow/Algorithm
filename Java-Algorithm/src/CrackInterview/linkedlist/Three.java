package CrackInterview.linkedlist;

import java.util.LinkedList;

public class Three {
	public static void main(String[] args) {
		LinkedList<Integer> chain = new LinkedList<>();
		chain.add(4);
		chain.add(2);
		chain.add(1);
		chain.add(3);
		chain.add(1);
		pop(0,chain);
	}
	
	public static void pop(int item, LinkedList<Integer> list) {
		list.remove(item);
		list.stream().forEach(System.out::println);
	}
}
