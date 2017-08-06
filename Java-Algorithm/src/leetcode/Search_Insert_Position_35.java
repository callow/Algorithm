package leetcode;

import java.util.Arrays;

public class Search_Insert_Position_35 {

	public static void main(String[] args) {
		int[] input = {1,3,5,6};
		int target = 2;
		System.out.println(searchInsert2(input,target));
	
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
	
	 
	// leetcode的解,有点看不懂 回来研究
	 public static int searchInsert2(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(A[mid] == target) {
            	return mid;
            } else if(A[mid] > target) {
            	high = mid - 1;
            } else {
            	low = mid + 1;
            }
        }
        return low;
	 }

}
