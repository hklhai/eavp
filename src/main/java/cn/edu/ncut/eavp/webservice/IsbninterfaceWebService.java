package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

@WebService
public interface IsbninterfaceWebService {
	public String getElePublicationName(String xmlInfo);

}
