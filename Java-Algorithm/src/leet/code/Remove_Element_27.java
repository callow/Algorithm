package leet.code;

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
	 // �ҵĽ��
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
	 
	 // lettcode�Ľ⣬����һ�������㷨
	 public static int removeElement2(int[] nums, int val) {
	        int j = 0; int count = 0;
	        for (int i = 0; i < nums.length; i++) {
	            if (nums[i] != val) {
	                int tmp = nums[j];
	                nums[j] = nums[i];
	                nums[i] = tmp;
	                count++;
	                j++;
	            }
	            
	        }
	        return count;
	    }

}
