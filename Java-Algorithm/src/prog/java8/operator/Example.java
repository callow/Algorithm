package prog.java8.operator;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 
 * Operator: ��չ������ UnaryOperator (extends Function) ��BinaryOperator (extends BiFunction)
 * 
 * UnaryOperator : �ṩ�������Ͳ��������ҷ���һ���������������һ�µĽ��
 * BinaryOperator : �ṩ������ͬ���Ͳ��������ҷ��ؽ���������������һ�µĽ��
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
