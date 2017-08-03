package leetcode;

import java.util.Arrays;

public class Remove_Duplicates_from_Sorted_Array_26 {

	public static void main(String[] args) {
		int[] input = {1,1,2};
		System.out.println(removeDuplicates(input));
	}
	
	 public static int removeDuplicates(int[] nums) {
		    nums  = Arrays.stream(nums).distinct().sorted().toArray();
	        return nums.length;
	    }

}
