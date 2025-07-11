package dfs.week1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
/**
 * 类型： 回溯， 因为要回头恢复 然后尝试其他分支
 * 
 * https://leetcode.com/problems/restore-ip-addresses/description/
 */
public class ValidIPs {
	
	static List<String> aa = new ArrayList<>();
	public static void main(String[] args) {
		String num = "19216801";
		validIPs(num);
	}
	
	public static List<String> validIPs(String num) {
		int curIndex = -1;
		Deque<String> result = new ArrayDeque<>();
		dfs(num, curIndex, 1, result);
		return aa;
    }
	
	public static void dfs(String num, int curIndex, int level, Deque<String> result) {
		// 1. 截至条件
		if(curIndex == num.length() -1 || level == 5) {
			if (level == 5 && curIndex == num.length() -1) {
				System.out.println(String.join(".", result));
				aa.add(String.join(".", result));
			}
			return;
		}
		// 2. 遍历候选节点：1个，2个，3个数字
		for(int len = 1; len <= 3; len++) {
			if (curIndex + 1 + len > num.length()) {
				break;
			}
			String candidate = num.substring(curIndex + 1, curIndex + 1 + len);
			// 2.1 候选剪枝，合格了才能进入下一层dfs
			if (Integer.valueOf(candidate) < 256 && (candidate.equals("0") || !candidate.startsWith("0")) ) {
				result.add(candidate);
				dfs(num, curIndex + len, level + 1, result);
				result.removeLast();
			}
		}
	}
}
