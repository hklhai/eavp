package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.view.ProductpublishObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("productpublishDao")
public class ProductpublishDaoImpl extends DaoSupport<ProductpublishObj>
		implements ProductpublishDao {
	protected final static Logger logger = Logger
			.getLogger(ProductDaoImpl.class);

	@Override
	public DatagridResult getProductWaitAuditGrid(int firstResult,
			int maxResult, String where, Map<String, Object> params,
			String orderby, String[] fields) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("PRODUCTID", null);
			fieldMap.put("PUBLISHNAME", null);
			fieldMap.put("SERIESNAME", null);
			fieldMap.put("TOPICNUM", null);
			fieldMap.put("TOPICNAME", null);
			fieldMap.put("IMPORTANT", null);
			fieldMap.put("IMPORTANTINDEX", null);
			fieldMap.put("APPLYTIME", null);
			//fieldMap.put("STAGETIME", null);
	 
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
	public DatagridResult getProductTraceGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("PRODUCTID", null);
			fieldMap.put("PUBLISHNAME", null);
			fieldMap.put("TOPICNUM", null);
			fieldMap.put("TOPICNAME", null);
			fieldMap.put("SERIESNAME", null);
			fieldMap.put("PRODUCTISBN", null);
			fieldMap.put("IMPORTANT", null);
			fieldMap.put("IMPORTANTINDEX", null);
			fieldMap.put("APPLYTIME", null);
 
			Map<Object, String> statusMap = new HashMap<Object, String>();
			statusMap.put("100010", "待省局审核");
			statusMap.put("100020", "待中心发码");
			//待定Client端显示仍未   "审核通过，待发码"  状态  
			statusMap.put("100021", "待中心发码");
			//总署退回返回状态   
			statusMap.put("100014", "总署退回");
			statusMap.put("100030", "发码，待下载");
			statusMap.put("100040", "成品待上传");
			statusMap.put("100050", "成品已上传");
			statusMap.put("100011", "退回");
			statusMap.put("100012", "作品撤回");
			statusMap.put("100013", "配码返回修改");
			statusMap.put("100060", "作品终止");
			fieldMap.put("PRODUCTSTATUS", statusMap);
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
	public DatagridResult getProductHasBarcodeGrid(int firstResult,
			int maxResult, String where, Map<String, Object> params,
			String orderby, String[] fields) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("PRODUCTID", null);
			fieldMap.put("PUBLISHNAME", null);
			fieldMap.put("PRODUCTISBN", null);	
			fieldMap.put("TOPICNUM", null);
			fieldMap.put("TOPICNAME", null);
			fieldMap.put("SERIESNAME", null);
			fieldMap.put("IMPORTANT", null);
			fieldMap.put("IMPORTANTINDEX", null);
			fieldMap.put("APPLYTIME", null);
			//条码用户增加最后审核时间    lh   2015年1月13日18:12:23
			//fieldMap.put("STAGETIME", null);
	 
			Map<Object, String> statusMap = new HashMap<Object, String>();
			statusMap.put("100010", "待省局审核");
			statusMap.put("100020", "待省局审核");
			statusMap.put("100021", "待中心发码");
			statusMap.put("100030", "发码，待下载");
			statusMap.put("100040", "成品待上传");
			statusMap.put("100050", "成品已上传");
			statusMap.put("100060", "作品终止");
			statusMap.put("100011", "退回");
			statusMap.put("100012", "作品撤回");
			statusMap.put("100013", "配码返回修改");
			 
			fieldMap.put("PRODUCTSTATUS", statusMap);
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
