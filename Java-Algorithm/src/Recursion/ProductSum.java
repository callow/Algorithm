package Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 乘积求和
 * 
 * @author Song Lei
 *
 */
public class ProductSum {

	// Time: O(n) | Space: O(d) // d = 最大深度， n = 元素数
	public static int productSum(List<Object> array) {
		return productSumHelper(array, 1);
	}

	public static int productSumHelper(List<Object> array, int multiplier) {
		int sum = 0;

		for (Object element : array) {
			if (element instanceof ArrayList) {
				sum += productSumHelper((ArrayList) element, multiplier + 1);
			} else {
				sum += (int) element;
			}
		}

		return sum * multiplier;
	}
}
