package cn.edu.ncut.eavp.webservice;

import javax.jws.WebService;

@WebService
public interface MessageWebService {

	String getSendMessageList(String xml);

	String saveMessage(String xml);

	String sendMessage(String xml);

	String deleteMessage(String xml);

	String upadteMessage(String xml);

	String getRecieveMessageList(String xml);

	String getMessageDetail(String xml);

	String downloadFile(String xml);

	String saveReply(String xml);

	String saveRead(String xml);

	String getMessage4Update(String xml);

	String getReplyList(String xml);

	String downloadFileByMessageId(String xml);

}
