package com.algo.pattern.singleton;

import com.algo.pattern.singleton.model.Singleton;

/**
 * 
 * 保证一个类仅有一个实例，并提供一个访问它的全局访问点
 *
 */
public class SingletonUtil {

	public static void main(String[] args) {
		Singleton objA = Singleton.INSTANCE;
		Singleton objB = Singleton.INSTANCE;
		
		System.out.println("是否式单例： " + (objA == objB ? "Yes" : " No"));
		objA.run();
		objB.eat();
		
	}
}
