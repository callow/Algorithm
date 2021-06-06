package others;

import java.util.Arrays;
import java.util.Collections;

public class FindLargesNumber {
	public static void main(String[] args) {
		int i = 12655663;
		String[] input  = String.valueOf(i).split("");
		Arrays.sort(input,Collections.reverseOrder());
		System.out.println(Arrays.toString(input).replaceAll(", ", "").replace("[", "").replace("]",""));
	}
}
