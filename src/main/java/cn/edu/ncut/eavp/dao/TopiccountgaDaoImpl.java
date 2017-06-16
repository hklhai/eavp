package cn.edu.ncut.eavp.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.TopiccountgaObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("topiccountgaDao")
public class TopiccountgaDaoImpl extends DaoSupport<TopiccountgaObj> implements
		TopiccountgaDao {
	private final static Logger logger = Logger
			.getLogger(TopiccountgaDaoImpl.class);

	@Override
	public BaseModel getGridData(int firstResult, int maxResult, String where,
			Map<String, Object> params, String orderby) {

		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("ORGID", null);
			fieldMap.put("ORGFULLNAME", null);
			fieldMap.put("PUBLISHCOUNT", null);
			QueryResult<Map<String, Object>> qr = this.getScrollData(
					firstResult, maxResult, where, params, orderby, fieldMap
							.keySet().toArray(new String[0]));
			dr = qr.getDatagridResult(fieldMap);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return dr;
	}
}
