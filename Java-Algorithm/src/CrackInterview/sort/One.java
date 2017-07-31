package CrackInterview.sort;

import java.util.Arrays;

import basic.BubbleSort;

public class One {

	public static void main(String[] args) {
		int[] A = {1,3,5,7};
		int[] B = {1,2,3,4};
		int[] C = Arrays.copyOf(A, A.length + B.length);
		
		System.arraycopy(B, 0, C, A.length, B.length); //把 B 复制到C 当中
		
		BubbleSort.bubbleSort(C);
		System.out.println(Arrays.toString(C));
	}

}
