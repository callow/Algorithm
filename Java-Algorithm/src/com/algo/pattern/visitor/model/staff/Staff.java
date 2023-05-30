package com.algo.pattern.visitor.model.staff;

import java.util.Random;

import com.algo.pattern.visitor.model.boss.Visitor;

//Ա������
public abstract class Staff {

	 public String name;
	 public int kpi;// Ա��KPI
	
	 public Staff(String name) {
	     this.name = name;
	     kpi = new Random().nextInt(10);
	 }
	 // ���ķ���������Visitor�ķ���
	 public abstract void accept(Visitor visitor);
}
