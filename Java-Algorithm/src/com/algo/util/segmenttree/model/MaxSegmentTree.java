package com.algo.util.segmenttree.model;

public class MaxSegmentTree {

	private int[] max;
	private int[] change;
	private boolean[] update;

	/**
	 *  没有元数组，因为假设原数组全是0
	 */
	public MaxSegmentTree(int size) {
		int N = size + 1;
		max = new int[N << 2];

		change = new int[N << 2];
		update = new boolean[N << 2];
	}

	private void pushUp(int rt) {
		max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
	}

	// ln表示左子树元素结点个数，rn表示右子树结点个数
	private void pushDown(int rt, int ln, int rn) {
		if (update[rt]) {
			update[rt << 1] = true;
			update[rt << 1 | 1] = true;
			change[rt << 1] = change[rt];
			change[rt << 1 | 1] = change[rt];
			max[rt << 1] = change[rt];
			max[rt << 1 | 1] = change[rt];
			update[rt] = false;
		}
	}

	public void update(int L, int R, int C, int l, int r, int rt) {
		if (L <= l && r <= R) {
			update[rt] = true;
			change[rt] = C;
			max[rt] = C;
			return;
		}
		int mid = (l + r) >> 1;
		pushDown(rt, mid - l + 1, r - mid);
		if (L <= mid) {
			update(L, R, C, l, mid, rt << 1);
		}
		if (R > mid) {
			update(L, R, C, mid + 1, r, rt << 1 | 1);
		}
		pushUp(rt);
	}

	public int query(int L, int R, int l, int r, int rt) {
		if (L <= l && r <= R) {
			return max[rt];
		}
		int mid = (l + r) >> 1;
		pushDown(rt, mid - l + 1, r - mid);
		int left = 0;
		int right = 0;
		if (L <= mid) {
			left = query(L, R, l, mid, rt << 1);
		}
		if (R > mid) {
			right = query(L, R, mid + 1, r, rt << 1 | 1);
		}
		return Math.max(left, right);
	}
}
