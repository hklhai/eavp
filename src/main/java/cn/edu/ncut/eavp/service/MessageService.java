package cn.edu.ncut.eavp.service;

import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.service.base.BaseService;
import cn.edu.ncut.eavp.webservice.base.Result;

public interface MessageService extends BaseService {
	String getSendMessageList();
	String getRecieveMessageList();
	String getMessageDetail();
	Result saveMessage();
	Result sendMessage();
	Result deleteMessage();
	String downloadFile();
	Result saveReply();
	Result saveRead();
	String getMessage4Update();
	Result updateMessage();
	String getReplyList();
	String downloadFileByMessageId();

}
