package cn.edu.ncut.eavp.dao;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.TopicserialnumObj;

public interface TopicserialnumDao extends Dao<TopicserialnumObj> {
	public abstract Long getNextTopicnum(String publishid, Long year);
}
