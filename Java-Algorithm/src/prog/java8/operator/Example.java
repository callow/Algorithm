package prog.java8.operator;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 
 * Operator: 扩展操作： UnaryOperator (extends Function) 和BinaryOperator (extends BiFunction)
 * 
 * UnaryOperator : 提供单个类型参数，并且返回一个与输入参数类型一致的结果
 * BinaryOperator : 提供两个相同类型参数，并且返回结果与输入参数类型一致的结果
 *
 */
public class Example {

	public static void main(String[] args) {
		UnaryOperator<String> unaryOperator = greet -> greet + " Bob!";
		System.out.println(unaryOperator.apply("Hello"));
		
		BinaryOperator<String> binaryOperator = (flag, flag1) -> flag + flag1;
		Function<String, String> function = a -> a + "!!!";
		System.out.println(binaryOperator.andThen(function).apply("Hello", " Jack")); // Hello Jack!!!

	}
}
