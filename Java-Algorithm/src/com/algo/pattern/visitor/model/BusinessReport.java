package com.algo.pattern.visitor.model;

import java.util.LinkedList;
import java.util.List;

import com.algo.pattern.visitor.model.boss.Visitor;
import com.algo.pattern.visitor.model.staff.Engineer;
import com.algo.pattern.visitor.model.staff.Manager;
import com.algo.pattern.visitor.model.staff.Staff;

//Ա��ҵ�񱨱���
public class BusinessReport {

 private List<Staff> mStaffs = new LinkedList<>();

	 public BusinessReport() {
	     mStaffs.add(new Manager("����-A"));
	     mStaffs.add(new Manager("����-B"));
	     
	     mStaffs.add(new Engineer("����ʦ-A"));
	     mStaffs.add(new Engineer("����ʦ-B"));
	     mStaffs.add(new Engineer("����ʦ-C"));
	     mStaffs.add(new Engineer("����ʦ-D"));
	 }

	 /**
	  * Ϊ������չʾ����
	  * @param visitor ��˾�߲㣬��CEO��CTO
	  */
	 public void showReport(Visitor visitor) {
	     for (Staff staff : mStaffs) {
	         staff.accept(visitor);
	     }
	 }
}
