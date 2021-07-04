package Strs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {

	public static List<List<String>> groupAnagrams(List<String> words) {
		Map<String, List<String>> map = new HashMap<>();
		for (String word : words) { // O(W)
			char[] temp = word.toCharArray();
			Arrays.sort(temp); // O(Nlog(N))
			String ordered = new String(temp);
			if (map.containsKey(ordered)) {
				List<String> mutated = map.get(ordered);
				mutated.add(word);
				map.put(ordered, mutated);
			} else {
				List<String> newItem = new ArrayList<>();
				newItem.add(word);
				map.put(ordered, newItem);
			}
		}
		List<List<String>> result = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) { // O(N)
			result.add(entry.getValue());
		}
		return result;
	}
}
