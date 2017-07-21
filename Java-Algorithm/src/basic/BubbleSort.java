package basic;

import java.util.Arrays;

public class BubbleSort {
	
	// ��һ������߰������������С��Ԫ�� �Ƚ��� ������һ���ϸ�
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
                    exchangeElements(array, j, j + 1);// ���ڵ�������λ��
                }  
            }
        }
    }
    
    // ��������������ܳ���
    public static void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];  // ��һ��
        array[index1] = array[index2];  // �ڶ���������һ��
        array[index2] = temp;  // ��һ�������ڶ���
    }  
	
}
