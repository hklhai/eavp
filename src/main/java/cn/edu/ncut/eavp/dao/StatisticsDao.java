package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.StatisticsObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface StatisticsDao extends Dao<StatisticsObj>{

	DatagridResult getYearTopicStatisticsGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
	
}
