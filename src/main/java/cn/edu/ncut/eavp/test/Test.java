package cn.edu.ncut.eavp.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.edu.ncut.eavp.model.TransferObj;
import cn.edu.ncut.eavp.model.assistant.IntegeratedQueryObj;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.MessagesendrecieveObj;
import cn.edu.ncut.eavp.webservice.base.BaseParameters;

import com.thoughtworks.xstream.XStream;

public class Test {
	public static void main(String[] args) {
		System.out.println(computerIsbn("987-7-3090",(long)4547));
		 
		Date date=new Date(1386060460386L);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:ss");
		System.out.println(df.format(date));
		
	}
	private static String  computerIsbn(String prefix, Long currentnum) {
		String result = prefix;
		String suffer = String.valueOf(currentnum);
		prefix = prefix.replaceAll("-", "");
		// 补位
		int fill = 12 - prefix.length();
		for (int i = 1; i < fill; i++) {
			if (suffer.length() < fill) {
				suffer = '0' + suffer;
			}
		}
		String partIsbn = prefix + suffer;
		result = result + "-" + suffer;
		int total = 0;
		for (int i = 1; i <= partIsbn.length(); i++) {
			if (i % 2 == 0) {
				char c = partIsbn.charAt(i - 1);
				total = total + Integer.parseInt(c + "") * 3;
			} else {
				char c = partIsbn.charAt(i - 1);
				total = total + Integer.parseInt(c + "") * 1;
			}
		}
		int tail = 0;
		if (10 - total % 10 == 10)
			tail = 0;
		else
			tail = 10 - total % 10;
		return result + "-" + tail;

	}
}
