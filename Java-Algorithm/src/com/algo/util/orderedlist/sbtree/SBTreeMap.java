package com.algo.util.orderedlist.sbtree;
/**
 * O（Logn）
 * 叔叔节点为头的整棵树，不可以比侄子节点的子树的任何节点个数少！叔叔 >= 任何侄子 
 * 
 * 平衡因子： Size, 节点个数
 * 
 * 四种违规行为我（父亲）：
 * 	LR : 左儿子的右孩子 > 我右儿子
 *  LL : 左儿子的左孩子 > 我右儿子
 *  RL:  右儿子的左孩子 < 我左儿子
 *  RR:  右儿子的右孩子 < 我左儿子
 * 		
 * SB树中四种调整和AVL树一样，不同点是调整完后 需要审视一下谁的孩子变了 进而要做后续的递归调整。
 * 
 * 删除节点的时候不做平衡调整！
 * 
 */
public class SBTreeMap<K extends Comparable<K>, V> {
	
	public static class SBTNode<K extends Comparable<K>, V> {
		public K key;
		public V value;
		public SBTNode<K, V> l;
		public SBTNode<K, V> r;
		public int size; // 不同的key的数量

		public SBTNode(K key, V value) {
			this.key = key;
			this.value = value;
			size = 1;
		}
	}
	
	private SBTNode<K, V> root;

