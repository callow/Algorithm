package prog.java8.function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 
 * Function: 有入参，有返回值
 *
 */
public class Exmaple {

	public static void main(String[] args) {
		Function<String, String> function = input -> input + " Jack!";
		System.out.println(function.apply("Hello")); // Hello Jack!
		
		
		// 返回一个组合函数，该函数结果应用到after函数中
		Function<String, String> function1 = a -> a + " Jack!";
		Function<String, String> function2 = a -> a + " Bob!";
		String greet = function1.andThen(function2).apply("Hello");
		System.out.println(greet); // Hello Jack! Bob!
		
		//返回一个组合函数，首先将入参应用到before函数，再将before函数结果应用到该函数中
		Function<String, String> function3 = a -> a + " Jack!";
		Function<String, String> beforeFunction = a -> a + " Bob!";
		String greet2 = function3.compose(beforeFunction).apply("Hello");
		System.out.println(greet2); // Hello Bob! Jack!
		
		
		//传2个参数，返回一个参数
		BiFunction<String, String, String> biFunction = (a, b) -> a + b;
		Function<String, String> function5 = (a) -> a + "!!!";
		System.out.println(biFunction.andThen(function5).apply("Hello", " Jack")); // Hello Jack!!!


	}
}
