package com.algo.util.segmenttree.model;

public class SumSegmentTree {
	// arr[]Ϊԭ���е���Ϣ��0��ʼ������arr���Ǵ�1��ʼ��
	// sum[]ģ���߶���ά�������
	// lazy[]Ϊ�ۼӺ�������
	// change[]Ϊ���µ�ֵ
	// update[]Ϊ����������
	private int MAXN;
	private int[] arr;
	private int[] sum;
	private int[] lazy;
	private int[] change;
	private boolean[] update;

	public SumSegmentTree(int[] origin) {
		MAXN = origin.length + 1;
		arr = new int[MAXN]; // arr[0] ���� ��1��ʼʹ��
		for (int i = 1; i < MAXN; i++) {
			arr[i] = origin[i - 1];
		}
		// << 2 =  ��4��
		sum = new int[MAXN << 2]; // ����֧���Բ������У�ĳһ����Χ���ۼӺ���Ϣ
		lazy = new int[MAXN << 2]; // ����֧���Բ������У�ĳһ����Χ�]�������f���n���΄�
		change = new int[MAXN << 2]; // ����֧���Բ������У�ĳһ����Χ��û�и��²���������
		update = new boolean[MAXN << 2]; // ����֧���Բ������У�ĳһ����Χ�������񣬸��³���ʲô
	}

	/**
	 * 
	 * rt << 1 = 2 * rt
	 * rt << 1 | 1 = 2 * rt + 1
	 * 
	 * �� = ������ + ���Һ���
	 */
	private void pushUp(int rt) {
		sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
	}

	// ֮ǰ�ģ����������ӣ��������£��Ӹ���Χ���������������ӷ�Χ
	// �ַ�������ʲô
	// ln��ʾ������Ԫ�ؽ�������rn��ʾ������������
	private void pushDown(int rt, int ln, int rn) {
		if (update[rt]) {
			update[rt << 1] = true;
			update[rt << 1 | 1] = true;
			change[rt << 1] = change[rt];
			change[rt << 1 | 1] = change[rt];
			lazy[rt << 1] = 0;
			lazy[rt << 1 | 1] = 0;
			sum[rt << 1] = change[rt] * ln;
			sum[rt << 1 | 1] = change[rt] * rn;
			update[rt] = false;
		}
		if (lazy[rt] != 0) {
			lazy[rt << 1] += lazy[rt];
			sum[rt << 1] += lazy[rt] * ln;
			lazy[rt << 1 | 1] += lazy[rt];
			sum[rt << 1 | 1] += lazy[rt] * rn;
			lazy[rt] = 0;
		}
	}

	// �ڳ�ʼ���׶Σ��Ȱ�sum���飬��ã��ݹ麯��
	// ��arr[l~r]��Χ�ϣ�ȥbuild��1~N��
	// rt : �����Χ��sum�е��±�,e.g [1-5] ���������rtλ�� �����±�
	public void build(int l, int r, int rt) {
		if (l == r) { // [1000 ~ 1000] => L = R һ�����������Ҷ�ڵ��ˣ�����Ҫ�������ˣ�ֱ�Ӱ�origin����sum[]�н������� 
			sum[rt] = arr[l];
			return;
		}
		int mid = (l + r) >> 1;
		build(l, mid, rt << 1); // ���build����
		build(mid + 1, r, rt << 1 | 1); // �ұ�build����
		pushUp(rt); // ���Һ���һ�Ӿ��Ǹ���
	}

	/**
	 * L~R  ���е�ֵ���C
	 * l~r  rt
	 */
	public void update(int L, int R, int C, int l, int r, int rt) {
		if (L <= l && r <= R) {
			update[rt] = true;
			change[rt] = C;
			sum[rt] = C * (r - l + 1);
			lazy[rt] = 0;
			return;
		}
		// ��ǰ����㲻�����޷������£�Ҫ���·�
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

	/**
	 * L~R, C ���� e.g : ��L~R��Χ��ÿ��Ԫ��+C. 
	 * 
	 * rt��l~r e.g: 1λ�ñ�ʾ[1-1000], rt = 1�� l = 1, r = 1000
	 */
	public void add(int L, int R, int C, int l, int r, int rt) {
		
		// ��������Ѵ�ʱ�ķ�Χȫ���ˣ� => ������
		if (L <= l && r <= R) {
			sum[rt] += C * (r - l + 1); // �ۼӺ�ֱ�ӱ��
			lazy[rt] += C; // ��������Ϣ+=C��������Ҫ�������º���
			return;
		}
		// ����û�а���ȫ����
		// l  r  mid = (l+r)/2
		int mid = (l + r) >> 1;
		pushDown(rt, mid - l + 1, r - mid);
		// L~R
		if (L <= mid) { //e.g: ������ 3~874�� L = 3�� ��ǰrt ���� 1 ~ 500�� mid = 250,  3<250, �����������·���1~500���Ӽ�1~250
			add(L, R, C, l, mid, rt << 1);
		}
		if (R > mid) { // �·����Һ���
			add(L, R, C, mid + 1, r, rt << 1 | 1);
		}
		pushUp(rt);
	}

	// 1~6 �ۼӺ��Ƕ��٣� 1~8 rt
	public long query(int L, int R, int l, int r, int rt) {
		if (L <= l && r <= R) {
			return sum[rt];
		}
		int mid = (l + r) >> 1;
		pushDown(rt, mid - l + 1, r - mid);
		long ans = 0;
		if (L <= mid) {
			ans += query(L, R, l, mid, rt << 1);
		}
		if (R > mid) {
			ans += query(L, R, mid + 1, r, rt << 1 | 1);
		}
		return ans;
	}


}
