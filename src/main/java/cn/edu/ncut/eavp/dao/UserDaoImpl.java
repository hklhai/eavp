package cn.edu.ncut.eavp.dao;

import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.model.UserObj;

@Repository("userDao")
public class UserDaoImpl extends DaoSupport<UserObj> implements UserDao {

}
