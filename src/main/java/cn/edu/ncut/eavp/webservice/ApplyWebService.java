package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

@WebService
public interface ApplyWebService {

	public abstract String topicApply(String xml);
	
	public abstract String topicApplyBeach(String xml);
	
	public abstract String topicRead(String xml);
	
	public abstract String topicInfoRead(String xml);

	public abstract String topicUpdate(String xml);

	public abstract String productApplyBeach(String xml);
	
	public abstract String productRead(String xml);
	
	public abstract String productInfoRead(String xml);

	public abstract String getProductSavedList(String xml);
	
	public abstract String getProductCallbackList(String xml);
	
	public abstract String getProductAuditedList(String xml);
	
	public abstract String getBarcodeDownloadList(String xml);
	
	public abstract String getProductRefusedList(String xml);
	
	public abstract String getProductModifyList(String xml);
	
	public abstract String getProductFinishingList(String xml);
	
	public abstract String getProductTerminateList(String xml);

	public abstract String getProductFinishedList(String xml);
	
	public abstract String productApply(String xml);
	
	public abstract String productReApply(String xml);
	
	public abstract String productCallback(String xml);
	
	public abstract String productDelete(String xml);
	
	public abstract String productUpdate(String xml);
	
	public abstract String productFinish(String xml);
	
	public abstract String productISBN(String xml);
	
	public abstract String productISBNBeach(String xml);
	
	public abstract String getProductExportList(String xml);
	
	public abstract String productCallbackBeach(String xml);
	
	public abstract String productDeleteBeach(String xml);
	
	public abstract String getTopicNoISBNList(String xml);
	
	public abstract String getTopicRefusedList(String xml);
	
	public abstract String getTopicTraceList(String xml);
	
	public abstract String getTopicExportList(String xml);

	public abstract String productTerminate(String xml);
	
	public abstract String productTerminateBeach(String xml);

	public abstract String getProductpublishExportList(String xml);

	public abstract String getTopicpublishExportList(String xml);
	
    public abstract String topicTerminate(String xml);
    
    public abstract String topicTerminateBeach(String xml);
    
    
    //获取导出StatisticsObj列表	lyy
    public abstract String getStatisticsExportList(String xml);
    //获取导出ExpertAuditObj列表	lyy
    public abstract String getExpertAuditExportList(String xml);
    //获取导出ExpertTopicAuditObj列表	lyy
    public abstract String getExpertTopicAuditExportList(String xml);
    //获取IntegratedExportObj视图对象列表	lyy
    public abstract String getIntegratedExportList(String xml);
    
    
    //增加前缀使用量的判断      lh   2015年1月28日21:32:34
    public abstract String productCount(String xml) throws Exception;
	
    //增加修订前判断，防止出版社用户未关闭页面，总署端退回后，作品状态异常的问题      lh   2015-10-30 07:43:33      
    public abstract String getProductById(String xml) throws Exception;
    
    public abstract String getCanUsePrefixByPublishidCount(String xml);
    
    
}