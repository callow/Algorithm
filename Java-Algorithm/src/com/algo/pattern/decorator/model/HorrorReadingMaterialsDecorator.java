package com.algo.pattern.decorator.model;

public class HorrorReadingMaterialsDecorator extends ReadingMaterialsDecorator{

	public HorrorReadingMaterialsDecorator(ReadingMaterials decoratedReadingMaterial) {
		super(decoratedReadingMaterial);
	}
	
	@Override
	public void describe() {
		decoratedReadingMaterial.describe(); // 有父类的特性
		System.out.println("This is a horror category!"); // 有自己的增强
	}

}
