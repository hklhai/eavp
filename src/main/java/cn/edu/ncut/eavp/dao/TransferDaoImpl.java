package cn.edu.ncut.eavp.dao;

import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.model.TransferObj;
@Repository("transferDao")
public class TransferDaoImpl extends DaoSupport<TransferObj> implements
		TransferDao {

}
