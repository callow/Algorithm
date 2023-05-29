package com.algo.pattern.Memento;

import com.algo.pattern.Memento.model.CareTaker;
import com.algo.pattern.Memento.model.Originator;

/**
 * 
 * 1�����ҩ�� 2������Ϸʱ�Ĵ浵�� 3��Windows ��� ctrl + z�� 4��IE �еĺ��ˡ� 5�����ݿ���������
 *
 */
public class MementoUtil {
	   public static void main(String[] args) {
		   
		      Originator originator = new Originator();
		      CareTaker careTaker = new CareTaker();
		      
		      originator.setState("State #1");
		      originator.setState("State #2");
		      careTaker.add(originator.saveStateToMemento());
		      
		      originator.setState("State #3");
		      careTaker.add(originator.saveStateToMemento());
		      originator.setState("State #4");
		 
		      System.out.println("Current State: " + originator.getState());    
		      originator.getStateFromMemento(careTaker.get(0));
		      System.out.println("First saved State: " + originator.getState());
		      originator.getStateFromMemento(careTaker.get(1));
		      System.out.println("Second saved State: " + originator.getState());
		   }
}
