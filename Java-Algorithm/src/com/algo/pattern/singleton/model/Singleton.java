package com.algo.pattern.singleton.model;

public enum Singleton {
	INSTANCE;
	
	public void run() {
		System.out.println("run");
	}
	
	public void eat() {
		System.out.println("eat");
	}
}
