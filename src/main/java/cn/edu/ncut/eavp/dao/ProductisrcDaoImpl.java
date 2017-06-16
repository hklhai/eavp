package cn.edu.ncut.eavp.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.edu.ncut.eavp.common.basedao.DaoSupport;
import cn.edu.ncut.eavp.common.basedao.QueryResult;
import cn.edu.ncut.eavp.model.ProductisrcObj;
import cn.edu.ncut.eavp.model.view.ProductpublishObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Repository("productisrcDao")
public class ProductisrcDaoImpl extends DaoSupport<ProductisrcObj>
		implements ProductisrcDao {

	@Override
	public void deleteByProductid(Long productid) {
		Map<String,Long> params=new HashMap<String,Long>();
		params.put("productid", productid);
	List<ProductisrcObj>objs=	this.findAll("productid =:productid", params, null);
		for(ProductisrcObj o:objs)
			this.delete(o.getProductisrcid());
	}
	
}
