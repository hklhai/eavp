package cn.edu.ncut.eavp.dao;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.model.ProductObj;

public interface ProductDao extends Dao<ProductObj> {
	public void saveWithIsrc(ProductObj obj);
	public ProductObj findWithIsrc(long id);
	public void updateWithIsrc (ProductObj obj) throws Exception;
}
