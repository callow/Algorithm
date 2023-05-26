package com.algo.pattern.responsibility_chain.model;

public class ZeroProcessor extends Processor {

	public ZeroProcessor(Processor nextProcessor) {
		super(nextProcessor);
	}
	
	@Override
	public void process(int input){ 
        if (input == 0) { // �ж��Ƿ����Լ���ְ��
            System.out.println("ZeroProcessor : " + input); 
        } 
        else { // �������Լ���ְ�𣬽�����һ��������ȥ��
            super.process(input); 
        } 
    } 

}
