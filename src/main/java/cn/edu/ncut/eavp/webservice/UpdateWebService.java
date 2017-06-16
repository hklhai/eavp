package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

@WebService
public interface UpdateWebService {
	String getDownloadFilesList(String xml);
	String downloadFile(String xml);	 
	String getVersionList(String xml);
	String updateVersion(String xml);
	String sendVersion(String xml);
	String deleteVersion(String xml);
	String saveVersion(String xml);
	String getVersionById(String xml);
}
