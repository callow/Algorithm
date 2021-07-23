package Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeOverlapInterval {

	public int[][] mergeOverlappingIntervals(int[][] intervals) {
		int[][] sortedIntervals = intervals.clone(); // ����һ�ݳ���
		Arrays.sort(sortedIntervals, (x, y) -> Integer.compare(x[0], y[0])); // Based On ��һ��Ԫ�������Ƚ�����

		List<int[]> result = new LinkedList<>();
		int[] currentInterval = sortedIntervals[0];
		result.add(currentInterval); // �ȷ�һ����ȥ

		for (int[] interval : sortedIntervals) {
			int currentEnd = currentInterval[1]; // ��һ��ĩ
			int nextStart = interval[0]; // �ڶ�����
			int nextEnd = interval[1]; // �ڶ���ĩ

			if (currentEnd >= nextStart) { // ���Ժϲ�
				currentInterval[1] = Math.max(currentEnd, nextEnd);
			} else { // ���ܺϲ��ˣ�׼����������
				currentInterval = interval; // ��ǰ��
				result.add(currentInterval);
			}
		}
		int elementSize = result.size();
		return result.toArray(new int[elementSize][2]);
	}
}
