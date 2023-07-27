package prog.java8.supplier;

import java.util.function.Supplier;

/**
 * Supplier: 不提供输入参数，但是返回结果的函数
 *
 */
public class Example {

	public static void main(String[] args) {
		
		Supplier<String> supplier = () -> "Hello Jack!";
		System.out.println(supplier.get()); // Hello Jack!
	}
}
