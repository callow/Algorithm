package basic;

public class PrimeUnder100 {
	
	// find all primary numbers under 100, prime start from 2 3 5 ...
	public static void main(String[] args) {
		
		for (int i =2; i <= 100; i++) {  // prime
			for (int k = 2; k <= i; k++) { // divisor
				
				if (i % k  == 0 && i != k) { // �ܱ�����  && ���Ǳ�1 ���Լ�����
					break;
				}
				
				if (i % k == 0 && i == k ) { // �ܱ����� && �� 1 �� �Լ�����
					System.out.println(i);
				}
				
			}
		}
	}
}
