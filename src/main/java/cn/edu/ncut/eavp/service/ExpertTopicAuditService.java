package cn.edu.ncut.eavp.service;

import cn.edu.ncut.eavp.model.assistant.ExpertTopicAuditObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;


public interface ExpertTopicAuditService {
	public abstract DatagridResult getGridData(int firstResult,int maxResult,
			String orderby,String []fields, String fuzzy,ExpertTopicAuditObj model,String orgids);
}