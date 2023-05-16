package com.algo.util.array_3_sum;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * �����ۼӺ�������⡣= ����3������ = ������鳤������
 *
 */
public class ArraySumUtil {

	/**
	 * һ��+���飬�ĸ�������� = k�ҳ������<br>
	 * �⣺ÿ��λ�ÿ�ͷ������˫ָ�룺 ������leftΪ��ͷ���������ۼӺ���target
	 */
	public static int findLongestSubArrayPositive(int[] arr, int k) {
		if (CommonArrayUtil.isEmpty(arr) || k <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}

	/**
	 * һ��+ - 0 ���飬�ĸ�������� = k�ҳ������<br>
	 * 
	 * �⣺ ÿ��λ�ý�β��ô����<br>
	 * 
	 * sum - target = ǰ׺�����Ƶ��λ�ã�
	 * 
	 * �� ��(0~100) = 200, target = 30, then ĳһ��ǰ׺��170������6λ�� <br>
	 * �� ��7 ~ 100�������Ƶ������100��β��������Ƶ�7λ�õõ���30
	 * 
	 * ע���任��e.g: ��������-1��1������һ������������ => ������-1 1�����ֱ��0������target=0��ʱ��ı���
	 * 
	 */
	public static int findLongestSubArrayAnySymbol(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key:ǰ׺��
		// value : 0~value���ǰ׺�����������key���ֵ��
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1); // important

		int maxLen = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) { // ��i��β��������
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				int prefixSumIndex = map.get(sum - k);
				maxLen = Math.max(i - prefixSumIndex, maxLen);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i); // ��ǰ׺�͵�index����map
			}
		}
		return maxLen;
	}

	/**
	 * һ��+ - 0 ���飬�ĸ�������� <= k�ҳ������<br>
	 * 
	 * �⣺����2����������ɶ�ʹ��,�Ӻ���ǰ�<br>
	 * 
	 * minSum[] : �����ֱ�����i��ͷ��ȡ�õ���С���ۼӺ� <br>
	 * minSumEnd[] : �����ֱ�����i��ͷ�����飬��ȡ����С�ۼӺ�ʱ���ұ߽�<br>
	 * 
	 * ˼·�� <br>
	 * 1. �����i��ͷ��С�ۼӺͶ�>k, ��ô��0��ͷ�κ��������ۼӺͲ�����<=k <br>
	 * 2. �����i��ͷ��С�ۼӺ�<k, ��minSum����������ֱ��>kͣ���洢��<br>
	 * e.g : 0 ��ͷ������minSum������ֱ��13λ�ó���K��ͣ ����14������ʱ�򻻳�1��ͷ������14λ���ܲ��ܽ�������������Ȼ��2λ��...
	 * <br>
	 * 
	 * ���ڵ��ұ߽粻����O(N)��<br>
	 * 0............13|14 // minSum��0��ͷ����13�������ˡ�(0~13) = sum����ʱ�򵯳�0����һ��1��ʼ��������14��,
	 * ��ע�Ƿ��ܰѴ��ƸߵĿ�����<br>
	 * 0|1..........13|14 // ��(1~13) = sum - minSum[0]<br>
	 * 
	 * Hard��
	 * 
	 *
	 */
	public static int findLongestSubArrayAnySymbolLessThanK(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] minSums = new int[arr.length];
		int[] minSumEnds = new int[arr.length];
		minSums[arr.length - 1] = arr[arr.length - 1];
		minSumEnds[arr.length - 1] = arr.length - 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (minSums[i + 1] < 0) {
				minSums[i] = arr[i] + minSums[i + 1];
				minSumEnds[i] = minSumEnds[i + 1];
			} else {
				minSums[i] = arr[i];
				minSumEnds[i] = i;
			}
		}

		// ��ʼ��
		int end = 0;// �ٳ�����������һ����Ŀ�ͷλ�ã�0~13�� ��endͣ��14λ��
		int sum = 0; // �����ۼӺ�
		int ans = 0; // ��󳤶�/��
		for (int i = 0; i < arr.length; i++) {
			// whileѭ������֮��
			// 1) �����i��ͷ������£��ۼӺ�<=k�����������arr[i..end-1]��������������鳤���ܲ��ܸ���res��
			// 2) �����i��ͷ������£��ۼӺ�<=k����������arr[i..end-1]�̣����»��ǲ�����res������Ӱ�����ս����

			while (end < arr.length && sum + minSums[end] <= k) {
				sum += minSums[end];
				end = minSumEnds[end] + 1;
			}
			ans = Math.max(ans, end - i); // end �ǵ�һ����������λ�� - i���ڿ�ͷ��λ��
			if (end > i) { // ���д��ڣ����´���û������ [i~end) [4,4)
				sum -= arr[i]; // iҪ��ȥ�ˣ�sum-Ҫ��ȥ��
			} else { // i == end, ���� i++, i > end, ��ʱ���ڸ���ά�ֲ�ס�ˣ�����end����iһ����
				end = i + 1;
			}
		}
		return ans;

	}

	/**
	 * ��������ƽ��ֵ<=k��������鳤�ȣ�<br>
	 * 
	 * �⣺����k=10�� ����������-10�� => ���������ۼӺ�<= 0��������鳤��.
	 */
	public static int findLongestSubArrayAverageLessThanK(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] -= k;
		}

		return findLongestSubArrayAnySymbolLessThanK(arr, 0);

	}

}
