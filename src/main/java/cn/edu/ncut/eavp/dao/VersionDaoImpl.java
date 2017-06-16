package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.VersionObj;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;
@Repository("versionDao")
public class VersionDaoImpl extends DaoSupport<VersionObj> implements VersionDao {
	private final static Logger logger = Logger.getLogger(VersionDaoImpl.class);
	@Override
	public BaseModel getGridData(int firstResult, int maxResult, String where,
			Map<String, Object> params, String orderby) {
		 
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("VERSIONID", null);
			fieldMap.put("VERSIONNUM", null);
			fieldMap.put("FILENAME", null);
			fieldMap.put("FILEPATH", null);
			HashMap<Boolean,String>map=new HashMap<Boolean,String>();
			map.put(true, "已下发");
			map.put(false, "未下发");
			fieldMap.put("ISUSE", map);
			QueryResult<Map<String, Object>> qr = this.getScrollData(
					firstResult, maxResult, where, params, orderby, fieldMap
							.keySet().toArray(new String[0]));
			dr = qr.getDatagridResult(fieldMap);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return dr;
	}

}
