package com.algo.pattern.visitor;

import com.algo.pattern.visitor.model.BusinessReport;
import com.algo.pattern.visitor.model.boss.CEOVisitor;
import com.algo.pattern.visitor.model.boss.CTOVisitor;

/**
 * 
 * 不同老板查看(visit) 不同员工的表现
 *
 */
public class VisitorUtil {

	  public static void main(String[] args) {
        // 构建报表
        BusinessReport report = new BusinessReport();
        
        System.out.println("=========== CEO看报表 ===========");
        report.showReport(new CEOVisitor());
        
        System.out.println("=========== CTO看报表 ===========");
        report.showReport(new CTOVisitor());
	  }
}
