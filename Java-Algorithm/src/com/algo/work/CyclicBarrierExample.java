package com.algo.work;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier һ������һ���̻߳���ȴ���ĳ��״̬��Ȼ����һ���߳���ͬʱִ�У�CyclicBarrier �ǿ��Ը��õģ�CyclicBarrier�������ǶԵȵ�
 * 
 * �����Ƿֲ���ÿ���߳��ϣ�Ŀ������ÿ���߳��ڴ���ʱ�򣬴ﵽһ��ͳһ���ٽ�㣬Ȼ��ͬʱ����
 * 
 */
public class CyclicBarrierExample {

	 // ���ڱ���ÿ���̼߳���Ľ��
     private static int[] partialSums = new int[4]; 
     
     // ģ��һ������
     private static final int[] data = new int[1000]; 
     static {
		 for (int i = 0; i < data.length; i++) {
		        data[i] = i;
		 }
	 }
    
     // ����һ��4�ź�����ƽ����
	 private static final CyclicBarrier FOUR_SIGNAL_BARRIER = new CyclicBarrier(4, () -> {
		 int total = 0;
         for (int sum : partialSums) {
             total += sum;
         }
         System.out.println("�ܺ���: " + total);
	 });
	 
	 
	 
	 public static void main(String[] args) throws InterruptedException {
		// ���� 4 ���̲߳��м���
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
                System.out.println("�߳� " + threadIndex + " ���㲿�ֺ�Ϊ: " + sum);
                try {
                    // ��ʾ 4 ���̶߳� await() �󣬻�ִ���ṩ�Ļ������񣨼��ϲ������
                	FOUR_SIGNAL_BARRIER.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
	       }).start();
		 }
	 }
	 
	 
	
}
