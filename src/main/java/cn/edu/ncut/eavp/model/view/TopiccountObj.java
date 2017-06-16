package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import cn.edu.ncut.eavp.model.base.BaseModel;

@Entity
@Table(name = "V_TOPICCOUNT")
public class TopiccountObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = -5601626196907939389L;
	private long publishcount;
	 
	private String publishname;
	 
	@Id
	@XStreamOmitField
	public TopiccountId topiccountId;
	
	@Transient
	private String publishid;
	@Transient
	private Long publishtype;
	@Transient
	private Long baktime;
	@Transient
	private String topicstatus;
	
	
	public String getPublishid() {
		return publishid;
	}


	public void setPublishid(String publishid) {
		this.publishid = publishid;
	}


	public Long getPublishtype() {
		return publishtype;
	}


	public void setPublishtype(Long publishtype) {
		this.publishtype = publishtype;
	}


	public Long getBaktime() {
		return baktime;
	}


	public void setBaktime(Long baktime) {
		this.baktime = baktime;
	}


	public String getTopicstatus() {
		return topicstatus;
	}


	public void setTopicstatus(String topicstatus) {
		this.topicstatus = topicstatus;
	}


	public void setTopiccountId(TopiccountId topiccountId) {
		this.topiccountId = topiccountId;
	}


	public TopiccountId getTopiccountId() {
		return topiccountId;
	}

	 
	public String getPublishname() {
		return publishname;
	}

	public void setPublishname(String publishname) {
		this.publishname = publishname;
	}

	 
 
	public long getPublishcount() {
		return publishcount;
	}

	public void setPublishcount(long publishcount) {
		this.publishcount = publishcount;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("TopiccountObj", TopiccountObj.class);
		xstream.setMode(XStream.NO_REFERENCES);

	}

}
