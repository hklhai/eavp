package cn.edu.ncut.eavp.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.PublishinfoObj;
import cn.edu.ncut.eavp.model.view.TopiccountObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface PublishinfoDao extends Dao<PublishinfoObj> {
	public DatagridResult getTopicCountGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby, String[] fields,String countWhere,
			Map<String, Object> countParams, LinkedHashMap<String, String> countOrderby);

	List<TopiccountObj> getTopiccountByIds(String where,
			Map<String, Object> params, String orderby, String[] fields,
			String countWhere, Map<String, Object> countParams,
			LinkedHashMap<String, String> countOrderby, Long publishtype);
}
