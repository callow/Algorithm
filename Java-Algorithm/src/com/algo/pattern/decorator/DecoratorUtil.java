package com.algo.pattern.decorator;

import com.algo.pattern.decorator.model.FictionReadingMaterialsDecorator;
import com.algo.pattern.decorator.model.HorrorReadingMaterialsDecorator;
import com.algo.pattern.decorator.model.Newspaper;
import com.algo.pattern.decorator.model.ReadingMaterials;

/**
 * 
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。 = Wrapper
 * 
 * 这种设计模式通过子类的灵活拼装，实现了各种属性随意组合的功能
 *
 */
public class DecoratorUtil {

	public static void main(String[] args) {
		
		ReadingMaterials newspaper = new Newspaper();
		
		// 报纸式科幻的
		FictionReadingMaterialsDecorator fictionReadingMaterial = new FictionReadingMaterialsDecorator(newspaper);
		
		// 叠加： 报纸也是恐怖的
		HorrorReadingMaterialsDecorator horrorReadingMaterial = new HorrorReadingMaterialsDecorator(fictionReadingMaterial);
		
		// 综合描述 Newspaper
		horrorReadingMaterial.describe();
		
	}
}
