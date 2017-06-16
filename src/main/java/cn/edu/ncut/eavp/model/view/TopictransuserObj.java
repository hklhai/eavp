package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.thoughtworks.xstream.XStream;


import cn.edu.ncut.eavp.model.base.BaseModel;

@Entity
@Table(name="V_TOPICTRANSUSER")
public class TopictransuserObj  extends BaseModel implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private Long publishid;
	private Long topicid;
	@Id
	private Long topictransferid;
	private String stagereason;
	private String stagestatus;
	@Temporal(TemporalType.TIMESTAMP)
	private Date stagetime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getPublishid() {
		return publishid;
	}

	public void setPublishid(Long publishid) {
		this.publishid = publishid;
	}

	 
	public Long getTopicid() {
		return topicid;
	}

	public void setTopicid(Long topicid) {
		this.topicid = topicid;
	}

	public Long getTopictransferid() {
		return topictransferid;
	}

	public void setTopictransferid(Long topictransferid) {
		this.topictransferid = topictransferid;
	}

	public String getStagereason() {
		return stagereason;
	}

	public void setStagereason(String stagereason) {
		this.stagereason = stagereason;
	}

	public String getStagestatus() {
		return stagestatus;
	}

	public void setStagestatus(String stagestatus) {
		this.stagestatus = stagestatus;
	}

	public Date getStagetime() {
		return stagetime;
	}

	public void setStagetime(Date stagetime) {
		this.stagetime = stagetime;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("TransferuserObj", TopictransuserObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
		
	}

}
