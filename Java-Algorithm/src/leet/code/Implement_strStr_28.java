package leet.code;

public class Implement_strStr_28 {
	public static void main(String[] args) {
		System.out.println(strStr("as","a"));
	}
	
    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
