package com.algo.tasks.day3;

import java.util.Arrays;

/**
 * һ��int[], һ������goal, ѡһ�������д�int[]�У���sum ��ӽ�goal �� |sum - goal| ��С
 *
 * https://leetcode.com/problems/closest-subsequence-sum/
 * 
 *  1 <= nums.length <= 40
   -10^7 <= nums[i] <= 10^7
   -10^9 <= goal <= 10^9
   
   ͨ�����������������֪����Ҫ�õ����Σ���Ϊ���鳤�Ȳ��� ��ֵ�ܴ��ö�̬�滮�Ļ�����ᱬ > 10^8
 */
public class ClosestSubsequenceSum {

	// ������벿�ֵ����п����ۼӺͶ���������
	public static int[] l = new int[1 << 20]; // 2^20 ~ 100��� 

	// �����Ұ벿�ֵ����п����ۼӺͶ���������
	public static int[] r = new int[1 << 20];

	public static int minAbsDifference(int[] nums, int goal) {
		if (nums == null || nums.length == 0) {
			return goal;
		}
		// �ռ�������벿�֣����п��ܵ��ۼӺ�
		// ���ҷ���һ���ռ��˼�����������le
		int le = process(nums, 0, nums.length >> 1, 0, 0, l);
		// �ռ������Ұ벿�֣����п��ܵ��ۼӺ�
		// ���ҷ���һ���ռ��˼�����������re
		int re = process(nums, nums.length >> 1, nums.length, 0, 0, r);
		// ����벿���ռ������ۼӺ�����
		Arrays.sort(l, 0, le);
		// ����벿���ռ������ۼӺ�����
		Arrays.sort(r, 0, re--);
		// ΪʲôҪ����
		// ��Ϊ����֮��λ���ֿ��Բ�����
		// ���������ۼӺ;����ӽ�10
		// ��벿���ۼӺͼ���Ϊ : 0, 2, 3, 7, 9
		// �Ұ벿���ۼӺͼ���Ϊ : 0, 6, 7, 8, 9
		// �󲿷��ۼӺ���0ʱ���Ұ벿��ѡ�ĸ��ۼӺ���ӽ�10����9
		// �󲿷��ۼӺ���2ʱ���Ұ벿��ѡ�ĸ��ۼӺ���ӽ�10����8
		// �󲿷��ۼӺ���3ʱ���Ұ벿��ѡ�ĸ��ۼӺ���ӽ�10����7
		// �󲿷��ۼӺ���7ʱ���Ұ벿��ѡ�ĸ��ۼӺ���ӽ�10����0
		// �󲿷��ۼӺ���9ʱ���Ұ벿��ѡ�ĸ��ۼӺ���ӽ�10����0
		// ��������Կ�����
		// �����������ѡ���󲿷��ۼӺ�ʱ���Ҳ����ۼӺ͵�ѡȡ�����Դ�������
		// ��ͷǳ��ķ���
		// ����Ĵ�����������˼
		int ans = Math.abs(goal);
		for (int i = 0; i < le; i++) {
			int rest = goal - l[i];
			while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
				re--;
			}
			ans = Math.min(ans, Math.abs(rest - r[re]));
		}
		return ans;
	}

	// ����һ��
	// nums[0..index-1]�Ѿ�ѡ��һЩ���֣�������ۼӺ�sum
	// ��ǰ����nums[index....end)�����Χ�����п��ܵ��ۼӺ�
	// ��д��arr��ȥ
	// fill��������˼��: ��������µ��ۼӺͣ���д��arr��ʲôλ��
	// �����������ɵ��ۼӺͣ��������arr��ʲôλ��
	public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
		if (index == end) { // ������ֹΪֹ�ˣ��ý�����
			// �ѵ�ǰ���ۼӺ�sum
			// ��д��arr[fill]��λ��
			// Ȼ��fill++����ʾ�����������Ļ�
			// �÷���ʲôλ����
			arr[fill++] = sum;
		} else {
			// ������1 : ��Ҫ��ǰ������
			// ��һ����֧���γɶ����ۼӺͣ�����д��arr��ȥ
			// ͬʱ���������֧��arr���ʲôλ��
			fill = process(nums, index + 1, end, sum, fill, arr);
			// ������2 : Ҫ��ǰ������
			// ��һ����֧���γɶ����ۼӺͣ�����д��arr��ȥ
			// ���ſ�����1�����λ�ã�������д��arr��ȥ
			// �����ΪʲôҪ�õ���һ����֧�����
			// ��Ϊ���û�������Ϣ��������2�ķ�֧��֪�����������ɵ��ۼӺ�
			fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
		}
		// ������1 + ������2���ܹ����˶��ٶ�����
		return fill;
	}
}
