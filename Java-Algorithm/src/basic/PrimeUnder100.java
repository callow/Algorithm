package basic;

public class PrimeUnder100 {
	
	// find all primary numbers under 100, prime start from 2 3 5 ...
	public static void main(String[] args) {
		
		for (int i =2; i <= 100; i++) {  // prime
			for (int k = 2; k <= i; k++) { // divisor
				
				if (i % k  == 0 && i != k) { // 能被整除  && 不是被1 和自己整除
					break;
				}
				
				if (i % k == 0 && i == k ) { // 能被整除 && 被 1 和 自己整除
					System.out.println(i);
				}
				
			}
		}
	}
}
