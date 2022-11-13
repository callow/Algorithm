package com.algo.util.heap.lotteryTop2;

import java.util.ArrayList;
import java.util.List;

import com.algo.util.heap.lotteryTop2.model.WinnerSelection;

public class EcommerceLottery {

	/**
	 * ��ÿһ����Winner
	 * @param arr �� �û�ID 
	 * @param op �� true ����+1 false �˻�+1
	 * @param k
	 * @return
	 */

	public static List<List<Integer>> topK(int[] customerIds, boolean[] op, int k) {
		List<List<Integer>> ans = new ArrayList<>();
		WinnerSelection winnerSelect = new WinnerSelection(k);
		for (int i = 0; i < customerIds.length; i++) {
			winnerSelect.operate(i, customerIds[i], op[i]);
			ans.add(winnerSelect.getWinners()); 
		}
		return ans;
	}
}
