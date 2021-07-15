package Searching;

public class BinarySearch {

	public static int binarySearch(int[] array, int target) {
		return binarySearch(array, target, 0, array.length - 1);

	}

	public static int binarySearch(int[] array, int target, int left, int right) {
		if (left > right) {
			return -1; // �������� û�ҵ�
		}
		int middle = (left + right) / 2;
		int middleValue = array[middle];
		if (target == middleValue) {
			return middle; //ֱ������
		} else if (target < middleValue) { // Ŀ�������
			return binarySearch(array, target, left, middle - 1);
		} else { // Ŀ�����ұ�
			return binarySearch(array, target, middle + 1, right);
		}
	}
}
