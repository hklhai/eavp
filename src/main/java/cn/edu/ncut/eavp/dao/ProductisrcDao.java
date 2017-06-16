package cn.edu.ncut.eavp.dao;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.ProductisrcObj;

public interface ProductisrcDao extends Dao<ProductisrcObj>{

	public void deleteByProductid(Long productid);

}
