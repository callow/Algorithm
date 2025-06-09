package com.algo.work;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * �� synchronized ������������
 */
public class ReentrantLockExample {
	
	private static final ReentrantLock LOCK = new ReentrantLock();
	
	public static void main(String[] args) {
		ReentrantLockExample example = new ReentrantLockExample();
        for (int i = 0; i < 3; i++) {
            new Thread(example::doWork).start();
        }
    }
	
	   public void doWork() {
		   LOCK.lock(); // ��ȡ��
	        try {
	            System.out.println(Thread.currentThread().getName() + " ��ȡ������ִ����...");
	            Thread.sleep(1000); // ģ��ҵ���߼�
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        } finally {
	        	LOCK.unlock(); // һ��Ҫ�ͷ�����������
	        }
	    }
}
