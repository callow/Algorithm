package com.algo.pattern.observer;

import com.algo.pattern.observer.model.ObserverA;
import com.algo.pattern.observer.model.ObserverB;
import com.algo.pattern.observer.model.ObserverableObject;

/**
 * 
 * 观察者模式 = pub/sub模式
 * Observable = 被观察的物体/事务
 * Observer = 观察着
 *
 * 定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新
 */
public class ObserverUtil {

	public static void main(String[] args) {
		// 初始化
		ObserverableObject observable  = new ObserverableObject();
		
		ObserverA observerA = new ObserverA();
		ObserverB observerB = new ObserverB();
		
		observable.addPropertyChangeListener(observerB);
		observable.addPropertyChangeListener(observerA);
		
		// 被观察物状态改变
		observable.setNews("oh my god");
		
		// 观察者知晓
		System.out.println(observerA.getNews());
		System.out.println(observerB.getNews());
		
	}
}
