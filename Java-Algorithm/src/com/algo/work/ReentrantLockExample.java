package com.algo.work;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 是 synchronized 替代，更加灵活
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
		   LOCK.lock(); // 获取锁
	        try {
	            System.out.println(Thread.currentThread().getName() + " 获取到锁，执行中...");
	            Thread.sleep(1000); // 模拟业务逻辑
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        } finally {
	        	LOCK.unlock(); // 一定要释放锁！！！！
	        }
	    }
}
