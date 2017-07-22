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
	       int start = 0; // 开始index 
	       int end = input.length - 1; // 结束的index
	       
	       while (start <= end) {
	           int middle = (start + end) / 2; // 中间的index 
	           
	           if (target < input[middle]) {
	               end = middle - 1; // 向左狭窄
	           } else if (target > input[middle]) {
	               start = middle + 1; // 向右狭窄
	           } else {
	               return middle; // 直接找到
	           }
	       }
	       return -1;
	   }

}
