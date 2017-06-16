package cn.edu.ncut.eavp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

@Entity
@Table(name="TB_TOPICSERIALNUM")
public class TopicserialnumObj extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -6461875685781823931L;
	
	@Id
	@SequenceGenerator(name="TB_TOPICSERIALNUM_TOPICSERIALNUMID_GENERATOR",allocationSize=1, sequenceName="SEQ_TOPICSERIALNUM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TOPICSERIALNUM_TOPICSERIALNUMID_GENERATOR")
	private Long topicserialnumid;
	
	private String publishid;
	
	private Long year;
	
	private Long serialnum;
	
	private Boolean iselec;

	public Long getTopicserialnumid() {
		return topicserialnumid;
	}

	public void setTopicserialnumid(Long topicserialnumid) {
		this.topicserialnumid = topicserialnumid;
	}

	public String getPublishid() {
		return publishid;
	}

	public void setPublishid(String publishid) {
		this.publishid = publishid;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getSerialnum() {
		return serialnum;
	}
	

	public Boolean getIselec() {
		return iselec;
	}

	public void setIselec(Boolean iselec) {
		this.iselec = iselec;
	}

	public void setSerialnum(Long serialnum) {
		this.serialnum = serialnum;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("TopicserialnumObj", TopicObj.class);
		xstream.setMode(XStream.NO_REFERENCES);		
	}

}
