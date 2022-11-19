package com.algo.util.common.model;

import java.util.Arrays;
import java.util.Comparator;
/**
 * Ïß¶Î
 *
 */
public class Line {

	public int start;
	public int end;

	public Line(int s, int e) {
		start = s;
		end = e;
	}
	
	public static Comparator<Line> getStartAscComparator() {
		return (o1,o2) -> o1.start - o2.start;
	}
	
	public static Line[] fill(int[][] m) {
		Line[] lines = new Line[m.length];
		for (int i = 0; i < m.length; i++) {
			lines[i] = new Line(m[i][0], m[i][1]);
		}
		return lines;
	}
	
	public static Line[] fillAndSort(int[][] m) {
		Line[] lines = fill(m);
		Arrays.sort(lines, Line.getStartAscComparator());
		return lines;
	}
}
