package com.algo.util.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.algo.util.PrefixSum.PrefixSumUtil;
import com.algo.util.array.model.Queue;

public class ArrayUtil {
	
	
	/**
	 * 子数组和=K的个数
	 * https://leetcode.com/problems/binary-subarrays-with-sum/description/
	 */
	public static int subarraySum(int[] nums, int aim) {
		return PrefixSumUtil.subarraySum(nums, aim);
	}
	
	/**
	 * reverse an array
	 */
	public static int[] reverse(int[] arr) {
		for(int i = 0; i < arr.length / 2; i++) {
		    int temp = arr[i];
		    arr[i] = arr[arr.length - i - 1];
		    arr[arr.length - i - 1] = temp;
		}
		return arr;
	}
	
	/**
	 * flip an array
	 */
	public static int[] flip(int[] arr) {
	    for (int i = 0; i < arr.length; i++) {
	        arr[i] = arr[i] ^ 1;
	      }
	    return arr;
	}

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
	
	/**
	 * 10^18 瓶可乐，有100，50，10 面值的钱，每次只能买一瓶可乐，要买m瓶可乐，只能从大面值花，要投币几次？
	 * 
	 * 思路：不可以一瓶一瓶模拟，因为不可以超过10^8
	 * m: 要买的可乐数量
	 * a: 100元a张
	 * b: 50元b张
	 * c: 10元c张
	 * x: 可乐单价
	 * 技巧：
	 * a/x 向上取整 = (a + (x-1)) / x
	 */
	public static int buycoke(int m, int a,int b,int c, int x) {
		//              0    1   2
		int[] qian = { 100, 50, 10 };
		int[] zhang = { c,  b,  a };
		// 总共需要多少次投币
		int puts = 0;
		// 之前面值的钱还剩下多少总钱数
		int preQianRest = 0;
		// 之前面值的钱还剩下多少总张数
		int preQianZhang = 0;
		for (int i = 0; i < 3 && m != 0; i++) {
			// 要用之前剩下的钱、当前面值的钱，共同买第一瓶可乐
			// 之前的面值剩下多少钱，是preQianRest
			// 之前的面值剩下多少张，是preQianZhang
			// 之所以之前的面值会剩下来，一定是剩下的钱，一直攒不出一瓶可乐的单价
			// 当前的面值付出一些钱+之前剩下的钱，此时有可能凑出一瓶可乐来
			// 那么当前面值参与搞定第一瓶可乐，需要掏出多少张呢？就是curQianFirstBuyZhang
			int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];
			if (zhang[i] >= curQianFirstBuyZhang) { // 如果之前的钱和当前面值的钱，能凑出第一瓶可乐
				// 凑出来了一瓶可乐也可能存在找钱的情况，
				giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
				puts += curQianFirstBuyZhang + preQianZhang;
				zhang[i] -= curQianFirstBuyZhang;
				m--;
			} else { // 如果之前的钱和当前面值的钱，不能凑出第一瓶可乐
				preQianRest += qian[i] * zhang[i];
				preQianZhang += zhang[i];
				continue;
			}
			// 凑出第一瓶可乐之后，当前的面值有可能能继续买更多的可乐
			// 以下过程就是后续的可乐怎么用当前面值的钱来买
			// 用当前面值的钱，买一瓶可乐需要几张
			int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
			// 用当前面值的钱，一共可以搞定几瓶可乐
			int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
			// 用当前面值的钱，每搞定一瓶可乐，收货机会吐出多少零钱
			int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
			// 每次买一瓶可乐，吐出的找零总钱数是oneTimeRest
			// 一共买的可乐数是curQianBuyColas，所以把零钱去提升后面几种面值的硬币数，
			// 就是giveRest的含义
			giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
			// 当前面值去搞定可乐这件事，一共投了几次币
			puts += curQianBuyOneColaZhang * curQianBuyColas;
			// 还剩下多少瓶可乐需要去搞定，继续用后面的面值搞定去吧
			m -= curQianBuyColas;
			// 当前面值可能剩下若干张，要参与到后续买可乐的过程中去，
			// 所以要更新preQianRest和preQianZhang
			zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
			preQianRest = qian[i] * zhang[i];
			preQianZhang = zhang[i];
		}
		return m == 0 ? puts : -1;
	}
	
	private static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
		for (; i < 3; i++) {
			zhang[i] += (oneTimeRest / qian[i]) * times;
			oneTimeRest %= qian[i];
		}
	}
	
	/**
	 * 扔色子，n个忘记了，但是知道一部分和一个平均数,推导出忘记的n个色子
	 * 
	 * https://leetcode.com/problems/find-missing-observations/
	 * 
	 *  curSum = sum(rolls)
		(curSum + missingSum) / (n + m) == mean
		curSum + missingSum = mean * (n+m)
		missingSum = mean * (n+m) - curSum
		
		for (int i = 0; i < remainder; ++i) {
        	ans[i]++;
        }
        这里很炫，根据余数逐个分配到ans[]里面去，每个分1点：
        e.g 如果需要9， [2,2,2,2] ， 9%4 mod 1 则把这1点分配到ansp[] 第1个 => [3,2,2,2]
	 */
	public static int[] solution(int[] rolls, int n, int mean) {
		int curSum = Arrays.stream(rolls).sum(), m = rolls.length;
        int missingSum = mean * (n + m) - curSum;
        
        // 因为色子最小为1
        if (missingSum < n || missingSum > 6 * n) {
        	return new int[0];
        }
        //开始分配	
        int part = missingSum / n, remainder = missingSum % n;
        int[] ans = new int[n];
        Arrays.fill(ans, part); // 先填充上可以整除的
        for (int i = 0; i < remainder; ++i) {
        	ans[i]++;
        }
        return ans;
	}

}
