package com.algo.util.heap.lotteryTop2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.algo.util.heap.model.SuperHeap;

public class WinnerSelection {

	private HashMap<Integer, Customer> customers; // �û�ID��ӦʲôCustomer
	private SuperHeap<Customer> candidateHeap; // ��ѡ���Ķѣ�����ѣ� - ˭��Ķ�˭������
	private SuperHeap<Customer> winnerHeap; // �ý����Ķѣ�С���ѣ� - ˭�������������
	private int winnerLimit; // ����м����ý���
	
	
	public WinnerSelection(int limit) {
		customers = new HashMap<Integer, Customer>();
		candidateHeap = new SuperHeap<>(new CandidateComparator());
		winnerHeap = new SuperHeap<>(new WinnerComparator());
		winnerLimit = limit;
	}

	// ��ǰ����i���¼����û���arr[i] -> id,  ������ʲô�� buyOrRefund
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
		// ��ǰ�û������ں�ѡ����Ҳ���ڵý���
		if (!candidateHeap.contains(c) && !winnerHeap.contains(c)) {
			if (winnerHeap.size() < winnerLimit) {
				c.enterTime = time;
				winnerHeap.push(c);
			} else {
				c.enterTime = time;
				candidateHeap.push(c);
			}
		// ��ǰ�û��ں�ѡ��
		} else if (candidateHeap.contains(c)) {
			if (c.buy == 0) { // ��������0,Logn
				candidateHeap.remove(c);
			} else { // ����������0�����ǿ϶��仯�� => ��̬����Logn
				candidateHeap.resign(c);
			}
			// ��ǰ�û��ڵý���
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
