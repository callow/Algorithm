package Strs;

public class LongestPalintroSubStrig {

	public static void main(String[] args) {
		System.out.println(longestPalindromicSubstring("abaxyzzyxf"));
	}

	public static String longestPalindromicSubstring(String str) {
		int[] currentLongest = { 0, 1 };
		for (int i = 1; i < str.length(); i++) { // 从左到右遍历
			int[] odd = getLongestPFrom(str, i - 1, i + 1);
			int[] even = getLongestPFrom(str, i - 1, i);
			int[] longest = odd[1] - odd[0] > even[1] - even[0] ? odd : even;
			currentLongest = currentLongest[1] - currentLongest[0] > longest[1] - longest[0] ? currentLongest : longest;
		}

		return str.substring(currentLongest[0], currentLongest[1]);

	}

	public static int[] getLongestPFrom(String str, int leftIndex, int rightIndex) {
		while (leftIndex >= 0 && rightIndex <= str.length()) { // 左右指针
			if (str.charAt(leftIndex) != str.charAt(rightIndex)) {
				break;
			}
			leftIndex--;
			rightIndex++;
		}
		return new int[] { leftIndex + 1, rightIndex };
	}
}
