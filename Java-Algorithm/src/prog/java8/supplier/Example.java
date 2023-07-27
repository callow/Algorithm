package prog.java8.supplier;

import java.util.function.Supplier;

/**
 * Supplier: ���ṩ������������Ƿ��ؽ���ĺ���
 *
 */
public class Example {

	public static void main(String[] args) {
		
		Supplier<String> supplier = () -> "Hello Jack!";
		System.out.println(supplier.get()); // Hello Jack!
	}
}
