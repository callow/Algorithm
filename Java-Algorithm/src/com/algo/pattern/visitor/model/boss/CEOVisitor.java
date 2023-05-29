package com.algo.pattern.visitor.model.boss;

import com.algo.pattern.visitor.model.staff.Engineer;
import com.algo.pattern.visitor.model.staff.Manager;

//CEO������
public class CEOVisitor implements Visitor {
	 
	/**
	 * ��CEO��Engineer��ʱ����ʲô
	 */
	 @Override
	 public void visit(Engineer engineer) {
	     System.out.println("����ʦ: " + engineer.name + ", KPI: " + engineer.kpi);
	 }
	
	 /**
	 * ��CEO��Manager��ʱ����ʲô
	 */
	 @Override
	 public void visit(Manager manager) {
	     System.out.println("����: " + manager.name + ", KPI: " + manager.kpi +
	             ", �²�Ʒ����: " + manager.getProducts());
	 }
}
