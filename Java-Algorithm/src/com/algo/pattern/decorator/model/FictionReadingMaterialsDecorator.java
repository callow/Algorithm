package com.algo.pattern.decorator.model;

public class FictionReadingMaterialsDecorator extends ReadingMaterialsDecorator{

	public FictionReadingMaterialsDecorator(ReadingMaterials decoratedReadingMaterial) {
		super(decoratedReadingMaterial);
	}
	
	@Override
	public void describe() {
		decoratedReadingMaterial.describe(); // 有父类的特性
		System.out.println("This is a fiction category!"); // 有自己的增强
	}

}
