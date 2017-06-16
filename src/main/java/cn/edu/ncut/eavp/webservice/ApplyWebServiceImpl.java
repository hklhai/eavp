package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.ncut.eavp.service.ApplyService;
import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.BaseWebService;
import cn.edu.ncut.eavp.webservice.base.Result;

/**
 * 进行申报管理的webservice发布类
 */
@WebService(endpointInterface = "cn.edu.ncut.eavp.webservice.ApplyWebService")
@Service("applyWebService")
@Scope("request")
public class ApplyWebServiceImpl implements ApplyWebService, BaseWebService {

	@Autowired
	private ApplyService applyService;

	@Override
	public BaseService getService() {
		return applyService;
	}

	@Override
	public String topicApplyBeach(String xml) {
		Result result = new Result();
		try {
			boolean b = applyService.topicApplyBeach();
			if (b) {
				result.setType(0);
				result.setMessage("申请成功");
			} else {
				result.setType(1);
				result.setMessage("选题已经上报");
			}
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("申请出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productApplyBeach(String xml) {
		Result result = new Result();
		try {
			applyService.productApplyBeach();
			result.setType(0);
			result.setMessage("申请成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result();
			result.setType(1);
			result.setMessage("申请出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String topicRead(String xml) {
		return applyService.topicRead();
	}

	@Override
	public String productRead(String xml) {
		return applyService.productRead();
	}

	@Override
	public String getProductSavedList(String xml) {
		return applyService.getProductSavedList();
	}

	@Override
	public String getProductCallbackList(String xml) {
		return applyService.getProductCallbackList();
	}

	@Override
	public String getProductAuditedList(String xml) {
		return applyService.getProductAuditedList();
	}

	@Override
	public String getProductRefusedList(String xml) {
		return applyService.getProductRefusedList();
	}

	@Override
	public String getProductModifyList(String xml) {
		return applyService.getProductModifyList();
	}

	@Override
	public String getProductFinishingList(String xml) {
		return applyService.getProductFinishingList();
	}

	@Override
	public String getProductTerminateList(String xml) {
		return applyService.getProductTerminateList();
	}

	@Override
	public String getProductFinishedList(String xml) {
		return applyService.getProductFinishedList();
	}

	@Override
	public String productCallback(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productCallback();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("撤回成功");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("撤回出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productDelete(String xml) {
		Result result = new Result();
		try {
			applyService.productDelete();
			result.setType(0);
			result.setMessage("删除成功");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("删除出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productUpdate(String xml) {
		Result result = new Result();
		try {
			applyService.productUpdate();
			result.setType(0);
			result.setMessage("保存完成");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productApply(String xml) {
		Result result = new Result();
		try {
			applyService.productApply();
			result.setType(0);
			result.setMessage("申请完成");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("申请出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productFinish(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productFinish();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("成品上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result();
			result.setType(1);
			result.setMessage("成品上传出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productCallbackBeach(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productCallbackBeach();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("撤回成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result();
			result.setType(1);
			result.setMessage("撤回出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productDeleteBeach(String xml) {
		Result result = new Result();
		try {
			applyService.productDeleteBeach();
			result.setType(0);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("删除出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productISBN(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productISBN();
			result.setType(0);
			if (StringUtils.isNotEmpty(message)) {
				result.setMessage(message);
				result.setType(1);
			} else
				result.setMessage("条码申请成功！");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("条码申请出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productISBNBeach(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productISBNBeach();
			result.setType(0);
			if (StringUtils.isNotEmpty(message)) {
				result.setMessage(message);
				result.setType(1);
			} else
				result.setMessage("条码申请成功！");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("条码申请出錯！");
		}
		return result.asXML(true);
	}
	
	@Override
	public String productCount(String xml) throws Exception  {
		return applyService.productCount();
	}
	
	@Override
	public String getCanUsePrefixByPublishidCount(String xml) {
		return applyService.getCanUsePrefixByPublishidCount();
	}
	
	@Override
	public String productInfoRead(String xml) {
		return applyService.productInfoRead();
	}

	@Override
	public String getBarcodeDownloadList(String xml) {
		return applyService.getBarcodeDownloadList();
	}

	@Override
	public String getProductExportList(String xml) {
		return applyService.getProductExportList();
	}

	@Override
	public String topicApply(String xml) {
		Result result = new Result();
		try {
			applyService.topicApply();
			result.setType(0);
			result.setMessage("申请成功");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("申请出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String getTopicNoISBNList(String xml) {
		return applyService.getTopicNoISBNList();
	}

	@Override
	public String getTopicRefusedList(String xml) {
		return applyService.getTopicRefusedList();
	}

	@Override
	public String topicUpdate(String xml) {
		Result result = new Result();
		try {
			applyService.topicUpdate();
			result.setType(0);
			result.setMessage("更新完成");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result();
			result.setType(1);
			result.setMessage("更新出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String getTopicExportList(String xml) {
		return applyService.getTopicExportList();
	}

	@Override
	public String topicInfoRead(String xml) {
		return applyService.topicInfoRead();
	}

	@Override
	public String productTerminate(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productTerminate();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("终止成功");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("终止出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String productTerminateBeach(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productTerminateBeach();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("终止成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result();
			result.setType(1);
			result.setMessage("终止出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String getProductpublishExportList(String xml) {
		return applyService.getProductpublishExportList();
	}

	@Override
	public String getTopicpublishExportList(String xml) {
		return applyService.getTopicpublishExportList();
	}

	@Override
	public String productReApply(String xml) {
		Result result = new Result();
		try {
			String message = applyService.productReApply();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("申请成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result();
			result.setType(1);
			result.setMessage("申请出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String getTopicTraceList(String xml) {
		return applyService.getTopicTraceList();
	}

	@Override
	public String topicTerminate(String xml) {
		Result result = new Result();
		try {
			String message = applyService.topicTerminate();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("终止成功");
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("终止出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String topicTerminateBeach(String xml) {
		Result result = new Result();
		try {
			String message = applyService.topicTerminateBeach();
			result.setType(0);
			if (StringUtils.isNotEmpty(message))
				result.setMessage(message);
			else
				result.setMessage("终止成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result();
			result.setType(1);
			result.setMessage("终止出錯！");
		}
		return result.asXML(true);
	}

	@Override
	public String getStatisticsExportList(String xml) {
		return applyService.getStatisticsExportList();
	}

	@Override
	public String getExpertAuditExportList(String xml) {
		return applyService.getExpertAuditExportList();
	}

	@Override
	public String getExpertTopicAuditExportList(String xml) {
		return applyService.getExpertTopicAuditExportList();
	}
	
	@Override
	public String getIntegratedExportList(String xml){
		return applyService.getIntegratedExportList();
	}

	@Override
	public String getProductById(String xml) throws Exception {
		return applyService.getProductById();
	}
	
}
