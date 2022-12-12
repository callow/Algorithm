package com.algo.util.common.comparators;

import java.util.Comparator;

import com.algo.util.common.model.Record;

public class RecordDepthComparator implements Comparator<Record> {

	@Override
	public int compare(Record o1, Record o2) {
		return o2.deep - o1.deep;
	}
}
