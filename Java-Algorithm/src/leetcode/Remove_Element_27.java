package leetcode;

import java.util.Arrays;
/**
 * 
 * �Ƴ�ָ��Ԫ�� �����µ�����
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
		            nums[i] = nums[j]; // α��һ��linkedlist,�����ǰԪ�ز�����ָ��Ԫ�أ���ӵ��µ�������ȥ
		            i++;
		        }
		    }
		    nums = Arrays.copyOf(nums, i);
		    System.out.println(Arrays.toString(nums));
		    
		    return i;
	 }

}
