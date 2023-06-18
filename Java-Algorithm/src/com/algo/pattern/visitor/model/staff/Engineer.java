package com.algo.pattern.visitor.model.staff;

import com.algo.pattern.visitor.model.boss.Visitor;

//����ʦ
public class Engineer extends Staff {

	 public Engineer(String name) {
	     super(name);
	 }
	
	 @Override
	 public void accept(Visitor visitor) {
	     visitor.visit(this);
	 }
	 // ����ʦһ��Ĵ�������
	 public int getCodeLines() {
	     return 5;
	 }
}
