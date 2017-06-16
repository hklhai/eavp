package cn.edu.ncut.eavp.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.PublishinfoObj;
import cn.edu.ncut.eavp.model.view.TopiccountId;
import cn.edu.ncut.eavp.model.view.TopiccountObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("publishinfoDao")
public class PublishinfoDaoImpl extends DaoSupport<PublishinfoObj> implements
		PublishinfoDao {
	private final static Logger logger = Logger
			.getLogger(PublishinfoDaoImpl.class);
	@Autowired
	private TopicCountDao topicCountDao;

	public DatagridResult getTopicCountGridData(int firstResult, int maxResult,
			String where, Map<String, Object> params, String orderby,
			String[] fields, String countWhere,
			Map<String, Object> countParams,
			LinkedHashMap<String, String> countOrderby) {
		DatagridResult dr = null;
		try {
			Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
			fieldMap.put("PUBLISHID", null);
			fieldMap.put("PUBLISHNAME", null);
			QueryResult<Map<String, Object>> qr = this.getScrollData(
					firstResult, maxResult, where, params, orderby, fieldMap
							.keySet().toArray(new String[0]));
			//fieldMap.put("BAKTIME", null);
			fieldMap.put("PUBLISHCOUNT", null);
			List<Map<String, Object>> templist = qr.getResultlist();
			List<TopiccountObj> countlist = topicCountDao.findAll(countWhere,
					countParams, countOrderby);
			for (Map<String, Object> temp : templist) {
				temp.put("BAKTIME",
						Long.parseLong(countParams.get("year").toString()));
				temp.put("PUBLISHCOUNT", (long)0);
				for (TopiccountObj count : countlist) {
					if (count.getTopiccountId().getPublishid().equals(temp.get("PUBLISHID"))) {
						temp.put("BAKTIME", count.getTopiccountId().getBaktime());
						if(temp.get("PUBLISHCOUNT")!=null)
						temp.put("PUBLISHCOUNT", count.getPublishcount()+(long)temp.get("PUBLISHCOUNT"));
						else
							temp.put("PUBLISHCOUNT", count.getPublishcount());
					}
				}
			}
			dr = qr.getDatagridResult(fieldMap);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return dr;

	}

	@Override
	public List<TopiccountObj> getTopiccountByIds(String where,
			Map<String, Object> params, String orderby, String[] fields,
			String countWhere, Map<String, Object> countParams,
			LinkedHashMap<String, String> countOrderby, Long publishtype) {
		List<PublishinfoObj> templist = this.findAll(where, params, null);
		List<TopiccountObj> countlist = topicCountDao.findAll(countWhere,
				countParams, countOrderby);
		List<TopiccountObj> result = new ArrayList<TopiccountObj>();
		for (PublishinfoObj obj : templist) {
			TopiccountObj tobj = new TopiccountObj();
			TopiccountId id=new TopiccountId();
			tobj.setTopiccountId(id);
			tobj.setPublishid(obj.getPublishid());
			tobj.setPublishname(obj.getPublishname());
			tobj.setBaktime(Long.parseLong(countParams.get("year").toString()));
			tobj.setPublishcount((long) 0);
			tobj.setPublishtype(publishtype);
			for (TopiccountObj count : countlist) {
				if (count.getTopiccountId().getPublishid().equals(obj.getPublishid())) {
					tobj.setBaktime(count.getTopiccountId().getBaktime());
					if(tobj.getPublishcount()!=(long)0)
						tobj.setPublishcount(tobj.getPublishcount()+count.getPublishcount());
					else
						tobj.setPublishcount(count.getPublishcount());
					tobj.setPublishtype(count.getTopiccountId().getPublishtype());
					tobj.setTopicstatus(count.getTopiccountId().getTopicstatus());
					tobj.setPublishid(count.getTopiccountId().getPublishid());
				}
			}
			result.add(tobj);
		}
		return result;
	}
}
