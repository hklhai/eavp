package cn.edu.ncut.eavp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.util.ObjectHelper;
import cn.edu.ncut.eavp.model.ProductObj;
import cn.edu.ncut.eavp.model.ProductisrcObj;

@Repository("productDao")
public class ProductDaoImpl extends DaoSupport<ProductObj> implements ProductDao {
	
	@Autowired
	private ProductisrcDao productisrcDao;
	
	@Override
	public void saveWithIsrc(ProductObj obj) {
		this.save(obj);
		List<ProductisrcObj> list= obj.getProductisrcs();
		if(list!=null && list.size() > 0){
			for (ProductisrcObj productisrcObj : list) {
				productisrcObj.setTbProduct(obj);
				productisrcDao.save(productisrcObj);
			}
		}
	}

	@Override
	public ProductObj findWithIsrc(long id) {
		ProductObj obj = this.find(id);
		List<ProductisrcObj> list = new ArrayList<ProductisrcObj>();
		for (ProductisrcObj productisrcObj : obj.getTbProductisrcs()) {
			list.add(productisrcObj);
		}
		obj.setProductisrcs(list);
		return obj;
	}

	@Override
	public void updateWithIsrc(ProductObj obj) throws Exception {
		this.merge(obj);
		productisrcDao.deleteByProductid(obj.getProductid());
		List<ProductisrcObj> list= obj.getProductisrcs();
		if(list!=null && list.size() > 0){
			for (ProductisrcObj productisrcObj : list) {
				ProductisrcObj isrcobj=new ProductisrcObj();
				ObjectHelper.beanClone(ProductisrcObj.class, productisrcObj, ProductisrcObj.class,
						isrcobj);
				isrcobj.setTbProduct(obj);
			    isrcobj.setProductisrcid(null);
				productisrcDao.save(isrcobj);
			}
		}
		
	}
	 
}
