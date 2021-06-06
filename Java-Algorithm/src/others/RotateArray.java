package others;

import java.util.Arrays;

public class RotateArray {
	public static void main(String[] args) {
		int[] input = {1,2,4,6,7,8,6};
		
		int[] result = new int[input.length];
		int count = 0;
		for (int j = input.length -1; j >= 0; j--) {
			result[count] = input[j]; // 加入结果集准备打印
			count++;
		}
		
		System.out.println(Arrays.toString(result));
	}
}
