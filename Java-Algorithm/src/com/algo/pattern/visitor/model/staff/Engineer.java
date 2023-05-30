package com.algo.pattern.visitor.model.staff;

import com.algo.pattern.visitor.model.boss.Visitor;

//工程师
public class Engineer extends Staff {

	 public Engineer(String name) {
	     super(name);
	 }
	
	 @Override
	 public void accept(Visitor visitor) {
	     visitor.visit(this);
	 }
	 // 工程师一年的代码数量
	 public int getCodeLines() {
	     return 5;
	 }
}
