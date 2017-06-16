package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_MESSAGESENDRECIEVE database table.
 * 
 */
@Entity
@Table(name="V_MESSAGESENDRECIEVE")
public class MessagesendrecieveObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    private Long orgmessageid;
	@Temporal(TemporalType.DATE)
	private Date createtime;

	private String fromid;

	private String fromname;

	private String fromusername;

	private Boolean isneedreply;

	private String messageattachname;

	private String messageattachurl;

	private String messagename;
	
	private String RETURNMESSAGE;

	private Long messagestatus;
	private String messagecontent;
	private Boolean readflag;

	@Temporal(TemporalType.DATE)
	private Date recievetime;

	private String returnmessage;

	@Temporal(TemporalType.DATE)
	private Date sendtime;

	private String toid;

	private String toname;

	 private Long userid;
	 
	 
	 
	public String getRETURNMESSAGE() {
		return RETURNMESSAGE;
	}

	public void setRETURNMESSAGE(String rETURNMESSAGE) {
		RETURNMESSAGE = rETURNMESSAGE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public MessagesendrecieveObj() {
	}

	public Long getOrgmessageid() {
		return orgmessageid;
	}

	public void setOrgmessageid(Long orgmessageid) {
		this.orgmessageid = orgmessageid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getFromid() {
		return this.fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	public String getFromname() {
		return this.fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public String getFromusername() {
		return this.fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public Boolean getIsneedreply() {
		return this.isneedreply;
	}

	public void setIsneedreply(Boolean isneedreply) {
		this.isneedreply = isneedreply;
	}

	public String getMessageattachname() {
		return this.messageattachname;
	}

	public void setMessageattachname(String messageattachname) {
		this.messageattachname = messageattachname;
	}

	public String getMessageattachurl() {
		return this.messageattachurl;
	}

	public void setMessageattachurl(String messageattachurl) {
		this.messageattachurl = messageattachurl;
	}

	public String getMessagename() {
		return this.messagename;
	}

	public void setMessagename(String messagename) {
		this.messagename = messagename;
	}

	public Long  getMessagestatus() {
		return this.messagestatus;
	}

	public void setMessagestatus(Long messagestatus) {
		this.messagestatus = messagestatus;
	}

	public Boolean getReadflag() {
		return this.readflag;
	}

	public void setReadflag(Boolean readflag) {
		this.readflag = readflag;
	}

	public Date getRecievetime() {
		return this.recievetime;
	}

	public void setRecievetime(Date recievetime) {
		this.recievetime = recievetime;
	}

	public String getReturnmessage() {
		return this.returnmessage;
	}

	public void setReturnmessage(String returnmessage) {
		this.returnmessage = returnmessage;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public String getToid() {
		return this.toid;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	public String getToname() {
		return this.toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	 
	public String getMessagecontent() {
		return messagecontent;
	}

	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("MessagesendrecieveObj", MessagesendrecieveObj.class);
		xstream.setMode(XStream.NO_REFERENCES);			
		
	}
}