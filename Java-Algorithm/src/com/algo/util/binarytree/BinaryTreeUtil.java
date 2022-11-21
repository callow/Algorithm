package com.algo.util.binarytree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.util.common.CommonStringUtil;
import com.algo.util.common.model.BTNode;
import com.algo.util.common.model.NTNode;
import com.algo.util.common.model.PTNode;
/**
 * 
 * �������Ķ���Ϊһ��Ҫ�ݹ������
 */
public class BinaryTreeUtil {

	/**
	 * ��N����encode��2����O(N)��<BR>
	 * ÿ��Node�ĺ��ӹ��������ұ߽��ϣ�ͬ���Ĺ��ڴ����Һ���<BR>
	 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
	 */
	
	public static BTNode NTtoBT(NTNode root) {
		if (root == null) {
			return null;
		}
		BTNode head = new BTNode(root.val);
		head.left = encode(root.children); // ���к������������ұ߽��Ϲ�
		return head;
	}
	private static BTNode encode(List<NTNode> children) {
		BTNode head = null;
		BTNode cur = null;
		for (NTNode child : children) {
			BTNode tNode = new BTNode(child.val);
			if (head == null) {
				head = tNode;
			} else {
				cur.right = tNode;
			}
			cur = tNode;
			cur.left = encode(child.children); //�ٰ��Һ��ӵĶ����Ӽ����ң�������ȱ���
		}
		return head;
	}
	/**
	 * �ٽ�2����dencode��N������<BR>
	 * 
	 */
	public NTNode BTtoNT(BTNode root) {
		if (root == null) {
			return null;
		}
		return new NTNode(root.value, decode(root.left));
	}

	private static List<NTNode> decode(BTNode root) {
		List<NTNode> children = new ArrayList<>();
		while (root != null) {
			// ��ͣ�����ҳ��Ȼ��ӵ����Ӽ�����ȥ
			NTNode cur = new NTNode(root.value, decode(root.left));
			children.add(cur);
			root = root.right;
		}
		return children;
	}
	
	/**
	 * ��ӡһ��2����<br><br>
	 * H: ͷ�ڵ�<br>
	 * ^: ��������ϲ����������Ǹ�<br>
	 * v:��������²����������Ǹ�<br>
	 */
	
	public static void print(BTNode head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}
	private static void printInOrder(BTNode head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = CommonStringUtil.getSpace(lenL) + val + CommonStringUtil.getSpace(lenR);
		System.out.println(CommonStringUtil.getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}
	
	/**
	 * �õ������ǲ�ڵ���<br><br>
	 * Queue�Զ��������������������ȣ�Ȼ����max. ֻҪ֪����ǰ��ʲôʱ������Ϳ�����<br>
	 * �ο��� BinaryTreeIterateUtil.level(...)
	 */
	
	public static int widestLevel(BTNode head) {
		if (head == null) {
			return 0;
		}
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(head);
		BTNode curEnd = head; // ��ǰ�㣬���ҽڵ���˭
		BTNode nextEnd = null; // ��һ�㣬���ҽڵ���˭
		int max = 0;
		int curLevelNodes = 0; // ��ǰ��Ľڵ���
		while (!queue.isEmpty()) {
			BTNode cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			curLevelNodes++;
			if (cur == curEnd) { // curײ��curEnd�ˣ���һ��ͳ�ƽ���
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 0;
				curEnd = nextEnd;
			}
		}
		return max;
	}
	
	/**
	 * �ҵ�����ָ���2�����ĺ�̽ڵ�,��̽ڵ㣺 ��������е�ǰ�ڵ����һ�����<br><br>
	 * 
	 * X�������� successor = ����������ĺ���. <br>
	 * X�������� Look up until �����Լ������Һ��ӣ������ӣ����Ǹ��ڵ����x���<br>
	 * ����û�У���ȫ�������ҵ��Ǹ��ڵ��Һ�̣����ĺ��= null
	 */
	
	public static PTNode getSuccessor(PTNode node) {
		if (node == null) {
			return node;
		}
		if (node.right != null) {
			return getLeftmost(node.right);
		} else { // ��������
			PTNode parent = node.parent;
			while (parent != null && parent.right == node) { // ��ǰ�ڵ����丸�׽ڵ��Һ���
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}
	
	private static PTNode getLeftmost(PTNode node) {
		if (node == null) {
			return node;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
}
