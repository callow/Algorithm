package leetcode;

import java.util.Arrays;
/**
 * 
 * 去除数组中重复的元素
 *
 */
public class Remove_Duplicates_from_Sorted_Array_26 {

	public static void main(String[] args) {
		int[] input = {1,1,2};
		System.out.println(removeDuplicates(input));
	}
	
	//自己的解
	 public static int removeDuplicates(int[] nums) {
		    nums  = Arrays.stream(nums).distinct().sorted().toArray();
	        return nums.length;
	  }
	 
	 //leetcode的解
	 public static int removeDuplicates2(int[] A) {
		 int j=0;
         if (A.length==0) {
        	 return 0; 
         }
        
         for (int i=0; i<A.length; i++) {
        	 if (A[i]!=A[j]) {
        		 A[++j]=A[i];
        	 }
         }
         return ++j;
	 }
}
