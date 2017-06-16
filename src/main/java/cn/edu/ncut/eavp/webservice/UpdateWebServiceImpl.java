package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.ncut.eavp.service.UpdateService;
import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.BaseWebService;
import cn.edu.ncut.eavp.webservice.base.Result;

@WebService(endpointInterface = "cn.edu.ncut.eavp.webservice.UpdateWebService")
@Service("updateWebService")
@Scope("request")
public class UpdateWebServiceImpl implements UpdateWebService ,BaseWebService{
    @Autowired
    private UpdateService updateService;
	@Override
	public BaseService getService() {
		 return updateService;
	}
	@Override
	public String getDownloadFilesList(String xml) {
		return updateService.getDownloadFilesList();
	}
	@Override
	public String downloadFile(String xml) {
		return updateService.downloadFile();
	}
	@Override
	public String getVersionList(String xml) {
		// TODO Auto-generated method stub
		return updateService.getVersionList();
	}
 
	@Override
	public String sendVersion(String xml) {
		Result result = null;
		try {
			result = updateService.sendVersion();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("下发更新失败！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String updateVersion(String xml) {
		Result result = null;
		try {
			result = updateService.updateVersion();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("下发更新失败！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String deleteVersion(String xml) {
		Result result = null;
		try {
			result = updateService.deleteVersion();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("删除失败！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String saveVersion(String xml) {		 
		Result result = null;
		try {
			result = updateService.saveVersion();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("添加失败！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String getVersionById(String xml) {
		return updateService.getVersionById();
	}
 
	

}
