package cn.edu.ncut.eavp.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ncut.eavp.common.basedao.Dao;
import cn.edu.ncut.eavp.common.util.ObjectHelper;
import cn.edu.ncut.eavp.dao.CodetreeinfoDao;
import cn.edu.ncut.eavp.dao.IsbnrecycleDao;
import cn.edu.ncut.eavp.dao.OrganizationinfoDao;
import cn.edu.ncut.eavp.dao.PrefixinfoDao;
import cn.edu.ncut.eavp.dao.ProductDao;
import cn.edu.ncut.eavp.dao.ProductpublishDao;
import cn.edu.ncut.eavp.dao.ProductpublishtmDao;
import cn.edu.ncut.eavp.dao.PublishinfoDao;
import cn.edu.ncut.eavp.dao.StatisticsDao;
import cn.edu.ncut.eavp.dao.StatisticsSumDao;
import cn.edu.ncut.eavp.dao.TopicCountDao;
import cn.edu.ncut.eavp.dao.TopiccountgaDao;
import cn.edu.ncut.eavp.dao.TopicDao;
import cn.edu.ncut.eavp.dao.TopicpublishDao;
import cn.edu.ncut.eavp.dao.TopicserialnumDao;
import cn.edu.ncut.eavp.dao.TopictransDao;
import cn.edu.ncut.eavp.dao.TopictransuserDao;
import cn.edu.ncut.eavp.dao.TransferDao;
import cn.edu.ncut.eavp.dao.TransferuserDao;
import cn.edu.ncut.eavp.model.CodetreeinfoObj;
import cn.edu.ncut.eavp.model.IsbnrecycleObj;
import cn.edu.ncut.eavp.model.OrganizationinfoObj;
import cn.edu.ncut.eavp.model.PrefixinfoObj;
import cn.edu.ncut.eavp.model.ProductObj;
import cn.edu.ncut.eavp.model.ProductisrcObj;
import cn.edu.ncut.eavp.model.PublishinfoObj;
import cn.edu.ncut.eavp.model.TopicObj;
import cn.edu.ncut.eavp.model.TopicserialnumObj;
import cn.edu.ncut.eavp.model.TopictransObj;
import cn.edu.ncut.eavp.model.TransferObj;
import cn.edu.ncut.eavp.model.UserObj;
import cn.edu.ncut.eavp.model.UserroleObj;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.ProductpublishObj;
import cn.edu.ncut.eavp.model.view.ProductpublishtmObj;
import cn.edu.ncut.eavp.model.view.TopiccountObj;
import cn.edu.ncut.eavp.model.view.TopicpublishObj;
import cn.edu.ncut.eavp.model.view.TopictransuserObj;
import cn.edu.ncut.eavp.model.view.TransferuserObj;
import cn.edu.ncut.eavp.service.base.BaseServiceImpl;
import cn.edu.ncut.eavp.webservice.base.ObjectResult;
import cn.edu.ncut.eavp.webservice.base.Result;

/**
 * 为审核提供数据
 * 
 * @author pilchard
 * 
 */
