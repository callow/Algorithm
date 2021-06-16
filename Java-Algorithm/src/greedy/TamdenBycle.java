package greedy;

import java.util.Arrays;
import java.util.Collections;

public class TamdenBycle {
	public static void main(String[] args) {
		int[] redShirtSpeeds = new int[] { 5, 5, 3, 9, 2 };
		int[] blueShirtSpeeds = new int[] { 3, 6, 7, 2, 1 };
		int expected = 32;
		System.out.println(tandemBicycle(redShirtSpeeds, blueShirtSpeeds, true));
	}

	public static int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
		Integer[] red = Arrays.stream(redShirtSpeeds).boxed().toArray(Integer[]::new);
		Integer[] blue = Arrays.stream(blueShirtSpeeds).boxed().toArray(Integer[]::new);
		if (fastest) {
			Arrays.sort(red);
			Arrays.sort(blue, Collections.reverseOrder());
			int sum = 0;
			for (int i = 0; i < red.length; i++) {
				sum += Math.max(red[i], blue[i]);
			}
			Arrays.sort(blue);
			Arrays.sort(red, Collections.reverseOrder());
			int sum2 = 0;
			for (int i = 0; i < red.length; i++) {
				sum2 += Math.max(red[i], blue[i]);
			}
			return Math.max(sum, sum2);
		} else {
			Arrays.sort(red);
			Arrays.sort(blue);
			int sum = 0;
			for (int i = 0; i < red.length; i++) {
				sum += Math.max(red[i], blue[i]);
			}
			return sum;
		}
	}
}
