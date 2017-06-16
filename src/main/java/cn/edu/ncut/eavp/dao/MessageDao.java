package cn.edu.ncut.eavp.dao;

import java.util.Map;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.MessageObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface MessageDao extends Dao<MessageObj> {

	DatagridResult getSendMessageGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby);

}
