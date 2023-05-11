package com.algo.pattern.observer;

import com.algo.pattern.observer.model.ObserverA;
import com.algo.pattern.observer.model.ObserverB;
import com.algo.pattern.observer.model.ObserverableObject;

/**
 * 
 * �۲���ģʽ = pub/subģʽ
 * Observable = ���۲������/����
 * Observer = �۲���
 *
 * ���������һ��һ�Զ��������ϵ����һ�������״̬�����ı�ʱ���������������Ķ��󶼵õ�֪ͨ�����Զ�����
 */
public class ObserverUtil {

	public static void main(String[] args) {
		// ��ʼ��
		ObserverableObject observable  = new ObserverableObject();
		
		ObserverA observerA = new ObserverA();
		ObserverB observerB = new ObserverB();
		
		observable.addPropertyChangeListener(observerB);
		observable.addPropertyChangeListener(observerA);
		
		// ���۲���״̬�ı�
		observable.setNews("oh my god");
		
		// �۲���֪��
		System.out.println(observerA.getNews());
		System.out.println(observerB.getNews());
		
	}
}
