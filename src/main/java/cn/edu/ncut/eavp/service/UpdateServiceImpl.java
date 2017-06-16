package cn.edu.ncut.eavp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.ncut.eavp.dao.VersionDao;
import cn.edu.ncut.eavp.model.VersionObj;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.service.base.BaseServiceImpl;
import cn.edu.ncut.eavp.webservice.base.ObjectResult;
import cn.edu.ncut.eavp.webservice.base.Result;

@Service("updateService")
@Scope("request")
public class UpdateServiceImpl extends BaseServiceImpl<Object> implements
		UpdateService {
	private final static Logger logger = Logger
			.getLogger(UpdateServiceImpl.class);
	@Autowired
	private VersionDao versionDao;
	@Value("${cn.edu.ncut.eavp.updatepath}")
	private String path;

	@Override
	public String getDownloadFilesList() {
		ObjectResult<List<VersionObj>> result = new ObjectResult<List<VersionObj>>();
		try {
			List<VersionObj> resultObject = versionDao.findAll("isuse=1", null,
					null);
			if (null != resultObject) {
				result.setResultObject(resultObject);
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("getBakedTopicDetail没有查询到数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("getBakedTopicDetail出现错误！");
		}
		return result.asXML(true);
	}

	@Override
	public String downloadFile() {
		ObjectResult<VersionObj> result = new ObjectResult<VersionObj>();
		Map<String, String> frontParams = parameters.getParams();
		long id = new Long(frontParams.get("versionid"));
		VersionObj obj = versionDao.find(id);
		String filename = path + "/" + obj.getFilename();
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			byte[] b = new byte[fileInputStream.available()];
			fileInputStream.read(b);
			obj.setFiledata(b);
			result.setResultObject(obj);
			result.setType(0);
			fileInputStream.close();
			return result.asXML(true);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		result.setType(0);
		result.setMessage("没有找到需要下载的附件！");
		return result.asXML(true);
	}

	@Override
	public String getVersionList() {
		BaseModel result;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String fuzzy = parameters.getParams().get("fuzzy");
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");
			String where = "";
			if (!fuzzy.equals("")) {

				params.put("fuzzy", fuzzy);
				where += " and (instr(filename,:fuzzy)>0 )";
				result = versionDao.getGridData(firstResult, maxResult, where,
						params, orderby);
			} else
				result = versionDao.getGridData(firstResult, maxResult, where,
						params, orderby);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}

	@Override
	public Result deleteVersion() {
		Result result = new Result();
		Map<String, String> params = parameters.getParams();
		String versionids = params.get("versionids");
		String[] versionid = versionids.split(";");
		for (String id : versionid) {		 
			versionDao.delete(Long.parseLong(id));		
		}
		result.setType(0);
		result.setMessage("删除成功！");
		return result;
	}
	

	@Override
	public Result updateVersion() {
		Result result = new Result();
		VersionObj obj = (VersionObj) parameters.getDataObject();
		versionDao.merge(obj);
		saveFile(obj);
		result.setType(0);
		result.setMessage("更新下发成功！");
		return result;
	}

	private void saveFile(VersionObj version) {
		if (version.getFiledata() != null && version.getFiledata().length > 0) {
			File f = new File(path);
			if (!f.exists())
				f.mkdirs();
			String filename = path + "/" + version.getFilename();
			FileOutputStream outputStream;
			try {
				outputStream = new FileOutputStream(new File(filename));
				outputStream.write(version.getFiledata());
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	@Override
	public Result saveVersion() {
		Result result = new Result();
		VersionObj obj = (VersionObj) parameters.getDataObject();
		versionDao.save(obj);
		saveFile(obj);
		result.setType(0);
		result.setMessage("添加成功！");
		return result;
	}

	@Override
	public String getVersionById() {
		ObjectResult<VersionObj> result = new ObjectResult<VersionObj>();
		Map<String, String> frontParams = parameters.getParams();
		long id = new Long(frontParams.get("versionid"));
		try {
			VersionObj obj = versionDao.find(id);
			result.setResultObject(obj);
			result.setType(0);
		} catch (Exception e) {
			result.setType(1);
			result.setMessage("获取对象失败！");
			logger.error(e.getMessage());
		}
		return result.asXML(true);
	}

	@Override
	public Result sendVersion() {
		Result result = new Result();
		Map<String, String> params = parameters.getParams();
		String versionids = params.get("versionids");
		String[] versionid = versionids.split(";");
		for (String id : versionid) {
			VersionObj obj = versionDao.find(Long.parseLong(id));
			obj.setIsuse(true);
			versionDao.merge(obj);
			
		}
		result.setType(0);
		result.setMessage("下发成功！");
		return result;
	}
}
