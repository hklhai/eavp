package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import cn.edu.ncut.eavp.model.base.BaseModel;

@Entity
@Table(name = "V_TOPICCOUNTGA")
public class TopiccountgaObj extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long publishcount;

	private String orgfullname;

	@Id
	private String orgid;

	private Long publishtype;

	private Long baktime;

	public long getPublishcount() {
		return publishcount;
	}

	public void setPublishcount(long publishcount) {
		this.publishcount = publishcount;
	}

	public String getOrgfullname() {
		return orgfullname;
	}

	public void setOrgfullname(String orgfullname) {
		this.orgfullname = orgfullname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
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

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("TopiccountgaObj", TopiccountgaObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
	}

}
