package com.algo.pattern.decorator.model;

/**
 * 
 * Decorator
 *
 */
public abstract class ReadingMaterialsDecorator implements ReadingMaterials{
	
	protected ReadingMaterials decoratedReadingMaterial;
	 
	public ReadingMaterialsDecorator(ReadingMaterials decoratedReadingMaterial) {
        this.decoratedReadingMaterial = decoratedReadingMaterial;
    }
	
	@Override
	public void describe() {
		decoratedReadingMaterial.describe();
	}
}
