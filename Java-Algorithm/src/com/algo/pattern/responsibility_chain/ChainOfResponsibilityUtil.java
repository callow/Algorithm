package com.algo.pattern.responsibility_chain;

import com.algo.pattern.responsibility_chain.model.Chain;

public class ChainOfResponsibilityUtil {
	
	/**
	 * 
	 * ������ģʽ�������������ͣ�����������ϲ�ͬ�Ĵ�����ȥ����
	 */
	public static void main(String[] args) {
		Chain chain = new Chain();
         
        //Calling chain of responsibility 
        chain.process(90); 
        chain.process(-50); 
        chain.process(0); 
        chain.process(91); 
	}
}
