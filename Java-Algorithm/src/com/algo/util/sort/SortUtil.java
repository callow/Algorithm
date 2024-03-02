package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.CommonNumberUtil;

public class SortUtil {
	
	/**
	 * ѡ������ O��N^2����ÿ���ҵ���С�Ǹ��ŵ�ǰ��ȥ(����) <br>
	 *   - 0 ~ N-1  ѡ����Сֵ�����ģ�swap�ŵ�0λ����  <br>
	 *   - 1 ~ n-1  �ҵ���Сֵ�����ģ�swap�ŵ�1 λ����  <br>
	 *   - 2 ~ n-1  �ҵ���Сֵ�����ģ�swap�ŵ�2 λ����  <br>
	 */
	public static void selectionSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		for (int i =0; i < arr.length -1; i++) {
			int min = i;
			for (int j = i+1; j <arr.length; j++) {
				min = arr[j] < arr[min] ? j : min;
			}
			CommonArrayUtil.swap(arr, i, min);
		}
	}
	
	/**
	 * ð������ O��N^2��: ��������Ϊ2�Ĵ���(ð��)��˭��˭����(����)��ÿ�ֽ������������Ȼ����i-1����
	 *   - 0 ~ N-1����, ���ķ��������
	 *   - 0 ~ N-2������ ������2��������������
	 *   - 0 ~ N-3������ ������3��������������
	 */
	
	public static void bubbleSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		
		for (int e = arr.length -1; e > 0; e--) {
			for (int i = 0; i < e; i++) {
				if (arr[i] > arr[i+1]) {
					CommonArrayUtil.swap(arr, i, i+1);
				}
			}
		}
	}
	/**
	 * �������� O��N^2��: ֻҪ��ǰ�����������С��һֱ����,һֱ����
	 *  0 ~ 1 ��������
	 *  0 ~ 2 ��������
	 *  0 ~ 3 ��������
	 *  0 ~ n-1 ��������
	 */
	public static void insertSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				CommonArrayUtil.swap(arr, j, j + 1);
			}
		}
	}
	
	/**
	 * �鲢���� O(N * logN) <br>
	 *  - ׼��һ��help[], ����˭С����˭�����������ƶ�.help[]�����󣬿�����ԭ����������� <br>
	 *  - ָ�벻���� => O(N) <br>
	 *  - ��ͣ�طֽ⣬merge,�ֽ⣬merge
	 */
	
	public static void mergeSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}
	
	private static void process(int[] arr, int l, int r) {
		if (l == r) { // base case , ������
			return;
		}
		int mid = l + ((r - l) / 2);
		process(arr, l, mid);
		process(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}
	
	// merge = ˭С����˭
	private static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r-l +1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		
		while(p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		// Ҫôp1Խ���ˣ�Ҫôp2Խ����
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}
	
	/**
	 * �������� �� �����ڱȽϵ����� / Ͱ���� O(N)<br><br>
	 * �������ݷ�ΧС�ҹ̶���
	 * arr ֻ�����ݷ�ΧС�� e.g: �˵�����
	 * 
	 */
	public static void countSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) { // O(n)
			max = Math.max(max, arr[i]);
		}
		int[] bucket = new int[max + 1]; // ׼��max+1��Ͱ
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) {
				arr[i++] = j;
			}
		}
	}
	/**
	 * ���Խ��ܸ����ؼ������� 
	 */
	
    public static void countingSort(int[] arr) {
    	if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
    	
        int i, j, max = -1; 
        int[] values;
        //get the highest absolute value to determine the size of the array 
        for (i = 0; i < arr.length; i++)
            if (Math.abs(arr[i]) > max) max = Math.abs(arr[i]);
        //create double the max size array
        values = new int[max*2 + 1];

        //when reaching a value make a shift of max size            
        for (i = 0; i < arr.length; i++)
            values[arr[i]+max]++;

        i = 0; j = values.length - 1;
        while (i < arr.length)
        {
            if (values[j] > 0)
            {
                values[j]--;
                //shift back when putting in the sorted array
                arr[i] = j-max;
                i++;
            }
            else j--;
        }
    }
	
	
	/**
	 * �������� �� �����ڱȽϵ����� / Ͱ���� O(N)<br><br>
	 * ���ø�λΪ׼��Ͱ�����ð�λΪ׼��Ͱ������ǧλΪ׼��Ͱ.....<br> <br>
	 * ������ֻ�����ڷǸ���������и�����ͳһ�ӳ�������������
	 */
	
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		radix(arr, 0, arr.length - 1, CommonNumberUtil.maxbits(arr));
	}
	
	
	/**
	 * ��L ~ R ��Χ������
	 * @param digit ���ֵ��λ����e.g 3λ ����λ ʮλ ��λ
	 * 
	 * ���̣�
	 * 	��λ�Ƚ�Ͱ �ٵ��� -> ��λ�����Ѿ�����
	 * 	ʮλ�Ƚ�Ͱ �ٵ��� -> ʮλ�����Ѿ�����
	 *  ��λ�Ƚ�Ͱ �ٵ��� -> ��λ�����Ѿ�����
	 * 		
	 */
	private static void radix(int[] arr, int L, int R, int digit) {
		final int radix = 10;
		int i = 0, j = 0;
		// �ж��ٸ���׼�����ٸ������ռ�
		int[] help = new int[R - L + 1];
		for (int d = 1; d <= digit; d++) { // �ж���λ�ͽ���Ͱ����
			// 10���ռ�
		    // count[0] ��ǰλ(dλ)��0�������ж��ٸ�
			// count[1] ��ǰλ(dλ)��(0��1)�������ж��ٸ�
			// count[2] ��ǰλ(dλ)��(0��1��2)�������ж��ٸ�
			// count[i] ��ǰλ(dλ)��(0~i)�������ж��ٸ�
			int[] count = new int[radix]; // count[0..9]
			
			// offset��ȡ��λ���ɣ����ÿһλ�Ĵ�Ƶcount[]
			for (i = L; i <= R; i++) {
				// 103  1   3
				// 209  1   9  
				j = CommonNumberUtil.getDigit(arr[i], d);
				
				count[j]++;
			}
			// <= 2���� a���� �� <=4�ľ���a+b��
			for (i = 1; i < radix; i++) {
				count[i] = count[i] + count[i - 1];
			}
			// ������������� = ��Ͱ��
				// e.g: 514 ������ʮλʱ�� <=10 ��count[] = 4 �� 4 -1 = 3, ��������help[]��3λ��
			for (i = R; i >= L; i--) {
				j = CommonNumberUtil.getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			for (i = L, j = 0; i <= R; i++, j++) {
				arr[i] = help[j];
			}
		}
	}
	
	/**
	 * ϣ������ Time O(n^(1.3-2)) Space: O(1) <br><br>
	 * 
	 * û�� O(n(logn))�죬���Ǳ�O(n^2 ) ��̫��
	 * 
	 */
	
	public static void shellSort(int[] arrays) {
        for (int step = arrays.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arrays.length; i++) {
                int j = i;
                int temp = arrays[j];
                while (j - step >= 0 && arrays[j - step] > temp) {
                    arrays[j] = arrays[j - step];
                    j = j - step;
                }
                arrays[j] = temp;
            }
        }
    }
}
