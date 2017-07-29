package basic;

import java.util.Arrays;
/**
 * 合并排序,分成若干个子序列 对子序列排序 最后再把合并好的合并成一个整体
 * @author
 *
 */
public class MergeSort {
	public static void main(String[] args) {
		int[] input = {1,3,5,7,9,3,2,6};
		
		sort(input,0,input.length-1);
		
		System.out.println(Arrays.toString(input));
	}
	
	public static int[] sort(int[] input, int left, int right) {
		int middle = (left + right) / 2;
		if (left < right) {
			// 中间的左边
			sort(input,left,middle);
			//中间的右边
			sort(input,middle + 1,right);
			// 左右合并
			merge(input,left,middle,right);
		}
		return input;
	}
	
    public static void merge(int[] nums, int low, int mid, int high) {  
        int[] temp = new int[high - low + 1];  
        int i = low;// 左指针  
        int j = mid + 1;// 右指针  
        int k = 0;  
  
        // 把较小的数先移到新数组中  
        while (i <= mid && j <= high) {  
            if (nums[i] < nums[j]) {  
                temp[k++] = nums[i++];  
            } else {  
                temp[k++] = nums[j++];  
            }  
        }  
  
        // 把左边剩余的数移入数组  
        while (i <= mid) {  
            temp[k++] = nums[i++];  
        }  
  
        // 把右边边剩余的数移入数组  
        while (j <= high) {  
            temp[k++] = nums[j++];  
        }  
  
        // 把新数组中的数覆盖nums数组  
        for (int k2 = 0; k2 < temp.length; k2++) {  
            nums[k2 + low] = temp[k2];  
        }  
    }  
}
