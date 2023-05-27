package com.algo.pattern.responsibility_chain.model;

public class PositiveProcessor extends Processor {

	public PositiveProcessor(Processor nextProcessor) {
		super(nextProcessor);
	}
	
	@Override
	public void process(int input){ 
        if (input > 0) {  // �ж��Ƿ����Լ���ְ��
            System.out.println("PositiveProcessor : " + input); 
        } 
        else {  // �������Լ���ְ�𣬽�����һ��������ȥ��
            super.process(input); 
        } 
    } 

}
