package com.algo.work;

import java.util.concurrent.Semaphore;

/**
 * 主要用于控制同时访问某个资源的线程数量。它可以用来实现限流、连接池控制、访问令牌机制等
 * 
 */
public class SemaphoreExample {

	 // 同时允许 2 个线程访问 ： 资源池里有 2 个“通行证”，拿到通行证的线程可以继续执行
	 private static final Semaphore SEMAPHORE = new Semaphore(2, false);
	 
	 public static void main(String[] args) {
		 for (int i = 0; i < 6; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("线程 " + threadId + " 等待许可...");
                    SEMAPHORE.acquire(); // 请求一个许可，如果没有会阻塞
                    System.out.println("线程 " + threadId + " 获得许可，开始执行");
                    Thread.sleep(1000); // 模拟执行任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("线程 " + threadId + " 执行完成，释放许可");
                    SEMAPHORE.release(); // 释放一个许可
                }
            }).start();
		 }
	}

}
