package com.algo.util.PrefixSum;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.PrefixSum.model.PrefixSumArray;

/**
 * ǰ׺�����鼼��
 */
public class PrefixSumUtil {

	/**
	 * ��o(1) ʵ�� L ~ R���ۼӺ�
	 */
	public static int sumRange(int[] arr, int left, int right) {
		PrefixSumArray prefixSum = new PrefixSumArray(arr); // ����ǰ׺������
		
		return prefixSum.sum[right + 1] - prefixSum.sum[left]; // ���һ��
	}
	
	
	/**
	 * ���������У��ۼӺ�Ϊk���������. https://www.nowcoder.com/practice/36fb0fd3c656480c92b569258a1223d5
	 * 
	 * ��0 ��i = 1000 k=100����õ���i��β�������飨�� = 100�� = ��ǰ׺��=900�������������
	 */
	
	public static int longestSubArraySumK(int[] arr, int k) {
		
		// key : ĳ��ǰ׺��  value : ���ǰ׺��������ֵ�λ��
		HashMap<Integer, Integer> map = new HashMap<>();
		map.clear();
		map.put(0, -1); // ��Ҫ : 0���ǰ׺�ͣ�һ������Ҳû�е�ʱ�򣬾ʹ�����
		int longestLength = 0;
		
		for (int i = 0, sum = 0; i < arr.length; i++) {
			sum += arr[i]; // ǰ׺��
			if (map.containsKey(sum - k)) { // �ҵ�ǰ׺��=900�������λ��
				longestLength = Math.max(longestLength, i - map.get(sum - k));
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return longestLength;
	}
	
	
	/**
	 * ��������飺 https://leetcode.cn/problems/longest-well-performing-interval/
	 * 
	 * ת���� >8 �� 1�� <= 8 �� -1��Ȼ����ÿ��λ�ý�β�����������͸����ֵ ������һ��
	 * ��0��i �ۼӺ�> 0 ,ֱ�Ӵ�ꡣ ���ۼӺ�sum <= 0, ��ֻ��Ҫ��sum -1 ����������ļ���
	 * 
	 * ��Ϊ�ۼӺ����Ӻͼ��ٶ���һ��һ�����ģ�
	 * 
	 * if 0~i sum = -3, then we need to find a prefixsum = -4 so that prefixsum + 1 ~ i > 0 
	 * why we ignore - 5 -6 -7 ..., as -5 is increased from -4, and -4 must be "left" than -5
	 */
	public static int longestWPI(int[] hours) {
		// ĳ��ǰ׺�ͣ�������ֵ�λ��
		HashMap<Integer, Integer> map = new HashMap<>();
		// 0���ǰ׺�ͣ����������-1��һ����Ҳû�е�ʱ��
		map.put(0, -1);
		int ans = 0;
		for (int i = 0, sum = 0; i < hours.length; i++) {
			sum += hours[i] > 8 ? 1 : -1;
			if (sum > 0) {
				ans = i + 1;
			} else {
				// sum <= 0
				if (map.containsKey(sum - 1)) {
					ans = Math.max(ans, i - map.get(sum - 1));
				}
			}
			if (!map.containsKey(sum)) { // if sum not appeared before, note down the earliest location
				map.put(sum, i);
			}
		}
		return ans;
	}
	
	 /**
		�������ۼӺ�=k�ĸ��� : https://leetcode.com/problems/subarray-sum-equals-k/
		
	    1.ǰ׺��ת���� sum(a ~ b) = k  => prefix[b] - prefix[a-1] = k or prefix[a-1] = prefix[b] - k
	    2. Map(prefix,no.of occurrences of prefix)
	    
	 */
	public static int subarraySum(int[] nums, int k) {
	    
	    Map<Integer,Integer> map = new HashMap<>();
	    int sum = 0;
	    int ans = 0;
	    map.put(0,1); // ����߽磺 prefix[b] - k = 0 (֮ǰȫ�����ֵ�ǰ׺��=0) , ������ǰ׺����û��0�����Ҫ��1��
	    for(int i = 0; i < nums.length; i++) {
	        sum += nums[i];
	
	        if(map.containsKey(sum - k)) { // �Ƿ�֮ǰ���ֹ�prefix[a-1]
	            ans += map.get(sum-k);  // �����˼��ξ�+����
	        }
	
	        map.put(sum, map.getOrDefault(sum,0) + 1);
	    }
	
	    return ans;
	}
	
	
	
	
    /**
     * 	�ۼ�ƽ��ԭ��
     * 
	    ������飬����0��1��������ͬ.
	    ˼·�� 1s = 0s => 1s - 0s = 0
	          0 => -1, 1 => +1, = ��������������飬��Ԫ�غ�Ϊ 0
	    ������+1/-1�ۼ�������ͬԪ����Ĳ��֣�
	    e.g�� [1,0, 0, 0  0,  1,  1, 1]
	    sum:  [1,0,-1,-2,-3, -2, -1, 0]
	    ����balanced�������β��ͬ�����飺0~0, -1~-1, -2~-2, ��ľ���0��0
	
	    ����map��¼counter���״γ��ֵ�λ�á�

	 */
	public static int findMaxLength(int[] nums) {
	    Map<Integer,Integer> map = new HashMap<>(); // ��һ�γ��ֵ�λ�ã�count: index
	    map.put(0,-1);
	    int maxLen = 0, count = 0;
	    for (int i =0; i < nums.length; i++) {
	        count += (nums[i] == 1 ? 1 : -1);
	        if (map.containsKey(count)) { // ������ͬ���ˣ������λ��
	            maxLen = Math.max(maxLen, i - map.get(count));
	        } else {
	            map.put(count,i);
	        }
	    }
	    return maxLen;
	}
}
