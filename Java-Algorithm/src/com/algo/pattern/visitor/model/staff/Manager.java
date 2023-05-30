package com.algo.pattern.visitor.model.staff;

import com.algo.pattern.visitor.model.boss.Visitor;

//经理
public class Manager extends Staff {

	 public Manager(String name) {
	     super(name);
	 }
	
	 @Override
	 public void accept(Visitor visitor) {
	     visitor.visit(this);
	 }
	 // 一年做的产品数量
	 public int getProducts() {
	     return 10;
	 }
}
