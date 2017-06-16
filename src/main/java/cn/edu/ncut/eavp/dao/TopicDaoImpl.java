package cn.edu.ncut.eavp.dao;

import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.model.TopicObj;

@Repository("topicDao")
public class TopicDaoImpl extends DaoSupport<TopicObj> implements TopicDao {

}
