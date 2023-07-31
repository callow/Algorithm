package prog.java8.p1;

import java.util.function.Function;

public interface Functions {

    static <A, R> Function<A, R> def(Function<A, R> function) {
        return function;
    }
    
    /**
     * 	 workaround: 将方法签名改了改
     */
    static <A, B, R> Function<A, Function<B, R>> def(Currying.Of2<A, B, R> function) {
        return function;
    }

    static <A, B, C, R> Function<A, Function<B, Function<C, R>>> def(Currying.Of3<A, B, C, R> function) {
        return function;
    }
}
