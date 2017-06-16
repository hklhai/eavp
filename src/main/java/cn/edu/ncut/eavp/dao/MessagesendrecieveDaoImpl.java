package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.view.MessagesendrecieveObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;
@Repository("messagesendrecieveDao")
public class MessagesendrecieveDaoImpl extends
		DaoSupport<MessagesendrecieveObj> implements MessagesendrecieveDao {
	protected final static Logger logger = Logger
			.getLogger(MessagesendrecieveDaoImpl.class);
	@Override
	public DatagridResult getRecieveMessageGridData(int firstResult,
			int maxResult, String where, Map<String, Object> params,
			String orderby) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("ORGMESSAGEID", null);
			fieldMap.put("MESSAGENAME", null);
		//	fieldMap.put("MESSAGECONTENT", null);
    		fieldMap.put("FROMNAME", null);
			fieldMap.put("SENDTIME", null);
			fieldMap.put("CREATETIME", null);
			Map<Object, String> readFlag = new HashMap<Object, String>();
			readFlag.put(false, "未阅读");
			readFlag.put(true, "阅读");
			fieldMap.put("READFLAG", readFlag);			
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
