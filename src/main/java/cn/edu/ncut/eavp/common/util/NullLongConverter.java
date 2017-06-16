package cn.edu.ncut.eavp.common.util;

import com.thoughtworks.xstream.converters.basic.LongConverter;

public class NullLongConverter extends LongConverter {
	
	@Override
	public boolean canConvert(Class type) {
		// TODO Auto-generated method stub
		return super.canConvert(type);
	}

	@Override
	public Object fromString(String str) {
		// TODO Auto-generated method stub
		if(null==str||"".equals(str))
			return null;
		return super.fromString(str);
	}
}
