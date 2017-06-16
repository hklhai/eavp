package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.ncut.eavp.service.IsbninterfaceService;
import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.BaseWebService;

@WebService(endpointInterface = "cn.edu.ncut.eavp.webservice.IsbninterfaceWebService")
@Service("isbninterfaceWebService")
@Scope("request")
public class IsbninterfaceWebServiceImpl implements  IsbninterfaceWebService {

	@Autowired
	private IsbninterfaceService isbninterfaceService;
	

	@Override
	public String getElePublicationName(String xmlInfo) {
		// TODO Auto-generated method stub
		return isbninterfaceService.getElePublicationName(xmlInfo);
	}


	

}
