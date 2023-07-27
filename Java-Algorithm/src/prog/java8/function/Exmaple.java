package prog.java8.function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 
 * Function: ����Σ��з���ֵ
 *
 */
public class Exmaple {

	public static void main(String[] args) {
		Function<String, String> function = input -> input + " Jack!";
		System.out.println(function.apply("Hello")); // Hello Jack!
		
		
		// ����һ����Ϻ������ú������Ӧ�õ�after������
		Function<String, String> function1 = a -> a + " Jack!";
		Function<String, String> function2 = a -> a + " Bob!";
		String greet = function1.andThen(function2).apply("Hello");
		System.out.println(greet); // Hello Jack! Bob!
		
		//����һ����Ϻ��������Ƚ����Ӧ�õ�before�������ٽ�before�������Ӧ�õ��ú�����
		Function<String, String> function3 = a -> a + " Jack!";
		Function<String, String> beforeFunction = a -> a + " Bob!";
		String greet2 = function3.compose(beforeFunction).apply("Hello");
		System.out.println(greet2); // Hello Bob! Jack!
		
		
		//��2������������һ������
		BiFunction<String, String, String> biFunction = (a, b) -> a + b;
		Function<String, String> function5 = (a) -> a + "!!!";
		System.out.println(biFunction.andThen(function5).apply("Hello", " Jack")); // Hello Jack!!!


	}
}
