package com.algo.pattern.responsibility_chain.model;

public class Processor {
	
	private Processor nextProcessor;
	
    public Processor(Processor nextProcessor){
        this.nextProcessor = nextProcessor;
    };
    /**
     * 
     * ��ʼ����
     */
    public void process(int request){
        if(nextProcessor != null)
            nextProcessor.process(request);
    }; 
}
