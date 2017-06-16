package cn.edu.ncut.eavp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.edu.ncut.eavp.model.assistant.ISBNParameters;
import cn.edu.ncut.eavp.model.assistant.Parameter;
import cn.edu.ncut.eavp.webservice.IsbninterfaceWebService;


public class TestWebService {
	@Test
	public void getXML() {
		ISBNParameters params = new ISBNParameters();
		Parameter param = new Parameter();
		param.setEleISBN("978-7-900796-94-3");
		param.setPressName("北京理工大学出版社");
		params.setParam(param);
		System.out.println(params.asXML(false));
		System.out.println();
	}

	@Test
	public void getBookName() {
		ISBNParameters params = new ISBNParameters();
		Parameter param = new Parameter();
		param.setEleISBN("978-7-900796-96-7");
		param.setPressName("北京理工大学");
		params.setParam(param);
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring/cxf/appcontext.xml");
		IsbninterfaceWebService client = (IsbninterfaceWebService) context
				.getBean("isbninterfaceWebService");
		String xml =params.asXML(false);
		String s = client.getElePublicationName(xml);
		System.out.println(s);

	}

}
