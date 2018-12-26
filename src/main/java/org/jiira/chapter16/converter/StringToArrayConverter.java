package org.jiira.chapter16.converter;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.util.StringUtils;

public class StringToArrayConverter implements ConditionalGenericConverter {

	//转换服务类
	private final ConversionService conversionService;
	//构造函数
	public StringToArrayConverter(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	//可接受的类型
	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		// TODO Auto-generated method stub
		return Collections.singleton(new ConvertiblePair(String.class, Object[].class));
	}
	//查找是否存在COnverter支持转换，如果不使用系统的，那么需要自己注册
	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		// TODO Auto-generated method stub
		if(null == sourceType) {
			return null;
		}
		//数据源
		String str = (String) source;
		//逗号分隔字符
		String[] dotSplit = StringUtils.commaDelimitedListToStringArray(str);
		int len = dotSplit.length;
		//转换目标
		Object target = Array.newInstance(targetType.getElementTypeDescriptor().getType(), len);
		//遍历数组
		for(int i = 0; i < len; ++i) {
			String sourceElement = dotSplit[i];
			//使用conversionService做出的类型转换，要求我们使用一个自定义或者Spring Core的Converter
			Object targetElement = conversionService.convert(sourceElement.trim(), sourceType, targetType.getElementTypeDescriptor());
			Array.set(target, i, targetElement);
		}
		return target;
	}

	//转换方法
	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		// TODO Auto-generated method stub
		return conversionService.canConvert(sourceType, targetType.getElementTypeDescriptor());
		
	}

}
