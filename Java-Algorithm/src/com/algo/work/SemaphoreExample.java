package com.algo.work;

import java.util.concurrent.Semaphore;

/**
 * ��Ҫ���ڿ���ͬʱ����ĳ����Դ���߳�����������������ʵ�����������ӳؿ��ơ��������ƻ��Ƶ�
 * 
 */
public class SemaphoreExample {

	 // ͬʱ���� 2 ���̷߳��� �� ��Դ������ 2 ����ͨ��֤�����õ�ͨ��֤���߳̿��Լ���ִ��
	 private static final Semaphore SEMAPHORE = new Semaphore(2, false);
	 
	 public static void main(String[] args) {
		 for (int i = 0; i < 6; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("�߳� " + threadId + " �ȴ����...");
                    SEMAPHORE.acquire(); // ����һ����ɣ����û�л�����
                    System.out.println("�߳� " + threadId + " �����ɣ���ʼִ��");
                    Thread.sleep(1000); // ģ��ִ������
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("�߳� " + threadId + " ִ����ɣ��ͷ����");
                    SEMAPHORE.release(); // �ͷ�һ�����
                }
            }).start();
		 }
	}

}
