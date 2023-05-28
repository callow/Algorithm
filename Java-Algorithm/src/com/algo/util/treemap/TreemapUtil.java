package com.algo.util.treemap;

import java.util.Arrays;
import java.util.TreeMap;

import com.algo.util.treemap.model.Job;
import com.algo.util.treemap.model.JobComparator;

/**
 * 
 * TreeMap: O(logN),�����
 */
public class TreemapUtil {

	public static void demo() {
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		System.out.println(treeMap.firstKey()); // ����key����С��key
		System.out.println(treeMap.lastKey()); // ����key������key
		System.out.println(treeMap.floorKey(4)); // <= 4,��4�����key
		System.out.println(treeMap.ceilingKey(4)); // >= 4,��4�����key
	}
	
	/**
	 * һ���������飬һ��ÿ���˵��������飬����������������ݹ���Ҫ��������Լ����� �������޷ݣ���ÿ�������������Ǯ��
	 * ˼·��
	 * 	- ɾ����ͬ�Ѷȱ���͵�
	 *  - ɾ���Ѷȵͣ�Ǯ�ٵ�
	 *  - ���֤�ѶȺͱ����˫����������
	 */
	public static int[] earnMoney(Job[] jobs, int[] ability) {
		Arrays.sort(jobs, new JobComparator());
		
		// ɾ��û�õĹ���
		// key : �Ѷ�   value������
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(jobs[0].hard, jobs[0].money); // ��õĹ���һ��Ҫ����
		
		// pre : ��һ�ݽ���map�Ĺ���
		Job pre = jobs[0];
		for (int i = 1; i < jobs.length; i++) { // ��������
			// �Ѷ�һ��������
			// �ѶȲ�һ��ʱ��ֻ�б����֮ǰ�Ĵ�ű����������ɣ������ֶࣩ
			if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
				pre = jobs[i];
				map.put(pre.hard, pre.money);
			}
		}
		
		// ׼�����ؽ��
		int[] ans = new int[ability.length];
		for (int i = 0; i < ability.length; i++) {
			// ability[i] ��ǰ�˵����� <= ability[i]  �����������
			Integer key = map.floorKey(ability[i]);
			ans[i] = key != null ? map.get(key) : 0;
		}
		return ans;
		
	}
	
}
