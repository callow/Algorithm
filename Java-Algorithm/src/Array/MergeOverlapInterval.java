package Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeOverlapInterval {

	public int[][] mergeOverlappingIntervals(int[][] intervals) {
		int[][] sortedIntervals = intervals.clone(); // 复制一份出来
		Arrays.sort(sortedIntervals, (x, y) -> Integer.compare(x[0], y[0])); // Based On 第一个元素两两比较排序

		List<int[]> result = new LinkedList<>();
		int[] currentInterval = sortedIntervals[0];
		result.add(currentInterval); // 先放一个进去

		for (int[] interval : sortedIntervals) {
			int currentEnd = currentInterval[1]; // 第一个末
			int nextStart = interval[0]; // 第二个初
			int nextEnd = interval[1]; // 第二个末

			if (currentEnd >= nextStart) { // 可以合并
				currentInterval[1] = Math.max(currentEnd, nextEnd);
			} else { // 不能合并了，准备加入结果中
				currentInterval = interval; // 往前走
				result.add(currentInterval);
			}
		}
		int elementSize = result.size();
		return result.toArray(new int[elementSize][2]);
	}
}
