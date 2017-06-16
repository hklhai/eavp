package cn.edu.ncut.eavp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.time.DateUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ncut.eavp.model.IsbnrecycleObj;
import cn.edu.ncut.eavp.model.PrefixinfoObj;
import cn.edu.ncut.eavp.model.ProductObj;
import cn.edu.ncut.eavp.model.ProductisrcObj;
import cn.edu.ncut.eavp.model.PublishinfoObj;
import cn.edu.ncut.eavp.model.RoleObj;
import cn.edu.ncut.eavp.model.StatisticsObj;
import cn.edu.ncut.eavp.model.TopicObj;
import cn.edu.ncut.eavp.model.TopictransObj;
import cn.edu.ncut.eavp.model.TransferObj;
import cn.edu.ncut.eavp.model.UserObj;
import cn.edu.ncut.eavp.model.assistant.CountObj;
import cn.edu.ncut.eavp.model.assistant.ProductInfoAssistantObj;
import cn.edu.ncut.eavp.model.assistant.TopicInfoAssistantObj;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.ExpertAuditObj;
import cn.edu.ncut.eavp.model.view.ExpertTopicAuditObj;
import cn.edu.ncut.eavp.model.view.IntegratedExportObj;
import cn.edu.ncut.eavp.model.view.ProductpublishObj;
import cn.edu.ncut.eavp.model.view.TopicpublishObj;
import cn.edu.ncut.eavp.model.view.TransferuserObj;
import cn.edu.ncut.eavp.common.util.ObjectHelper;
import cn.edu.ncut.eavp.dao.ExpertAuditDao;
import cn.edu.ncut.eavp.dao.ExpertTopicAuditDao;
import cn.edu.ncut.eavp.dao.IntegratedExportDao;
import cn.edu.ncut.eavp.dao.IsbnrecycleDao;
import cn.edu.ncut.eavp.dao.PrefixinfoDao;
import cn.edu.ncut.eavp.dao.ProductDao;
import cn.edu.ncut.eavp.dao.ProductpublishDao;
import cn.edu.ncut.eavp.dao.PublishinfoDao;
import cn.edu.ncut.eavp.dao.StatisticsDao;
import cn.edu.ncut.eavp.dao.TopicDao;
import cn.edu.ncut.eavp.dao.TopicpublishDao;
import cn.edu.ncut.eavp.dao.TopictransDao;
import cn.edu.ncut.eavp.dao.TransferDao;
import cn.edu.ncut.eavp.dao.TransferuserDao;
import cn.edu.ncut.eavp.dao.UserDao;
import cn.edu.ncut.eavp.service.base.BaseServiceImpl;
import cn.edu.ncut.eavp.webservice.base.ObjectResult;
import cn.edu.ncut.eavp.webservice.base.Result;

/**
 * @Description SystemServiceImpl用来实现所有系统功能相关的webservice的业务
 *              使用场合：SystemServiceImpl调用基本的Dao为系统功能提供数据。
 */
