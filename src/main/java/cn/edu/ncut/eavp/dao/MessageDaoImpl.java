package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.MessageObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;
@Repository("messageDao")
public class MessageDaoImpl extends DaoSupport<MessageObj> implements
		MessageDao {
	protected final static Logger logger = Logger
			.getLogger(MessageDaoImpl.class);
	@Override
	public DatagridResult getSendMessageGridData(int firstResult,
			int maxResult, String where, Map<String, Object> params,
			String orderby) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("MESSAGEID", null);
			fieldMap.put("MESSAGENAME", null);
		//	fieldMap.put("MESSAGECONTENT", null);
			Map<Object, String> messageStatus = new HashMap<Object, String>();
			messageStatus.put((long)0, "待发送");
			messageStatus.put((long)1, "已发送");
			messageStatus.put((long)2, "被阅读");
			fieldMap.put("MESSAGESTATUS", messageStatus);
			fieldMap.put("CREATETIME", null);
			
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
