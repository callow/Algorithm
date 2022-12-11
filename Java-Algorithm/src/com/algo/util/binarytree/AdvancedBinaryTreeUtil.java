package com.algo.util.binarytree;

import com.algo.util.binarytree.model.TreeDP;
import com.algo.util.common.model.BTNode;
import com.algo.util.common.model.NTNode;
import com.algo.util.common.treemodel.HappynessInfo;
/**
 * 
 * 二叉树递归套路： 利用树形DP ~ O(N)
 */
public class AdvancedBinaryTreeUtil {

	/**
	 * 是否是完全2叉树？<br><br>
	 * 采用二叉树按层遍历 = 二叉树宽度优先遍历： <br>
	 * 1. 有右孩子，没有左孩子 <br>
	 * 2. 当第一次遇到左右孩子不全时，剩下节点必须全是叶节点才行
	 */
	
	public static boolean isCompleteBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goComplete(head).isCBT;
	}

	
	/**
	 * 是否是平衡2叉树？<br><br>
	 * 每一棵子树左右高度相差<=1 <br>
	 * 1. x 左树要平衡 <br>
	 * 2. x 右树要平衡<br>
	 * 3. |x（左高） - x（右高）| < 2 <br>
	 */
	public static boolean isBalancedBT(BTNode head) {
		return TreeDP.goBalanced(head).isBalanced;
	}

	/**
	 * 是否是搜索2叉树？<br><br>
	 * 每一棵子树左树 < 头 < 右树 <br>
	 * 1. x 左树是搜2<br>
	 * 2. x 右树是搜2<br>
	 * 3. x 左树最大值 < x<br>
	 * 4. x 右树最小值 > x<br>
	 */
	public static boolean isSearchBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goSearch(head).isBST;
	}
	
	/**
	 * 找到2叉树上两节点的最大距离？ <br><br>
	 * 左/右树最大距离 = 左/右树的高度<br>
	 * 1. 经过 x:  左树最大距离 vs 右树最大距离<br>
	 * 2. 不经过 x: (左树最大距离 + 右树最大距离 + 1)<br>
	 * 3. maxDistance = Math.max(1,2)
	 */
	
	public static int maxDistance(BTNode head) {
		return TreeDP.goMaxDistance(head).maxDistance;
	}
	
	/**
	 * 是否是满2叉树？<br><br>
	 * 如果树高度为H，则节点数必须是（2^H -1）个<br>
	 * 1. 收集子树是否是满二叉树,收集子树的高度<br>
	 * 2. 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
	 */
	public static boolean isFullBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goFull(head).isFull;
	}
	
	/**
	 * 找到最大的搜索2叉子树，然后问此树有多少节点？ <br><br>
	 * 1. x不是头 : 左树的maxBSTSize vs 右树的maxBSTSize<br>
	 * 2. x是头: (左树是BST && 右树是BST && 左树max < x && 右树min > x) => 答案： 左Size + 右Size + 1  
	 */
	// https://leetcode.com/problems/largest-bst-subtree
	public static int maxSubBSTSize(BTNode head) {
		if (head == null) {
			return 0;
		}
		return TreeDP.goSubBSTSize(head).maxBSTSubtreeSize;
		
	}
	
	/**
		求 A 和 B的最低公共祖先？<br><br>
		1. 汇聚点与X无关/X不是答案：  (汇聚点已经在X的左数/右树汇聚完了!) or (X下面缺A或B)<br>
		2. 汇聚点与X有关/X是答案：  X下面发现了A和B全部<br>
	 */
	public static BTNode lowestAncestor(BTNode head, BTNode a, BTNode b) {
		return TreeDP.findAncestor(head, a, b).ans;
	}
	
	/**
	 * 一个N叉树表示一个公司的层级关系，现在发请柬邀请参加舞会，上下级不可以同时邀请，如何发让整个误会开心值最高？<br><br>
	 * 1. x 来时： (x自己的快乐值 + x所有下级在自己不来时候的最大快乐值)<br>
	 * 2. x 不来： (0 + Max(x所有下级在自己来时候的最大快乐值,x所有下级在自己不来时候的最大快乐值)) 因为下级可以爱来不来<br>
	 * 3. 返回： Max(1,2)
	 */
	
	public static int gainMaxHappyness(NTNode boss) {
		HappynessInfo allInfo = TreeDP.gainHappyness(boss);
		return Math.max(allInfo.no, allInfo.yes);
	}
}
