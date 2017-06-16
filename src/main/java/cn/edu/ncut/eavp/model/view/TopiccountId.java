package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TopiccountId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5441502862697606871L;
	private String publishid;
	private Long publishtype;
	private Long baktime;
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
	
	

}
