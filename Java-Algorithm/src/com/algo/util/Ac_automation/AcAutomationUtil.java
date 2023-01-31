package com.algo.util.Ac_automation;

import java.util.List;

import com.algo.util.Ac_automation.model.ACAutomation;

/**
 * 
 * 大文章中包含了哪些敏感词？<br>
 * 
 * 很多敏感词： abc, bkz ， 先把敏感词建成前缀树Tries.
 * 
 * 每个结点都有一个fail指针。在树上先找匹配，找不到则去fail指针指的地方找最长前缀。尽可能不淘汰没有考察的。
 * 
 * 假设有N个模式串，平均长度为L；文章长度为M。 建立Trie树：O(N*L) 建立fail指针：O(N*L) 模式匹配：O(M*L) 所以，总时间复杂度为:O( (N+M)*L )
 *
 */
public class AcAutomationUtil {
	
	
	public static void example() {
		ACAutomation ac = new ACAutomation();
		ac.insertSensitiveWord("dhe");
		ac.insertSensitiveWord("he");
		ac.insertSensitiveWord("abcdheks");
		// 设置fail指针
		ac.build();

		List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
		for (String word : contains) {
			System.out.println(word); // 打印"abcdhekskdjfafhasldkflskdjhwqaeruv" 包含的所有敏感词
		}
	}
}
