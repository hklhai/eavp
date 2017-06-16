package cn.edu.ncut.eavp.service;

import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.Result;

public interface UpdateService extends BaseService{

	String getDownloadFilesList();

	String downloadFile();

	String getVersionList();

	Result deleteVersion();

	Result updateVersion();
	
	Result saveVersion();

	String getVersionById();

	Result sendVersion();

}
