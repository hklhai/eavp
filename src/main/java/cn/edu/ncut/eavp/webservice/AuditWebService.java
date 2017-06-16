package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

import cn.edu.ncut.eavp.webservice.base.Result;

@WebService
public interface AuditWebService {
	// 省局选题备案查看
	public abstract String getTopicWaitAuditList(String xml);

	public abstract String getTopicCountList(String xml);
	public abstract String getTopicCountGAList(String xml);
	public abstract String getTransferByTopicId(String xml);
	public abstract String getBakedTopicList(String xml);

	public abstract String getBakedTopicDetail(String xml);
	

	// 备案审核
	// 审核操作
	public abstract String auditOneTopic(String xml);

	public abstract String auditListTopic(String xml);

	// 作品审核查询
	public abstract String getProductWaitAuditList(String xml);

	public abstract String getProductById(String xml);

	public abstract String getTransferByProductId(String xml);

	public abstract String getPublishById(String xml);

	public abstract String getPrefixByPublishId(String xml);

	public abstract String getTopicById(String xml);

	// 审核操作
	public abstract String auditOneProduct(String xml);

	public abstract String auditListProduct(String xml);
	
	//总署退回
	public abstract String ZSrefuseProduct(String xml);
	
	// 待定操作
	public abstract String undeterminOneProduct(String xml);
	
	public abstract String undeterminListProduct(String xml);

	//解除待定
	public abstract String removeOneProduct(String xml);
	// 审核撤回
	public abstract String getProductHasAuditedList(String xml);

	public abstract String backAudit(String xml);

	// 配码
	public abstract String getProductWaitBarCode(String xml);
	//待定号列表
	public abstract String getProductUndeterminBarcode(String xml);

	public abstract String auditBarcode(String xml);

	public abstract String batchAuditBarcode(String xml);

	public abstract String getProductHasBarcode(String xml);

	// 选题跟踪查看
	public abstract String getProductTrace(String xml);

	// 终止选题查看
	public abstract String getProductExitList(String xml);

	// 暂停选题查看
	public abstract String getProductSuspendList(String xml);

	// 修改选题查看
	public abstract String getProductModifiedList(String xml);

	public abstract String getEarliestTransferByProductId(String xml);

	public abstract String getExportTopics(String xml);

	public abstract String getTopiccountByIds(String xml);

	public abstract String getAllProductByStatus(String xml);

	public abstract String getExportProduct(String xml);

	public abstract String getAllTopicByStatus(String xml);

	public abstract String getToSuspendList(String xml);
	public abstract String suspendProduct(String xml);
	public abstract String recoverProduct(String xml);
	
	//获取代码库顶级
	public abstract String getTopCodeTree();
	
	//年度选题统计分析
	public abstract String getYearTopicStatistics(String xml);
	
	//年度选题统计分析总
	public abstract String getYearTopicStatisticsSum(String xml);

}
