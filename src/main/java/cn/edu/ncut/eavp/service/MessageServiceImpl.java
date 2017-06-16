package cn.edu.ncut.eavp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ncut.eavp.dao.MessageDao;
import cn.edu.ncut.eavp.dao.MessagesendrecieveDao;
import cn.edu.ncut.eavp.dao.OrganizationinfoDao;
import cn.edu.ncut.eavp.dao.OrgmessageDao;
import cn.edu.ncut.eavp.dao.UserDao;
import cn.edu.ncut.eavp.model.MessageObj;
import cn.edu.ncut.eavp.model.OrganizationinfoObj;
import cn.edu.ncut.eavp.model.OrgmessageObj;
import cn.edu.ncut.eavp.model.ProductObj;
import cn.edu.ncut.eavp.model.UserObj;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.MessagesendrecieveObj;
import cn.edu.ncut.eavp.service.base.BaseServiceImpl;
import cn.edu.ncut.eavp.webservice.base.ObjectResult;
import cn.edu.ncut.eavp.webservice.base.Result;

@Transactional
@Service("messageService")
@Scope("request")
public class MessageServiceImpl extends BaseServiceImpl<Object> implements
		MessageService {
	private final static Logger logger = Logger
			.getLogger(MessageServiceImpl.class);
	@Value("${cn.edu.ncut.eavp.uploadpath}")
	private String path;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private OrgmessageDao orgmessageDao;
	@Autowired
	private OrganizationinfoDao organizationinfoDao;
	@Autowired
	private MessagesendrecieveDao messagesendrecieveDao;

	@Autowired
	private UserDao userDao;

	@Override
	public String getSendMessageList() {
		BaseModel result;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String userid = parameters.getParams().get("userid");

			String fuzzy = parameters.getParams().get("fuzzy");
			String year = parameters.getParams().get("year");
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");

			String where = " userid=:userid and createtime>=to_date(:year1,'yyyy-MM-dd') and createtime<to_date(:year2,'yyyy-MM-dd') ";
			params.put("userid", Long.parseLong(userid));
			params.put("year1", year + "-01-01");
			params.put("year2", String.valueOf(Integer.parseInt(year) + 1)
					+ "-01-01");
			if (!fuzzy.equals("")) {

				params.put("fuzzy", fuzzy);
				where += " and (instr(messagename,:fuzzy)>0 )";
				result = messageDao.getSendMessageGridData(firstResult,
						maxResult, where, params, orderby);
			} else
				result = messageDao.getSendMessageGridData(firstResult,
						maxResult, where, params, orderby);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}

	@Override
	public String getRecieveMessageList() {
		BaseModel result;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			int firstResult = Integer.parseInt(parameters.getParams().get(
					"firstResult"));
			int maxResult = Integer.parseInt(parameters.getParams().get(
					"maxResult"));
			String userid = parameters.getParams().get("userid");

			String fuzzy = parameters.getParams().get("fuzzy");
			String year = parameters.getParams().get("year");
			String orderby = " ORDER BY "
					+ (String) parameters.getParams().get("orderby");
			UserObj user = userDao.find(new BigDecimal(userid));

			String where = " toid=:toid and createtime>=to_date(:year1,'yyyy-MM-dd') and createtime<to_date(:year2,'yyyy-MM-dd') and messagestatus in (1,2) ";
			params.put("toid", user.getTbOrganizationinfo().getOrgid());
			params.put("year1", year + "-01-01");
			params.put("year2", String.valueOf(Integer.parseInt(year) + 1)
					+ "-01-01");
			if (!fuzzy.equals("")) {

				params.put("fuzzy", fuzzy);
				where += " and (instr(messagename,:fuzzy)>0 )";
				result = messagesendrecieveDao.getRecieveMessageGridData(
						firstResult, maxResult, where, params, orderby);
			} else
				result = messagesendrecieveDao.getRecieveMessageGridData(
						firstResult, maxResult, where, params, orderby);

		} catch (Exception e) {
			logger.error(e);
			Result res = new Result();
			res.setMessage("获取列表异常！");
			res.setType(1);
			result = res;
		}
		return result.asXML(true);
	}

	@Override
	public String getMessageDetail() {
		ObjectResult<MessagesendrecieveObj> result = new ObjectResult<MessagesendrecieveObj>();
		try {
			Map<String, String> frontParams = parameters.getParams();
			long id = new Long(frontParams.get("orgmessageid"));
			MessagesendrecieveObj resultObject = messagesendrecieveDao.find(id);

			if (null != resultObject) {
				result.setResultObject(resultObject);
				result.setType(0);
			} else {
				result.setType(1);
				result.setMessage("没有查询到消息数据！");
			}
		} catch (Exception e) {
			logger.error(e);
			result.setType(1);
			result.setMessage("查询消息信息出现错误！");
		}
		return result.asXML(true);
	}

	private Result save(long status) {
		Result result = new Result();
		MessageObj mobj = (MessageObj) parameters.getDataObject();
		mobj.setCreatetime(new Date());
		mobj.setMessagestatus(status);
		messageDao.save(mobj);
		if (mobj.getOrgids() != null && !"".equals(mobj.getOrgids())) {
			String[] orgids = mobj.getOrgids().split(";");
			for (String orgid : orgids) {
				OrgmessageObj oobj = new OrgmessageObj();
				oobj.setReadflag(false);
				oobj.setReadflag(false);
				oobj.setTbMessage(mobj);
				oobj.setSendtime(new Date());
				UserObj fromuser = userDao
						.find(new BigDecimal(mobj.getUserid()));

				OrganizationinfoObj from = fromuser.getTbOrganizationinfo();
				oobj.setTbOrganizationinfo1(from);
				oobj.setTbOrganizationinfo2(organizationinfoDao.find(orgid
						.toString()));
				orgmessageDao.save(oobj);
			}

		}
		saveFile(mobj);
		result.setMessage(mobj.getMessageid() + "");
		result.setType(0);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result sendMessage() {
		Result result;
		MessageObj mobj = (MessageObj) parameters.getDataObject();
		String orgids = mobj.getOrgids();
		if (mobj.getMessageid() != null && !"".equals(mobj.getMessageid())) {
			mobj = messageDao.find(mobj.getMessageid());
			mobj.setMessagestatus((long) 1);
			messageDao.update(mobj);
			List<OrgmessageObj> list = mobj.getTbOrgmessages();
			if (orgids != null && !"".equals(orgids)) {
				String[] orgid = orgids.split(";");
				addNewOrg(mobj, orgid, list);
				deleteOrg(orgid, list);

			} else {
				String[] orgid = new String[0];
				addNewOrg(mobj, orgid, list);
				deleteOrg(orgid, list);
			}
			saveFile(mobj);
			result = new Result();
			result.setType(0);
			result.setMessage("保存成功");
		} else {
			result = save((long) 1);
		}
		return result;
	}

	@Override
	public Result deleteMessage() {
		Result result = new Result();
		Map<String, String> frontParams = parameters.getParams();
		String ids = frontParams.get("ids");
		String[] id = ids.split(";");
		for (String tempid : id) {
			MessageObj obj = messageDao.find(Long.parseLong(tempid));
			messageDao.delete(Long.parseLong(tempid));
			if (obj.getMessageattachname() != null
					&& !"".equals(obj.getMessageattachname())) {
				File f = new File(path
						+ "/"
						+ tempid
						+ obj.getMessageattachname().substring(
								obj.getMessageattachname().lastIndexOf(".")));
				if (f.exists())
					f.delete();
			}
		}
		result.setType(0);
		return result;
	}

	@Override
	public Result saveMessage() {
		// TODO Auto-generated method stub
		return save((long) 0);
	}

	private void addNewOrg(MessageObj mobj, String[] orgid,
			List<OrgmessageObj> list) {

		for (String id : orgid) {
			boolean flag = false;
			for (OrgmessageObj obj : list) {
				if (obj.getTbOrganizationinfo2().getOrgid().equals(id)) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				OrgmessageObj obj = new OrgmessageObj();
				obj.setReadflag(false);
				obj.setSendtime(new Date());
				UserObj fromuser = userDao
						.find(new BigDecimal(mobj.getUserid()));
				obj.setTbOrganizationinfo1(fromuser.getTbOrganizationinfo());
				obj.setTbOrganizationinfo2(organizationinfoDao.find(id));
				obj.setSendtime(new Date());
				obj.setTbMessage(mobj);
				orgmessageDao.save(obj);
			}
		}
	}

	private void deleteOrg(String[] orgid, List<OrgmessageObj> list) {

		for (OrgmessageObj obj : list) {
			boolean flag = false;
			for (String id : orgid) {
				if (obj.getTbOrganizationinfo2().getOrgid().equals(id)) {
					flag = true;
					break;
				}

			}
			if (!flag) {
				orgmessageDao.delete(obj.getOrgmessageid());
			}
		}
	}

	private void saveFile(MessageObj mobj) {
		if (mobj.getPostdata() != null && mobj.getPostdata().length > 0) {
			File f = new File(path);

			if (!f.exists())
				f.mkdirs();
			String filename = path
					+ "/"
					+ mobj.getMessageid()
					+ mobj.getMessageattachname().substring(
							mobj.getMessageattachname().lastIndexOf("."));
			FileOutputStream outputStream;
			try {
				outputStream = new FileOutputStream(new File(filename));
				outputStream.write(mobj.getPostdata());
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}

		}
	}

	@Override
	public String downloadFile() {
		ObjectResult<MessageObj> result = new ObjectResult<MessageObj>();
		Map<String, String> frontParams = parameters.getParams();
		long id = new Long(frontParams.get("orgmessageid"));
		OrgmessageObj obj = orgmessageDao.find(id);
		MessageObj mobj = obj.getTbMessage();
		String filename = path
				+ "/"
				+ mobj.getMessageid()
				+ mobj.getMessageattachname().substring(
						mobj.getMessageattachname().lastIndexOf("."));
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			byte[] b = new byte[fileInputStream.available()];
			fileInputStream.read(b);
			MessageObj m = new MessageObj();
			m.setMessageattachname(mobj.getMessageattachname());
			m.setPostdata(b);
			result.setResultObject(m);
			result.setType(0);
			fileInputStream.close();
			return result.asXML(true);

		} catch (Exception e) {
			logger.error(e.toString());
			result.setType(1);
			result.setMessage("没有找到需要下载的附件！");
		}	
		return result.asXML(true);

	}

	@Override
	public String downloadFileByMessageId() {
		ObjectResult<MessageObj> result = new ObjectResult<MessageObj>();
		Map<String, String> frontParams = parameters.getParams();
		long id = new Long(frontParams.get("messageid"));
		MessageObj mobj = messageDao.find(id);
		String filename = path
				+ "/"
				+ mobj.getMessageid()
				+ mobj.getMessageattachname().substring(
						mobj.getMessageattachname().lastIndexOf("."));
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			byte[] b = new byte[fileInputStream.available()];
			fileInputStream.read(b);
			MessageObj m = new MessageObj();
			m.setMessageattachname(mobj.getMessageattachname());
			m.setPostdata(b);
			result.setResultObject(m);
			result.setType(0);
			fileInputStream.close();
			return result.asXML(true);

		} catch (Exception e) {
			logger.error(e.toString());
		}

		result.setType(0);
		result.setMessage("没有找到需要下载的附件！");
		return result.asXML(true);

	}

	@Override
	public Result saveReply() {
		Result result = new Result();
		MessagesendrecieveObj tomobj = (MessagesendrecieveObj) parameters
				.getDataObject();
		OrgmessageObj omobj = orgmessageDao.find(tomobj.getOrgmessageid());
		omobj.setUserid(tomobj.getUserid());
		omobj.setReadflag(true);
		omobj.setReturnmessage(tomobj.getReturnmessage());
		orgmessageDao.update(omobj);
		MessageObj mobj = omobj.getTbMessage();
		mobj.setMessagestatus((long) 2);
		messageDao.update(mobj);
		result.setType(0);
		return result;
	}

	@Override
	public Result saveRead() {
		Result result = new Result();
		MessagesendrecieveObj tomobj = (MessagesendrecieveObj) parameters
				.getDataObject();
		OrgmessageObj omobj = orgmessageDao.find(tomobj.getOrgmessageid());
		omobj.setRecievetime(new Date());
		omobj.setUserid(tomobj.getUserid());
		omobj.setReadflag(true);
		omobj.setReturnmessage(tomobj.getReturnmessage());
		orgmessageDao.update(omobj);
		result.setType(0);
		return result;
	}

	@Override
	public String getMessage4Update() {
		ObjectResult<MessageObj> result = new ObjectResult<MessageObj>();
		Map<String, String> frontParams = parameters.getParams();
		long id = new Long(frontParams.get("messageid"));
		MessageObj obj = messageDao.find(id);
		List<OrgmessageObj> list = obj.getTbOrgmessages();
		String orgids = "";
		for (OrgmessageObj org : list) {
			orgids += org.getTbOrganizationinfo2().getOrgid() + ";";
		}
		if (!"".equals(orgids))
			obj.setOrgids(orgids.substring(0, orgids.length() - 1));
		result.setResultObject(obj);
		return result.asXML(true);
	}

	@Override
	public Result updateMessage() {
		// TODO Auto-generated method stub
		return update((long) 0);
	}

	private Result update(long status) {
		Result result = new Result();
		MessageObj baseObj = (MessageObj) parameters.getDataObject();

		MessageObj mobj = messageDao.find(baseObj.getMessageid());
		List<OrgmessageObj> list = mobj.getTbOrgmessages();
		mobj.setOrgids(baseObj.getOrgids());
		mobj.setMessageattachname(baseObj.getMessageattachname());
		mobj.setPostdata(baseObj.getPostdata());
		mobj.setMessagename(baseObj.getMessagename());
		mobj.setMessagecontent(baseObj.getMessagecontent());

		String orgids = mobj.getOrgids();
		messageDao.update(mobj);

		if (orgids != null && !"".equals(orgids)) {
			String[] orgid = orgids.split(";");
			addNewOrg(mobj, orgid, list);
			deleteOrg(orgid, list);
		} else {
			String[] orgid = new String[0];
			addNewOrg(mobj, orgid, list);
			deleteOrg(orgid, list);
		}
		saveFile(mobj);
		result = new Result();
		result.setType(0);
		result.setMessage("保存成功");

		return result;
	}

	@Override
	public String getReplyList() {
		MessageObj obj = (MessageObj) parameters.getDataObject();
		List<List<String>> list = new ArrayList<List<String>>();
		ObjectResult<List<List<String>>> result = new ObjectResult<List<List<String>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		params.put("messageid", obj.getMessageid());
		List<OrgmessageObj> tempList = orgmessageDao.findAll(
				"o.tbMessage.messageid=:messageid", params, orderby);
		for (OrgmessageObj oobj : tempList) {
			List<String> itemlist = new ArrayList<String>();
			itemlist.add(oobj.getTbOrganizationinfo2().getOrgfullname());
			itemlist.add(oobj.getReturnmessage() == null ? "" : oobj
					.getReturnmessage());
			list.add(itemlist);
		}
		result.setResultObject(list);
		return result.asXML(true);
	}

}
