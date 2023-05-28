package com.algo.util.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.algo.util.array.model.Queue;

public class ArrayUtil {

	/**
	 * 生成一个数组实现的Queue
	 */

	public static Queue<Integer> getQueue() {
		return new Queue<Integer>(100000000);
	}

	/**
	 * 合并互相覆盖的区间<br>
	 * intervals = [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
	 */
	public static int[][] mergeOverlaps(int[][] intervals) {
		List<int[]> result = new ArrayList<>();
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0])); // 从小到大排序

		int[] cur = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] > cur[1]) { // 无重叠
				result.add(cur);
				cur = intervals[i];
			} else {
				cur[1] = Math.max(cur[1], intervals[i][1]); // 有重叠 可combine, 更新end 推更高
			}
		}
		result.add(cur);
		return result.toArray(new int[result.size()][2]); // 将ArrayList变成数组的方法
	}

	/**
	 * 找到最长的连续的1的长度
	 */

	public static int findMaxConsecutiveOnes(int[] nums) {
		int count = 0;
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				count++;
			} else {
				max = Math.max(max, count);
				count = 0;
			}
		}
		return Math.max(max, count);
	}

	/**
	 * 
	 * valid state: one or one 0, invalid state: two zeros
	 * 
	 * @return
	 */
	public static int findMaxConsecutiveOnesAllowFlip1Zero(int[] nums) {
		int numOfZero = 0, max = 0;
		for (int left = 0, right = 0; right < nums.length; right++) {
			if (nums[right] == 0) {
				numOfZero++;
			}
			while (numOfZero > 1) {
				if (nums[left++] == 0) {
					numOfZero--;
				}
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	/**
	 * 检查数组中是否有元素是我的2倍
	 */
	public static boolean hasElement2TimesThanMe(int[] arr) {
		List<Integer> copy = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			copy.add(arr[i] * 2);
		}

		for (int i = 0; i < arr.length; i++) {
			if (copy.contains(arr[i]) && i != copy.indexOf(arr[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是山形数组
	 */

	public static boolean isMountainArray(int[] arr) {
		if (arr.length < 3) {
			return false;
		}
		int turningPoint = -1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) {
				return false;
			}
			if (turningPoint != -1 && arr[i] > arr[i - 1]) {
				return false;
			}

			if (arr[i] < arr[i - 1]) {
				turningPoint = i - 1;
			}
			if (turningPoint == 0) {
				return false;
			}
		}
		return turningPoint == -1 ? false : true;
	}

	/**
	 * 删除连续的重复元素
	 */
	public static int removeDuplicates(int[] nums) {
		int insertIndex = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] != nums[i]) {
				nums[insertIndex] = nums[i];
				insertIndex++;
			}
		}
		return insertIndex;
	}

	/**
	 * 删除某个数字
	 */
	public static int removeElement(int[] nums, int val) {
		int counter = 0;
		if (nums.length != 0) {
			int[] nNums = new int[nums.length];
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] != val) {
					nNums[counter++] = nums[i];
				}
			}
			if (nNums.length != 0) {
				System.arraycopy(nNums, 0, nums, 0, nNums.length);
			}
		}
		return counter;
	}

	/**
	 * 找出倒数第3大的数字
	 */

	public static int findthirdMaxNum(int[] nums) {
		Arrays.sort(nums);
		int counter = 1;
		List<Integer> answer = new ArrayList<>();
		answer.add(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				counter++;
				answer.add(nums[i]);
			}
		}
		return counter < 3 ? answer.get(counter - 1) : answer.get(counter - 3);
	}

	/**
	 * 数组转成整数，给数组组成的整数+1，然后再转回成数组,进位 -> [1,2,4] -> [1,2,5]
	 */
	public static int[] plusOne(int[] d) {
		int[] digits = new int[d.length + 1];
		System.arraycopy(d, 0, digits, 1, d.length);
		digits[digits.length - 1]++;

		for (int i = digits.length - 1; i > 0; i--) {
			if (digits[i] == 10) {
				digits[i] = 0;
				digits[i - 1]++;
			}
		}
		if (digits[0] == 0) {
			System.arraycopy(digits, 1, d, 0, d.length);
			return d;
		}
		return digits;
	}

	/**
	 * 数组 2 2组队，挑出每组最小，加起来的和最大，打印这种组合
	 */
	public static int arrayPairSum(int[] nums) {
		Arrays.sort(nums);
		int sum = 0;
		for (int i = nums.length - 1; i > 0; i = i - 2) {
			int groupMin = Math.min(nums[i], nums[i - 1]);
			sum += groupMin;
		}
		return sum;
	}

	/**
	 * 从numbers中找到能加出target的2个位置，下标从1开始
	 */

	public int[] twoSum(int[] numbers, int target) {
		int low = 0;
		int high = numbers.length - 1;
		while (low < high) {
			int sum = numbers[low] + numbers[high];

			if (sum == target) {
				return new int[] { low + 1, high + 1 };
			} else if (sum > target) {
				high--;
			} else {
				low++;
			}
		}
		return new int[] { 0, 0 };
	}

	/**
	 * 返回累加和子数组 >= target的最小长度，没有返回0
	 */
	public static int minSubArrayLen(int target, int[] nums) {
		int left = 0;
		int n = nums.length;
		int sum = 0;
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			while (sum >= target) {
				answer = Math.min(answer, (i + 1) - left);
				sum -= nums[left++];
			}
		}
		return answer != Integer.MAX_VALUE ? answer : 0;

	}
	
	/**
	 * 让全部nums有序，找到最短需要排序的子数组？
	 * 
	 * 思路： 从左往右遍历一遍找到LeftMaxIndex, 从右往左遍历一遍找到rightMinIndex -> 答案：[rightMinIndex,LeftMaxIndex]
	 * 
	 * LeftMaxIndex: 左边部分的Max，遍历滑过部分的最大值, 如果左边部分Max > cur : x ， 反之 * ,最右的x的位置收集
	 * 
	 * -  1 2 6 5 4 3 8 9 
	 *    0 1 2 3 4 5 6 7
	 *    * * * x x x ..
	 * 
	 * rightMinIndex: 右边部分的Min，遍历滑过部分的最小值, 如果右边部分Min < cur ： x, 反之： *,最左x的位置收集
	 * 
	  * -  1 2 6 5 4 3 8 9 
	 *     0 1 2 3 4 5 6 7
	 *         x x x * * *
	 */
	public static int findUnsortedSubarray(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int N = nums.length;
		int right = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (max > nums[i]) {
				right = i;
			}
			max = Math.max(max, nums[i]);
		}
		int min = Integer.MAX_VALUE;
		int left = N;
		for (int i = N - 1; i >= 0; i--) {
			if (min < nums[i]) {
				left = i;
			}
			min = Math.min(min, nums[i]);
		}
		return Math.max(0, right - left + 1);
	}

}
