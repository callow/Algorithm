package leetcode;

import java.util.Arrays;
/**
 * 
 * 移除指定元素 返回新的数组
 *
 */
public class Remove_Element_27 {

	public static void main(String[] args) {
		int[] nums = {3,2,2,1,3};
		int remove = 3;
		removeElement(nums,remove);
		
		
	}
	
	 public static int removeElement(int[] nums, int val) {
		    int i = 0;
		    for (int j = 0; j < nums.length; j++) {
		        if (nums[j] != val) {
		            nums[i] = nums[j]; // 伪造一个linkedlist,如果当前元素不等于指定元素，则加到新的链表中去
		            i++;
		        }
		    }
		    nums = Arrays.copyOf(nums, i);
		    System.out.println(Arrays.toString(nums));
		    
		    return i;
	 }

}
