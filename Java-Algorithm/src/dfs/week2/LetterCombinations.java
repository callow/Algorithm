package dfs.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型： 带路径递归， DFS 树宽度变化（每层分支数不同）
 * 
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */
public class LetterCombinations {
	
    private static final Map<Character, String> MAP = new HashMap<>();
    static {
    	MAP.put('2', "abc");
    	MAP.put('3', "def");
    	MAP.put('4', "ghi");
    	MAP.put('5', "jkl");
    	MAP.put('6', "mno");
    	MAP.put('7', "pqrs");
    	MAP.put('8', "tuv");
    	MAP.put('9', "wxyz");
    }


	public static void main(String[] args) {
		System.out.println(letterCombinations("23"));
	}
	
	public static List<String> letterCombinations(String digits) {
	    List<String> path = new ArrayList<>();
	        
        if (digits == null || digits.length() == 0) {
            return path;
        }
        
        dfs(digits,0,path,new StringBuilder());    
	    return path; 
    }
	
	public static void dfs(String digits, int index, List<String> path, StringBuilder builder) {
		int level = digits.length();
		if(index == level) { // 2个数字就只能2层
			path.add(builder.toString());
			return;
		}
		
		// 尝试每一个数字，的每一个字母，回溯= 尝试+撤回再尝试
		String letters = MAP.get(digits.charAt(index));
		for (char letter : letters.toCharArray()) {
			builder.append(letter);
			dfs(digits, index + 1, path, builder);
			builder.deleteCharAt(builder.length() - 1);
		}
	}
	
}
