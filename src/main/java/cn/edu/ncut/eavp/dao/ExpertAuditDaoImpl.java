package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.view.ExpertAuditObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("expertauditDao")
public class ExpertAuditDaoImpl extends DaoSupport<ExpertAuditObj> implements
		ExpertAuditDao {
	
	protected final static Logger logger = Logger
			.getLogger(ProductDaoImpl.class);
	
	@Override
	public DatagridResult getExpertProductGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			
//			Map<Object, String> statusMap = new HashMap<Object, String>();
//			statusMap.put("0", "音像");
//			statusMap.put("1", "电子");
			
			fieldMap.put("PRODUCTID", null);
			//fieldMap.put("ISELEC", statusMap);
			fieldMap.put("TOPICNAME", null);
			fieldMap.put("ORGFULLNAME", null);
			fieldMap.put("CONTENTABSTRACT", null);
			fieldMap.put("PUBLISHTYPE", null);
			fieldMap.put("CONTENTTYPE", null);	
			fieldMap.put("PROVINCENAME", null);
			fieldMap.put("PUBLISHID", null);
			

			
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
