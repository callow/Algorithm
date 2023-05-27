package com.algo.pattern.responsibility_chain.model;

public class NegativeProcessor extends Processor {

	public NegativeProcessor(Processor nextProcessor) {
		super(nextProcessor);
	}
	
	@Override
	public void process(int input){ 
        if (input < 0) {  // 判断是否是自己的职责
            System.out.println("NegativeProcessor : " + input); 
        } 
        else { // 不是是自己的职责，交给下一个处理器去看
            super.process(input); 
        } 
    } 

}
