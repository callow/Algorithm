package prog.java8.consumer;

import java.util.function.Consumer;

/**
 * 
 * Consumer: Consumer�����ͺ���,���ղ�����û�з���ֵ
 *
 */
public class Example {

	public static void main(String[] args) {
		
		// �Ը����Ĳ���ִ�в���
		StringBuilder sb = new StringBuilder("Hello ");
		Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
		consumer.accept(sb);
		System.out.println(sb.toString());	// Hello Jack!

		//	����һ����Ϻ�����after�����ڸú���ִ��֮��Ӧ��
		StringBuilder sb1 = new StringBuilder("Hello ");
		Consumer<StringBuilder> consumer1 = (str) -> str.append("Jack!");
		Consumer<StringBuilder> afterFunction = (str) -> str.append(" Bob!");
		consumer.andThen(afterFunction).accept(sb);
		System.out.println(sb1.toString());	// Hello Jack! Bob!

	}
}
