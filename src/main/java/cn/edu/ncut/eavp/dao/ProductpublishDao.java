package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.view.ProductpublishObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface ProductpublishDao extends Dao<ProductpublishObj>{
	public DatagridResult getProductWaitAuditGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby, String[] fields);

	DatagridResult getProductTraceGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
	
	DatagridResult getProductHasBarcodeGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);

}