@Transactional
@Service("applyService")
@Scope("request")
public class ApplyServiceImpl extends BaseServiceImpl<Object> implements
		ApplyService {

	private final static Logger logger = Logger
			.getLogger(ApplyServiceImpl.class);

	public final static String SAVED = "100010";
	public final static String REFUSED = "100011";
	public final static String CALLBACK = "100012";
	public final static String AUDITED = "100020";
	public final static String CODEAUDITED = "100030";
	public final static String CODEDOWNED = "100040";
	public final static String FINISHED = "100050";
	public final static String STOPED = "100060";
	public final static String REJECTED = "100014";
	public final static String UNDETERMINED = "100021"; // 待定
	public final static String MODIFY = "100070";
	public final static String PAUSE = "100080";

	public final static String pauseReason = "作品已被总局暂停，无法操作！\n";

	public final static String stopReason = "作品已备案或已终止，无法进行操作！\n";

	@Autowired
	private UserDao userDao;

	@Autowired
	private TopicDao topicDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductpublishDao productpublishDao;

	@Autowired
	private PublishinfoDao publishinfoDao;

	@Autowired
	private TransferDao transferDao;

	@Autowired
	private IsbnrecycleDao isbnrecycleDao;

	@Autowired
	private TransferuserDao transferuserDao;

	@Autowired
	private PrefixinfoDao prefixinfoDao;

	@Autowired
	private TopicpublishDao topicpublishDao;

	@Autowired
	private TopictransDao topictransDao;

	@Autowired
	private StatisticsDao statisticsDao;
	
	@Autowired
	private ExpertAuditDao expertAuditDao;
	
	@Autowired
	private ExpertTopicAuditDao expertTopicAuditDao;
	
	@Autowired
	private IntegratedExportDao integratedExportDao;

	@Override
	public void topicApply() throws Exception {
		TopicObj topicObj = (TopicObj) parameters.getDataObject();
		this.topicApply(topicObj, getUser());
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean topicApplyBeach() throws Exception {
		List<TopicObj> topics = (List<TopicObj>) parameters.getDataObject();
		UserObj userObj = getUser();
		for (TopicObj topicObj : topics) {
			this.topicApply(topicObj, userObj);
		}
		return true;
	}

	@Override
	public String topicRead() {
		Map<String, String> map = parameters.getParams();
		TopicObj topicObj = null;
		if (map.containsKey("topicid")) {
			topicObj = topicDao.find(Long.parseLong(map.get("topicid")));
		} else if (map.containsKey("topicnumpart")) {
			String topicnum = new StringBuilder(getShortName()).append("-%-n-")
					.append(map.get("topicnumpart")).toString();
			Map<String, String> paras = new HashMap<String, String>();
			paras.put("topicnum", topicnum);
			List<TopicObj> topics = topicDao.findAll("topicnum like :topicnum",
					paras, null);
			topicObj = topics.size() > 0 ? topics.get(0) : null;
		}

		ObjectResult<TopicObj> result = new ObjectResult<TopicObj>();
		result.setType(0);
		result.setResultObject(topicObj);
		return result.asXML(true);
	}

	@Override
	public String topicInfoRead() {
		Map<String, String> map = parameters.getParams();
		TopicInfoAssistantObj topicInfo = new TopicInfoAssistantObj();
		if (map.containsKey("topicid") && map.containsKey("stagestatus")) {
			TopicObj topicObj = topicDao
					.find(Long.parseLong(map.get("topicid")));
			topicInfo.setTopicObj(topicObj);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("topicid", topicObj.getTopicid());
			params.put("stagestatus", map.get("stagestatus"));
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("topictransferid", "desc");
			List<TopictransObj> topictransObjs = topictransDao.findAll(
					"topicid=:topicid and stagestatus=:stagestatus", params,
					orderby);
			if (topictransObjs != null && topictransObjs.size() > 0) {
				topicInfo.setTopictransObj(topictransObjs.get(0));
			}
		}

		ObjectResult<TopicInfoAssistantObj> result = new ObjectResult<TopicInfoAssistantObj>();
		result.setType(0);
		result.setResultObject(topicInfo);
		return result.asXML(true);
	}

	@Override
	public void topicUpdate() throws Exception {
		TopicObj topicObj = (TopicObj) parameters.getDataObject();
		TopicObj curobj = topicDao.find(topicObj.getTopicid());
		topicObj.setTopicid(null);
		ObjectHelper
				.beanClone(TopicObj.class, topicObj, TopicObj.class, curobj);
		topicDao.merge(curobj);
		this.addTransfer(curobj, MODIFY);
	}

	@Override
	public String productRead() {
		Map<String, String> map = parameters.getParams();
		ProductObj productObj = null;
		if (map.containsKey("productid")) {
			productObj = productDao.findWithIsrc(Long.parseLong(map
					.get("productid")));
		}

		ObjectResult<ProductObj> result = new ObjectResult<ProductObj>();
		result.setType(0);
		result.setMessage("保存完成");
		result.setResultObject(productObj);
		return result.asXML(true);
	}

	@Override
	public String productInfoRead() {
		Map<String, String> map = parameters.getParams();
		ProductInfoAssistantObj productInfo = new ProductInfoAssistantObj();
		if (map.containsKey("productid")) {
			ProductObj productObj = productDao.findWithIsrc(Long.parseLong(map
					.get("productid")));
			productInfo.setProductObj(productObj);
			productInfo.setPublishinfoObj(publishinfoDao.find(productInfo
					.getProductObj().getPublishid()));

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("productid", productObj.getProductid());
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("transferid", "asc");
			List<TransferuserObj> transferuserObjs = transferuserDao.findAll(
					"productid=:productid", params, orderby);
			productInfo.setTransferuserObjs(transferuserObjs);
			params.put("publishid", productObj.getPublishid());
			List<PrefixinfoObj> prefixinfos = prefixinfoDao.findAll(
					"publishid=:publishid", params, null);
			productInfo.setPrefixinfoObjs(prefixinfos);
		}

		ObjectResult<ProductInfoAssistantObj> result = new ObjectResult<ProductInfoAssistantObj>();
		result.setType(0);
		result.setResultObject(productInfo);
		return result.asXML(true);
	}

	@Override
	public String productISBN() throws Exception {
		Long productid = Long
				.parseLong(parameters.getParams().get("productid"));
		return this.productUpdateStatus(productid, CODEDOWNED);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String productISBNBeach() throws Exception {
		String result = "";
		List<String> ids = (List<String>) parameters.getDataObject();
		for (String id : ids) {
			result += this.productUpdateStatus(Long.parseLong(id), CODEDOWNED);
		}
		return result;
	}

	@Override
	public void productDelete() {
		Long productid = Long
				.parseLong(parameters.getParams().get("productid"));
		productDao.delete(productid);
	}

	@Override
	public void productUpdate() throws Exception {
		ProductObj productObj = (ProductObj) parameters.getDataObject();
		ProductObj curobj = productDao.find(productObj.getProductid());
		productObj.setProductid(null);

		ObjectHelper.beanClone(ProductObj.class, productObj, ProductObj.class,
				curobj);
		curobj.setProductisrcs(productObj.getProductisrcs());
		productDao.updateWithIsrc(curobj);
		this.addTransfer(curobj, MODIFY);
	}

	@Override
	public Long productApply() throws Exception {
		ProductObj productObj = (ProductObj) parameters.getDataObject();
		return this.productApply(productObj, getUser());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void productApplyBeach() throws Exception {
		List<ProductObj> productObjs = (List<ProductObj>) parameters
				.getDataObject();
		UserObj userObj = getUser();
		for (ProductObj productObj : productObjs) {
			this.productApply(productObj, userObj);
		}

	}

	@Override
	public String productReApply() throws Exception {
		Long productid = Long
				.parseLong(parameters.getParams().get("productid"));
		return this.productUpdateStatus(productid, SAVED);
	}

	@Override
	public String productFinish() throws Exception {
		Long productid = Long
				.parseLong(parameters.getParams().get("productid"));
		return this.productUpdateStatus(productid, FINISHED);
	}

	@Override
	public String productCallback() throws Exception {
		Long productid = Long
				.parseLong(parameters.getParams().get("productid"));
		String stagereason = parameters.getParams().get("reason");
		return this.productUpdateStatus(productid, CALLBACK, stagereason);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String productCallbackBeach() throws Exception {
		String result = "";
		List<String> ids = (List<String>) parameters.getDataObject();
		String stagereason = parameters.getParams().get("reason");
		for (String id : ids) {
			result += this.productUpdateStatus(Long.parseLong(id), CALLBACK,
					stagereason);
		}
		return result;
	}

	@Override
	public String productTerminate() throws Exception {
		String reason = parameters.getParams().get("reason");
		Long productid = Long
				.parseLong(parameters.getParams().get("productid"));
		return this.productUpdateStatus(productid, STOPED, reason);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String productTerminateBeach() throws Exception {
		String result = "";
		String reason = parameters.getParams().get("reason");
		List<String> ids = (List<String>) parameters.getDataObject();
		for (String id : ids) {
			result += this.productUpdateStatus(Long.parseLong(id), STOPED,
					reason);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void productDeleteBeach() {
		List<String> ids = (List<String>) parameters.getDataObject();
		for (String id : ids) {
			ProductObj obj = productDao.find(Long.parseLong(id));
			List<TransferObj> list = obj.getTbTransfers();
			for (TransferObj o : list)
				transferDao.delete(o.getTransferid());
			productDao.delete(Long.parseLong(id));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getProductExportList() {
		ObjectResult<List<ProductObj>> result = new ObjectResult<List<ProductObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		List<ProductObj> productObjs = productDao.findAll("productid in :ids",
				params, null);
		result.setResultObject(productObjs);
		return result.asXML(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTopicExportList() {
		ObjectResult<List<TopicObj>> result = new ObjectResult<List<TopicObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		List<TopicObj> topicObjs = topicDao.findAll("topicid in :ids", params,
				null);
		result.setResultObject(topicObjs);
		return result.asXML(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getStatisticsExportList() {
		ObjectResult<List<StatisticsObj>> result = new ObjectResult<List<StatisticsObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		List<StatisticsObj> statisticsObjs = statisticsDao.findAll(
				" statisticsid in :ids ", params, null);
		result.setResultObject(statisticsObjs);
		return result.asXML(true);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public String getExpertAuditExportList() {
		ObjectResult<List<ExpertAuditObj>> result = new ObjectResult<List<ExpertAuditObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		LinkedHashMap<String,String> orderby = new LinkedHashMap<String, String>();
		orderby.put("productid", "desc");
		List<ExpertAuditObj> expertAuditObjs = expertAuditDao.findAll(
				" productid in :ids ", params, orderby);
		result.setResultObject(expertAuditObjs);
		return result.asXML(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getExpertTopicAuditExportList() {
		ObjectResult<List<ExpertTopicAuditObj>> result = new ObjectResult<List<ExpertTopicAuditObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		LinkedHashMap<String,String> orderby = new LinkedHashMap<String, String>();
		orderby.put("topicid", "desc");
		List<ExpertTopicAuditObj> expertTopicAuditObjs = expertTopicAuditDao.findAll(
				" topicid in :ids ", params, orderby);
		result.setResultObject(expertTopicAuditObjs);
		return result.asXML(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getIntegratedExportList() {
		ObjectResult<List<IntegratedExportObj>> result = new ObjectResult<List<IntegratedExportObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		LinkedHashMap<String,String> orderby = new LinkedHashMap<String, String>();
		orderby.put("productid", "desc");
		List<IntegratedExportObj> IntegratedExportObjs = integratedExportDao.findAll(
				" productid in :ids ",params , orderby);
		result.setResultObject(IntegratedExportObjs);
		return result.asXML(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getProductpublishExportList() {
		ObjectResult<List<ProductpublishObj>> result = new ObjectResult<List<ProductpublishObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		List<ProductpublishObj> productObjs = productpublishDao.findAll(
				"productid in :ids", params, null);
		result.setResultObject(productObjs);
		return result.asXML(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTopicpublishExportList() {
		ObjectResult<List<TopicpublishObj>> result = new ObjectResult<List<TopicpublishObj>>();
		List<Long> ids = (List<Long>) parameters.getDataObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		List<TopicpublishObj> topicObjs = topicpublishDao.findAll(
				"topicid in :ids", params, null);
		result.setResultObject(topicObjs);
		return result.asXML(true);
	}

	/*-------------------------------选题列表----------------------------------------------*/
	// 申请ISBN号只允许申请当年和去年的ISBN lh 2014年12月26日
	@Override
	public String getTopicNoISBNList() {
		BaseModel result = getTopicList(true, AUDITED);
		return result.asXML(true);
	}

	// 退回选题可以看到来年的退回选题 lh 2014年12月26日
	@Override
	public String getTopicRefusedList() {
		BaseModel result = getTopicRefusedList(false, REFUSED);
		return result.asXML(true);
	}

	@Override
	public String getTopicTraceList() {
		BaseModel result = getTopicList();
		return result.asXML(true);
	}

	/*-------------------------------产品列表----------------------------------------------*/
	@Override
	public String getProductSavedList() {
		BaseModel result = getProductList(false, SAVED);
		return result.asXML(true);
	}

	@Override
	public String getProductCallbackList() {
		BaseModel result = getProductList(false, CALLBACK);
		return result.asXML(true);
	}

	@Override
	public String getProductAuditedList() {
		BaseModel result = getProductList(false, AUDITED, UNDETERMINED);
		return result.asXML(true);
	}

	@Override
	public String getProductRefusedList() {
		BaseModel result = getProductList(false, REFUSED, REJECTED);
		return result.asXML(true);
	}

	@Override
	public String getBarcodeDownloadList() {
		BaseModel result = getProductList(true, CODEAUDITED);
		return result.asXML(true);
	}

	@Override
	public String getProductModifyList() {
		BaseModel result = getProductList(true, AUDITED, CODEAUDITED,UNDETERMINED,
				CODEDOWNED);
		return result.asXML(true);
	}

	@Override
	public String getProductFinishingList() {
		BaseModel result = getProductList(true, CODEDOWNED);
		return result.asXML(true);
	}

	@Override
	public String getProductTerminateList() {
		BaseModel result = getProductList(true, AUDITED, CODEAUDITED,
				CODEDOWNED);
		return result.asXML(true);
	}

	@Override
	public String getProductFinishedList() {
		BaseModel result = getProductList(true, FINISHED);
		return result.asXML(true);
	}

	/*-------------------------------私有函数----------------------------------------------*/
	
	/*
	 * 撤回后,再次申请只对选题更新
	 * lh   2015年11月3日10:56:46
	 */
	
	private String productUpdateStatus(Long productid, String productstatus)
			throws Exception {
		ProductObj productObj = productDao.find(productid);
		//如业务需要更新其余信息，在这里将productObj，放入topicObj即可
		TopicObj topicObj = topicDao.find(productObj.getTopicid());
		
		updateTopicWhenReApply(productObj,topicObj);
			
		if (productObj.getIssuspend())
			return "作品名称为" + productObj.getTopicname() + "的" + pauseReason;
		topicObj.setTopicstatus(CODEAUDITED);
		topicDao.update(topicObj);
		
		productObj.setProductstatus(productstatus);
		productDao.update(productObj);
		this.addTransfer(productObj, productstatus);
		return "";
	}
	
	private void updateTopicWhenReApply(ProductObj productObj,TopicObj topicObj)
	{
		topicObj.setAuthor(productObj.getAuthor());
		topicObj.setCapacity(productObj.getCapacity());
		topicObj.setCapacityunit(productObj.getCapacityunit());
		topicObj.setContentabstract(productObj.getContentabstract());
		topicObj.setContenttype(productObj.getContenttype());
		topicObj.setCreatetime(productObj.getCreatetime());
		topicObj.setDimensions(productObj.getDimensions());
		
		topicObj.setImportant(productObj.getImportant());
		topicObj.setImportantindex(productObj.getImportantindex());
		topicObj.setIstranslate(productObj.getIstranslate());
		topicObj.setIsyear(productObj.getIsyear());
		topicObj.setLanguageother(productObj.getLanguageother());	
		topicObj.setMade(productObj.getMade());
		topicObj.setMediatype(productObj.getMediatype());
		topicObj.setMediatypeother(productObj.getMediatypeother());
		topicObj.setOtherimportant(productObj.getOtherimportant());	
		topicObj.setPlanguage(productObj.getPlanguage());
		topicObj.setPmonth(productObj.getPmonth());
		
		topicObj.setPsize(productObj.getPsize());
		topicObj.setPublishid(productObj.getPublishid());
		topicObj.setPublishmethod(productObj.getPublishmethod());
		topicObj.setPublishtime(productObj.getPublishtime());
		topicObj.setPublishtype(productObj.getPublishtype());
		topicObj.setReader(productObj.getReader());
		topicObj.setTopicname(productObj.getTopicname());
		topicObj.setUsetype(productObj.getUsetype());
		topicObj.setIselec(productObj.getIselec());
		topicObj.setTopicnum(productObj.getTopicnum());
		
		topicObj.setTopicnumberself(productObj.getTopicnumberself());		
		topicObj.setSeriesname(productObj.getSeriesname());
		topicObj.setImportantbakcode(productObj.getImportantbakcode());
		topicObj.setOtherimportant(productObj.getOtherimportant());
		topicObj.setBackup1(productObj.getBackup1());
		topicObj.setBackup2(productObj.getBackup2());
		topicObj.setBackup3(productObj.getBackup3());
		topicObj.setBackup4(productObj.getBackup4());
		topicObj.setBackup5(productObj.getBackup5());	
		
	}


	//	  出版社撤回终止的业务逻辑修正              lh      2015-4-14 07:14:49
	//	1.保持正常流程已发号的作品回收ISBN号     (CODEAUDITED   CODEDOWNED 正常)
	//	2.作品终止列表AUDITED   CODEAUDITED   CODEDOWNED
	//	   修改AUDITED出错
	//	3.出版社申请ISBN号后在待核件中撤回终止异常
	//	4.出版社申请ISBN号后在退回件中终止异常		lh   2015-7-1 15:58:48
	private String productUpdateStatus(Long productid, String productstatus,
			String stagereason) throws Exception {
		ProductObj productObj = productDao.find(productid);
		TopicObj topicObj = topicDao.find(productObj.getTopicid());
		if (productObj.getIssuspend())
			return pauseReason;

		if (productstatus.equals(STOPED)) {
			if (productObj.getProductstatus().equals(CALLBACK)) {
			
			}else if(productObj.getProductstatus().equals(AUDITED))
			{
			}
			else if(productObj.getProductstatus().equals(REFUSED))
			{
			}
 			else if (!productObj.getProductisbn().isEmpty()) {
					// 回收ISBN号
					IsbnrecycleObj io = new IsbnrecycleObj();
					io.setProductisbn(productObj.getProductisbn());
					io.setCreatedate(new Date());
					io.setTbProduct(productObj);
					PublishinfoObj pi = publishinfoDao.find(productObj
							.getPublishid());
					io.setTbPublishinfo(pi);
					io.setIsbnstatus(false);
					io.setIsbntype(productObj.getIselec());
					isbnrecycleDao.save(io);
			}
			topicObj.setTopicstatus(productstatus);
			topicDao.update(topicObj);
		}
		productObj.setProductstatus(productstatus);
		productDao.update(productObj);
		topicObj.setTopicstatus(productstatus);
		this.addTransfer(productObj, productstatus, stagereason);
		return "";
	}

	private BaseModel getProductList(boolean hasCode, String... productstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishid = getPublishId();
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			String where = "publishid=:publishid and iselec=:iselec and productstatus in :productstatus";
			String orderby = " ORDER BY "
					+ (parameters.getParams().containsKey("orderby") ? (String) parameters
							.getParams().get("orderby") : "CREATETIME DESC");

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("productstatus", productstatus); // 撤回
			params.put("publishid", publishid);
			params.put("iselec", Long.parseLong(iselec));
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or "
						+ "instr(topicname,:fuzzy)>0 or instr(seriesname,:fuzzy)>0 or instr(important,:fuzzy)>0"
						+ "or instr(importantindex,:fuzzy)>0"
						+ " or instr(topicnum,:fuzzy)>0"
						+ " or instr(productisbn,:fuzzy)>0 )";
			}
			if (!hasCode) {
				result = productpublishDao.getProductWaitAuditGrid(firstResult,
						maxResult, where, params, orderby, null);
			} else {
				result = productpublishDao.getProductHasBarcodeGrid(
						firstResult, maxResult, where, params, orderby, null);
			}

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	private BaseModel getTopicList() {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishid = getPublishId();
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			Long year = Long.parseLong(parameters.getParams().get("year"));

			String where = " publishid=:publishid and iselec=:iselec and baktime=:baktime ";
			String orderby = parameters.getParams().containsKey("orderby") ? " ORDER BY "
					+ parameters.getParams().get("orderby")
					: null;

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("baktime", year);
			params.put("publishid", publishid);
			params.put("iselec", Long.parseLong(iselec));

			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or instr(topicname,:fuzzy)>0 or instr(seriesname,:fuzzy)>0  or instr(topicnum,:fuzzy)>0 or  instr(important,:fuzzy)>0 or instr(importantindex,:fuzzy)>0 ) ";
			}
			result = topicpublishDao.getTraceGridData(firstResult, maxResult,
					where, params, orderby, null);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	// 申请ISBN号
	// 当年和前一年申请的选题可以申请ISBN号 lh 2014年12月26日15:37:33
	private BaseModel getTopicList(boolean hasCode, String... topicstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishid = getPublishId();
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			String year = parameters.getParams().get("year");

			String where = "publishid=:publishid and iselec=:iselec and topicstatus in :topicstatus";
			String orderby = parameters.getParams().containsKey("orderby") ? " ORDER BY "
					+ parameters.getParams().get("orderby")
					: null;

			Long year_Long = Long.parseLong(parameters.getParams().get("year"));
			Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
			DateFormat df = new SimpleDateFormat("yyyy");// 设置显示格式
			String nowTime = "";
			nowTime = df.format(dt);// 用DateFormat的format()方法在dt中获取并以yyyy格式显示
			Long nowTime_Long = Long.parseLong(nowTime);

			Map<String, Object> params = new HashMap<String, Object>();

			if (year != null && !"".equals(year) && !(year_Long > nowTime_Long)) {
				where += " and baktime=:year ";
				params.put("year", Long.parseLong(year));
			} else {
				where += " and baktime=null ";
			}
			params.put("topicstatus", topicstatus);
			params.put("publishid", publishid);
			params.put("iselec", Long.parseLong(iselec));
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or instr(topicname,:fuzzy)>0 or instr(seriesname,:fuzzy)>0 or  instr(topicnum,:fuzzy)>0 or instr(important,:fuzzy)>0 or instr(importantindex,:fuzzy)>0 ) ";
			}
			result = topicpublishDao.getGridData(firstResult, maxResult, where,
					params, orderby, null);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	// 退回为改变 lh 2014年12月26日15:37:13
	private BaseModel getTopicRefusedList(boolean hasCode,
			String... topicstatus) {
		BaseModel result;
		try {
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String publishid = getPublishId();
			String iselec = parameters.getParams().get("iselec");
			String fuzzy = parameters.getParams().get("fuzzy");
			String year = parameters.getParams().get("year");

			String where = "publishid=:publishid and iselec=:iselec and topicstatus in :topicstatus";
			String orderby = parameters.getParams().containsKey("orderby") ? " ORDER BY "
					+ parameters.getParams().get("orderby")
					: null;

			Map<String, Object> params = new HashMap<String, Object>();
			if (year != null && !"".equals(year)) {
				where += " and baktime=:year ";
				params.put("year", Long.parseLong(year));
			}
			params.put("topicstatus", topicstatus);
			params.put("publishid", publishid);
			params.put("iselec", Long.parseLong(iselec));
			if (!fuzzy.equals("")) {
				params.put("fuzzy", fuzzy);
				where += " and (instr(publishname,:fuzzy)>0 or instr(topicname,:fuzzy)>0 or instr(seriesname,:fuzzy)>0 or  instr(topicnum,:fuzzy)>0 or instr(important,:fuzzy)>0 or instr(importantindex,:fuzzy)>0 ) ";
			}
			result = topicpublishDao.getGridData(firstResult, maxResult, where,
					params, orderby, null);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result;
	}

	private Long productApply(ProductObj productObj, UserObj userObj)
			throws Exception {
		productObj.setIsmodify(false);
		productObj.setIslocked(false);
		productObj.setUserid(userObj.getUserid());

		productObj.setCreatetime(new Date());
		productObj.setPublishid(userObj.getOrgid());
		if (productObj.getTopicid() != null) {
			// 申请完成后选题标记为已申请条码
			TopicObj topicObj = topicDao.find(productObj.getTopicid());
			topicObj.setTopicstatus(CODEAUDITED);
			topicDao.update(topicObj);
			productObj.setTbTopic(topicObj);
		}
		productObj.setProductstatus(SAVED);
		productDao.saveWithIsrc(productObj);

		this.addTransfer(productObj, SAVED);
		return productObj.getProductid();

	}

	private Long topicApply(TopicObj topicObj, UserObj userObj)
			throws Exception {
		topicObj.setTbUser(userObj);
		if (topicObj.getIsyear()) {
			Calendar cl = Calendar.getInstance();
			int month = cl.get(Calendar.MONTH);
			int year = cl.get(Calendar.YEAR);
			if (month >= 6)
				topicObj.setTopicyear((long) year + 1);
			else
				topicObj.setTopicyear((long) year);
		}
		topicObj.setTopicstatus(SAVED);
		if ("100012".equals(topicObj.getTopicstatus())
				&& topicObj.getTopicid() != null) {
			TopicObj curobj = topicDao.find(topicObj.getTopicid());
			ObjectHelper.beanClone(TopicObj.class, topicObj, TopicObj.class,
					curobj);
			topicDao.update(curobj);
		} else {
			topicObj.setCreatetime(new Date());
			topicObj.setPublishid(userObj.getOrgid());
			topicDao.save(topicObj);
		}
		this.addTransfer(topicObj, SAVED);
		return topicObj.getTopicid();
	}

	private String getPublishId() {
		return getUser().getOrgid();
	}

	private UserObj getUser() {
		BigDecimal userid = new BigDecimal(parameters.getUserId());
		return userDao.find(userid);
	}

	private String getShortName() {
		BigDecimal userid = new BigDecimal(parameters.getUserId());
		return userDao.find(userid).getTbOrganizationinfo().getOrgshortname();
	}

	private void addTransfer(ProductObj productObj, String stagestatus)
			throws Exception {
		TransferObj transferObj = new TransferObj();
		ObjectHelper.beanClone(ProductObj.class, productObj, TransferObj.class,
				transferObj);
		transferObj.setStagetime(new Date());
		transferObj.setStageuser(Long.parseLong(parameters.getUserId()));
		transferObj.setStagestatus(stagestatus);
		transferObj.setTbProduct(productObj);
		transferDao.save(transferObj);
	}

	private void addTransfer(ProductObj productObj, String stagestatus,
			String stagereason) throws Exception {
		TransferObj transferObj = new TransferObj();
		ObjectHelper.beanClone(ProductObj.class, productObj, TransferObj.class,
				transferObj);
		transferObj.setStagetime(new Date());
		transferObj.setStageuser(Long.parseLong(parameters.getUserId()));
		transferObj.setStagestatus(stagestatus);
		transferObj.setStagereason(stagereason);
		transferObj.setTbProduct(productObj);
		transferDao.save(transferObj);
	}

	private void addTransfer(TopicObj topicObj, String stagestatus)
			throws Exception {
		TopictransObj topictransObj = new TopictransObj();
		ObjectHelper.beanClone(TopicObj.class, topicObj, TopictransObj.class,
				topictransObj);
		topictransObj.setStagetime(new Date());
		topictransObj.setStageuser(Long.parseLong(parameters.getUserId()));
		topictransObj.setStagestatus(stagestatus);
		topictransObj.setTopicid(topicObj.getTopicid());
		topictransDao.save(topictransObj);

	}

	private void addTransfer(TopicObj topicObj, String stagestatus,
			String reason) throws Exception {
		TopictransObj topictransObj = new TopictransObj();
		ObjectHelper.beanClone(TopicObj.class, topicObj, TopictransObj.class,
				topictransObj);
		topictransObj.setStagetime(new Date());
		topictransObj.setStageuser(Long.parseLong(parameters.getUserId()));
		topictransObj.setStagestatus(stagestatus);
		topictransObj.setTopicid(topicObj.getTopicid());
		topictransObj.setStagereason(reason);
		topictransDao.save(topictransObj);

	}

	@Override
	public String topicTerminateBeach() throws Exception {
		String result = "";
		String reason = parameters.getParams().get("reason");
		List<String> ids = (List<String>) parameters.getDataObject();
		for (String id : ids) {
			result += this
					.topicUpdateStatus(Long.parseLong(id), STOPED, reason);
		}
		return result;
	}

	@Override
	public String topicTerminate() throws Exception {
		String reason = parameters.getParams().get("reason");
		Long topicid = Long.parseLong(parameters.getParams().get("topicid"));
		return topicUpdateStatus(topicid, STOPED, reason);

	}

	private String topicUpdateStatus(Long topicid, String topicstatus,
			String stagereason) throws Exception {
		TopicObj topicObj = topicDao.find(topicid);
/*		if (topicObj.getTopicstatus().equals(STOPED)
				|| topicObj.getTopicstatus().equals(AUDITED)
				|| topicObj.getTopicstatus().equals(CODEAUDITED))*/
			
			if (topicObj.getTopicstatus().equals(STOPED)
					|| topicObj.getTopicstatus().equals(CODEAUDITED))
			return "作品名称为" + topicObj.getTopicname() + "的" + stopReason;
		topicObj.setTopicstatus(topicstatus);
		topicDao.update(topicObj);
		this.addTransfer(topicObj, topicstatus, stagereason);
		return "";
	}

	@SuppressWarnings("null")
	@Override
	public String productCount() throws Exception {

		CountObj countobj = new CountObj();

		ProductObj productObj = (ProductObj) parameters.getDataObject();
		String publishid = productObj.getPublishid();
		Boolean iselec = productObj.getIselec();

		Map<String, Object> params = new HashMap<String, Object>();
		String where = "publishid=:publishid and iselec =:iselec and productstatus in ('100020','100010')";
		params.put("publishid", publishid);
		params.put("iselec", iselec);

		ObjectResult<CountObj> result = new ObjectResult<CountObj>();
		Long count = productDao.getCount(where, params);
		countobj.setCount(count);

		// result.setType(0);
		// result.setMessage("");
		result.setResultObject(countobj);
		return result.asXML(true);
	}

	@Override
	public String getCanUsePrefixByPublishidCount() {
		// 非回收的前缀
		CountObj countobj = new CountObj();
		ProductObj productObj = (ProductObj) parameters.getDataObject();
		String publishid = productObj.getPublishid();
		Boolean iselecbool = productObj.getIselec();

		String where = "publishinfo.publishid = :publishid and prefixtype =:prefixtype and stopstatus=:stopstatus and prefixstatus in (1,2)";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("publishid", publishid);
		params.put("prefixtype", iselecbool);
		// params.put("prefixstatus",1L);
		params.put("stopstatus", false);

		ObjectResult<CountObj> result = new ObjectResult<CountObj>();
		List<PrefixinfoObj> listPix = prefixinfoDao
				.findAll(where, params, null);

		Long countSum, currentNum = 0L;
		Long noUseNum = 0L;
		for (PrefixinfoObj pix : listPix) {
			if (pix.getPrefixstatus() == 1L) {
				currentNum = pix.getEndcode() - pix.getCurstartcode() + 1L;
			} else if (pix.getPrefixstatus() == 2L) {
				noUseNum += pix.getEndcode() + 1L;
			}
		}

		// 回收的前缀
		String where2 = "publishid = :publishid and ISBNSTATUS=0";

		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("publishid", publishid);
		Long pixrecyclecount = isbnrecycleDao.getCount(where2, params2);

		countSum = currentNum + noUseNum + pixrecyclecount;
		countobj.setCanUsePrefix(countSum);

		result.setType(0);
		result.setMessage("");
		result.setResultObject(countobj);
		return result.asXML(true);
	}

	@Override
	public String getProductById() {		
		ObjectResult<ProductObj> result = new ObjectResult<ProductObj>();

		try {
			ProductObj curobj = productDao.find((new Long(parameters.getParams().get("productid"))));
			result.setResultObject(curobj);
			result.setType(0);
			result.setMessage("状态一致！");
		} catch (Exception e) {
			result.setType(1);
			result.setMessage("状态不一致！请刷新列表后重试");
		}
		return result.asXML(true);
	}
	
}
