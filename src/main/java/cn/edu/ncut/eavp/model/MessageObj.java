package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import cn.edu.ncut.eavp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TB_MESSAGE database table.
 * 
 */
@Entity
@Table(name="TB_MESSAGE")
public class MessageObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MESSAGE_MESSAGEID_GENERATOR", sequenceName="SEQ_MESSAGE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MESSAGE_MESSAGEID_GENERATOR")
	private Long messageid;

	@Temporal(TemporalType.DATE)
	private Date createtime;

	private Boolean isneedreply;

	private String messageattachname;

	private String messageattachurl;

	private String messagecontent;

	private String messagename;

	private Long messagestatus;

	private Long userid;
	@Transient
	private byte[] postdata; 
	@Transient
	private String orgids;

	//bi-directional many-to-one association to OrgmessageObj
	@XStreamOmitField
	@OneToMany(mappedBy="tbMessage",cascade=CascadeType.ALL)
	private List<OrgmessageObj> tbOrgmessages;

	public MessageObj() {
	}

	public Long getMessageid() {
		return this.messageid;
	}

	public void setMessageid(Long messageid) {
		this.messageid = messageid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	public String getMessagecontent() {
		return this.messagecontent;
	}

	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}

	public String getMessagename() {
		return this.messagename;
	}

	public void setMessagename(String messagename) {
		this.messagename = messagename;
	}

	public Long getMessagestatus() {
		return this.messagestatus;
	}

	public void setMessagestatus(Long messagestatus) {
		this.messagestatus = messagestatus;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public byte[] getPostdata() {
		return postdata;
	}

	public void setPostdata(byte[] postdata) {
		this.postdata = postdata;
	}

	public String getOrgids() {
		return orgids;
	}

	public void setOrgids(String orgids) {
		this.orgids = orgids;
	}

	public List<OrgmessageObj> getTbOrgmessages() {
		return this.tbOrgmessages;
	}

	public void setTbOrgmessages(List<OrgmessageObj> tbOrgmessages) {
		this.tbOrgmessages = tbOrgmessages;
	}
	

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("MessageObj", MessageObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
	}

}