package com.algo.pattern.decorator.model;

public class Book implements ReadingMaterials{

	@Override
	public void describe() {
		System.out.println("book");
	}

}