package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.PrefixinfoObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface PrefixinfoDao extends Dao<PrefixinfoObj> {
	public abstract DatagridResult getGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields);
}
