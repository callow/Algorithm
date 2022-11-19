package com.algo.util.heap.lotteryTop2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.algo.util.heap.model.SuperHeap;

public class WinnerSelection {

	private HashMap<Integer, Customer> customers; // 用户ID对应什么Customer
	private SuperHeap<Customer> candidateHeap; // 候选区的堆（大根堆） - 谁买的多谁排上面
	private SuperHeap<Customer> winnerHeap; // 得奖区的堆（小根堆） - 谁买的少排最上面
	private int winnerLimit; // 最多有几个得奖者
	
	
	public WinnerSelection(int limit) {
		customers = new HashMap<Integer, Customer>();
		candidateHeap = new SuperHeap<>(new CandidateComparator());
		winnerHeap = new SuperHeap<>(new WinnerComparator());
		winnerLimit = limit;
	}

	// 当前处理i号事件，用户：arr[i] -> id,  发生了什么： buyOrRefund
	public void operate(int time, int id, boolean buyOrRefund) {
		if (!buyOrRefund && !customers.containsKey(id)) {
			return;
		}
		if (!customers.containsKey(id)) {
			customers.put(id, new Customer(id, 0, 0));
		}
		Customer c = customers.get(id);
		if (buyOrRefund) {
			c.buy++;
		} else {
			c.buy--;
		}
		if (c.buy == 0) {
			customers.remove(id);
		}
		// 当前用户即不在候选区，也不在得奖区
		if (!candidateHeap.contains(c) && !winnerHeap.contains(c)) {
			if (winnerHeap.size() < winnerLimit) {
				c.enterTime = time;
				winnerHeap.push(c);
			} else {
				c.enterTime = time;
				candidateHeap.push(c);
			}
		// 当前用户在候选区
		} else if (candidateHeap.contains(c)) {
			if (c.buy == 0) { // 购买数是0,Logn
				candidateHeap.remove(c);
			} else { // 购买数不是0，但是肯定变化了 => 动态调整Logn
				candidateHeap.resign(c);
			}
			// 当前用户在得奖区
		} else {
			if (c.buy == 0) {
				winnerHeap.remove(c);
			} else {
				winnerHeap.resign(c);
			}
		}
		winnerMove(time);
	}
	
	public List<Integer> getWinners() {
		List<Customer> customers = winnerHeap.getAllElements();
		List<Integer> ans = new ArrayList<>();
		for (Customer c : customers) {
			ans.add(c.id);
		}
		return ans;
	}
	
	private void winnerMove(int time) {
		if (candidateHeap.isEmpty()) {
			return;
		}
		if (winnerHeap.size() < winnerLimit) {
			Customer p = winnerHeap.pop();
			p.enterTime = time;
			winnerHeap.push(p);
		} else {
			if (candidateHeap.peek().buy > winnerHeap.peek().buy) {
				Customer oldDaddy = winnerHeap.pop();
				Customer newDaddy = candidateHeap.pop();
				oldDaddy.enterTime = time;
				newDaddy.enterTime = time;
				winnerHeap.push(newDaddy);
				candidateHeap.push(oldDaddy);
			}
		}
	}
}
