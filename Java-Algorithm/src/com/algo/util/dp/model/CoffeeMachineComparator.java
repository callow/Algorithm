package com.algo.util.dp.model;

import java.util.Comparator;

public class CoffeeMachineComparator implements Comparator<CoffeeMachine> {

	/**
	 * o1.timePoint + o1.workTime ���������У�˭С˭������
	 */
	@Override
	public int compare(CoffeeMachine o1, CoffeeMachine o2) {
		return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
	}

}
