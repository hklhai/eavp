package cn.edu.ncut.eavp.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.StatisticsSumObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("statisticsSumDao")
public class StatisticsSumDaoImpl extends DaoSupport<StatisticsSumObj> implements StatisticsSumDao{
	
	protected final static Logger logger = Logger
			.getLogger(ProductDaoImpl.class);
	
	@Override
	public DatagridResult getYearTopicStatisticsSumGrid(int firstResult,
			int maxResult, String where, Map<String, Object> params,
			String orderby, String[] fields) {
		
		DatagridResult dr = null;
		try{
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("STATISTICSSUMID", null);
			fieldMap.put("AUDIOYEARTOPIC", null);
			fieldMap.put("ELECYEARTOPIC", null);
			fieldMap.put("AUDIONOYEARTOPIC", null);
			fieldMap.put("ELECNOYEARTOPIC", null);
			fieldMap.put("TOPICYEAR", null);
			
			QueryResult<Map<String, Object>> qr = this.getScrollData(firstResult, maxResult, where, params
					, orderby, fieldMap.keySet().toArray(new String[0]));
			dr = qr.getDatagridResult(fieldMap);
		}catch(Exception e){
			logger.debug(e.getMessage());
		}
		return dr;
	}
}
