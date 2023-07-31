package prog.java8.p1;

import java.util.function.Function;

/**
 * 
 * change function with multiply input arguments to chain of functions with only one input argument
 *
 */
public interface Currying {
	
	 interface Of2<A, B, R> extends Function<A, Function<B, R>> {}
	 interface Of3<A, B, C, R> extends Function<A, Function<B, Function<C, R>>> {}
}
