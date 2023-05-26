package com.algo.pattern.responsibility_chain.model;

public class NegativeProcessor extends Processor {

	public NegativeProcessor(Processor nextProcessor) {
		super(nextProcessor);
	}
	
	@Override
	public void process(int input){ 
        if (input < 0) {  // �ж��Ƿ����Լ���ְ��
            System.out.println("NegativeProcessor : " + input); 
        } 
        else { // �������Լ���ְ�𣬽�����һ��������ȥ��
            super.process(input); 
        } 
    } 

}
