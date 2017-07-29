package basic;

import java.util.Arrays;
/**
 * �ϲ�����,�ֳ����ɸ������� ������������ ����ٰѺϲ��õĺϲ���һ������
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
			// �м�����
			sort(input,left,middle);
			//�м���ұ�
			sort(input,middle + 1,right);
			// ���Һϲ�
			merge(input,left,middle,right);
		}
		return input;
	}
	
    public static void merge(int[] nums, int low, int mid, int high) {  
        int[] temp = new int[high - low + 1];  
        int i = low;// ��ָ��  
        int j = mid + 1;// ��ָ��  
        int k = 0;  
  
        // �ѽ�С�������Ƶ���������  
        while (i <= mid && j <= high) {  
            if (nums[i] < nums[j]) {  
                temp[k++] = nums[i++];  
            } else {  
                temp[k++] = nums[j++];  
            }  
        }  
  
        // �����ʣ�������������  
        while (i <= mid) {  
            temp[k++] = nums[i++];  
        }  
  
        // ���ұ߱�ʣ�������������  
        while (j <= high) {  
            temp[k++] = nums[j++];  
        }  
  
        // ���������е�������nums����  
        for (int k2 = 0; k2 < temp.length; k2++) {  
            nums[k2 + low] = temp[k2];  
        }  
    }  
}
