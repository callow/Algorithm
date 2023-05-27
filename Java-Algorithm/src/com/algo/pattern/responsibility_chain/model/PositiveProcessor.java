package com.algo.pattern.responsibility_chain.model;

public class PositiveProcessor extends Processor {

	public PositiveProcessor(Processor nextProcessor) {
		super(nextProcessor);
	}
	
	@Override
	public void process(int input){ 
        if (input > 0) {  // 判断是否是自己的职责
            System.out.println("PositiveProcessor : " + input); 
        } 
        else {  // 不是是自己的职责，交给下一个处理器去看
            super.process(input); 
        } 
    } 

}
