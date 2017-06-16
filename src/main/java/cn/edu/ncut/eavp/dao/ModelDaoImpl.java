package cn.edu.ncut.eavp.dao;

import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.model.ModelObj;

@Repository("modelDao")
public class ModelDaoImpl extends DaoSupport<ModelObj> implements ModelDao {

}
