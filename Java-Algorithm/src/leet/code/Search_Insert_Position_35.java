package leet.code;

import java.util.Arrays;

public class Search_Insert_Position_35 {

	public static void main(String[] args) {
		int[] input = {1,3,5,6};
		int target = 2;
		System.out.println(searchInsert(input,target));
	
	}
	
	// 自己的解
	public static int searchInsert(int[] nums, int target) {
        Arrays.sort(nums);
        if (Arrays.binarySearch(nums, target) >= 0) {
        	return Arrays.binarySearch(nums, target);
        }
        int[] expand = Arrays.copyOf(nums, nums.length +1); // 扩容数组
        expand[nums.length] = target;
        Arrays.sort(expand); // 给数组排序
        return Arrays.binarySearch(expand, target); // 获取某个元素出现的index
    }
}
