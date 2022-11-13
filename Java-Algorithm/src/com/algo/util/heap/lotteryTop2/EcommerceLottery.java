package com.algo.util.heap.lotteryTop2;

import java.util.ArrayList;
import java.util.List;

import com.algo.util.heap.lotteryTop2.model.WinnerSelection;

public class EcommerceLottery {

	public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
		List<List<Integer>> ans = new ArrayList<>();
		WinnerSelection winnerSelect = new WinnerSelection(k);
		for (int i = 0; i < arr.length; i++) {
			winnerSelect.operate(i, arr[i], op[i]);
			ans.add(winnerSelect.getWinners());
		}
		return ans;
	}
}
