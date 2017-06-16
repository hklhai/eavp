package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.thoughtworks.xstream.XStream;


import cn.edu.ncut.eavp.model.base.BaseModel;

@Entity
@Table(name="V_TRANSFERUSER")
public class TransferuserObj  extends BaseModel implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private Long publishid;
	private Long productid;
	@Id
	private Long transferid;
	private String stagereason;
	private String stagestatus;
	@Temporal(TemporalType.TIMESTAMP)
	private Date stagetime;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public Long getTransferid() {
		return transferid;
	}

	public void setTransferid(Long transferid) {
		this.transferid = transferid;
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
		xstream.alias("TransferuserObj", TransferuserObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
		
	}

}
