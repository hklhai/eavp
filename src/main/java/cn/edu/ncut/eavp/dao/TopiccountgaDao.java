package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.TopiccountgaObj;

public interface TopiccountgaDao extends Dao<TopiccountgaObj> {
	BaseModel getGridData(int firstResult, int maxResult, String where,
			Map<String, Object> params, String orderby);
}
