package cn.edu.ncut.eavp.service;

import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.Result;

public interface AuditService extends BaseService{
	String getTopicCountList();
	String getTopicCountGAList();
	String getBakedTopicList();
	String getBakedTopicDetail();
	String getProductWaitAuditList();
	String getProductById();
	String getTransferByProductId();
	String getPublishById();
	String getPrefixByPublishId();
	String getTopicById();
	Result auditOneProduct() throws Exception;
	Result auditListProduct() throws Exception;
	
	Result ZSrefuseProduct() throws Exception;			//总署退回
	
	Result undeterminOneProduct() throws Exception;	    //待定
	Result undeterminListProduct() throws Exception;	//待定
	Result removeOneProduct() throws Exception;			//解除待定
	
	
	String getProductHasAuditedList();
	String getProductWaitBarCode();
	String getProductUndeterminBarcode();               //待定号
	String getProductHasBarCode();
	String getProductTraceList();
	Result backAudit();
	String getProductExitList();
	String getProductSuspendList();
	String getProductModifiedList();
	String getEarliestTransferByProductId();
	Result auditBarcode() throws Exception;
	Result batchAuditBarcode() throws Exception;
	String getProductHasBarcode();
	String getTopiccountByIds();
	String getExportTopics();
	String getAllProductByStatus();
	Result auditOneTopic();
	Result auditListTopic();
	String getTopicWaitAuditList();
	String getTransferByTopicId();
	String getExportProduct();
	String getAllTopicByStatus();
	String getToSuspendList();
	String suspendProduct();
	String recoverProduct();
	String getTopCodeTree();	//获取代码树顶级
	String getYearTopicStatistics();
	String getYearTopicStatisticsSum();
}