	private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
		SBTNode<K, V> leftNode = cur.l;
		cur.l = leftNode.r;
		leftNode.r = cur;
		leftNode.size = cur.size; // 彼此互换Size
		cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
		return leftNode;
	}

	private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
		SBTNode<K, V> rightNode = cur.r;
		cur.r = rightNode.l;
		rightNode.l = cur;
		rightNode.size = cur.size;
		cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
		return rightNode;
	}

	private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
		if (cur == null) {
			return null;
		}
		int leftSize = cur.l != null ? cur.l.size : 0;
		int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
		int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
		int rightSize = cur.r != null ? cur.r.size : 0;
		int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
		int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
		if (leftLeftSize > rightSize) { //LL
			cur = rightRotate(cur);
			cur.r = maintain(cur.r); // 递归
			cur = maintain(cur);
		} else if (leftRightSize > rightSize) { // LR
			cur.l = leftRotate(cur.l);
			cur = rightRotate(cur);
			cur.l = maintain(cur.l);
			cur.r = maintain(cur.r);
			cur = maintain(cur);
		} else if (rightRightSize > leftSize) { // RL
			cur = leftRotate(cur);
			cur.l = maintain(cur.l);
			cur = maintain(cur);
		} else if (rightLeftSize > leftSize) { // RL
			cur.r = rightRotate(cur.r);
			cur = leftRotate(cur);
			cur.l = maintain(cur.l);
			cur.r = maintain(cur.r);
			cur = maintain(cur);
		}
		return cur;
	}

	private SBTNode<K, V> findLastIndex(K key) {
		SBTNode<K, V> pre = root;
		SBTNode<K, V> cur = root;
		while (cur != null) {
			pre = cur;
			if (key.compareTo(cur.key) == 0) {
				break;
			} else if (key.compareTo(cur.key) < 0) {
				cur = cur.l;
			} else {
				cur = cur.r;
			}
		}
		return pre;
	}

	private SBTNode<K, V> findLastNoSmallIndex(K key) {
		SBTNode<K, V> ans = null;
		SBTNode<K, V> cur = root;
		while (cur != null) {
			if (key.compareTo(cur.key) == 0) {
				ans = cur;
				break;
			} else if (key.compareTo(cur.key) < 0) {
				ans = cur;
				cur = cur.l;
			} else {
				cur = cur.r;
			}
		}
		return ans;
	}

	private SBTNode<K, V> findLastNoBigIndex(K key) {
		SBTNode<K, V> ans = null;
		SBTNode<K, V> cur = root;
		while (cur != null) {
			if (key.compareTo(cur.key) == 0) {
				ans = cur;
				break;
			} else if (key.compareTo(cur.key) < 0) {
				cur = cur.l;
			} else {
				ans = cur;
				cur = cur.r;
			}
		}
		return ans;
	}

	// 现在，以cur为头的树上，新增，加(key, value)这样的记录
	// 加完之后，会对cur做检查，该调整调整
	// 返回，调整完之后，整棵树的新头部
	private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
		if (cur == null) {
			return new SBTNode<K, V>(key, value);
		} else {
			cur.size++;
			if (key.compareTo(cur.key) < 0) {
				cur.l = add(cur.l, key, value);
			} else {
				cur.r = add(cur.r, key, value);
			}
			return maintain(cur);
		}
	}

	// 在cur这棵树上，删掉key所代表的节点
	// 返回cur这棵树的新头部
	private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
		cur.size--;
		if (key.compareTo(cur.key) > 0) {
			cur.r = delete(cur.r, key);
		} else if (key.compareTo(cur.key) < 0) {
			cur.l = delete(cur.l, key);
		} else { // 当前要删掉cur
			if (cur.l == null && cur.r == null) {
				// free cur memory -> C++
				cur = null;
			} else if (cur.l == null && cur.r != null) {
				// free cur memory -> C++
				cur = cur.r;
			} else if (cur.l != null && cur.r == null) {
				// free cur memory -> C++
				cur = cur.l;
			} else { // 有左有右
				SBTNode<K, V> pre = null;
				SBTNode<K, V> des = cur.r;
				des.size--;
				while (des.l != null) {
					pre = des;
					des = des.l;
					des.size--;
				}
				if (pre != null) {
					pre.l = des.r;
					des.r = cur.r;
				}
				des.l = cur.l;
				des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
				// free cur memory -> C++
				cur = des;
			}
		}
		// cur = maintain(cur); // 不需要调整，反正add的时候会调整，当然也可以加上
		return cur;
	}

	private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
		if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
			return cur;
		} else if (kth <= (cur.l != null ? cur.l.size : 0)) {
			return getIndex(cur.l, kth);
		} else {
			return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
		}
	}

	public int size() {
		return root == null ? 0 : root.size;
	}

	public boolean containsKey(K key) {
		if (key == null) {
			throw new RuntimeException("invalid parameter.");
		}
		SBTNode<K, V> lastNode = findLastIndex(key);
		return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
	}

	// （key，value） put -> 有序表 新增、改value
	public void put(K key, V value) {
		if (key == null) {
			throw new RuntimeException("invalid parameter.");
		}
		SBTNode<K, V> lastNode = findLastIndex(key);
		if (lastNode != null && key.compareTo(lastNode.key) == 0) {
			lastNode.value = value;
		} else {
			root = add(root, key, value);
		}
	}

	public void remove(K key) {
		if (key == null) {
			throw new RuntimeException("invalid parameter.");
		}
		if (containsKey(key)) {
			root = delete(root, key);
		}
	}

	public K getIndexKey(int index) {
		if (index < 0 || index >= this.size()) {
			throw new RuntimeException("invalid parameter.");
		}
		return getIndex(root, index + 1).key;
	}

	public V getIndexValue(int index) {
		if (index < 0 || index >= this.size()) {
			throw new RuntimeException("invalid parameter.");
		}
		return getIndex(root, index + 1).value;
	}

	public V get(K key) {
		if (key == null) {
			throw new RuntimeException("invalid parameter.");
		}
		SBTNode<K, V> lastNode = findLastIndex(key);
		if (lastNode != null && key.compareTo(lastNode.key) == 0) {
			return lastNode.value;
		} else {
			return null;
		}
	}

	public K firstKey() {
		if (root == null) {
			return null;
		}
		SBTNode<K, V> cur = root;
		while (cur.l != null) {
			cur = cur.l;
		}
		return cur.key;
	}

	public K lastKey() {
		if (root == null) {
			return null;
		}
		SBTNode<K, V> cur = root;
		while (cur.r != null) {
			cur = cur.r;
		}
		return cur.key;
	}

	public K floorKey(K key) {
		if (key == null) {
			throw new RuntimeException("invalid parameter.");
		}
		SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
		return lastNoBigNode == null ? null : lastNoBigNode.key;
	}

	public K ceilingKey(K key) {
		if (key == null) {
			throw new RuntimeException("invalid parameter.");
		}
		SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
		return lastNoSmallNode == null ? null : lastNoSmallNode.key;
	}
	
}
