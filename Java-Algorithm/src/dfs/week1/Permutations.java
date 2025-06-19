package dfs.week1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 类型： 回溯， 因为要回头恢复 然后尝试其他分支
 * 
 */
public class Permutations {

	public static void main(String[] args) {
		char[] letters = {'A','B','C'};
		permute(letters);
	}
	
	public static void permute(char[] letters) {
		boolean[] used = new boolean[letters.length];
		Deque<Character> result = new ArrayDeque<>();
		dfs(letters, used, 1, result);
    }
	
	public static void dfs(char[] letters, boolean[] used, int level, Deque<Character> result) {
		// 1. 截至条件
		if (level == letters.length + 1) {
			System.out.println(result);
			return;
		}
		// 2. 遍历候选节点
		for(int i = 0; i < letters.length; i++) {
			char c = letters[i];
			// 2.1 候选剪枝，合格了才能进入下一层dfs
			if(!used[i]) {
				result.add(c);
				used[i] = true;
				dfs(letters, used, level + 1, result); // 树的展开
				result.removeLast(); // 恢复现场
				used[i] = false; // 恢复现场
			}
			
		}
	}
}
