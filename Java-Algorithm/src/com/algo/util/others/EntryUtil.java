package com.algo.util.others;

import java.util.Arrays;

/**
 * �㷨���ţ�
 * ʵ�飺 
 *  һ��ʼ��100���ˣ�ÿ���˶���100Ԫ
	��ÿһ�ֶ������µ����� : ÿ���˶������ó�1ԪǮ��--�������Լ�����������ˣ�++������˭��ȫ��� ���ĳ��������һ�ֵ�Ǯ��Ϊ0����ô�����Բ��������ǿ��Խ��� �����ܶ�ܶ���֮����100�˵����Ƹ��ֲ��ܾ�����
 * 
 */
public class EntryUtil {

	
	public static void main(String[] args) {
		int n = 100;
		int t = 1000000;
		System.out.println("���� : " + n + " ���� : " + t);
		experiment(n, t);
		System.out.println("ʵ�����");
	}
	
	/**
	 * �Ƹ�����ʵ��
	 */
	
	public static void experiment(int n, int t) {
		double[] wealth = new double[n];
		Arrays.fill(wealth, 100);
		
		boolean[] hasMoney = new boolean[n];
		for(int i = 0; i < t; i++) {
			Arrays.fill(hasMoney, false);
			for (int j = 0; j < n; j++) {
				if (wealth[j] > 0) {
					hasMoney[j] = true;
				}
			}
			
			// ��ʼʵ��
			for (int j = 0; j < n; j++) {
				if (hasMoney[j]) {
					int other = j;
					do {
						other = (int) (Math.random() * n); // 0 ~ n-1���ȸ������, ���ǲ����Ը��Լ� other ��= j
					} while (other == j);
					wealth[j]--;
					wealth[other]++;
				}
			}			
		}
		
		
		Arrays.sort(wealth); // �Ÿ���
		System.out.println("�г�ÿ���˵ĲƸ�(ƶ�����) : ");
		for (int i = 0; i < n; i++) {
			System.out.print((int) wealth[i] + " ");
			if (i % 10 == 9) {
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println("������Ļ���ϵ��Ϊ : " + calculateGini(wealth));
	}
	
	
	
	
	
	
	/**
	 * �������ϵ����
	 *  һ�����Ļ���ϵ����һ����0~1֮���С���� ����ϵ��Ϊ0���������˵ĲƸ���ȫһ�� ����ϵ��Ϊ1������1����������ȫ���ĲƸ� ����ϵ��ԽС���������Ƹ��ֲ�Խ���⣻Խ�������Ƹ��ֲ�Խ������ 
	 *  ��2022�꣬���������ƽ������ϵ��Ϊ0.44 Ŀǰ�ձ���Ϊ��������ϵ������ 0.5 ʱ ����ζ�����ƶ�����ǳ��󣬷ֲ��ǳ������� �����ܻ��������Σ������������ķ�����߾�����ᶯ��
	 *  
	 */
	
	public static double calculateGini(double[] wealth) {
		double sumOfAbsoluteDifferences = 0;
		double sumOfWealth = 0;
		int n = wealth.length;
		for (int i = 0; i < n; i++) {
			sumOfWealth += wealth[i];
			for (int j = 0; j < n; j++) {
				sumOfAbsoluteDifferences += Math.abs(wealth[i] - wealth[j]);
			}
		}
		return sumOfAbsoluteDifferences / (2 * n * sumOfWealth);
	}
}
