package com.algo.util.validator;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.sort.SortUtil;

/**
 * 对数器：利用随机行为，构建成千上万的数据,既能控制长度 也可以控制范围
 * 
 * 这里已经把方法封装好了：
 * 	
 *  CommonArrayUtil.generateRandomArray
 *  CommonArrayUtil.copyArray
 *  CommonArrayUtil.equal
 *  等等等等
 * 
 */
public class ValidatorUtil {

	public static void main(String[] args) {
		
		// 随机数组最大长度
		int N = 200;
		// 随机数组每个值，在1~V之间等概率随机
		int V = 1000;
		// testTimes : 测试次数
		int testTimes = 50000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			
			// 随机得到一个长度，长度在[0~N-1]
			int n = (int) (Math.random() * N);
			// 得到随机数组
			int[] arr = CommonArrayUtil.generateRandomArray(n, V); 
			int[] arr1 = CommonArrayUtil.copyArray(arr);
			int[] arr2 = CommonArrayUtil.copyArray(arr);
			int[] arr3 = CommonArrayUtil.copyArray(arr);
			
			SortUtil.selectionSort(arr1);
			SortUtil.bubbleSort(arr2);
			SortUtil.insertSort(arr3);
			
			
			if (!CommonArrayUtil.equals(arr1, arr2) || !CommonArrayUtil.equals(arr1, arr3)) {
				System.out.println("出错了!");
				
				CommonArrayUtil.printArray(arr1);
				CommonArrayUtil.printArray(arr2);
				CommonArrayUtil.printArray(arr3);
				break;
				// 当有错了
				// 打印是什么例子，出错的
				// 打印三个功能，各自排序成了什么样
				// 可能要把例子带入，每个方法，去debug！
			}
		}
		System.out.println("测试结束");
	}
}
