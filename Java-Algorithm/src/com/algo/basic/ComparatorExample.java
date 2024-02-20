package com.algo.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
/**
 * sort:
 * - score descending
 * - if score =, algabatic order based on name
 *
 */
class Checker implements Comparator<Player>{

	@Override
	public int compare(Player p1, Player p2) {
		return p1.score != p2.score ? p2.score - p1.score : p1.name.compareTo(p2.name);
	}
	
}

class Player {
	String name;
	int score;
	
	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
}


public class ComparatorExample {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		Player[] player = new Player[n];
		Checker checker = new Checker();
		
		for (int i = 0; i < n; i++) {
			player[i] = new Player(scanner.next(),scanner.nextInt());
		}
		scanner.close();
		Arrays.sort(player,checker);
		
		for (int i = 0; i < player.length; i++) {
			System.out.printf("%s %s\n", player[i].name, player[i].score);
		}
	}
}
