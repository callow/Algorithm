package com.algo.util.binarytree;

import com.algo.util.binarytree.model.TreeDP;
import com.algo.util.common.model.BTNode;
import com.algo.util.common.model.NTNode;
import com.algo.util.common.treemodel.HappynessInfo;
/**
 * 
 * �������ݹ���·�� ��������DP ~ O(N)
 */
public class AdvancedBinaryTreeUtil {

	/**
	 * �Ƿ�����ȫ2������<br><br>
	 * ���ö������������ = ������������ȱ����� <br>
	 * 1. ���Һ��ӣ�û������ <br>
	 * 2. ����һ���������Һ��Ӳ�ȫʱ��ʣ�½ڵ����ȫ��Ҷ�ڵ����
	 */
	
	public static boolean isCompleteBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goComplete(head).isCBT;
	}

	
	/**
	 * �Ƿ���ƽ��2������<br><br>
	 * ÿһ���������Ҹ߶����<=1 <br>
	 * 1. x ����Ҫƽ�� <br>
	 * 2. x ����Ҫƽ��<br>
	 * 3. |x����ߣ� - x���Ҹߣ�| < 2 <br>
	 */
	public static boolean isBalancedBT(BTNode head) {
		return TreeDP.goBalanced(head).isBalanced;
	}

	/**
	 * �Ƿ�������2������<br><br>
	 * ÿһ���������� < ͷ < ���� <br>
	 * 1. x ��������2<br>
	 * 2. x ��������2<br>
	 * 3. x �������ֵ < x<br>
	 * 4. x ������Сֵ > x<br>
	 */
	public static boolean isSearchBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goSearch(head).isBST;
	}
	
	/**
	 * �ҵ�2���������ڵ�������룿 <br><br>
	 * ��/���������� = ��/�����ĸ߶�<br>
	 * 1. ���� x:  ���������� vs ����������<br>
	 * 2. ������ x: (���������� + ���������� + 1)<br>
	 * 3. maxDistance = Math.max(1,2)
	 */
	
	public static int maxDistance(BTNode head) {
		return TreeDP.goMaxDistance(head).maxDistance;
	}
	
	/**
	 * �Ƿ�����2������<br><br>
	 * ������߶�ΪH����ڵ��������ǣ�2^H -1����<br>
	 * 1. �ռ������Ƿ�����������,�ռ������ĸ߶�<br>
	 * 2. ������ && ������ && �������߶�һ�� -> ������������
	 */
	public static boolean isFullBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goFull(head).isFull;
	}
	
	/**
	 * �ҵ���������2��������Ȼ���ʴ����ж��ٽڵ㣿 <br><br>
	 * 1. x����ͷ : ������maxBSTSize vs ������maxBSTSize<br>
	 * 2. x��ͷ: (������BST && ������BST && ����max < x && ����min > x) => �𰸣� ��Size + ��Size + 1  
	 */
	// https://leetcode.com/problems/largest-bst-subtree
	public static int maxSubBSTSize(BTNode head) {
		if (head == null) {
			return 0;
		}
		return TreeDP.goSubBSTSize(head).maxBSTSubtreeSize;
		
	}
	
	/**
		�� A �� B����͹������ȣ�<br><br>
		1. ��۵���X�޹�/X���Ǵ𰸣�  (��۵��Ѿ���X������/�����������!) or (X����ȱA��B)<br>
		2. ��۵���X�й�/X�Ǵ𰸣�  X���淢����A��Bȫ��<br>
	 */
	public static BTNode lowestAncestor(BTNode head, BTNode a, BTNode b) {
		return TreeDP.findAncestor(head, a, b).ans;
	}
	
	/**
	 * һ��N������ʾһ����˾�Ĳ㼶��ϵ�����ڷ��������μ���ᣬ���¼�������ͬʱ���룬��η���������Ὺ��ֵ��ߣ�<br><br>
	 * 1. x ��ʱ�� (x�Լ��Ŀ���ֵ + x�����¼����Լ�����ʱ���������ֵ)<br>
	 * 2. x ������ (0 + Max(x�����¼����Լ���ʱ���������ֵ,x�����¼����Լ�����ʱ���������ֵ)) ��Ϊ�¼����԰�������<br>
	 * 3. ���أ� Max(1,2)
	 */
	
	public static int gainMaxHappyness(NTNode boss) {
		HappynessInfo allInfo = TreeDP.gainHappyness(boss);
		return Math.max(allInfo.no, allInfo.yes);
	}
}
