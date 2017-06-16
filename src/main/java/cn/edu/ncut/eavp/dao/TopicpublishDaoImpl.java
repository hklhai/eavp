package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.view.TopicpublishObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("topicpublishDao")
public class TopicpublishDaoImpl extends DaoSupport<TopicpublishObj>  implements TopicpublishDao {
	private final static Logger logger = Logger.getLogger(TopicCountDaoImpl.class);
	public DatagridResult getGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("TOPICID", null);
			fieldMap.put("TOPICNUM", null);
			fieldMap.put("TOPICNAME", null);
			fieldMap.put("SERIESNAME", null);
			fieldMap.put("IMPORTANT", null);
			fieldMap.put("IMPORTANTINDEX", null);
			fieldMap.put("BAKTIME", null);
			QueryResult<Map<String, Object>> qr = this.getScrollData(
					firstResult, maxResult, where, params, orderby, fieldMap
							.keySet().toArray(new String[0]));
			dr = qr.getDatagridResult(fieldMap);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return dr;
	}
	
	@Override
	public DatagridResult getTraceGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("TOPICID", null);
			fieldMap.put("TOPICNUM", null);
			fieldMap.put("TOPICNAME", null);
			fieldMap.put("SERIESNAME", null);
			fieldMap.put("IMPORTANT", null);
			fieldMap.put("IMPORTANTINDEX", null);
			fieldMap.put("APPLYTIME", null);
 
			Map<Object, String> statusMap = new HashMap<Object, String>();
			statusMap.put("100010", "待审核");
			statusMap.put("100020", "审核通过，未申请条码");
			statusMap.put("100030", "审核通过，已申请条码");
			statusMap.put("100011", "退回");
			statusMap.put("100012", "作品撤回");
			statusMap.put("100060", "终止");
			fieldMap.put("TOPICSTATUS", statusMap);
			QueryResult<Map<String, Object>> qr = this.getScrollData(
					firstResult, maxResult, where, params, orderby, fieldMap
							.keySet().toArray(new String[0]));
			dr = qr.getDatagridResult(fieldMap);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return dr;
	}
	public DatagridResult getPublishGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("TOPICID", null);
			fieldMap.put("PUBLISHNAME", null);
			fieldMap.put("TOPICNUM", null);
			fieldMap.put("TOPICNAME", null);
			fieldMap.put("SERIESNAME", null);
			fieldMap.put("IMPORTANT", null);
			fieldMap.put("IMPORTANTINDEX", null);
			fieldMap.put("BAKTIME", null);
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
