package cn.edu.ncut.eavp.common.util;

import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;

public class NullBigDecimalConverter extends BigDecimalConverter {

	@Override
	public Object fromString(String str) {
		// TODO Auto-generated method stub
		if(null==str||"".equals(str))
			return null;
		return super.fromString(str);
	}
	

}
