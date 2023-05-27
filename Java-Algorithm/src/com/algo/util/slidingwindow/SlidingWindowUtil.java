package com.algo.util.slidingwindow;

import java.util.LinkedList;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.CommonStringUtil;

/**
 * 
 * ��������(˫�˶������ʹ��) = ׷�Ͻṹ /�������<br><br>
 * 
 * �����ڴ����� ���/��С/ƽ��/��λ��... �����ݵĸ��º��ռ�<br>
 * 
 * L <= R<br><br>
 *
 * ��������һ�㶼����Ҫ��Lֻ��Ҫ��R����R���ƶ��ǹ̶��ľ���R < arr.length. L�����R�ı䶯������
 */
public class SlidingWindowUtil {

	/**
	 * �ռ�һ����СΪw�Ĵ��ڻ����������ռ��������ֵ���� ~ O(N)<br><br>
	 * ˼·�� �������� + ˫�˶���(linkedlist/deque)<br>
	 * while (arr[maxQueue.peekLast()] <= arr[r]){maxQueue.pollLast()} => ֻҪ������β���Ķ���>С�ڵ�ǰr�ģ�
	 *  ������˫�˶����ǴӴ�С����ģ����� ���𰸶��Ƕ��е�ͷ�� 
	 */
	
	public static int[] getWindowMax(int[] arr, int w) {
		if (CommonArrayUtil.isEmpty(arr) || arr.length < w || w < 1) {
			return null;
		}
		
		int index = 0; // ��responseר�õ��±����
		int[] response = new int[arr.length - w + 1];
		LinkedList<Integer> maxQueue = new LinkedList<>(); // ˫�˶��дӴ�С�����
		
		for (int r = 0; r < arr.length; r++) { // ֻ�� r, r < arr.lnegth
			
			WindowStablizer.removeElementsLessThanMe(maxQueue, arr, r);
			
			// r - w = �����
			WindowStablizer.expireUselessElement(maxQueue, r - w); // �����ڣ�����˫�˶���û�õ�Ԫ��
			if (r >= w - 1) { // �Ƿ��γ�һ�������Ĵ���, 
				response[index++] = arr[maxQueue.peekFirst()];
			}
		}
		return response;
	}
	
	/**
	 * Arr�д��������sub ������ Max(sub) - Min(sub) <= sum, ��arr�д������������ ? O��N�� <br><br>
	 * 
	 * 1. �� Max(sub) - Min(sub) <= sum, ��sub�е�����������һ������ꡣ(��Ϊ���ֵֻ�ܱ�С/���䣬��Сֵֻ�ܱ��/����) <br><br>
	 * 2. �� L ~ R �����(Max(sub) - Min(sub) > sum)���� (L����) ~ R �� L ~ (R����) ��������һ������ꡣ(��Ϊ���ֵֻ�ܱ��/���䣬��Сֵֻ�ܱ�С/����) 
	 */
	
