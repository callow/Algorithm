package com.algo.pattern.decorator.model;

public class FictionReadingMaterialsDecorator extends ReadingMaterialsDecorator{

	public FictionReadingMaterialsDecorator(ReadingMaterials decoratedReadingMaterial) {
		super(decoratedReadingMaterial);
	}
	
	@Override
	public void describe() {
		decoratedReadingMaterial.describe(); // �и��������
		System.out.println("This is a fiction category!"); // ���Լ�����ǿ
	}

}
