package com.algo.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Priorities {
	
	PriorityQueue<Student> pq = new PriorityQueue<>();
	
	public List<Student> getStudents(List<String> events) {
		return null;
	}
}

class Student {
	int id;
	String name;
	double cgpa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCGPA(double cgpa) {
		this.cgpa = cgpa;
	}
}

public class PriorityQueueExample {
	private final static Scanner scanner = new Scanner(System.in);
	private final static Priorities priority = new Priorities();

	public static void main(String[] args) {
		int totalEvent = Integer.parseInt(scanner.nextLine());
		List<String> events = new ArrayList<>();
		
		while(totalEvent-- != 0) {
			String event = scanner.nextLine();
			events.add(event);
		}
		
		List<Student> students = priority.getStudents(events);
		
		if(students.isEmpty()) {
			System.out.println("EMPTY");
		} else {
			for(Student student : students) {
				System.out.println(student.getName());
			}
		}
	}
}
