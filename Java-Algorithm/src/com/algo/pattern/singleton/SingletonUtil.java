package com.algo.pattern.singleton;

import com.algo.pattern.singleton.model.Singleton;

public class SingletonUtil {

	public static void main(String[] args) {
		Singleton objA = Singleton.INSTANCE;
		Singleton objB = Singleton.INSTANCE;
		
		System.out.println("�Ƿ�ʽ������ " + (objA == objB ? "Yes" : " No"));
		objA.run();
		objB.eat();
		
	}
}
