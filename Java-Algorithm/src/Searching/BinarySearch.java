package Searching;

public class BinarySearch {

	public static int binarySearch(int[] array, int target) {
		return binarySearch(array, target, 0, array.length - 1);

	}

	public static int binarySearch(int[] array, int target, int left, int right) {
		if (left > right) {
			return -1; // 左右相逢后 没找到
		}
		int middle = (left + right) / 2;
		int middleValue = array[middle];
		if (target == middleValue) {
			return middle; //直接命中
		} else if (target < middleValue) { // 目标在左边
			return binarySearch(array, target, left, middle - 1);
		} else { // 目标在右边
			return binarySearch(array, target, middle + 1, right);
		}
	}
}
