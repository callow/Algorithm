package com.algo.util.Ac_automation;

import java.util.List;

import com.algo.util.Ac_automation.model.ACAutomation;

/**
 * 
 * �������а�������Щ���дʣ�<br>
 * 
 * �ܶ����дʣ� abc, bkz �� �Ȱ����дʽ���ǰ׺��Tries.
 * 
 * ÿ����㶼��һ��failָ�롣����������ƥ�䣬�Ҳ�����ȥfailָ��ָ�ĵط����ǰ׺�������ܲ���̭û�п���ġ�
 * 
 * ������N��ģʽ����ƽ������ΪL�����³���ΪM�� ����Trie����O(N*L) ����failָ�룺O(N*L) ģʽƥ�䣺O(M*L) ���ԣ���ʱ�临�Ӷ�Ϊ:O( (N+M)*L )
 *
 */
public class AcAutomationUtil {
	
	
	public static void example() {
		ACAutomation ac = new ACAutomation();
		ac.insertSensitiveWord("dhe");
		ac.insertSensitiveWord("he");
		ac.insertSensitiveWord("abcdheks");
		// ����failָ��
		ac.build();

		List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
		for (String word : contains) {
			System.out.println(word); // ��ӡ"abcdhekskdjfafhasldkflskdjhwqaeruv" �������������д�
		}
	}
}
