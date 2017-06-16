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

//专家作品审读	视图Obj	lyy
@Entity
@Table(name="V_EXPERTAUDIT")
public class ExpertAuditObj extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4612190306847412380L;
	
	private Long productid;
	
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
	
	private String provincename;
	
	private String publishid;

	private String iselec;
	
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
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	@Id
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ExpertAuditObj", ExpertAuditObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
	}
}
