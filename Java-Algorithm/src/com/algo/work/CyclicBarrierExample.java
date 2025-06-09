package com.algo.work;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；CyclicBarrier 是可以复用的；CyclicBarrier的任务是对等的
 * 
 * 挡板是分布在每个线程上，目的是让每个线程在处理时候，达到一个统一的临界点，然后同时触发
 * 
 */
public class CyclicBarrierExample {

	 // 用于保存每个线程计算的结果
     private static int[] partialSums = new int[4]; 
     
     // 模拟一组数据
     private static final int[] data = new int[1000]; 
     static {
		 for (int i = 0; i < data.length; i++) {
		        data[i] = i;
		 }
	 }
    
     // 定义一个4信号量的平屏障
	 private static final CyclicBarrier FOUR_SIGNAL_BARRIER = new CyclicBarrier(4, () -> {
		 int total = 0;
         for (int sum : partialSums) {
             total += sum;
         }
         System.out.println("总和是: " + total);
	 });
	 
	 
	 
	 public static void main(String[] args) throws InterruptedException {
		// 启动 4 个线程并行计算
		 for (int i = 0; i < 4; i++) {
			 final int threadIndex = i;
			 new Thread(() -> {
                int start = threadIndex * 250;
                int end = start + 250;
                int sum = 0;
                for (int j = start; j < end; j++) {
                    sum += data[j];
                }
                partialSums[threadIndex] = sum;
                System.out.println("线程 " + threadIndex + " 计算部分和为: " + sum);
                try {
                    // 表示 4 个线程都 await() 后，会执行提供的汇总任务（即合并结果）
                	FOUR_SIGNAL_BARRIER.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
	       }).start();
		 }
	 }
	 
	 
	
}
