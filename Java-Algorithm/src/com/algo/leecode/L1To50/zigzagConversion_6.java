package com.algo.leecode.L1To50;

public class zigzagConversion_6 {
	
	public static void main(String[] args) {
		 System.out.println(convert("PAYPALISHIRING", 3));
	}
	

	/**
	 * �۲췢�ֵ�û�ߵ����һ��ʱ��step����positive: +1<br>
	 * ���ߵ����һ��ʱ����ΪҪ������ step����negative�� -1
	 */
    public static String convert(String s, int numRows) {
    	if (numRows == 1 || numRows >= s.length()) {
    		return s;
    	}
    	String[] zigzag = new String[numRows];
    	int row = 0;
    	int step = 1;
    	for (int i = 0; i < s.length();i++) {
    		char word = s.charAt(i);
    		zigzag[row] = (zigzag[row] == null) ? ""+word: zigzag[row] + word;
    		
    		if (row == 0) {
    			step = 1;
    		} else if (row  == numRows -1) {
    			step = -1;
    		}
    		row += step; // rowһֱ�ڶ�̬����
    	}
    	return String.join("", zigzag);
    }
}
