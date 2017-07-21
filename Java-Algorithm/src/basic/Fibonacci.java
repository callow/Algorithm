package basic;

public class Fibonacci  {
	
	// 1 1 2 3 5 8 ...
	public static void main(String[] args) {
		int a = 1, b = 1, c = 0;
		
		for (int i = 1; i <= 18; i++) {// first 20 element
			c = a + b;
			a = b;
			b = c;
			System.out.println(c);
		}
	}
}
