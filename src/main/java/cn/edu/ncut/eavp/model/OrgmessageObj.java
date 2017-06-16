package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;


/**
 * The persistent class for the TB_ORGMESSAGE database table.
 * 
 */
@Entity
@Table(name="TB_ORGMESSAGE")
public class OrgmessageObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ORGMESSAGE_ORGMESSAGEID_GENERATOR", allocationSize=1,sequenceName="SEQ_ORGMESSAGE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ORGMESSAGE_ORGMESSAGEID_GENERATOR")
	private long orgmessageid;

	private Boolean readflag;

	@Temporal(TemporalType.DATE)
	private Date recievetime;

	private String returnmessage;

	@Temporal(TemporalType.DATE)
	private Date sendtime;

	private Long userid;
	@Transient
	private Long messageid;
	//bi-directional many-to-one association to MessageObj
	@ManyToOne
	@JoinColumn(name="MESSAGEID")
	private MessageObj tbMessage;
	


	//bi-directional many-to-one association to OrganizationinfoObj
	@ManyToOne
	@JoinColumn(name="FROMID")
	private OrganizationinfoObj tbOrganizationinfo1;

	//bi-directional many-to-one association to OrganizationinfoObj
	@ManyToOne
	@JoinColumn(name="TOID")
	private OrganizationinfoObj tbOrganizationinfo2;

	public OrgmessageObj() {
	}

	public long getOrgmessageid() {
		return this.orgmessageid;
	}

	public void setOrgmessageid(long orgmessageid) {
		this.orgmessageid = orgmessageid;
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

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public MessageObj getTbMessage() {
		return this.tbMessage;
	}

	public void setTbMessage(MessageObj tbMessage) {
		this.tbMessage = tbMessage;
	}

	public OrganizationinfoObj getTbOrganizationinfo1() {
		return this.tbOrganizationinfo1;
	}

	public void setTbOrganizationinfo1(OrganizationinfoObj tbOrganizationinfo1) {
		this.tbOrganizationinfo1 = tbOrganizationinfo1;
	}

	public OrganizationinfoObj getTbOrganizationinfo2() {
		return this.tbOrganizationinfo2;
	}

	public void setTbOrganizationinfo2(OrganizationinfoObj tbOrganizationinfo2) {
		this.tbOrganizationinfo2 = tbOrganizationinfo2;
	}


	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("OrgmessageObj", OrgmessageObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
		
	}

}