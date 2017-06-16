package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.VersionObj;
import cn.edu.ncut.eavp.model.base.BaseModel;

public interface VersionDao extends Dao<VersionObj> {

	BaseModel getGridData(int firstResult, int maxResult, String where,
			Map<String, Object> params, String orderby);

}