@Transactional
@Service("auditService")
@Scope("request")
public class AuditServiceImpl extends BaseServiceImpl<Object> implements
		AuditService {
	private final static Logger logger = Logger
			.getLogger(AuditServiceImpl.class);
	public final static String SAVED = "100010";		
	public final static String REFUSED = "100011";		
	public final static String CALLBACK = "100012";		
	public final static String WITHDRAW = "100013";
	public final static String AUDITED = "100020";
	public final static String UNDETERMINED = "100021";  //待定
	public final static String REJECTED = "100014";		 //总署退回
	public final static String CODEAUDITED = "100030";
	public final static String CODEDOWNED = "100040";
	public final static String FINISHED = "100050";
	public final static String STOPED = "100060";

	public final static String MODIFY = "100070";
	public final static String PAUSE = "100080";
	@Autowired
	private PublishinfoDao publishinfoDao;
	@Autowired
	private TopicpublishDao topicpublishDao;
	@Autowired
	private TopictransuserDao topictransuserDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ProductpublishDao productpublishDao;
	@Autowired
	private TopicserialnumDao topicserialnumDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private TransferuserDao transferuserDao;
	@Autowired
	private PrefixinfoDao prefixinfoDao;
	@Autowired
	private TransferDao transferDao;
	@Autowired
	private IsbnrecycleDao isbnrecycleDao;
	@Autowired
	private TopictransDao topictransDao;
	@Autowired
	private OrganizationinfoDao organizationinfoDao;
	@Autowired
	private TopiccountgaDao topiccountgaDao;
	
	@Autowired
	private ProductpublishtmDao productpublishtmDao;
	
	@Autowired
	private CodetreeinfoDao codetreeinfoDao;
	
	@Autowired
	private StatisticsDao statisticsDao;
	
	@Autowired
	private StatisticsSumDao statisticsSumDao;

	/*
	 * 省局选题统计列表
	 * 
	 * @see cn.edu.ncut.eavp.service.AuditService#getTopicCountList()
	 */
	@Override
	public String getTopicCountList() {
		BaseModel result;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, Object> countParams = new HashMap<String, Object>();
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String scope = " (";
			String scopecount = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				countParams.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
				scopecount += " o.topiccountId.publishid like:publishid" + i
						+ "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			scopecount = scopecount.substring(0, scopecount.lastIndexOf("or"));
			String publishType = parameters.getParams().get("publishtype");
			String fuzzy = parameters.getParams().get("fuzzy");
			Long year = Long.parseLong(parameters.getParams().get("year"));
			String where = scope + ")";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			String countWhere = scopecount
					+ ") and o.topiccountId.publishtype = :publishType"
					+ " and o.topiccountId.baktime>=:year and o.topiccountId.baktime<:year1 and (o.topiccountId.topicstatus='100020' or o.topiccountId.topicstatus='100030')";

			countParams.put("publishType", Long.parseLong(publishType));
			countParams.put("year", year);
			countParams.put("year1", year + 1);
			if (!fuzzy.equals("")) {
				countParams.put("fuzzy", fuzzy);
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 )";
				countWhere += " and (instr(publishname,:fuzzy)>0 ) ";
				result = publishinfoDao.getTopicCountGridData(firstResult,
						maxResult, where, params, orderby, null, countWhere,
						countParams, null);
			} else
				result = publishinfoDao.getTopicCountGridData(firstResult,
						maxResult, where, params, orderby, null, countWhere,
						countParams, null);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}

	@Override
	public String getTopicCountGAList() {
		BaseModel result;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			params.put("publishtype", Long.parseLong(parameters.getParams().get("publishtype")));
			params.put("baktime",
					Long.parseLong(parameters.getParams().get("year")));
			String orderby = " ORDER BY "
					+ parameters.getParams().get("orderby");
			result = topiccountgaDao.getGridData(firstResult, maxResult,
					"publishtype = :publishtype and baktime = :baktime",
					params, orderby);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}

	/*
	 * 省局选题备案列表
	 * 
	 * @see cn.edu.ncut.eavp.service.AuditService#getBakedTopicList()
	 */
	@Override
	public String getBakedTopicList() {
		BaseModel result;
		try {

			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishid = parameters.getParams().get("publishid");
			String iselec = parameters.getParams().get("publishtype");
			String fuzzy = parameters.getParams().get("fuzzy");
			Long year = Long.parseLong(parameters.getParams().get("year"));
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");
			String where = " publishid =:publishid   and iselec = :iselec"
					+ " and baktime>= :year  and baktime<:year1 and (topicstatus='100020' or topicstatus='100030') ";

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("publishid", publishid);
			parameters.put("iselec", Long.parseLong(iselec));
			parameters.put("year", year);
			parameters.put("year1", year + 1);
			if (!fuzzy.equals("")) {
				parameters.put("fuzzy", fuzzy);
				where += "and (instr(publishname,:fuzzy)>0 or instr(topicnum,:fuzzy)>0 or instr(important,:fuzzy)>0  or instr(importantindex,:fuzzy)>0  or instr(TOPICNAME,:fuzzy)>0 or instr(seriesname,:fuzzy)>0    ) ";
				result = topicpublishDao.getGridData(firstResult, maxResult,
						where, parameters, orderby, null);
			} else
				;
			result = topicpublishDao.getGridData(firstResult, maxResult, where,
					parameters, orderby, null);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}

	/*
	 * 省局选题备案详情
	 * 
	 * @see cn.edu.ncut.eavp.service.AuditService#getBakedTopicDetail()
	 */
	@Override
	public String getBakedTopicDetail() {
		ObjectResult<TopicObj> result = new ObjectResult<TopicObj>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			long id = new Long(frontParams.get("topicid"));
			TopicObj resultObject = topicDao.find(id);
			if (null != resultObject) {
				result.setResultObject(resultObject);
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("getBakedTopicDetail没有查询到数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("getBakedTopicDetail出现错误！");
		}
		return result.asXML(true);
	}

	/*
	 * 省局作品待审核列表
	 * 
	 * @see cn.edu.ncut.eavp.service.AuditService#getProductWaitAuditList()
	 */
	@Override
	public String getProductWaitAuditList() {
		BaseModel result;
		result = getProductList(SAVED);
		return result.asXML(true);
	}

	@Override
	public String getProductById() {
		ObjectResult<ProductObj> result = new ObjectResult<ProductObj>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			long id = new Long(frontParams.get("productid"));
			ProductObj resultObject = productDao.find(id);
			if (resultObject.getTbTopic() != null || resultObject.getIsyear())
				resultObject.setTopicid(resultObject.getTbTopic().getTopicid());
			if (resultObject.getTbProductisrcs() != null) {
				resultObject.setProductisrcs(new ArrayList<ProductisrcObj>());
				for (ProductisrcObj isrc : resultObject.getTbProductisrcs()) {
					resultObject.getProductisrcs().add(isrc);
				}
			}
			result.setResultObject(resultObject);
			result.setType(0);
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询作品信息出现错误！");
		}
		return result.asXML(true);
	}

	@Override
	public String getTransferByProductId() {
		ObjectResult<List<TransferuserObj>> result = new ObjectResult<List<TransferuserObj>>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			long id = new Long(frontParams.get("productid"));
			Map<String, Object> params = new HashMap<String, Object>();
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			params.put("productid", id);
			orderby.put("transferid", "asc");
			List<TransferuserObj> resultObject = transferuserDao.findAll(
					"productid=:productid", params, orderby);
			if (null != resultObject) {
				result.setResultObject(resultObject);
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("没有查询到流转信息数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询流转信息出现错误！");
		}
		return result.asXML(true);
	}

	@Override
	public String getPublishById() {
		ObjectResult<PublishinfoObj> result = new ObjectResult<PublishinfoObj>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			String id = new String(frontParams.get("publishid"));
			PublishinfoObj resultObject = publishinfoDao.find(id);
			if (null != resultObject) {
				result.setResultObject(resultObject);
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("没有查询到出版社数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询出版社数据出现错误！");
		}
		return result.asXML(true);
	}

	@Override
	public String getTopicById() {
		ObjectResult<TopicObj> result = new ObjectResult<TopicObj>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			Long id = new Long(frontParams.get("topicid"));
			TopicObj resultObject = topicDao.find(id);
			if (null != resultObject) {
				result.setResultObject(resultObject);
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("没有查询到年度选题社数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询年度选题数据出现错误！");
		}
		return result.asXML(true);
	}

	@Override
	public String getPrefixByPublishId() {
		ObjectResult<List<PrefixinfoObj>> result = new ObjectResult<List<PrefixinfoObj>>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			String id = new String(frontParams.get("publishid"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("publishid", id);
			List<PrefixinfoObj> resultObject = prefixinfoDao.findAll(
					"publishid=:publishid", params, null);
			for (PrefixinfoObj obj : resultObject) {
				obj.setPublishid(obj.getPublishinfo().getPublishid());
			}
			result.setResultObject(resultObject);
			result.setType(0);
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询前缀数据出现错误！");
		}
		return result.asXML(true);
	}

	@Override
	public String getEarliestTransferByProductId() {
		ObjectResult<TransferObj> result = new ObjectResult<TransferObj>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			String id = new String(frontParams.get("productid"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("productid", id);
			params.put("stagestatus", SAVED);
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("transferid", "asc");
			List<TransferObj> resultObject = transferDao.findAll(
					"productid=:productid and stagestatus=:stagestatus",
					params, orderby);
			if (null != resultObject && resultObject.size() > 0) {
				result.setResultObject(resultObject.get(0));
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("没有查询到前缀数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询前缀数据出现错误！");
		}
		return result.asXML(true);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result auditOneProduct() throws Exception {
		Result result = new Result();
		TransferObj trans = (TransferObj) parameters.getDataObject();
		ProductObj product = productDao.find(trans.getProductid());
		if (product.getProductstatus().equals(SAVED)
				&& (product.getIssuspend() == null || !product.getIssuspend())) {
			product.setProductstatus(trans.getStagestatus());
			trans.setTbProduct(product);
			trans.setProductid(product.getProductid());
			trans.setStagetime(new Date());
			ObjectHelper.beanClone(ProductObj.class, product,
					TransferObj.class, trans);
			transferDao.save(trans);
			result.setMessage("审核成功");
			result.setType(0);
		} else if (product.getIssuspend()) {
			result.setMessage("作品审核失败，该作品已经被总局暂停");
			result.setType(0);
		} else {
			result.setMessage("作品审核失败，该作品已经被出版社撤回或终止！");
			result.setType(0);
		}
		return result;
	}
	//总署退回
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result ZSrefuseProduct() throws Exception {
		Result result = new Result();
		TransferObj trans = (TransferObj)parameters.getDataObject();
		ProductObj product = productDao.find(trans.getProductid());
		if(product.getProductstatus().equals(UNDETERMINED) && (product.getIssuspend() == null || !product.getIssuspend())){
			product.setProductstatus(REJECTED);
			trans.setTbProduct(product);
			trans.setProductid(product.getProductid());
			trans.setStagetime(new Date());
			ObjectHelper.beanClone(ProductObj.class, product,
					TransferObj.class, trans);
			transferDao.save(trans);
			productDao.update(product);
			result.setMessage("退回成功");
			result.setType(0);
		}else if (product.getIssuspend()) {
			result.setMessage("作品退回失败，该作品已经被总局暂停");
			result.setType(0);
		} else {
			result.setMessage("作品退回失败，该作品已经被出版社撤回或终止！");
			result.setType(0);
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result auditListProduct() throws Exception {
		Result result = new Result();
		List<TransferObj> transList = (List<TransferObj>) parameters
				.getDataObject();
		String message = "";
		int type = 0;
		for (TransferObj trans : transList) {
			ProductObj product = productDao.find(trans.getProductid());
			if (product.getProductstatus().equals(SAVED)
					&& (product.getIssuspend() == null || !product
							.getIssuspend())) {
				product.setProductstatus(trans.getStagestatus());
				ObjectHelper.beanClone(ProductObj.class, product,
						TransferObj.class, trans);
				trans.setTbProduct(product);
				trans.setStagetime(new Date());
				productDao.update(product);
				transferDao.save(trans);
				message += "选题编号：" + product.getTopicnum() + "审核成功!\n";
				type = 0;
			} else if (product.getIssuspend()) {
				message += ("选题编号：" + product.getTopicnum() + "作品审核失败，该作品已经被总署暂停\n");
				result.setType(0);
			} else {
				message += ("选题编号：" + product.getTopicnum() + "作品审核失败，该作品已经被出版社撤回或终止！\n");
				result.setType(0);
			}
		}
		result.setMessage(message);
		return result;
	}
	
	
	
	//解除待定
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result removeOneProduct() throws Exception {
		Result result = new Result();
		TransferObj trans = (TransferObj) parameters.getDataObject();
		ProductObj product = productDao.find(trans.getProductid());
		if (product.getProductstatus().equals(UNDETERMINED)
				&& (product.getIssuspend() == null || !product.getIssuspend())) {
			product.setProductstatus(AUDITED);
			productDao.update(product);
			result.setMessage("解除待定成功");
			result.setType(0);
		} else if (product.getIssuspend()) {
			result.setMessage("作品解除待定失败，该作品已经被总局暂停");
			result.setType(0);
		}/*else if(product.getProductstatus().equals(UNDETERMINED))
		{
			result.setMessage("作品解除待定失败，该作品已经处于待定状态！");
			result.setType(0);	
		}*/	
		else {
			result.setMessage("作品解除待定失败，该作品已经被出版社撤回或终止！");
			result.setType(0);
		}
		return result;
	}
	
	//待定
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result undeterminOneProduct() throws Exception {
		Result result = new Result();
		TransferObj trans = (TransferObj) parameters.getDataObject();
		ProductObj product = productDao.find(trans.getProductid());
		if (product.getProductstatus().equals(AUDITED)
				&& (product.getIssuspend() == null || !product.getIssuspend())) {
			product.setProductstatus(UNDETERMINED);
			productDao.update(product);
			result.setMessage("待定成功");
			result.setType(0);
		} else if (product.getIssuspend()) {
			result.setMessage("作品待定失败，该作品已经被总局暂停");
			result.setType(0);
		}else if(product.getProductstatus().equals(UNDETERMINED))
		{
			result.setMessage("作品待定失败，该作品已经处于待定状态！");
			result.setType(0);	
		}	
		else {
			result.setMessage("作品待定失败，该作品已经被出版社撤回或终止！");
			result.setType(0);
		}
		return result;
	}
	
	//待定
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result undeterminListProduct() throws Exception {
		Result result = new Result();
		List<TransferObj> transList = (List<TransferObj>) parameters
				.getDataObject();
		String message = "";
		int type = 0;
		for (TransferObj trans : transList) {
			ProductObj product = productDao.find(trans.getProductid());
			if (product.getProductstatus().equals(AUDITED)
					&& (product.getIssuspend() == null || !product
							.getIssuspend())) {
				product.setProductstatus(UNDETERMINED);	
				productDao.update(product);
				message += "作品编号：" + product.getTopicnum() + "待定成功!\n";
				type = 0;
			} else if (product.getIssuspend()) {
				message += ("作品编号：" + product.getTopicnum() + "作品待定失败，该作品已经被总署暂停\n");
				result.setType(0);
			} else {
				message += ("作品编号：" + product.getTopicnum() + "作品待定失败，该作品已经被出版社撤回或终止！\n");
				result.setType(0);
			}
		}
		result.setMessage(message);
		return result;
	}
	
	
		

	private BaseModel getProductList(String... productstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and iselec =:iselec and (productstatus in (:productstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("productstatus", productstatus);
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0 "
						+ "or instr(importantindex,:fuzzy)>0 or instr(topicnum,:fuzzy)>0  or instr(productisbn,:fuzzy)>0 or instr(seriesname,:fuzzy)>0) ";
				result = productpublishDao.getProductWaitAuditGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = productpublishDao.getProductWaitAuditGrid(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	//tm用户代配号
	private BaseModel getProductWaitBarCode(String... productstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and iselec =:iselec and (productstatus in (:productstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("productstatus", productstatus);
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0 "
						+ "or instr(importantindex,:fuzzy)>0 or instr(topicnum,:fuzzy)>0  or instr(productisbn,:fuzzy)>0 or instr(seriesname,:fuzzy)>0) ";
				result = productpublishtmDao.getProductWaitAuditGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = productpublishtmDao.getProductWaitAuditGrid(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}
	
	
	//待定号
	private BaseModel getProductUndeterminBarcode(String... productstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and iselec =:iselec and (productstatus in (:productstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("productstatus", productstatus);
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0 "
						+ "or instr(importantindex,:fuzzy)>0 or instr(topicnum,:fuzzy)>0  or instr(productisbn,:fuzzy)>0 or instr(seriesname,:fuzzy)>0) ";
				result = productpublishDao.getProductWaitAuditGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = productpublishDao.getProductWaitAuditGrid(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}
		
	
	
	@Override
	public String getProductHasAuditedList() {
		BaseModel result;
		result = getProductList(AUDITED);
		return result.asXML(true);
	}

	//代配号增加一列  省局最后审核时间
	@Override
	public String getProductWaitBarCode() {
		BaseModel result;
		result = getProductWaitBarCode(AUDITED);
		return result.asXML(true);
	}
	
	//待定号
	@Override
	public String getProductUndeterminBarcode() {
		BaseModel result;
		result = getProductUndeterminBarcode(UNDETERMINED);
		return result.asXML(true);
	}
	
	

	@Override
	public String getProductHasBarCode() {
		BaseModel result;
		result = getProductList(CODEAUDITED, CODEDOWNED, FINISHED);
		return result.asXML(true);
	}

	@Override
	public String getProductExitList() {
		BaseModel result;
		result = getProductList(STOPED);
		return result.asXML(true);
	}

	//少左括号       总署已暂停件快速搜索server报错问题          lh			2015-1-29 10:41:55
	@Override
	public String getProductSuspendList() {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += "( publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and iselec =:iselec and issuspend=:issuspend) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("issuspend", true);
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and  (instr(publishname,:fuzzy)>0  or  instr(topicname,:fuzzy)>0  or instr(important,:fuzzy)>0 "
						+ "or instr(importantindex,:fuzzy)>0 or instr(topicnum,:fuzzy)>0  or instr(seriesname,:fuzzy)>0 or instr(productisbn,:fuzzy)>0 )";
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}

	@Override
	public String getProductModifiedList() {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and iselec =:iselec and isModify=:isModify  ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("isModify", true);
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0 "
						+ "or instr(importantindex,:fuzzy)>0 or instr(topicnum,:fuzzy)>0 or instr(seriesname,:fuzzy)>0 or instr(productisbn,:fuzzy)>0  )";
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);

	}

	
	//增加待定显示、总署退回显示       lh    2015年4月9日15:31:19
	@Override
	public String getProductTraceList() {
		//增加待审核状态
		return getYearTraceProductList(SAVED,REFUSED, CALLBACK, WITHDRAW,AUDITED,
				CODEAUDITED, CODEDOWNED, FINISHED, STOPED,UNDETERMINED,REJECTED).asXML(true);
	}

	@Override
	public Result backAudit() {
		Result result = new Result();
		TransferObj trans = (TransferObj) parameters.getDataObject();
		ProductObj product = productDao.find(trans.getProductid());
		if (product.getProductstatus().equals(AUDITED)
				&& (product.getIssuspend() == null || !product.getIssuspend())) {

			product.setProductstatus(SAVED);
			trans.setTbProduct(product);
			trans.setProductid(product.getProductid());
			trans.setStagetime(new Date());
			transferDao.save(trans);
			result.setMessage("撤回审核成功");
			result.setType(0);
		} else if (product.getIssuspend()) {
			result.setMessage("作品审核失败，该作品已经被总署暂停");
			result.setType(0);
		} else {
			result.setMessage("作品审核失败，该作品已经配码，无法撤回审核！");
			result.setType(0);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result auditBarcode() throws Exception {
		Result result = new Result();
		TransferObj trans = (TransferObj) parameters.getDataObject();
		result.setMessage(sendOneCode(trans, false));
		result.setType(0);
		return result;
	}

	// 计算isbn
	private String computerIsbn(String prefix, Long currentnum) {
		String result = prefix;
		String suffer = String.valueOf(currentnum);
		prefix = prefix.replaceAll("-", "");
		// 补位
		int fill = 12 - prefix.length();
		for (int i = 1; i < fill; i++) {
			if (suffer.length() < fill) {
				suffer = '0' + suffer;
			}
		}
		String partIsbn = prefix + suffer;
		result = result + "-" + suffer;
		int total = 0;
		for (int i = 1; i <= partIsbn.length(); i++) {
			if (i % 2 == 0) {
				char c = partIsbn.charAt(i - 1);
				total = total + Integer.parseInt(c + "") * 3;
			} else {
				char c = partIsbn.charAt(i - 1);
				total = total + Integer.parseInt(c + "") * 1;
			}
		}
		int tail = 0;
		if (10 - total % 10 == 10)
			tail = 0;
		else
			tail = 10 - total % 10;
		return result + "-" + tail;

	}

	// 发号
	private void sendCode(PrefixinfoObj prefix, ProductObj product,
			TransferObj trans) throws Exception {
		String isbn = computerIsbn(prefix.getPrefixname(),
				prefix.getCurstartcode());
		product.setProductisbn(isbn);
		product.setProductstatus(trans.getStagestatus());
		ObjectHelper.beanClone(ProductObj.class, product, TransferObj.class,
				trans);
		trans.setTbProduct(product);
		prefix.setCurstartcode(prefix.getCurstartcode() + 1);
		if (prefix.getCurstartcode() > prefix.getEndcode()
				- prefix.getStartcode()) {
			prefix.setPrefixstatus((long) 0);
			prefix.setSuspenddate(new Date());
		}
		prefixinfoDao.update(prefix);
		productDao.update(product);
		trans.setTbProduct(product);
		trans.setStagetime(new Date());
		transferDao.save(trans);
	}

	/**
	 * 
	 * @param trans
	 * @param batch
	 *            批量过来的都是默认可以发号的，
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private String sendOneCode(TransferObj trans, Boolean batch)
			throws Exception {
		String message = "";
		ProductObj product = productDao.find(trans.getProductid());
		if (!batch && trans.getStagestatus().equals(WITHDRAW)) {
			product.setProductstatus(trans.getStagestatus());
			ObjectHelper.beanClone(ProductObj.class, product,
					TransferObj.class, trans);
			productDao.update(product);
			trans.setTbProduct(product);
			trans.setStagetime(new Date());
			transferDao.save(trans);
			return "退回成功";
		}

		if ((product.getProductstatus().equals(AUDITED)&& (product.getIssuspend() == null || !product.getIssuspend())&& (product.getProductisbn() == null || product.getProductisbn().endsWith("")))
		  ||(product.getProductstatus().equals(UNDETERMINED)) && (product.getIssuspend() == null || !product.getIssuspend())&& (product.getProductisbn() == null || product.getProductisbn().endsWith(""))) 
		{
			// 查询是否有可用回收号
			// 如果有就发回收号
			Boolean iselec = product.getIselec();
			Map<String, Object> isbnparams = new HashMap<String, Object>();
			isbnparams.put("publishid", product.getPublishid());
			isbnparams.put("isbnstatus", false);
			isbnparams.put("isbntype", iselec);
			List<IsbnrecycleObj> recycleList = isbnrecycleDao
					.findAll(
							"publishid=:publishid and isbnstatus=:isbnstatus and isbntype=:isbntype",
							isbnparams, null);
			if (recycleList.size() > 0) {
				IsbnrecycleObj isbnObj = recycleList.get(0);
				product.setProductisbn(isbnObj.getProductisbn());
				product.setProductstatus(trans.getStagestatus());
				ObjectHelper.beanClone(ProductObj.class, product,
						TransferObj.class, trans);
				trans.setTbProduct(product);
				isbnObj.setIsbnstatus(true);
				isbnObj.setCreatedate(new Date());
				isbnObj.setTbProduct(product);
				isbnrecycleDao.update(isbnObj);
				productDao.update(product);
				trans.setStagetime(new Date());
				transferDao.save(trans);
				message += "选题编号：" + product.getTopicnum() + "作品发号成功\n";
			} else {
				// 没有回收号发新号，取新号
				Map<String, Object> prefixparams = new HashMap<String, Object>();
				prefixparams.put("prefixtype", iselec);
				prefixparams.put("publishid", product.getPublishid());
				List<PrefixinfoObj> prefixInfoCurrent = prefixinfoDao
						.findAll(
								"publishid=:publishid and prefixtype=:prefixtype and prefixstatus =1 and stopstatus=0 ",
								prefixparams, null);
				List<PrefixinfoObj> prefixInfoNoUse = prefixinfoDao
						.findAll(
								"publishid=:publishid and prefixtype=:prefixtype and prefixstatus =2 and stopstatus=0 ",
								prefixparams, null);
				// 当前号可以发
				if (prefixInfoCurrent.size() > 0) {
					PrefixinfoObj prefix = prefixInfoCurrent.get(0);
					sendCode(prefix, product, trans);
					message += "选题编号：" + product.getTopicnum() + "作品发号成功\n";

				} else if (prefixInfoNoUse.size() > 0) {
					PrefixinfoObj prefix = prefixInfoNoUse.get(0);
					prefix.setPrefixstatus((long) 1);
					prefix.setStartdate(new Date());
					sendCode(prefix, product, trans);
					message += "选题编号：" + product.getTopicnum() + "作品发号成功\n";
				} else {
					message += "选题编号：" + product.getTopicnum()
							+ "作品发号失败，该出版社没有可以分配的前缀，请添加前缀！\n";

				}
			}

		} else if (product.getIssuspend()) {
			message += "选题编号：" + product.getTopicnum() + "作品发号失败，该作品已经被总署暂停\n";

		} else {
			message += "选题编号：" + product.getTopicnum()
					+ "作品发号失败，该作品已经被省局撤回或出版社终止！\n";

		}
		return message;
	}

	@Override
	public Result batchAuditBarcode() throws Exception {
		Result result = new Result();
		@SuppressWarnings("unchecked")
		List<TransferObj> transList = (List<TransferObj>) parameters
				.getDataObject();
		String message = "";
		for (TransferObj obj : transList) {
			message += sendOneCode(obj, true);
		}
		if ("".equals(message))
			message = "没有需要配发的书号！";
		result.setMessage(message);
		result.setType(0);
		return result;
	}

	@Override
	public String getProductHasBarcode() {
		return getYearProductBarcodeList(CODEAUDITED, CODEDOWNED, FINISHED)
				.asXML(true);
	}

	private BaseModel getYearProductBarcodeList(String... productstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			String year = parameters.getParams().get("year");

			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and createtime>=to_date(:year1,'yyyy-MM-dd') and createtime<to_date(:year2,'yyyy-MM-dd') and iselec =:iselec and (productstatus in (:productstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("productstatus", productstatus);
			params.put("year1", year + "-01-01");
			params.put("year2", (Integer.parseInt(year) + 1) + "-01-01");
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0"
						+ "or instr(importantindex,:fuzzy)>0 or instr(topicnum,:fuzzy)>0 or instr(productisbn,:fuzzy)>0 or instr(seriesname,:fuzzy)>0 )  ";
				result = productpublishDao.getProductHasBarcodeGrid(
						firstResult, maxResult, where, params, orderby, null);
			} else
				result = productpublishDao.getProductHasBarcodeGrid(
						firstResult, maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	private BaseModel getYearTraceProductList(String... productstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			String year = parameters.getParams().get("year");

			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and createtime>=to_date(:year1,'yyyy-MM-dd') and createtime<to_date(:year2,'yyyy-MM-dd') and iselec =:iselec and (productstatus in (:productstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("productstatus", productstatus);
			params.put("year1", year + "-01-01");
			params.put("year2", (Integer.parseInt(year) + 1) + "-01-01");
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0"
						+ " or instr(importantindex,:fuzzy)>0  or  instr(seriesname,:fuzzy)>0   or instr(topicnum,:fuzzy)>0 or instr(productisbn,:fuzzy)>0)   ";
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	@Override
	public String getTopiccountByIds() {
		ObjectResult<List<TopiccountObj>> result = new ObjectResult<List<TopiccountObj>>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, Object> countParams = new HashMap<String, Object>();
			String publishids = parameters.getParams().get("publishids");
			String orgids = parameters.getParams().get("orgids");
			String topicstatus = parameters.getParams().get("topicstatus");

			String scope = " (";
			String scopecount = " (";
			String[] orgid = orgids.split(";");

			for (int i = 0; i < orgid.length; i++) {
				params.put("orgid" + i, orgid[i]);
				countParams.put("orgid" + i, orgid[i]);
				scope += " publishid like:orgid" + i + "||'%' or ";
				scopecount += " o.topiccountId.publishid like:orgid" + i
						+ "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			scopecount = scopecount.substring(0, scopecount.lastIndexOf("or"));
			String publishType = parameters.getParams().get("publishtype");
			Long year = Long.parseLong(parameters.getParams().get("year"));
			String where = scope + ")  ";

			String countWhere = scopecount
					+ ") and o.topiccountId.publishtype = :publishType"
					+ " and o.topiccountId.baktime>=:year and o.topiccountId.baktime<:year1 and (o.topiccountId.topicstatus='100020' or o.topiccountId.topicstatus='100030')";
			countParams.put("topicstatus", topicstatus);
			countParams.put("publishType", Long.parseLong(publishType));
			countParams.put("year", year);
			countParams.put("year1", year + 1);
			if (publishids != null && !publishids.equals("")) {
				String[] publishid = publishids.split(";");
				if (publishid.length > 0) {
					String tempids = "";
					for (String temp : publishid) {
						tempids += "'" + temp + "',";
					}
					tempids = tempids.substring(0, tempids.length() - 1);
					countWhere += " and o.topiccountId.publishid in ("
							+ tempids + ")";
					where += " and publishid in (" + tempids + ")";
				}

			}
			List<TopiccountObj> list = publishinfoDao.getTopiccountByIds(where,
					params, null, null, countWhere, countParams, null,
					Long.parseLong(publishType));
			result.setType(0);
			result.setResultObject(list);
		} catch (Exception e) {
			logger.error(e);

			result.setMessage("获取列表异常！");
			result.setType(1);

		}
		return result.asXML(true);
	}

	@Override
	public String getExportTopics() {
		ObjectResult<List<TopicpublishObj>> result = new ObjectResult<List<TopicpublishObj>>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String topicids = parameters.getParams().get("topicids");
			String orgids = parameters.getParams().get("orgids");
			String topicstatus = parameters.getParams().get("topicstatus");

			String scope = " (";
			String[] orgid = orgids.split(";");

			for (int i = 0; i < orgid.length; i++) {
				params.put("orgid" + i, orgid[i]);
				scope += " publishid like:orgid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String publishType = parameters.getParams().get("publishtype");
			String year = parameters.getParams().get("year");

			String where = "";
			where += scope + ") and iselec = :publishType"
					+ " and topicstatus=:topicstatus ";
			if (year != null) {
				where += "	 and baktime>= :year  and baktime <:year1 ";
				params.put("year", Long.parseLong(year));
				params.put("year1", (Long.parseLong(year) + 1));
			}
			params.put("publishType", Long.parseLong(publishType));
			params.put("topicstatus", topicstatus);
			if (topicids != null && !topicids.equals("")) {
				String[] topicid = topicids.split(";");
				if (topicid.length > 0) {
					String tempids = "";
					for (String temp : topicid) {
						tempids += "'" + temp + "',";
					}
					tempids = tempids.substring(0, tempids.length() - 1);
					where += " and topicid in (" + tempids + ")";
				}

			}
			List<TopicpublishObj> list = topicpublishDao.findAll(where, params,
					null);
			result.setType(0);
			result.setResultObject(list);
		} catch (Exception e) {
			logger.error(e);

			result.setMessage("获取列表异常！");
			result.setType(1);

		}
		return result.asXML(true);
	}

	@Override
	public String getExportProduct() {
		ObjectResult<List<ProductpublishObj>> result = new ObjectResult<List<ProductpublishObj>>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String productids = parameters.getParams().get("productids");
			String orgids = parameters.getParams().get("orgids");
			String productstatus = parameters.getParams().get("productstatus");

			String scope = " (";
			String[] orgid = orgids.split(";");

			for (int i = 0; i < orgid.length; i++) {
				params.put("orgid" + i, orgid[i]);
				scope += " publishid like:orgid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String publishType = parameters.getParams().get("publishtype");
			String year = parameters.getParams().get("year");

			String where = "";
			where += scope
					+ ") and iselec = :publishType"
					+ " and createtime>= :year  and createtime <:year1 and productstatus=:productstatus ";
			params.put("publishType", Long.parseLong(publishType));
			params.put("year", Long.parseLong(year));
			params.put("year1", (Long.parseLong(year) + 1));
			params.put("productstatus", productstatus);
			if (productids != null && !productids.equals("")) {
				String[] productid = productids.split(";");
				if (productid.length > 0) {
					String tempids = "";
					for (String temp : productid) {
						tempids += "'" + temp + "',";
					}
					tempids = tempids.substring(0, tempids.length() - 1);
					where += " and productid in (" + tempids + ")";
				}

			}
			List<ProductpublishObj> list = productpublishDao.findAll(where,
					params, null);
			result.setType(0);
			result.setResultObject(list);
		} catch (Exception e) {
			logger.error(e);

			result.setMessage("获取列表异常！");
			result.setType(1);

		}
		return result.asXML(true);
	}

	@Override
	public String getAllProductByStatus() {
		ObjectResult<List<ProductObj>> result = new ObjectResult<List<ProductObj>>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			String orgids = parameters.getParams().get("dataAccess");
			String year = parameters.getParams().get("year");
			String scope = " (";
			String[] orgid = orgids.split(";");

			for (int i = 0; i < orgid.length; i++) {
				params.put("orgid" + i, orgid[i]);
				scope += " publishid like:orgid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String publishType = parameters.getParams().get("publishtype");
			String status = parameters.getParams().get("status");

			String where = "";
			where += scope + ") and iselec = :publishType"
					+ " and productstatus= :status  ";

			if (year != null && !year.equals("")) {
				params.put("year", year + "-01-01");
				params.put("year1", (Integer.parseInt(year) + 1) + "-01-01");
				where += " and createtime>=to_date(:year,'yyyy-MM-dd') and createtime<to_date(:year1,'yyyy-MM-dd')";
			}
			params.put("publishType", Long.parseLong(publishType));
			List<ProductObj> list = productDao.findAll(where, params, null);
			result.setType(0);
			result.setResultObject(list);
		} catch (Exception e) {
			logger.error(e);

			result.setMessage("获取列表异常！");
			result.setType(1);

		}
		return result.asXML(true);
	}

	@Override
	public String getAllTopicByStatus() {
		ObjectResult<List<TopicObj>> result = new ObjectResult<List<TopicObj>>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			String orgids = parameters.getParams().get("dataAccess");
			String year = parameters.getParams().get("year");
			String scope = " (";
			String[] orgid = orgids.split(";");

			for (int i = 0; i < orgid.length; i++) {
				params.put("orgid" + i, orgid[i]);
				scope += " publishid like:orgid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String publishType = parameters.getParams().get("publishtype");
			String status = parameters.getParams().get("status");

			String where = "";
			where += scope + ") and iselec = :publishType"
					+ " and topicstatus= :status  ";

			if (year != null && !year.equals("")) {
				params.put("year", year);
				params.put("year1", (Integer.parseInt(year) + 1));
				where += " and topicyear>=:year and  topicyear<:year1";
			}
			params.put("publishType", Long.parseLong(publishType));
			List<TopicObj> list = topicDao.findAll(where, params, null);
			result.setType(0);
			result.setResultObject(list);
		} catch (Exception e) {
			logger.error(e);

			result.setMessage("获取列表异常！");
			result.setType(1);

		}
		return result.asXML(true);
	}

	@Override
	public Result auditOneTopic() {
		TopictransObj trans = (TopictransObj) parameters.getDataObject();
		TopicObj topic = topicDao.find(trans.getTopicid());
		return auditTopic(topic, trans);

	}

	private Result auditTopic(TopicObj topic, TopictransObj trans) {
		Result result = new Result();
		try {
			if (topic.getTopicstatus().equals(SAVED)) {
				topic.setTopicstatus(trans.getStagestatus());
				trans.setTopicid(topic.getTopicid());
				trans.setStagetime(new Date());
				if (trans.getStagestatus().equals(AUDITED)) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("iselec", topic.getIselec());
					params.put("publishid", topic.getPublishid());
					if (topic.getTopicyear() != null)
						params.put("year", topic.getTopicyear());
					else {
						Date date = new Date();
						Calendar c = Calendar.getInstance();
						c.setTime(date);
						Calendar c1 = Calendar.getInstance();
						c1.set(c.get(Calendar.YEAR), 6, 1);
						if (topic.getIsyear() && c.after(c1)) {
							params.put("year", c.get(Calendar.YEAR) + 1);
							topic.setTopicyear((long) c.get(Calendar.YEAR) + 1);
						} else {
							params.put("year", c.get(Calendar.YEAR));
							topic.setTopicyear((long) c.get(Calendar.YEAR));
						}
					}
					// 配发topicnum
					List<TopicserialnumObj> list = topicserialnumDao
							.findAll(
									"publishid=:publishid and iselec=:iselec and year=:year",
									params, null);
					OrganizationinfoObj obj = organizationinfoDao.find(topic
							.getPublishid());
					String topicnum = obj.getOrgshortname() + "-"
							+ (topic.getIselec() ? "d" : "y")
							+ params.get("year");
					if (list.size() > 0) {
						TopicserialnumObj tn = list.get(0);
						StringBuilder num = new StringBuilder();
						num.append(tn.getSerialnum());
						while (num.length() < 4) {
							num.insert(0, "0");
						}
						topicnum += num.toString();
						topic.setTopicnum(topicnum);
						tn.setSerialnum(tn.getSerialnum() + 1);
						topicserialnumDao.update(tn);
					} else {
						TopicserialnumObj tn = new TopicserialnumObj();
						tn.setIselec(topic.getIselec());
						tn.setPublishid(topic.getPublishid());
						tn.setSerialnum((long) 2);
						tn.setYear((Long) params.get("year"));
						topicserialnumDao.save(tn);
						topicnum = obj.getOrgshortname() + "-"
								+ (topic.getIselec() ? "d" : "y")
								+ params.get("year") + "0001";
						topic.setTopicnum(topicnum);
					}
				}
				topicDao.update(topic);
				ObjectHelper.beanClone(TopicObj.class, topic,
						TopictransObj.class, trans);
				topictransDao.save(trans);
				result.setMessage("审核成功");
				result.setType(0);
			}
		} catch (Exception e) {
			result.setType(0);
			result.setMessage("审核失败！");
		}
		return result;
	}

	@Override
	public Result auditListTopic() {
		Result result = null;
		@SuppressWarnings("unchecked")
		List<TopictransObj> transList = (List<TopictransObj>) parameters
				.getDataObject();
		for (TopictransObj trans : transList) {
			TopicObj topic = topicDao.find(trans.getTopicid());
			result = auditTopic(topic, trans);
		}
		if (result == null) {
			result = new Result();
			result.setType(0);
			result.setMessage("审核失败！");
		}
		return result;
	}

	@Override
	public String getTopicWaitAuditList() {
		BaseModel result;
		result = getTopicYearList(SAVED);
		return result.asXML(true);
	}

	@Override
	public String getTransferByTopicId() {
		ObjectResult<List<TopictransuserObj>> result = new ObjectResult<List<TopictransuserObj>>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			long id = new Long(frontParams.get("topicid"));
			Map<String, Object> params = new HashMap<String, Object>();
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			params.put("topicid", id);
			orderby.put("topictransferid", "asc");
			List<TopictransuserObj> resultObject = topictransuserDao.findAll(
					"topicid=:topicid", params, orderby);
			if (null != resultObject) {
				result.setResultObject(resultObject);
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("没有查询到流转信息数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询流转信息出现错误！");
		}
		return result.asXML(true);
	}
	private BaseModel getTopicYearList(String... topicstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String isyear=parameters.getParams().get("isyear");
			String fuzzy = parameters.getParams().get("fuzzy");
			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and iselec =:iselec and isyear=:isyear and (topicstatus in (:topicstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("isyear", new BigDecimal(isyear));
			params.put("topicstatus", topicstatus);
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0"
						+ "or instr(importantindex,:fuzzy)>0  or instr(topicnum,:fuzzy)>0  or instr(seriesname,:fuzzy)>0 )";
				result = topicpublishDao.getPublishGridData(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = topicpublishDao.getPublishGridData(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}
	private BaseModel getTopicList(String... topicstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and iselec =:iselec and (topicstatus in (:topicstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("topicstatus", topicstatus);
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0"
						+ "or instr(importantindex,:fuzzy)>0  or instr(topicnum,:fuzzy)>0  or instr(seriesname,:fuzzy)>0   )";
				result = topicpublishDao.getPublishGridData(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = topicpublishDao.getPublishGridData(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	@Override
	@Transactional
	public String suspendProduct() {
		Result result = new Result();
		suspendRecoverOperation(parameters.getParams(), true);
		result.setType(0);
		result.setMessage("暂停作品成功！");
		return result.asXML(true);
	}

	@Override
	@Transactional
	public String recoverProduct() {
		Result result = new Result();
		suspendRecoverOperation(parameters.getParams(), false);
		result.setType(0);
		result.setMessage("恢复作品成功！");
		return result.asXML(true);
	}

	private void suspendRecoverOperation(Map<String, String> map,
			boolean isSuspend) {
		// 更新作品状态
		ProductObj pObj = productDao.find(Long.parseLong(map.get("productid")));
		pObj.setIssuspend(isSuspend);
		productDao.update(pObj);
		// 记流转记录
		TransferObj tObj = new TransferObj();
		tObj.setTbProduct(pObj);
		tObj.setIssuspend(isSuspend);
		tObj.setIslocked(false);
		tObj.setIsyear(pObj.getIsyear());
		tObj.setIselec(pObj.getIselec());
		tObj.setStagereason(map.get("stagereason"));
		tObj.setStagestatus(PAUSE);
		tObj.setStageuser(Long.parseLong(map.get("userid")));
		tObj.setStagetime(new Date());
		transferDao.save(tObj);
	}

	@Override
	public String getToSuspendList() {
		return suspendList(REFUSED, CALLBACK, WITHDRAW, CODEAUDITED,
				CODEDOWNED).asXML(true);
	}

	private BaseModel suspendList(String... productstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishids = parameters.getParams().get("orgid");
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			String year = parameters.getParams().get("year");

			Map<String, Object> params = new HashMap<String, Object>();
			String scope = " (";
			String[] publishid = publishids.split(";");
			for (int i = 0; i < publishid.length; i++) {
				params.put("publishid" + i, publishid[i]);
				scope += " publishid like:publishid" + i + "||'%' or ";
			}
			scope = scope.substring(0, scope.lastIndexOf("or"));
			String where = scope
					+ ") and issuspend = 0 and createtime>=to_date(:year1,'yyyy-MM-dd') and createtime<to_date(:year2,'yyyy-MM-dd') and iselec =:iselec and (productstatus in (:productstatus)) ";
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			params.put("iselec", Long.parseLong(iselec));
			params.put("productstatus", productstatus);
			params.put("year1", year + "-01-01");
			params.put("year2", (Integer.parseInt(year) + 1) + "-01-01");
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(important,:fuzzy)>0"
						+ "or instr(importantindex,:fuzzy)>0  or instr(topicnum,:fuzzy)>0  or instr(seriesname,:fuzzy)>0   )   ";
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else
				result = productpublishDao.getProductTraceGrid(firstResult,
						maxResult, where, params, orderby, null);
		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}
	
	//获取年度选题统计分析
	@Override
	public String getYearTopicStatistics() {
		BaseModel result = null;
		try{
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String year = parameters.getParams().get("year");
			
			String type = parameters.getParams().get("type");
			
			Map<String , Object> params = new HashMap<String, Object>();
 			params.put("year", Integer.parseInt(year));
 			params.put("type", type);
			String where = " topicyear =:year and forquery =:type ";
			String orderby = " ORDER BY " + (String) parameters.getParams().get("orderby");
			result = statisticsDao.getYearTopicStatisticsGrid(firstResult,maxResult,where,params,orderby,null);
		}catch(Exception e){
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}
		
	//获取年度选题统计分析总
	@Override
	public String getYearTopicStatisticsSum() {
		BaseModel result = null;
		try{
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String year = parameters.getParams().get("year");
			
			Map<String , Object> params = new HashMap<String, Object>();
			params.put("year",Integer.parseInt(year));
			String where = " topicyear =:year ";
			result = statisticsSumDao.getYearTopicStatisticsSumGrid(firstResult,maxResult,where,params,null,null);
		}catch(Exception e){
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}
	
	//获取代码树顶级
	@Override
	public String getTopCodeTree() {
		ObjectResult<List<CodetreeinfoObj>> result = new ObjectResult<List<CodetreeinfoObj>>();
		try{
			String where = " o.parentid=0 ";
			List<CodetreeinfoObj> list = codetreeinfoDao.findAll(where, null, null);
			result.setType(0);
			result.setResultObject(list);
		}catch(Exception e){
			logger.error(e);
			result.setMessage("获取异常");
			result.setType(1);
		}
		return result.asXML(true);
	}
	
}
