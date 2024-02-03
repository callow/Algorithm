package com.algo.util.others;

import java.util.Arrays;

/**
 * 算法入门：
 * 实验： 
 *  一开始有100个人，每个人都有100元
	在每一轮都做如下的事情 : 每个人都必须拿出1元钱（--）给除自己以外的其他人（++），给谁完全随机 如果某个人在这一轮的钱数为0，那么他可以不给，但是可以接收 发生很多很多轮之后，这100人的社会财富分布很均匀吗？
 * 
 */
public class EntryUtil {

	
	public static void main(String[] args) {
		int n = 100;
		int t = 1000000;
		System.out.println("人数 : " + n + " 轮数 : " + t);
		experiment(n, t);
		System.out.println("实验结束");
	}
	
	/**
	 * 财富分配实验
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
			
			// 开始实验
			for (int j = 0; j < n; j++) {
				if (hasMoney[j]) {
					int other = j;
					do {
						other = (int) (Math.random() * n); // 0 ~ n-1，等概率随机, 但是不可以给自己 other ！= j
					} while (other == j);
					wealth[j]--;
					wealth[other]++;
				}
			}			
		}
		
		
		Arrays.sort(wealth); // 排个序
		System.out.println("列出每个人的财富(贫穷到富有) : ");
		for (int i = 0; i < n; i++) {
			System.out.print((int) wealth[i] + " ");
			if (i % 10 == 9) {
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println("这个社会的基尼系数为 : " + calculateGini(wealth));
	}
	
	
	
	
	
	
	/**
	 * 计算基尼系数：
	 *  一个社会的基尼系数是一个在0~1之间的小数， 基尼系数为0代表所有人的财富完全一样 基尼系数为1代表有1个人掌握了全社会的财富 基尼系数越小，代表社会财富分布越均衡；越大则代表财富分布越不均衡 
	 *  在2022年，世界各国的平均基尼系数为0.44 目前普遍认为，当基尼系数到达 0.5 时 就意味着社会贫富差距非常大，分布非常不均匀 社会可能会因此陷入危机，比如大量的犯罪或者经历社会动荡
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
