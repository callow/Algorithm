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
 * O（Log(N)） 解决是区间查询和区间更新的问题<br>
 * 线段树用途：  Arr[1 ~ 200] 加 6 /  Arr[1 ~ 200] -> 6 /  Σ[1 ~ 200] <br>
 * 1. 区间的统一增加add<br>
 * 2. 区间的统一更新update<br>
 * 3. 区间的累加和sum<br>
 * 线段树的数组下表从1开始 0 位置弃而不用.<br>
 * 二叉树： 任何一个节点i , 它的父是i/2, 它的左孩子2i, 它的右孩子2i+1<br>
 * 
 * 线段树申请的数组大小： 4个元素 -> 数组大小8 ( 0位置一个 + 
 * 				[1-4]1个 
 * 				/		\
 *  		 [1-2] 		[3-4] 2个 
 *  		/	\		/	\	
 *  	  [1-1][2-2]  [3-3][4-4] 4个 = 8个， 4N肯定够用
 *
 * 懒更新。
 * 
 */
public class SegmentTreeUtil {
	
	/**
	 * 范围更新，并求和 例子O(Log(n))
	 */
	public static int getRangeSum(Integer[] original, int from, int to, int updatedValue) {
		RangeUpdatableSegmentTree<Integer> sumTree = RangeUpdatableSegmentTree.create(MutableArrayFromVarargs.create(original), (Integer a, Integer b)-> a+b);
		sumTree.updateRange(from, to, updatedValue); // 不更新也可以
		int sum = sumTree.query(from, to);
		return sum;
	}
	
	/**
	 * 范围更新，并求范围的max, min, or anything <br>
	 * https://psjava.org/ds/Segment_Tree
	 */
	
	public static int getRangeMax(Integer[] original, int from, int to) {
		SegmentTree<Integer> maxTree = GoodSegmentTreeFactory.getInstance().create( MutableArrayFromVarargs.create(original), (Integer a, Integer b) -> Math.max(a, b));
		maxTree.update(0, 99); // 不更新也可以
		int max = maxTree.query(from, to);
		return max;
	}
	
	/**
	 * 落方块问题： 每落一个方块，收集一个最大高度？ https://leetcode.com/problems/falling-squares/<br>
	 * [1,3] = 最左1位置落一个 3*3的方块
	 */
	
	public static List<Integer> collectionFallingSquareHeights(int[][] positions) {
		Map<Integer, Integer> map = index(positions);
		int N = map.size();
		MaxSegmentTree segmentTree = new MaxSegmentTree(N);
		int max = 0;
		List<Integer> res = new ArrayList<>();
		
		// 每落一个正方形，收集一下，所有东西组成的图像，最高高度是什么
		for (int[] arr : positions) {
			int L = map.get(arr[0]);
			int R = map.get(arr[0] + arr[1] - 1);
			int height = segmentTree.query(L, R, 1, N, 1) + arr[1]; // 原数组L ~ R的高度是多少？
			max = Math.max(max, height);
			res.add(max);
			segmentTree.update(L, R, height, 1, N, 1); // 原数组 L ~ R的height更新到线段树中
		}
		return res;
	}
	
	private static Map<Integer, Integer> index(int[][] positions) {
		Set<Integer> pos = new TreeSet<>(); // 方块左右的边集合
		for (int[] arr : positions) { 
			pos.add(arr[0]);
			pos.add(arr[0] + arr[1] - 1);
		}
		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for (Integer index : pos) {
			map.put(index, ++count);
		}
		return map; // 边从左到右的位置
	}
	
	public static void main(String[] args) {
		int[][] a = {{1,1},{2,4}};
		index(a);
	}

	
	/**
	 * 左神的线段树实现例子
	 */
	
	public static void example() {
		int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
		com.algo.util.segmenttree.model.SumSegmentTree seg = new com.algo.util.segmenttree.model.SumSegmentTree(origin);
		int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
		int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
		int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
		int L = 2; // 操作区间的开始位置 -> 可变
		int R = 5; // 操作区间的结束位置 -> 可变
		int C = 4; // 要加的数字或者要更新的数字 -> 可变
		
		// 区间生成，必须在[S,N]整个范围上build
		seg.build(S, N, root);
		
		// 区间修改，可以改变L、R和C的值，其他值不可改变
		seg.add(L, R, C, S, N, root);
		
		// 区间更新，可以改变L、R和C的值，其他值不可改变
		seg.update(L, R, C, S, N, root);
		
		// 区间查询，可以改变L和R的值，其他值不可改变
		long sum = seg.query(L, R, S, N, root);
		
		System.out.println(sum);
	}
	
	/**
	 * 2个数组x[]和hp[]:
	 * 	x[]一定是有序的，x[i]表示i号怪兽在x轴上的位置
	 * 	hp[]不要求有序，hp[i]表示i号怪兽的血量
	 *  range, 法师释放AOE技能的范围长度,被打到的每只怪兽损失1点血量
	 *  
	 *  返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
	 *  
	 *  思路： 
	 *  
	 *  1) 线段树
	 	2) 总是用技能的最左边缘刮死当前最左侧的没死的怪物
	 	3) 然后向右找下一个没死的怪物，重复步骤2)
	 * 
	 */
	public static int AOE(int[] hp, int[] x, int range) {
		int n = x.length;
		int[] cover = new int[n];
		int r = 0;
		// cover[i] : 如果i位置是技能的最左侧，技能往右的range范围内，最右影响到哪
		for (int i = 0; i < n; i++) {
			while (r < n && x[r] - x[i] <= range) {
				r++;
			}
			cover[i] = r - 1;
		}
		
		// 创建线段树
		com.algo.util.segmenttree.model.SumSegmentTree st = new com.algo.util.segmenttree.model.SumSegmentTree(hp);
		st.build(1, n, 1);
		
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			int  leftestHpEdge = (int) st.query(i, i, 1, n, 1);
			if (leftestHpEdge > 0) {
				ans +=  leftestHpEdge;
				st.add(i, cover[i - 1] + 1, -leftestHpEdge, 1, n, 1); // 从i~cover[i - 1] + 1 统一 -leftestHpEdge
			}
		}
		return ans;
	}
	
}
