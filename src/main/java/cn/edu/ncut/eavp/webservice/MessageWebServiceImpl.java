package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.edu.ncut.eavp.service.MessageService;
import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.BaseWebService;
import cn.edu.ncut.eavp.webservice.base.Result;

@WebService(endpointInterface = "cn.edu.ncut.eavp.webservice.MessageWebService")
@Service("messageWebService")
@Scope("request")
public class MessageWebServiceImpl implements MessageWebService , BaseWebService {

	@Autowired
	private MessageService messageService;
	@Override
	public BaseService getService() {
		// TODO Auto-generated method stub
		return messageService;
	}
	@Override
	public String getSendMessageList(String xml){
		return messageService.getSendMessageList();
	}
	@Override
	public String getMessageDetail(String xml){
		return messageService.getMessageDetail();
	}
	@Override
	public String getRecieveMessageList(String xml){
		return messageService.getRecieveMessageList();
	}
	@Override
	public String saveMessage(String xml){
		Result result = null;
		try {
			result = messageService.saveMessage();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String sendMessage(String xml){
		Result result = null;
		try {
			result = messageService.sendMessage();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String deleteMessage (String xml){
		Result result = null;
		try {
			result = messageService.deleteMessage();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String upadteMessage (String xml){
		Result result = null;
		try {
			result = messageService.updateMessage();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String downloadFile(String xml) {
		
		return messageService.downloadFile();
	}
	@Override
	public String saveReply(String xml) {
		Result result = null;
		try {
			result = messageService.saveReply();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String saveRead(String xml) {
		Result result = null;
		try {
			result = messageService.saveRead();
		} catch (Exception e) {
			result = new Result();
			result.setType(1);
			result.setMessage("保存出錯！");
			e.printStackTrace();
		}
		return result.asXML(true);
	}
	@Override
	public String getMessage4Update(String xml) {
		return messageService.getMessage4Update();
	}
	@Override
	public String getReplyList(String xml) {
		return messageService.getReplyList();
	}
	@Override
	public String downloadFileByMessageId(String xml){
		return messageService.downloadFileByMessageId();
	}
}
