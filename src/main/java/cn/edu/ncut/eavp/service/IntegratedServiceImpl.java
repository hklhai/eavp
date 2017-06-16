package cn.edu.ncut.eavp.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ncut.eavp.dao.OrganizationinfoDao;
import cn.edu.ncut.eavp.dao.ProductpublishDao;
import cn.edu.ncut.eavp.dao.PublishinfoDao;
import cn.edu.ncut.eavp.dao.TransferDao;
import cn.edu.ncut.eavp.model.OrganizationinfoObj;
import cn.edu.ncut.eavp.model.TransferObj;
import cn.edu.ncut.eavp.model.assistant.IntegeratedQueryObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

@Transactional
@Service("integratedService")
@Scope("request")
public class IntegratedServiceImpl implements IntegratedService {

	@Autowired
	private OrganizationinfoDao organizationinfoDao;
	@Autowired
	private PublishinfoDao publishinfoDao;
	@Autowired
	private ProductpublishDao productpublishDao;
	@Autowired
	private TransferDao transferDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DatagridResult getGridData(int firstResult, int maxResult,
			String orderby, String[] fields, IntegeratedQueryObj model,
			String orgids) {
		StringBuilder wherebuilder = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		// 作品类型分类
		Map<String, Boolean> productTypeMap = model.getProducttype();
		StringBuilder ptsb = new StringBuilder("and (");
		if (productTypeMap.get("electronic") == true) {
			ptsb.append("or iselec =1 ");
		}
		if (productTypeMap.get("media") == true) {
			ptsb.append("or iselec =0 ");
		}
		ptsb.delete(5, 7);
		ptsb.append(") ");
		if (ptsb.length() > 7)
			wherebuilder.append(ptsb);
		// // 选题类型
		// Map<String, Boolean> yearTypeMap = model.getYeartype();
		// StringBuilder ytsb = new StringBuilder("and (");
		// if (yearTypeMap.get("year") == true) {
		// ytsb.append("or isyear=1 ");
		// }
		// if (yearTypeMap.get("insert") == true) {
		// ytsb.append("or isyear =0 ");
		// }
		// ytsb.delete(5, 7);
		// ytsb.append(") ");
		// if (ytsb.length() > 7)
		// wherebuilder.append(ytsb);
		// 时间条件生成
		StringBuilder timeBuilder = new StringBuilder("");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String starttime = new StringBuilder("to_date('")
				.append(df.format(model.getStarttime()))
				.append("','yyyy-MM-dd') ").toString();
		String endtime = new StringBuilder("to_date('")
				.append(df.format(model.getEndtime()))
				.append("','yyyy-MM-dd') ").toString();
		Map<String, Boolean> timemap = model.getTimetype();
		
		if (timemap.get("create")) {
			if (model.getEndtimechecked()) {
				timeBuilder.append("or (CREATETIME<").append(endtime)
						.append(" )");
			}
			if (model.getStarttimechecked()) {
				timeBuilder.append("and (CREATETIME>=").append(starttime)
						.append(" )");
			}
		}else if(timemap.get("planpublish"))
		{	
			if (model.getStarttimechecked()) {

				timeBuilder.append("or (PUBLISHTIME<").append(endtime).append(" )");

			}
			if (model.getEndtimechecked()) {
				timeBuilder.append("and (PUBLISHTIME>=").append(starttime).append(" )");

			}	
		}

		// if (timemap.get("create")) {
		// if (model.getEndtimechecked()) {
		// timeBuilder.append("or (stagetime<").append(endtime).append(" and stagestatus='100010') ");
		// }
		// if (model.getStarttimechecked()) {
		// timeBuilder.append("or (stagetime>=").append(starttime).append(" and stagestatus='100010') ");
		// }
		// }
		// if (timemap.get("audit")) {
		// if (model.getEndtimechecked()) {
		// timeBuilder.append("or (stagetime<").append(endtime)
		// .append(" and stagestatus='100020') ");
		// }
		// if (model.getStarttimechecked()) {
		// timeBuilder.append("and (stagetime>=").append(starttime)
		// .append(" and stagestatus='100020') ");
		// }
		//
		// }
		// if (timemap.get("generate")) {
		// if (model.getStarttimechecked()) {
		// timeBuilder.append("or (stagetime>=").append(starttime)
		// .append(" and stagestatus='100030') ");
		// }
		// if (model.getEndtimechecked()) {
		// timeBuilder.append("or (stagetime<").append(endtime)
		// .append(" and stagestatus='100030') ");
		// }
		// }
		//
		//
		//

		timeBuilder.delete(0, 2);
		// if (timeBuilder.length() > 0) {
		// List<TransferObj> translist = transferDao.findAll(
		// timeBuilder.toString(), null, null);
		// Set<Long> set = new HashSet<Long>();
		// for (TransferObj obj : translist)
		// set.add(obj.getTbProduct().getProductid());
		// if (set.size() > 0) {
		// wherebuilder.append("and productid in(");
		// for (Long productid : set) {
		// wherebuilder.append(productid + ",");
		// }
		// wherebuilder.delete(wherebuilder.length() - 1,
		// wherebuilder.length());
		// wherebuilder.append(") ");
		// }
		// }

		if (timeBuilder.length() > 0) {
		
			wherebuilder.append(" and ").append(timeBuilder);
		}

		// 关键字生成
		if (model.getKeyword() != null && !"".equals(model.getKeyword().trim())) {
			StringBuilder sb = new StringBuilder("and (");
			Map<String, Boolean> map = model.getKeywordtype();
			String keyword = model.getKeyword();
			if (map.get("abstract")) {
				sb.append("or CONTENTABSTRACT like '%'||").append(":keyword")
						.append("||'%' ");
				params.put("keyword", keyword);
			}
			if (map.get("productname")) {
				sb.append("or TOPICNAME like '%'||").append(":keyword")
						.append("||'%' ");
				params.put("keyword", keyword);
			}
			sb.delete(5, 7);
			sb.append(") ");
			if (sb.length() > 7)
				wherebuilder.append(sb);
		}
		// 编审人员条件生成
		if (model.getThreeauditor() != null
				&& !"".equals(model.getThreeauditor())) {
			StringBuilder sb = new StringBuilder("and (");
			Map<String, Boolean> map = model.getThreeauditortype();
			String threeauditor = model.getThreeauditor();
			if (map.get("trial")) {
				sb.append("or auditonename  like '%'||")
						.append(":threeauditor").append("||'%' ");
				params.put("threeauditor", threeauditor);
			}
			if (map.get("review")) {
				sb.append("or audittwoname  like '%'||")
						.append(":threeauditor").append("||'%' ");
				params.put("threeauditor", threeauditor);

			}
			if (map.get("final")) {
				sb.append("or auditthreename  like '%'||")
						.append(":threeauditor").append("||'%' ");
				params.put("threeauditor", threeauditor);

			}
			sb.delete(5, 7);
			sb.append(") ");
			if (sb.length() > 7)
				wherebuilder.append(sb);
		}
		// 单位条件生成
		if (model.getOrgshortname() != null
				&& !"".equals(model.getOrgshortname())) {

			Map<String, Boolean> map = model.getOrgtype();
			StringBuilder orgbuilder = new StringBuilder();
			orgbuilder.append("and (");
			String orgname = model.getOrgshortname();
			HashMap p = new HashMap<String, Object>();
			p.put("orgname", orgname);
			if (model.getOrgtype().get("issued")) {
				List<OrganizationinfoObj> ol = organizationinfoDao.findAll(
						"ORGFULLNAME like '%'||:orgname||'%'", p, null);
				if (ol.size() > 0) {
					orgbuilder.append("or provinceid in (");
					for (OrganizationinfoObj o : ol) {
						orgbuilder.append(o.getOrgid()).append(",");
					}
					orgbuilder.delete(orgbuilder.length() - 1,
							orgbuilder.length());
					orgbuilder.append(") ");
				}
			}
			if (model.getOrgtype().get("competent")) {
				params.put("orgname", orgname);
				orgbuilder.append("or MANAGENAME like '%'||:orgname||'%' ");
			}
			if (model.getOrgtype().get("host")) {
				params.put("orgname", orgname);
				orgbuilder.append("or SPONSOR like '%'||:orgname||'%' ");
			}
			if (model.getOrgtype().get("press")) {
				params.put("orgname", orgname);
				orgbuilder.append("or PUBLISHNAME like '%'||:orgname||'%' ");
			}
			orgbuilder.delete(5, 7);
			orgbuilder.append(") ");
			if (orgbuilder.length() > 7)
				wherebuilder.append(orgbuilder);
		}
		// 单关键字生成
		if (model.getTopicnum() != null && !"".equals(model.getTopicnum())) {
			wherebuilder.append("and TOPICNUM like '%'||:topicnum||'%' ");
			params.put("topicnum", model.getTopicnum());
		}
		if (model.getAuthor() != null && !"".equals(model.getAuthor())) {
			wherebuilder.append("and AUTHOR like '%'||:author||'%' ");
			params.put("author", model.getAuthor());
		}
		if (model.getStartisbn() != null && !"".equals(model.getStartisbn())) {
			wherebuilder.append("and productisbn >= :startisbn ");
			params.put("startisbn", model.getStartisbn());
		}
		if (model.getEndisbn() != null && !"".equals(model.getEndisbn())) {
			wherebuilder.append("and productisbn <  :endisbn ");
			params.put("endisbn", model.getEndisbn());
		}
		if (model.getUsetype() != null && !"".equals(model.getUsetype())) {
			wherebuilder.append("and USETYPE like '%'||:usetype||'%' ");
			params.put("usetype", model.getUsetype());
		}
		if (model.getPublishmethod() != null
				&& !"".equals(model.getPublishmethod())) {
			wherebuilder
					.append("and PUBLISHMETHOD like '%'||:publishmethod||'%' ");
			params.put("publishmethod", model.getPublishmethod());
		}
		if (model.getReader() != null && !"".equals(model.getReader())) {
			wherebuilder.append("and READER like '%'||:reader||'%' ");
			params.put("reader", model.getReader());
		}
		if (model.getPlanguage() != null && !"".equals(model.getPlanguage())) {
			wherebuilder.append("and PLANGUAGE like '%'||:planguage||'%' ");
			params.put("planguage", model.getPlanguage());
		}
		if (model.getMade() != null && !"".equals(model.getMade())) {
			wherebuilder.append("and MADE like '%'||:made||'%' ");
			params.put("made", model.getMade());
		}
		if (model.getPublishtype() != null
				&& !"".equals(model.getPublishtype())) {
			wherebuilder.append("and PUBLISHTYPE like '%'||:publishtype||'%' ");
			params.put("publishtype", model.getPublishtype());
		}
		if (model.getContenttype() != null
				&& !"".equals(model.getContenttype())) {
			wherebuilder.append("and CONTENTTYPE like '%'||:contenttype||'%' ");
			params.put("contenttype", model.getContenttype());
		}
		if (model.getImportant() != null && !"".equals(model.getImportant())) {
			wherebuilder.append("and IMPORTANT like '%'||:important||'%' ");
			params.put("important", model.getImportant());
		}
		if (model.getImportantindex() != null
				&& !"".equals(model.getImportantindex())) {
			wherebuilder
					.append("and IMPORTANTINDEX like '%'||:importantindex||'%' ");
			params.put("importantindex", model.getImportantindex());
		}
		if (model.getOtherimportant() != null
				&& !"".equals(model.getOtherimportant())) {
			wherebuilder
					.append("and OTHERIMPORTANT like '%'||:otherimportant||'%' ");
			params.put("otherimportant", model.getOtherimportant());
		}
		if (model.getStopflag().get("yes")) {
			wherebuilder.append("and productstatus ='100060' ");
		}
		if (model.getPauseflag().get("yes")) {
			wherebuilder.append("and ISSUSPEND = 1 ");
		}
		if (model.getDownloadflag().get("yes")) {
			wherebuilder.append("and productstatus ='100040' ");
		}
		if (model.getCompletionflag().get("yes")) {
			wherebuilder.append("and productstatus ='100050' ");
		}
		// 状态查询
		StringBuilder statusBuilder = new StringBuilder("and (");
		if (model.getAuditstatus().get("ready"))
			statusBuilder.append("or productstatus ='100010' ");
		if (model.getAuditstatus().get("pass"))
			statusBuilder.append("or productstatus ='100020' ");
		if (model.getAuditstatus().get("coding"))
			statusBuilder.append("or productstatus ='100030' ");
		if (model.getAuditstatus().get("returned"))
			statusBuilder.append("or (productstatus ='100011') ");
		statusBuilder.append(") ");
		statusBuilder.delete(5, 7);
		if (statusBuilder.length() > 7) {
			wherebuilder.append(statusBuilder);
		}
		if (orgids != null && !"".equals(orgids)) {
			String[] orgid = orgids.split(";");
			StringBuilder temp = new StringBuilder("(");
			for (int i = 0; i < orgid.length; i++) {
				temp.append(" publishid like  :orgid").append(i)
						.append("||'%' or");
				params.put("orgid" + i, orgid[i]);
			}
			temp.delete(temp.length() - 3, temp.length());
			temp.append(") ");
			wherebuilder.append("and ").append(temp);
		}
		wherebuilder.delete(0, 3);
		return productpublishDao.getProductHasBarcodeGrid(firstResult,
				maxResult, wherebuilder.toString(), params, orderby, fields);
	}
}
