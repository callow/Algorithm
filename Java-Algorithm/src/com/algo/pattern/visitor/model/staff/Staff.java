package com.algo.pattern.visitor.model.staff;

import java.util.Random;

import com.algo.pattern.visitor.model.boss.Visitor;

//员工基类
public abstract class Staff {

	 public String name;
	 public int kpi;// 员工KPI
	
	 public Staff(String name) {
	     this.name = name;
	     kpi = new Random().nextInt(10);
	 }
	 // 核心方法，接受Visitor的访问
	 public abstract void accept(Visitor visitor);
}
