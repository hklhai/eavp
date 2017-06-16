package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.view.ExpertTopicAuditObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface ExpertTopicAuditDao extends Dao<ExpertTopicAuditObj> {
	DatagridResult getExpertTopicGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
}
