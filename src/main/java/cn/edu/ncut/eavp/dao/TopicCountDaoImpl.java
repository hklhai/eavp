package cn.edu.ncut.eavp.dao;

import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.model.view.TopiccountObj;

@Repository("topicCountDao")
public class TopicCountDaoImpl extends DaoSupport<TopiccountObj>  implements TopicCountDao {
	
}
