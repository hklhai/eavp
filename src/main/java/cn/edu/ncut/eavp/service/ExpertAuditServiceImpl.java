package cn.edu.ncut.eavp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ncut.eavp.dao.ExpertAuditDao;
import cn.edu.ncut.eavp.dao.OrganizationinfoDao;
import cn.edu.ncut.eavp.dao.ProductpublishDao;
import cn.edu.ncut.eavp.dao.PublishinfoDao;
import cn.edu.ncut.eavp.dao.TransferDao;
import cn.edu.ncut.eavp.model.assistant.ExpertAuditObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;


@Transactional
@Service("expertAuditService")
@Scope("request")
public class ExpertAuditServiceImpl implements ExpertAuditService {

	@Autowired
	private OrganizationinfoDao organizationinfoDao;
	@Autowired
	private PublishinfoDao publishinfoDao;
	@Autowired
	private ProductpublishDao productpublishDao;
	@Autowired
	private TransferDao transferDao;
	@Autowired
	private ExpertAuditDao expertauditDao;
	

	@Override
	public DatagridResult getGridData(int firstResult, int maxResult,
			String orderby, String[] fields, String fuzzy,ExpertAuditObj model, String orgids) {

		StringBuilder wherebuilder = new StringBuilder();
		String where = new String();
		String where2 = new String();
		Map<String , Object> params = new HashMap<String , Object>();
		
		
		//增加权限   根据作品申请时间查询   lh  2015年2月3日20:00:23
		if (orgids != null && !"".equals(orgids)) {
			String[] orgid = orgids.split(";");
			StringBuilder temp = new StringBuilder("(");
			for (int i = 0; i < orgid.length; i++) {
				temp.append(" publishid like  :orgid").append(i)
						.append("||'%' or");
				params.put("orgid" + i, orgid[i]);
			}
			temp.delete(temp.length() - 3, temp.length());
			//temp.append(") ");
			wherebuilder.append("and ").append(temp);
		}
		
		where2=" createtime>=:starttime and createtime<=:endtime ";
		

		where =where2+wherebuilder.toString();
		
		where += fuzzy==null? " " : " )and orgfullname like '%'||:fuzzy||'%'  ";	
		params.put("fuzzy", fuzzy);
		Map<String , Boolean> typeMap = model.getProducttype();

		if(typeMap.get("electronic")==true && typeMap.get("media")==false){
			where += " and iselec=1";
			Date starttime = model.getStarttime();
			Date endtime = model.getEndtime();
			params.put("starttime",starttime);
			params.put("endtime", endtime);
			return expertauditDao.getExpertProductGrid(firstResult, maxResult, where, params, orderby, fields);
		}
		if(typeMap.get("media")==true && typeMap.get("electronic")==false){
			where += " and iselec=0";
			Date starttime = model.getStarttime();
			Date endtime = model.getEndtime();
			params.put("starttime",starttime);
			params.put("endtime", endtime);
			return expertauditDao.getExpertProductGrid(firstResult, maxResult, where, params, orderby, fields);
		}else{
			Date starttime = model.getStarttime();
			Date endtime = model.getEndtime();
			params.put("starttime",starttime);
			params.put("endtime", endtime);
			return expertauditDao.getExpertProductGrid(firstResult, maxResult, where, params, orderby, fields);
		}
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String starttime = new StringBuilder("to_date('").append(df.format(model.getStarttime()))
//				.append("','yyyy-MM-dd')").toString();
//		String endtime = new StringBuilder("to_date('").append(df.format(model.getEndtime()))
//				.append("','yyyy-MM-dd')").toString();
		
		
//		//获取作品类别信息
//		Map<String, Boolean> productTypeMap = model.getProducttype();
//		StringBuilder ptsb = new StringBuilder("and (");
//		if (productTypeMap.get("electronic") == true) {
//			ptsb.append("or iselec =1 ");
//		}
//		if (productTypeMap.get("media") == true) {
//			ptsb.append("or iselec =0 ");
//		}
//		ptsb.delete(5, 7);
//		ptsb.append(") ");
//		if (ptsb.length() > 7){}
	
	}
}
