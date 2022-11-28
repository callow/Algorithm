package com.algo.util.disjointset.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.algo.util.common.model.DSNode;
/**
 * 
 * 使用Map实现并查集, 构造器接收： List<V> values
 */
public class UnionFind<V> {
	
	// V 与包装后地V 地对应关系
	public Map<V,DSNode<V>> nodes;
	
	// 用一张表来代替指针，V地父亲是谁
	public Map<DSNode<V>,DSNode<V>> parents;
	
	// 代表节点里面有几个元素
	public Map<DSNode<V>, Integer> sizeMap;
	
	// 构造器
	public UnionFind(List<V> values) {
		nodes = new HashMap<>();
		parents = new HashMap<>();
		sizeMap = new HashMap<>();
		for (V cur : values) {
			DSNode<V> node = new DSNode<>(cur);
			nodes.put(cur, node);
			parents.put(node, node);
			sizeMap.put(node, 1);
		}
	}
	
	/**
	 *  给你一个节点，请你往上到不能再往上，把代表节点返回
	 */
	
	public DSNode<V> findFather(DSNode<V> cur) {
		Stack<DSNode<V>> path = new Stack<>();
		while (cur != parents.get(cur)) {
			path.push(cur);
			cur = parents.get(cur);
		}
		while (!path.isEmpty()) {
			parents.put(path.pop(), cur);
		}
		return cur;
	}
	
	public boolean isSameSet(V a, V b) {
		return findFather(nodes.get(a)) == findFather(nodes.get(b));
	}
	
	/**
	 *  union ~ 平均 O(1)<br>
	 *  a背后所有地 与 b背后所有地合并成一个
	 */
	
	public void union(V a, V b) {
		DSNode<V> aHead = findFather(nodes.get(a));
		DSNode<V> bHead = findFather(nodes.get(b));
		if (aHead != bHead) {
			int aSetSize = sizeMap.get(aHead);
			int bSetSize = sizeMap.get(bHead);
			DSNode<V> big = aSetSize >= bSetSize ? aHead : bHead;
			DSNode<V> small = big == aHead ? bHead : aHead;
			parents.put(small, big);
			sizeMap.put(big, aSetSize + bSetSize);
			sizeMap.remove(small);
		}
	}
	
	public int numberOfSets() {
		return sizeMap.size();
	}

	
}
