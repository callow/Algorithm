package com.algo.pattern.decorator;

import com.algo.pattern.decorator.model.FictionReadingMaterialsDecorator;
import com.algo.pattern.decorator.model.HorrorReadingMaterialsDecorator;
import com.algo.pattern.decorator.model.Newspaper;
import com.algo.pattern.decorator.model.ReadingMaterials;

/**
 * 
 * ��̬�ظ�һ���������һЩ�����ְ�𡣾����ӹ�����˵��װ����ģʽ������������Ϊ�� = Wrapper
 * 
 * �������ģʽͨ����������ƴװ��ʵ���˸�������������ϵĹ���
 *
 */
public class DecoratorUtil {

	public static void main(String[] args) {
		
		ReadingMaterials newspaper = new Newspaper();
		
		// ��ֽʽ�ƻõ�
		FictionReadingMaterialsDecorator fictionReadingMaterial = new FictionReadingMaterialsDecorator(newspaper);
		
		// ���ӣ� ��ֽҲ�ǿֲ���
		HorrorReadingMaterialsDecorator horrorReadingMaterial = new HorrorReadingMaterialsDecorator(fictionReadingMaterial);
		
		// �ۺ����� Newspaper
		horrorReadingMaterial.describe();
		
	}
}
