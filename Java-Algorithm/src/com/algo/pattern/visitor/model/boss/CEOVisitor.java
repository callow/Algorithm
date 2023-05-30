package com.algo.pattern.visitor.model.boss;

import com.algo.pattern.visitor.model.staff.Engineer;
import com.algo.pattern.visitor.model.staff.Manager;

//CEO访问者
public class CEOVisitor implements Visitor {
	 
	/**
	 * 当CEO看Engineer的时候做什么
	 */
	 @Override
	 public void visit(Engineer engineer) {
	     System.out.println("工程师: " + engineer.name + ", KPI: " + engineer.kpi);
	 }
	
	 /**
	 * 当CEO看Manager的时候做什么
	 */
	 @Override
	 public void visit(Manager manager) {
	     System.out.println("经理: " + manager.name + ", KPI: " + manager.kpi +
	             ", 新产品数量: " + manager.getProducts());
	 }
}
