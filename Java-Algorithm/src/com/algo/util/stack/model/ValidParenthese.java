package com.algo.util.stack.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthese {

	public static String line;
	public static Stack<Character> stack  = new Stack<>();
	public static Map<Character, Character> map = new HashMap<>();
	static {
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
	}
	
	public static boolean isValid(String line) throws IOException {
		
		boolean status = true;
		for (char one : line.toCharArray()) {
			if (one == '(' || one == '[' || one == '{') {
				stack.push(one);
			} else {
				if (stack.isEmpty() || map.get(one) != stack.pop()) {
					status = false;
					break;
				}
			}
		}
		if (!stack.isEmpty()) {
			status = false;
		}
		stack.clear();
		return status;
			
	
	}
	
	
	
	public static void main(String[] args) throws IOException {
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while ((line = in.readLine()) != null) {
			boolean status = true;
			for (char one : line.toCharArray()) {
				if (one == '(' || one == '[' || one == '{') {
					stack.push(one);
				} else {
					if (stack.isEmpty() || map.get(one) != stack.pop()) {
						status = false;
						break;
					}
				}
			}
			if (!stack.isEmpty()) {
				status = false;
			}
			out.println(status);
			stack.clear();
			
		}
		out.flush();
		in.close();
		out.close();
	}
}
