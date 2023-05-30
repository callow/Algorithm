package com.algo.pattern.visitor.model.staff;

import com.algo.pattern.visitor.model.boss.Visitor;

//����
public class Manager extends Staff {

	 public Manager(String name) {
	     super(name);
	 }
	
	 @Override
	 public void accept(Visitor visitor) {
	     visitor.visit(this);
	 }
	 // һ�����Ĳ�Ʒ����
	 public int getProducts() {
	     return 10;
	 }
}
