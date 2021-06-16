package Recursion;

public class Fibonacci {

	// Time: O(2^n), Space : O(N)
	public static int getNthFib1(int n) {
		if (n == 1) {
			return 0;
		}
		if (n == 2) {
			return 1;
		}
		return getNthFib1(n - 1) + getNthFib1(n - 2);
	}

	// Time: O(n), Space : O(1)
	public static int getNthFib2(int n) {
		int[] last2 = { 0, 1 };
		int counter = 3;
		while (counter <= n) {
			int nextFib = last2[0] + last2[1];
			last2[0] = last2[1];
			last2[1] = nextFib;
			counter++;
		}
		return n > 1 ? last2[1] : last2[0];
	}
}