	public static int getQualifiedSubArray (int[] arr, int sum) {
		
		if (CommonArrayUtil.isEmpty(arr) || sum < 0) {
			return 0;
		}
		int n = arr.length;
		int count = 0;
		LinkedList<Integer> maxWindow = new LinkedList<>();// ��ͷ->β�� �� -> С
		LinkedList<Integer> minWindow = new LinkedList<>();// ��ͷ->β�� С -> ��
		int r = 0;
		
		for (int L = 0; L < n; L++) { // ������0��1��2��3....��ͷ�������飬�����ж���
			// ������������r���β������ ͣ��
			while (r < n) {
				
				WindowStablizer.removeElementsLessThanMe(maxWindow, arr, r);
				WindowStablizer.removeElementsGreaterThanMe(minWindow, arr, r);
				if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) { // ������ˣ�
					break;
				} else {
					r++;
				}
			}
			count += r - L;
			WindowStablizer.expireUselessElement(maxWindow, L); // ���LҪ���ڣ������
			WindowStablizer.expireUselessElement(minWindow, L);
		}
		return count;
	}
	
	/**
	 * O(N)����վ���ó��������⣬�����Щ����վ������������һȦ���� https://leetcode.com/problems/gas-station <br><br>
	 * gas [1,1,3,1] ����վ�ж������͡�<br>
	 * cost[2,2,1,1] ������һ������վ���롣<br>
	 * combined [-1,-1,2,0] <br>
	 * combinedArr��ǰ׺������cb2: [-1,-2,0,0,-1,-2,0,0] <br>	
	 * 2λ�ó����������ۼӺͣ� Min(cb2[2~5]) - cb2[1] < 0 ? '2λ�ò������ó�����' �� '2λ�������ó�����' <br>
	 * ˼·�� ��Ϊ��һ����������ӻ������һ���������ѭ����0->�����߹�������ۼӺ�����Ҫ��ԭ����*2��С��ֻҪ�ڴ����ڳ���<b>����</b>�Ͳ������ó�����
	 */
	public static boolean[] canFinishLoop(int[] gas, int[] cost) {
		int N = gas.length;
		int NN = N << 1; // N * 2
		int[] cb2 = new int[NN];
		// ���cb2,2��2�����һ��
		for (int i = 0; i < N; i++) {
			cb2[i] = gas[i] - cost[i];
			cb2[i + N] = gas[i] - cost[i];
		}
		
		for (int i = 1; i < NN; i++) {
			cb2[i] += cb2[i - 1];
		}
		
		//--------------------------------
		
		LinkedList<Integer> minWindow = new LinkedList<>();
		for (int me = 0; me < N; me++) {
			WindowStablizer.removeElementsGreaterThanMe(minWindow, cb2, me);
		}
		
		boolean[] answer = new boolean[N];
		for (int offset = 0, me = 0, j = N; j < NN; offset = cb2[me++], j++) {
			if (cb2[minWindow.peekFirst()] - offset >= 0) { // Min(cb2[2~5]) - cb2[1] >= 0 ? 2�����ó�����
				answer[me] = true;
			}
			WindowStablizer.expireUselessElement(minWindow, me); // ���ù��ˣ������ҴӴ�����
			WindowStablizer.removeElementsGreaterThanMe(minWindow, cb2, j); // �����������ڣ�����Ԫ�أ�����PeekFirst
		}
		return answer;
	}
	
	
	/**
	 * x����һЩ�㣬һ����k�����ӣ������ѹ�м����㣿<br>
	 * 
	 * �⣺����˫ָ�룬Rÿ�������ߵ���ͷ��R-L<=4ʱ�� �ռ���,L�����ƶ� O(N),ָ�벻����
	 */
	public static int pointsCoveredByRape(int[] x, int k) {
		int left = 0;
		int right = 0;
		int N = x.length;
		int max = 0;
		while (left < N) {
			while (right < N && x[right] - x[left] <= k) {
				right++;
			}
			max = Math.max(max, right - (left++));
		}
		return max;
	}
	
	/**
	 * 
	 * һ��������ֻ�������ַ�'G'��'B',���ڲſɽ�����GҪȫ����Bȫ���� || ��֮���������ٵĽ�������?
	 * 
	 * �⣺˫ָ�룬��ǰ�ƶ� O(N)
	 * 
	 * e.g: B B G G B B G
	 *      gi  i
	 */
	public static int swapGB(String s) {
		if (CommonStringUtil.isEmpty(s)) { 
			return 0;
		}
		char[] str = s.toCharArray();
		int step1 = 0;
		int step2 = 0;
		int gi = 0;
		int bi = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == 'G') { // ��ǰ��G��ȥ���   ����1
				step1 += i - (gi++);
			} else {// ��ǰ��B��ȥ���   ����2
				step2 += i - (bi++);
			}
		}
		return Math.min(step1, step2);
	}
	
}
