package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.ncut.eavp.model.TopicObj;
import cn.edu.ncut.eavp.service.AuditService;
import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.BaseWebService;
import cn.edu.ncut.eavp.webservice.base.ObjectResult;
import cn.edu.ncut.eavp.webservice.base.Result;

/**
 * 进行省局审核管理的webservice发布类
 */
@WebService(endpointInterface = "cn.edu.ncut.eavp.webservice.AuditWebService")
@Service("auditWebService")
@Scope("request")
public class AuditWebServiceImpl implements AuditWebService, BaseWebService {

	@Autowired
	private AuditService auditService;

	@Override
	public BaseService getService() {
		return auditService;
	}

	@Override
	public String getTopicCountList(String xml) {
		return auditService.getTopicCountList();
	}
	
	@Override
	public String getTopicCountGAList(String xml) {
		return auditService.getTopicCountGAList();
	}

	@Override
	public String getBakedTopicList(String xml) {
		return auditService.getBakedTopicList();
	}

	@Override
	public String getBakedTopicDetail(String xml) {
		return auditService.getBakedTopicDetail();
	}

	@Override
	public String getProductWaitAuditList(String xml) {

		return auditService.getProductWaitAuditList();
	}

	@Override
	public String getProductById(String xml) {
		return auditService.getProductById();
	}

	@Override
	public String getTransferByProductId(String xml) {
		return auditService.getTransferByProductId();
	}

	@Override
	public String getPublishById(String xml) {
		return auditService.getPublishById();
	}

	@Override
	public String getPrefixByPublishId(String xml) {
		return auditService.getPrefixByPublishId();
	}

	@Override
	public String getTopicById(String xml) {
		return auditService.getTopicById();
	}

	@Override
	public String auditOneProduct(String xml) {
		Result result = null;
		try {
			result = auditService.auditOneProduct();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}

	@Override
	public String auditListProduct(String xml) {
		Result result = null;
		try {
			result = auditService.auditListProduct();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}

	
	//待定
	@Override
	public String undeterminOneProduct(String xml) {
		Result result = null;
		try {
			result = auditService.undeterminOneProduct();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}

	//待定
	@Override
	public String undeterminListProduct(String xml) {
		Result result = null;
		try {
			result = auditService.undeterminListProduct();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	
	//解除待定
	@Override
	public String removeOneProduct(String xml) {
		Result result = null;
		try {
			result = auditService.removeOneProduct();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	
	@Override
	public String getProductHasAuditedList(String xml) {
		return auditService.getProductHasAuditedList();
	}

	@Override
	public String getProductWaitBarCode(String xml) {

		return auditService.getProductWaitBarCode();
	}

	@Override
	public String getProductUndeterminBarcode(String xml) {

		return auditService.getProductUndeterminBarcode();
	}
	
	@Override
	public String getProductTrace(String xml) {
		return auditService.getProductTraceList();
	}
	@Override
	public String getProductHasBarcode(String xml) {
		return auditService.getProductHasBarcode();
	}
	@Override
	public String backAudit(String xml) {
		Result result = null;
		try {
			result = auditService.backAudit();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("审核出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}

	@Override
	public String getProductExitList(String xml) {
		return auditService.getProductExitList();
	}

	@Override
	public String getProductSuspendList(String xml) {
		return auditService.getProductSuspendList();
	}

	@Override
	public String getProductModifiedList(String xml) {
		return auditService.getProductModifiedList();
	}
	@Override
	public String getEarliestTransferByProductId(String xml){
		return auditService.getEarliestTransferByProductId();
	}
	@Override
	public String auditBarcode(String xml){
		Result result = null;
		try {
			result = auditService.auditBarcode();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("发码出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String batchAuditBarcode(String xml){
		Result result = null;
		try {
			result = auditService.batchAuditBarcode();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("发码出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String getExportTopics(String xml){
		return auditService.getExportTopics();
	}
	@Override
	public String getTopiccountByIds(String xml){
		return auditService.getTopiccountByIds();
	}
	@Override
	public String getAllProductByStatus(String xml){
		return  auditService.getAllProductByStatus();
	}

	@Override
	public String auditOneTopic(String xml) {
		Result result = null;
		try {
			result = auditService.auditOneTopic();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	
	@Override
	public String ZSrefuseProduct(String xml) {
		Result result = null;
		try {
			result = auditService.ZSrefuseProduct();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出错！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}

	@Override
	public String auditListTopic(String xml) {
		Result result = null;
		try {
			result = auditService.auditListTopic();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}

	@Override
	public String getTopicWaitAuditList(String xml) {
		 return auditService.getTopicWaitAuditList();
	}

	@Override
	public String getTransferByTopicId(String xml) {
		return auditService.getTransferByTopicId();
	}

	@Override
	public String getExportProduct(String xml) {
		return auditService.getExportProduct();
	}

	@Override
	public String getAllTopicByStatus(String xml) {
		return auditService.getAllTopicByStatus();
	}

	@Override
	public String getToSuspendList(String xml) {
		return auditService.getToSuspendList();
	}

	@Override
	public String suspendProduct(String xml) {
		return auditService.suspendProduct();
	}

	@Override
	public String recoverProduct(String xml) {
		return auditService.recoverProduct();
	}
	
	
	@Override
	public String getTopCodeTree() {
		return auditService.getTopCodeTree();
	}

	@Override
	public String getYearTopicStatistics(String xml) {
		return auditService.getYearTopicStatistics();
	}

	@Override
	public String getYearTopicStatisticsSum(String xml) {
		return auditService.getYearTopicStatisticsSum();
	}

}
