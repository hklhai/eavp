package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.view.ExpertAuditObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface ExpertAuditDao extends Dao<ExpertAuditObj>{
	DatagridResult getExpertProductGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
}
