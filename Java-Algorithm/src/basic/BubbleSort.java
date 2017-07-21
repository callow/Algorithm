package basic;

import java.util.Arrays;

public class BubbleSort {
	
	// 对一大堆乱七八糟的数字排序，小的元素 比较轻 像气泡一样上浮
	public static void main(String[] args) {
		int[] input = {1,3,5,7,9,3,2,6};
		
		bubbleSort(input);
		
		System.out.println(Arrays.toString(input));
	}
	
    public static void bubbleSort(int[] array) {  
        int size = array.length;
        
        for (int i = size - 1; i > 0; i--) {  
            for (int j = 0; j < i; j++) {  
                if (array[j] > array[j + 1]) {
                    exchangeElements(array, j, j + 1);// 相邻的两个换位置
                }  
            }
        }
    }
    
    // 交换，这个方法很常用
    public static void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];  // 第一个
        array[index1] = array[index2];  // 第二个赋给第一个
        array[index2] = temp;  // 第一个赋给第二个
    }  
	
}
