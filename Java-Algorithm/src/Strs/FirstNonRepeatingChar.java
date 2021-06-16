package Strs;

import java.util.SortedMap;
import java.util.TreeMap;

public class FirstNonRepeatingChar {
	public static void main(String[] args) {
		System.out.println(firstNonRepeatingCharacter("ggyllaylacrhdzedddjsc"));
	}

	public static int firstNonRepeatingCharacter(String string) {
		if (string.equals("")) {
			return -1;
		}
		String[] ss = string.split("");
		SortedMap<String, Integer> sm = new TreeMap<>();
		for (String s : ss) {
			if (sm.containsKey(s)) {
				sm.put(s, sm.get(s) + 1);
			} else {
				sm.put(s, 1);
			}
		}

		for (int i = 0; i < ss.length; i++) {
			if (sm.get(ss[i]).equals(1)) {
				return i;
			}
		}

		return -1;
	}
}
