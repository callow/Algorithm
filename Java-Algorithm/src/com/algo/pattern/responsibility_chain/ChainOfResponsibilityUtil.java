package com.algo.pattern.responsibility_chain;

import com.algo.pattern.responsibility_chain.model.Chain;

public class ChainOfResponsibilityUtil {
	
	/**
	 * 
	 * 责任链模式：根据输入类型，分配给链条上不同的处理人去处理
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
