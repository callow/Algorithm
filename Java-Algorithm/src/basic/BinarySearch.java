package basic;

public class BinarySearch {
	// the array must be sorted, find a x whether in the array and what's the position
	public static void main(String[] args) {
		int[] input = new int[] { 12, 23, 34, 45, 56, 67, 77, 89, 90 };
	       System.out.println(search(input, 12));
	       System.out.println(search(input, 45));
	       System.out.println(search(input, 67));
	       System.out.println(search(input, 89));
	       System.out.println(search(input, 99));
	}
	
	 public static int search(int[] input, int target) {
	       int start = 0; // ��ʼindex 
	       int end = input.length - 1; // ������index
	       
	       while (start <= end) {
	           int middle = (start + end) / 2; // �м��index 
	           
	           if (target < input[middle]) {
	               end = middle - 1; // ������խ
	           } else if (target > input[middle]) {
	               start = middle + 1; // ������խ
	           } else {
	               return middle; // ֱ���ҵ�
	           }
	       }
	       return -1;
	   }

}
