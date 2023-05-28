package com.algo.util.treemap;

import java.util.Arrays;
import java.util.TreeMap;

import com.algo.util.treemap.model.Job;
import com.algo.util.treemap.model.JobComparator;

/**
 * 
 * TreeMap: O(logN),红黑树
 */
public class TreemapUtil {

	public static void demo() {
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		System.out.println(treeMap.firstKey()); // 所有key中最小的key
		System.out.println(treeMap.lastKey()); // 所有key中最大的key
		System.out.println(treeMap.floorKey(4)); // <= 4,离4最近的key
		System.out.println(treeMap.ceilingKey(4)); // >= 4,离4最近的key
	}
	
	/**
	 * 一个工作数组，一个每个人的能力数组，工作数组里面有这份工作要求的能力以及报酬 工作无限份，求每个人最多挣多少钱？
	 * 思路：
	 * 	- 删除相同难度报酬低的
	 *  - 删除难度低，钱少的
	 *  - 最后保证难度和报酬的双递增单调性
	 */
	public static int[] earnMoney(Job[] jobs, int[] ability) {
		Arrays.sort(jobs, new JobComparator());
		
		// 删除没用的工作
		// key : 难度   value：报酬
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(jobs[0].hard, jobs[0].money); // 最好的工作一定要保留
		
		// pre : 上一份进入map的工作
		Job pre = jobs[0];
		for (int i = 1; i < jobs.length; i++) { // 有序表遍历
			// 难度一样的舍弃
			// 难度不一样时，只有报酬比之前的大才保留（既轻松，挣得又多）
			if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
				pre = jobs[i];
				map.put(pre.hard, pre.money);
			}
		}
		
		// 准备返回结果
		int[] ans = new int[ability.length];
		for (int i = 0; i < ability.length; i++) {
			// ability[i] 当前人的能力 <= ability[i]  且离它最近的
			Integer key = map.floorKey(ability[i]);
			ans[i] = key != null ? map.get(key) : 0;
		}
		return ans;
		
	}
	
}
