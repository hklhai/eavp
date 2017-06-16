package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.model.TopicserialnumObj;

@Repository("topicserialnumDao")
public class TopicserialnumDaoImpl extends DaoSupport<TopicserialnumObj>
		implements TopicserialnumDao {

	@Override
	public Long getNextTopicnum(String publishid, Long year) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("publishid", publishid);
		List<TopicserialnumObj> list = this.findAll(
				"year=:year and publishid=:publishid", params, null);
		Long num = 1L;
		if (list.size() > 0) {
			TopicserialnumObj obj = list.get(0);
			num = obj.getSerialnum();
			obj.setSerialnum(num + 1L);
			this.update(obj);
			return num;
		} else {
			TopicserialnumObj obj = new TopicserialnumObj();
			obj.setPublishid(publishid);
			obj.setSerialnum(1L);
			obj.setYear(year);
			this.save(obj);
		}
		return num;
	}

}
