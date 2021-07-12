package stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinMaxStack {

	List<Map<String, Integer>> minMaxStack = new ArrayList<>();
	List<Integer> stack = new ArrayList<>();

	// O(1) time | O(1) space： 直接拿栈顶即List最后一个元素
	public int peek() {
		return stack.get(stack.size() - 1);
	}

	// O(1) time | O(1) space： 直接删栈顶即List最后一个元素
	public int pop() {
		minMaxStack.remove(minMaxStack.size() - 1);
		return stack.remove(stack.size() - 1);
	}

	// O(1) time | O(1) space
	public void push(Integer number) {
		Map<String, Integer> newMinMax = new HashMap<>();
		newMinMax.put("min", number);
		newMinMax.put("max", number);
		if (minMaxStack.size() > 0) { // has history records
			Map<String, Integer> lastMinMax = new HashMap<String, Integer>(minMaxStack.get(minMaxStack.size() - 1));
			newMinMax.replace("max", Math.max(lastMinMax.get("max"), number));
			newMinMax.replace("min", Math.min(lastMinMax.get("min"), number));
		}

		minMaxStack.add(newMinMax);
		stack.add(number);
	}

	// O(1) time | O(1) space
	public int getMin() {
		return minMaxStack.get(minMaxStack.size() - 1).get("min");
	}

	// O(1) time | O(1) space
	public int getMax() {
		return minMaxStack.get(minMaxStack.size() - 1).get("max");
	}
}
