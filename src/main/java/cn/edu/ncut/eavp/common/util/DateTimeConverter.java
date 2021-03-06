package cn.edu.ncut.eavp.common.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

import com.thoughtworks.xstream.converters.basic.DateConverter;

public class DateTimeConverter extends DateConverter {
	public DateTimeConverter(String dateTimeFormat, String[] acceptableFormats) {
		// TODO Auto-generated constructor stub
		super(dateTimeFormat, acceptableFormats,TimeZone.getDefault());
	}
	

	public DateTimeConverter(String dateTimeFormat, String[] acceptableFormats, TimeZone timeZone) {
		// TODO Auto-generated constructor stub
		super(dateTimeFormat,acceptableFormats,timeZone);
	}


	@SuppressWarnings("unchecked")
	public boolean canConvert(Class type) {
		if (Date.class.isAssignableFrom(type))
			return true;
		return super.canConvert(type);
	}

	@Override
	public String toString(Object obj) {
		Date date = null;
		// TODO Auto-generated method stub
		if (obj instanceof Timestamp) {
			Timestamp tempobj = (Timestamp) obj;
			date = new Date(tempobj.getTime());
			return super.toString(date);
		}else
		 return super.toString(obj);
		
	}

}
