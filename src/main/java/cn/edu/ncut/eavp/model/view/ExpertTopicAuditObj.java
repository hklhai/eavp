package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thoughtworks.xstream.XStream;
import cn.edu.ncut.eavp.model.base.BaseModel;

////专家选题审读	视图Obj	lyy
@Entity
@Table(name="V_EXPERTTOPICAUDIT")
public class ExpertTopicAuditObj extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941393439470924108L;

	private Long topicid;
	
/*	private Long rnum;

	public Long getRnum() {
		return rnum;
	}
	public void setRnum(Long rnum) {
		this.rnum = rnum;
	}*/
	
	private String topicname;
	
	private String contentabstract;

	private String orgfullname;
	
	private String publishtype;
	
	private Date createtime;
	
	private String iselec;
	
	private String publishid;
	
	private String provincename;
	
	private String  contenttype;

	
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public String getIselec() {
		return iselec;
	}
	public void setIselec(String iselec) {
		this.iselec = iselec;
	}
	public String getPublishid() {
		return publishid;
	}
	public void setPublishid(String publishid) {
		this.publishid = publishid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Id
	public Long getTopicid() {
		return topicid;
	}
	public void setTopicid(Long topicid) {
		this.topicid = topicid;
	}
	public String getTopicname() {
		return topicname;
	}
	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
	public String getContentabstract() {
		return contentabstract;
	}
	public void setContentabstract(String contentabstract) {
		this.contentabstract = contentabstract;
	}
	public String getOrgfullname() {
		return orgfullname;
	}
	public void setOrgfullname(String orgfullname) {
		this.orgfullname = orgfullname;
	}
	public String getPublishtype() {
		return publishtype;
	}
	public void setPublishtype(String publishtype) {
		this.publishtype = publishtype;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	
	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ExpertTopicAuditObj", ExpertTopicAuditObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
	}
}
