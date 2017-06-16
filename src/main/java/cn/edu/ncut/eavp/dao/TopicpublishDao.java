package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.view.TopicpublishObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface TopicpublishDao extends Dao<TopicpublishObj>{
	public DatagridResult getGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) ;
	public DatagridResult getPublishGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields) ;
	DatagridResult getTraceGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
}
