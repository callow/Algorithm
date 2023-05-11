package com.algo.pattern.decorator.model;

public class HorrorReadingMaterialsDecorator extends ReadingMaterialsDecorator{

	public HorrorReadingMaterialsDecorator(ReadingMaterials decoratedReadingMaterial) {
		super(decoratedReadingMaterial);
	}
	
	@Override
	public void describe() {
		decoratedReadingMaterial.describe(); // �и��������
		System.out.println("This is a horror category!"); // ���Լ�����ǿ
	}

}
