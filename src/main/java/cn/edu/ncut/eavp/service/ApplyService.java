package cn.edu.ncut.eavp.service;

import cn.edu.ncut.eavp.service.base.BaseService;

public interface ApplyService extends BaseService{

	public abstract void productApplyBeach() throws Exception;

	public abstract boolean topicApplyBeach() throws Exception;

	public abstract String topicRead();

	public abstract String productRead();

	public abstract String getProductSavedList();

	public abstract String getProductCallbackList();

	public abstract String getProductAuditedList();

	public abstract String getProductRefusedList();

	public abstract String getProductModifyList();

	public abstract String getProductFinishingList();

	public abstract String getProductTerminateList();

	public abstract String getProductFinishedList();

	public abstract void productDelete();

	public abstract void productUpdate() throws Exception;

	public abstract Long productApply() throws Exception;

	public abstract String productFinish() throws Exception;

	public abstract String productCallback() throws Exception;

	public abstract String productCallbackBeach() throws Exception;

	public abstract void productDeleteBeach() throws Exception;

	public abstract String productISBN() throws Exception;

	public abstract String productISBNBeach() throws Exception;

	public abstract String productInfoRead();

	public abstract String getBarcodeDownloadList();

	public abstract String getProductExportList();

	public abstract void topicApply() throws Exception;

	public abstract String getTopicNoISBNList();

	public abstract String getTopicRefusedList();

	public abstract void topicUpdate() throws Exception;

	public abstract String getTopicExportList();

	public abstract String topicInfoRead();

	public abstract String productTerminate() throws Exception;

	public abstract String productTerminateBeach() throws Exception;

	public abstract String getProductpublishExportList();

	public abstract String getTopicpublishExportList();

	public abstract String productReApply() throws Exception;

	public abstract String getTopicTraceList();

	public abstract String topicTerminateBeach() throws Exception;

	public abstract String topicTerminate() throws Exception;
	

	public abstract String productCount() throws Exception ;
	
	public abstract String getCanUsePrefixByPublishidCount() ;

	public abstract String getStatisticsExportList();

	public abstract String getExpertAuditExportList();

	public abstract String getExpertTopicAuditExportList();
	
	public abstract String getIntegratedExportList();
	
	public abstract String getProductById();

}
