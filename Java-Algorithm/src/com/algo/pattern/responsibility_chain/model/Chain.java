package com.algo.pattern.responsibility_chain.model;

public class Chain {
	Processor chain;
	  
	public Chain(){
	    buildChain();
	}
	  
	private void buildChain(){
	    chain = new NegativeProcessor(new ZeroProcessor(new PositiveProcessor(null)));
	}
	  
	public void process(int input) {
	    chain.process(input);
	}
}
