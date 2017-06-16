package cn.edu.ncut.eavp.service;

import cn.edu.ncut.eavp.model.assistant.ExpertAuditObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface ExpertAuditService {
	public abstract DatagridResult getGridData(int firstResult,int maxResult,
			String orderby,String []fields, String fuzzy, ExpertAuditObj model,String orgids);
}
