package binarySearchTree;
/**
 * Find closest value in binary search tree,agoexpert
 *
 */
public class findclosesValueInBST {
	
	public static void main(String[] args) {
		
	}
	
	public static int findClosestValueInBst(BST tree, int target) {
		 BST current = tree; 
		 int currentValue = current.getValue();
		 int closestValue = current.getValue(); // imagine
		 
		 // squeeze theorem
		 // Ask: when to stop the while loop ? 
		 // Answer: When all tree are traversed || break
		 while(current != null) {
			 
			 int nodeToTargetDistance = Math.abs(currentValue - target); // 当前节点到target的距离
			 int targetToClosestDistance = Math.abs(target - closestValue); // 已知Closest节点到Target的距离
			 
			 // 最新的最短距离产生，更新最短距离！！！！！！
			 if (nodeToTargetDistance < targetToClosestDistance) {
				 closestValue = currentValue;
			 }
			 
			 
			 if (currentValue > target) { // target is on the left, go left to find
				 current = current.getLeft();
			 } else if (currentValue < target) { // target is on the right, go to right to find
				 current = current.getRight();
			 } else {
				 break;
			 }
		 }
		 
		 return closestValue;
	 }
	
}
