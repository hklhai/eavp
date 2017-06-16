package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.StatisticsSumObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface StatisticsSumDao extends Dao<StatisticsSumObj>{

	DatagridResult getYearTopicStatisticsSumGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
	
}
