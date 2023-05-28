package com.algo.util.segmenttree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.psjava.ds.array.MutableArrayFromVarargs;
import org.psjava.ds.tree.segmenttree.RangeUpdatableSegmentTree;
import org.psjava.ds.tree.segmenttree.SegmentTree;
import org.psjava.goods.GoodSegmentTreeFactory;

import com.algo.util.segmenttree.model.MaxSegmentTree;

/**
 * O��Log(N)�� ����������ѯ��������µ�����<br>
 * �߶�����;��  Arr[1 ~ 200] �� 6 /  Arr[1 ~ 200] -> 6 /  ��[1 ~ 200] <br>
 * 1. �����ͳһ����add<br>
 * 2. �����ͳһ����update<br>
 * 3. ������ۼӺ�sum<br>
 * �߶����������±��1��ʼ 0 λ����������.<br>
 * �������� �κ�һ���ڵ�i , ���ĸ���i/2, ��������2i, �����Һ���2i+1<br>
 * 
 * �߶�������������С�� 4��Ԫ�� -> �����С8 ( 0λ��һ�� + 
 * 				[1-4]1�� 
 * 				/		\
 *  		 [1-2] 		[3-4] 2�� 
 *  		/	\		/	\	
 *  	  [1-1][2-2]  [3-3][4-4] 4�� = 8���� 4N�϶�����
 *
 * �����¡�
 * 
 */
public class SegmentTreeUtil {
	
	/**
	 * ��Χ���£������ ����O(Log(n))
	 */
	public static int getRangeSum(Integer[] original, int from, int to, int updatedValue) {
		RangeUpdatableSegmentTree<Integer> sumTree = RangeUpdatableSegmentTree.create(MutableArrayFromVarargs.create(original), (Integer a, Integer b)-> a+b);
		sumTree.updateRange(from, to, updatedValue); // ������Ҳ����
		int sum = sumTree.query(from, to);
		return sum;
	}
	
	/**
	 * ��Χ���£�����Χ��max, min, or anything <br>
	 * https://psjava.org/ds/Segment_Tree
	 */
	
	public static int getRangeMax(Integer[] original, int from, int to) {
		SegmentTree<Integer> maxTree = GoodSegmentTreeFactory.getInstance().create( MutableArrayFromVarargs.create(original), (Integer a, Integer b) -> Math.max(a, b));
		maxTree.update(0, 99); // ������Ҳ����
		int max = maxTree.query(from, to);
		return max;
	}
	
	/**
	 * �䷽�����⣺ ÿ��һ�����飬�ռ�һ�����߶ȣ� https://leetcode.com/problems/falling-squares/<br>
	 * [1,3] = ����1λ����һ�� 3*3�ķ���
	 */
	
	public static List<Integer> collectionFallingSquareHeights(int[][] positions) {
		Map<Integer, Integer> map = index(positions);
		int N = map.size();
		MaxSegmentTree segmentTree = new MaxSegmentTree(N);
		int max = 0;
		List<Integer> res = new ArrayList<>();
		
		// ÿ��һ�������Σ��ռ�һ�£����ж�����ɵ�ͼ����߸߶���ʲô
		for (int[] arr : positions) {
			int L = map.get(arr[0]);
			int R = map.get(arr[0] + arr[1] - 1);
			int height = segmentTree.query(L, R, 1, N, 1) + arr[1]; // ԭ����L ~ R�ĸ߶��Ƕ��٣�
			max = Math.max(max, height);
			res.add(max);
			segmentTree.update(L, R, height, 1, N, 1); // ԭ���� L ~ R��height���µ��߶�����
		}
		return res;
	}
	
	private static Map<Integer, Integer> index(int[][] positions) {
		Set<Integer> pos = new TreeSet<>(); // �������ҵı߼���
		for (int[] arr : positions) { 
			pos.add(arr[0]);
			pos.add(arr[0] + arr[1] - 1);
		}
		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for (Integer index : pos) {
			map.put(index, ++count);
		}
		return map; // �ߴ����ҵ�λ��
	}
	
	public static void main(String[] args) {
		int[][] a = {{1,1},{2,4}};
		index(a);
	}

	
	/**
	 * ������߶���ʵ������
	 */
	
	public static void example() {
		int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
		com.algo.util.segmenttree.model.SumSegmentTree seg = new com.algo.util.segmenttree.model.SumSegmentTree(origin);
		int S = 1; // ��������Ŀ�ʼλ�ã��涨��1��ʼ������0��ʼ -> �̶�
		int N = origin.length; // ��������Ľ���λ�ã��涨�ܵ�N������N-1 -> �̶�
		int root = 1; // ��������ͷ�ڵ�λ�ã��涨��1������0 -> �̶�
		int L = 2; // ��������Ŀ�ʼλ�� -> �ɱ�
		int R = 5; // ��������Ľ���λ�� -> �ɱ�
		int C = 4; // Ҫ�ӵ����ֻ���Ҫ���µ����� -> �ɱ�
		
		// �������ɣ�������[S,N]������Χ��build
		seg.build(S, N, root);
		
		// �����޸ģ����Ըı�L��R��C��ֵ������ֵ���ɸı�
		seg.add(L, R, C, S, N, root);
		
		// ������£����Ըı�L��R��C��ֵ������ֵ���ɸı�
		seg.update(L, R, C, S, N, root);
		
		// �����ѯ�����Ըı�L��R��ֵ������ֵ���ɸı�
		long sum = seg.query(L, R, S, N, root);
		
		System.out.println(sum);
	}
	
	/**
	 * 2������x[]��hp[]:
	 * 	x[]һ��������ģ�x[i]��ʾi�Ź�����x���ϵ�λ��
	 * 	hp[]��Ҫ������hp[i]��ʾi�Ź��޵�Ѫ��
	 *  range, ��ʦ�ͷ�AOE���ܵķ�Χ����,���򵽵�ÿֻ������ʧ1��Ѫ��
	 *  
	 *  ����Ҫ�����й���Ѫ����գ�������Ҫ�ͷŶ��ٴ�AOE���ܣ�
	 *  
	 *  ˼·�� 
	 *  
	 *  1) �߶���
	 	2) �����ü��ܵ������Ե������ǰ������û���Ĺ���
	 	3) Ȼ����������һ��û���Ĺ���ظ�����2)
	 * 
	 */
	public static int AOE(int[] hp, int[] x, int range) {
		int n = x.length;
		int[] cover = new int[n];
		int r = 0;
		// cover[i] : ���iλ���Ǽ��ܵ�����࣬�������ҵ�range��Χ�ڣ�����Ӱ�쵽��
		for (int i = 0; i < n; i++) {
			while (r < n && x[r] - x[i] <= range) {
				r++;
			}
			cover[i] = r - 1;
		}
		
		// �����߶���
		com.algo.util.segmenttree.model.SumSegmentTree st = new com.algo.util.segmenttree.model.SumSegmentTree(hp);
		st.build(1, n, 1);
		
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			int  leftestHpEdge = (int) st.query(i, i, 1, n, 1);
			if (leftestHpEdge > 0) {
				ans +=  leftestHpEdge;
				st.add(i, cover[i - 1] + 1, -leftestHpEdge, 1, n, 1); // ��i~cover[i - 1] + 1 ͳһ -leftestHpEdge
			}
		}
		return ans;
	}
	
}
