package prog.java8.consumer;

import java.util.function.Consumer;

/**
 * 
 * Consumer: Consumer消费型函数,接收参数，没有返回值
 *
 */
public class Example {

	public static void main(String[] args) {
		
		// 对给定的参数执行操作
		StringBuilder sb = new StringBuilder("Hello ");
		Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
		consumer.accept(sb);
		System.out.println(sb.toString());	// Hello Jack!

		//	返回一个组合函数，after将会在该函数执行之后应用
		StringBuilder sb1 = new StringBuilder("Hello ");
		Consumer<StringBuilder> consumer1 = (str) -> str.append("Jack!");
		Consumer<StringBuilder> afterFunction = (str) -> str.append(" Bob!");
		consumer.andThen(afterFunction).accept(sb);
		System.out.println(sb1.toString());	// Hello Jack! Bob!

	}
}
