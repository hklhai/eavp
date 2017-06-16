package cn.edu.ncut.eavp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ncut.eavp.dao.ExpertTopicAuditDao;
import cn.edu.ncut.eavp.dao.OrganizationinfoDao;
import cn.edu.ncut.eavp.dao.ProductpublishDao;
import cn.edu.ncut.eavp.dao.PublishinfoDao;
import cn.edu.ncut.eavp.dao.TransferDao;
import cn.edu.ncut.eavp.model.assistant.ExpertTopicAuditObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Transactional
@Service("expertTopicAuditService")
@Scope("request")
public class ExpertTopicAuditServiceImpl implements ExpertTopicAuditService {

	@Autowired
	private OrganizationinfoDao organizationinfoDao;
	@Autowired
	private PublishinfoDao publishinfoDao;
	@Autowired
	private ProductpublishDao productpublishDao;
	@Autowired
	private TransferDao transferDao;
	@Autowired
	private ExpertTopicAuditDao experttopicauditDao;
	
	@Override
	public DatagridResult getGridData(int firstResult, int maxResult,
			String orderby, String[] fields, String fuzzy,ExpertTopicAuditObj model,
			String orgids) {


		StringBuilder wherebuilder = new StringBuilder();
		String where = new String();
		String where2 = new String();
		
		Map<String , Object> params = new HashMap<String , Object>();
		
		//增加权限   根据选题申请时间查询     2015年2月3日20:20:30
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
		
		where2="createtime>=:starttime and createtime<=:endtime ";
		

		where =where2+wherebuilder.toString();
		
		where += fuzzy==null? " " : " )and orgfullname like '%'||:fuzzy||'%'  ";	
		params.put("fuzzy", fuzzy);

		//取选题类别
		Map<String , Boolean> typeMap = model.getProducttype();
		if(typeMap.get("electronic")==typeMap.get("media")){
			
			Date starttime = model.getStarttime();
			Date endtime = model.getEndtime();
			params.put("starttime", starttime);
			params.put("endtime", endtime);
			
			return experttopicauditDao.getExpertTopicGrid(firstResult, maxResult, where, params, orderby, fields);
		}
		
		if(typeMap.get("electronic")==true){
			
			where += " and iselec=1";
			
			Date starttime = model.getStarttime();
			Date endtime = model.getEndtime();
			params.put("starttime", starttime);
			params.put("endtime", endtime);
			
			return experttopicauditDao.getExpertTopicGrid(firstResult, maxResult, where, params, orderby, fields);
		}else{
			where += " and iselec=0";
			
			Date starttime = model.getStarttime();
			Date endtime = model.getEndtime();
			params.put("starttime", starttime);
			params.put("endtime", endtime);
			
			return experttopicauditDao.getExpertTopicGrid(firstResult, maxResult, where, params, orderby, fields);
		}

	}

}
