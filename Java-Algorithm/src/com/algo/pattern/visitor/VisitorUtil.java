package com.algo.pattern.visitor;

import com.algo.pattern.visitor.model.BusinessReport;
import com.algo.pattern.visitor.model.boss.CEOVisitor;
import com.algo.pattern.visitor.model.boss.CTOVisitor;

/**
 * 
 * ��ͬ�ϰ�鿴(visit) ��ͬԱ���ı���
 *
 */
public class VisitorUtil {

	  public static void main(String[] args) {
        // ��������
        BusinessReport report = new BusinessReport();
        
        System.out.println("=========== CEO������ ===========");
        report.showReport(new CEOVisitor());
        
        System.out.println("=========== CTO������ ===========");
        report.showReport(new CTOVisitor());
	  }
}
