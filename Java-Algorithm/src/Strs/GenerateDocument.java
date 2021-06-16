package Strs;
import java.util.HashMap;
import java.util.Map;

public class GenerateDocument {

	public boolean generateDocument(String characters, String document) {
		String[] chars = characters.split("");
		String[] docs = document.split("");
		if (document.equals("")) {
			return true;
		}
		Map<String, Integer> charMap = new HashMap<>();
		for (String s : chars) {
			if (charMap.containsKey(s)) {
				charMap.put(s, charMap.get(s) + 1);
			} else {
				charMap.put(s, 1);
			}
		}
		for (String s : docs) {
			if (charMap.containsKey(s)) {
				charMap.put(s, charMap.get(s) - 1);
			}
			if (!charMap.containsKey(s) || charMap.containsValue(-1)) {
				return false;
			}
		}
		return true;
	}
}
