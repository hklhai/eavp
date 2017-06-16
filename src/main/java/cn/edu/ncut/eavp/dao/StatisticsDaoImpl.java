package cn.edu.ncut.eavp.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.StatisticsObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("statisticsDao")
public class StatisticsDaoImpl extends DaoSupport<StatisticsObj> implements StatisticsDao{
	
	protected final static Logger logger = Logger
			.getLogger(ProductDaoImpl.class);
	
	@Override
	public DatagridResult getYearTopicStatisticsGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) {
		
		DatagridResult dr = null;
		try{
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("STATISTICSID", null);
			fieldMap.put("NODEID", null);
			fieldMap.put("NODENAME", null);
			fieldMap.put("PARENTID", null);
			fieldMap.put("COUNTELEC", null);
			fieldMap.put("COUNTELECTOTAL", null);
			fieldMap.put("PERCENTSELEC", null);
			fieldMap.put("COUNTAUDIO", null);
			fieldMap.put("COUNTAUDIOTOTAL", null);
			fieldMap.put("PERCENTSAUDIO", null);
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
