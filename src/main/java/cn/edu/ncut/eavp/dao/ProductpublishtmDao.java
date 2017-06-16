package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.view.ProductpublishtmObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface ProductpublishtmDao extends Dao<ProductpublishtmObj>{
	public DatagridResult getProductWaitAuditGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby, String[] fields);

	DatagridResult getProductTraceGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
	
	DatagridResult getProductHasBarcodeGrid(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
}
