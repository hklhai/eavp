package cn.edu.ncut.eavp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.dao.ProductpublishDao;
import cn.edu.ncut.eavp.model.assistant.ISBNParameters;
import cn.edu.ncut.eavp.model.assistant.Parameter;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.ProductpublishObj;

@Transactional
@Service("isbninterfaceService")
@Scope("request")
public class IsbninterfaceServiceImpl implements IsbninterfaceService{
	private final static Logger logger = Logger.getLogger(IsbninterfaceServiceImpl.class);
	@Autowired
	private ProductpublishDao productpublishDao;
	@Override
	public String getElePublicationName(String xmlInfo) {
		ISBNParameters parameters = new ISBNParameters();
		try {
			
			parameters=(ISBNParameters) parameters.fromXML(xmlInfo,false);
			if(parameters.getParam().getEleISBN()==null||parameters.getParam().getEleISBN().isEmpty()||parameters.getParam().getPressName()==null||parameters.getParam().getPressName().isEmpty())
				return "0";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("publishname",parameters.getParam().getPressName());
			params.put("productisbn",parameters.getParam().getEleISBN());
			List<ProductpublishObj>list=	productpublishDao.findAll("publishname=:publishname and productisbn=:productisbn", params, null);
		if(list.size()==0)
			return "0";
		else
			return list.get(0).getTopicname();
		} catch (Exception e) {
			logger.error(e);
			return "-1";
			
		}
     
	}

	
	
}
