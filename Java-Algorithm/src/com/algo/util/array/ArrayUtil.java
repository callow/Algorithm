package com.algo.util.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.algo.util.PrefixSum.PrefixSumUtil;
import com.algo.util.array.model.Queue;

public class ArrayUtil {
	
	
	/**
	 * �������=K�ĸ���
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
	 * ����һ������ʵ�ֵ�Queue
	 */

	public static Queue<Integer> getQueue() {
		return new Queue<Integer>(100000000);
	}

	/**
	 * �ϲ����า�ǵ�����<br>
	 * intervals = [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
	 */
	public static int[][] mergeOverlaps(int[][] intervals) {
		List<int[]> result = new ArrayList<>();
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0])); // ��С��������

		int[] cur = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] > cur[1]) { // ���ص�
				result.add(cur);
				cur = intervals[i];
			} else {
				cur[1] = Math.max(cur[1], intervals[i][1]); // ���ص� ��combine, ����end �Ƹ���
			}
		}
		result.add(cur);
		return result.toArray(new int[result.size()][2]); // ��ArrayList�������ķ���
	}

	/**
	 * �ҵ����������1�ĳ���
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
	 * ����������Ƿ���Ԫ�����ҵ�2��
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
	 * ��ɽ������
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
	 * ɾ���������ظ�Ԫ��
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
	 * ɾ��ĳ������
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
	 * �ҳ�������3�������
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
	 * ����ת����������������ɵ�����+1��Ȼ����ת�س�����,��λ -> [1,2,4] -> [1,2,5]
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
	 * ���� 2 2��ӣ�����ÿ����С���������ĺ���󣬴�ӡ�������
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
	 * ��numbers���ҵ��ܼӳ�target��2��λ�ã��±��1��ʼ
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
	 * �����ۼӺ������� >= target����С���ȣ�û�з���0
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
	 * ��ȫ��nums�����ҵ������Ҫ����������飿
	 * 
	 * ˼·�� �������ұ���һ���ҵ�LeftMaxIndex, �����������һ���ҵ�rightMinIndex -> �𰸣�[rightMinIndex,LeftMaxIndex]
	 * 
	 * LeftMaxIndex: ��߲��ֵ�Max�������������ֵ����ֵ, �����߲���Max > cur : x �� ��֮ * ,���ҵ�x��λ���ռ�
	 * 
	 * -  1 2 6 5 4 3 8 9 
	 *    0 1 2 3 4 5 6 7
	 *    * * * x x x ..
	 * 
	 * rightMinIndex: �ұ߲��ֵ�Min�������������ֵ���Сֵ, ����ұ߲���Min < cur �� x, ��֮�� *,����x��λ���ռ�
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
	 * 10^18 ƿ���֣���100��50��10 ��ֵ��Ǯ��ÿ��ֻ����һƿ���֣�Ҫ��mƿ���֣�ֻ�ܴӴ���ֵ����ҪͶ�Ҽ��Σ�
	 * 
	 * ˼·��������һƿһƿģ�⣬��Ϊ�����Գ���10^8
	 * m: Ҫ��Ŀ�������
	 * a: 100Ԫa��
	 * b: 50Ԫb��
	 * c: 10Ԫc��
	 * x: ���ֵ���
	 * ���ɣ�
	 * a/x ����ȡ�� = (a + (x-1)) / x
	 */
	public static int buycoke(int m, int a,int b,int c, int x) {
		//              0    1   2
		int[] qian = { 100, 50, 10 };
		int[] zhang = { c,  b,  a };
		// �ܹ���Ҫ���ٴ�Ͷ��
		int puts = 0;
		// ֮ǰ��ֵ��Ǯ��ʣ�¶�����Ǯ��
		int preQianRest = 0;
		// ֮ǰ��ֵ��Ǯ��ʣ�¶���������
		int preQianZhang = 0;
		for (int i = 0; i < 3 && m != 0; i++) {
			// Ҫ��֮ǰʣ�µ�Ǯ����ǰ��ֵ��Ǯ����ͬ���һƿ����
			// ֮ǰ����ֵʣ�¶���Ǯ����preQianRest
			// ֮ǰ����ֵʣ�¶����ţ���preQianZhang
			// ֮����֮ǰ����ֵ��ʣ������һ����ʣ�µ�Ǯ��һֱ�ܲ���һƿ���ֵĵ���
			// ��ǰ����ֵ����һЩǮ+֮ǰʣ�µ�Ǯ����ʱ�п��ܴճ�һƿ������
			// ��ô��ǰ��ֵ����㶨��һƿ���֣���Ҫ�ͳ��������أ�����curQianFirstBuyZhang
			int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];
			if (zhang[i] >= curQianFirstBuyZhang) { // ���֮ǰ��Ǯ�͵�ǰ��ֵ��Ǯ���ܴճ���һƿ����
				// �ճ�����һƿ����Ҳ���ܴ�����Ǯ�������
				giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
				puts += curQianFirstBuyZhang + preQianZhang;
				zhang[i] -= curQianFirstBuyZhang;
				m--;
			} else { // ���֮ǰ��Ǯ�͵�ǰ��ֵ��Ǯ�����ܴճ���һƿ����
				preQianRest += qian[i] * zhang[i];
				preQianZhang += zhang[i];
				continue;
			}
			// �ճ���һƿ����֮�󣬵�ǰ����ֵ�п����ܼ��������Ŀ���
			// ���¹��̾��Ǻ����Ŀ�����ô�õ�ǰ��ֵ��Ǯ����
			// �õ�ǰ��ֵ��Ǯ����һƿ������Ҫ����
			int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
			// �õ�ǰ��ֵ��Ǯ��һ�����Ը㶨��ƿ����
			int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
			// �õ�ǰ��ֵ��Ǯ��ÿ�㶨һƿ���֣��ջ������³�������Ǯ
			int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
			// ÿ����һƿ���֣��³���������Ǯ����oneTimeRest
			// һ����Ŀ�������curQianBuyColas�����԰���Ǯȥ�������漸����ֵ��Ӳ������
			// ����giveRest�ĺ���
			giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
			// ��ǰ��ֵȥ�㶨��������£�һ��Ͷ�˼��α�
			puts += curQianBuyOneColaZhang * curQianBuyColas;
			// ��ʣ�¶���ƿ������Ҫȥ�㶨�������ú������ֵ�㶨ȥ��
			m -= curQianBuyColas;
			// ��ǰ��ֵ����ʣ�������ţ�Ҫ���뵽��������ֵĹ�����ȥ��
			// ����Ҫ����preQianRest��preQianZhang
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
	 * ��ɫ�ӣ�n�������ˣ�����֪��һ���ֺ�һ��ƽ����,�Ƶ������ǵ�n��ɫ��
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
        ������ţ���������������䵽ans[]����ȥ��ÿ����1�㣺
        e.g �����Ҫ9�� [2,2,2,2] �� 9%4 mod 1 �����1����䵽ansp[] ��1�� => [3,2,2,2]
	 */
	public static int[] solution(int[] rolls, int n, int mean) {
		int curSum = Arrays.stream(rolls).sum(), m = rolls.length;
        int missingSum = mean * (n + m) - curSum;
        
        // ��Ϊɫ����СΪ1
        if (missingSum < n || missingSum > 6 * n) {
        	return new int[0];
        }
        //��ʼ����	
        int part = missingSum / n, remainder = missingSum % n;
        int[] ans = new int[n];
        Arrays.fill(ans, part); // ������Ͽ���������
        for (int i = 0; i < remainder; ++i) {
        	ans[i]++;
        }
        return ans;
	}

}
