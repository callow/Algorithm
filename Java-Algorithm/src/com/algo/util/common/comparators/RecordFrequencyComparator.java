package com.algo.util.common.comparators;

import java.util.Comparator;

import com.algo.util.common.model.Record;

public class RecordFrequencyComparator implements Comparator<Record> {

	@Override
	public int compare(Record o1, Record o2) {
		return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
	}

}
