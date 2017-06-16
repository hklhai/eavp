package cn.edu.ncut.eavp.model.assistant;

import java.util.Date;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

//专家选题审读Obj
public class ExpertTopicAuditObj extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4099059958383552119L;

	public Map<String, Boolean> producttype;
	
	public Date starttime;

	public Date endtime;
	
	public Boolean starttimechecked;

	public Boolean endtimechecked;
	
	
	public Map<String, Boolean> getProducttype() {
		return producttype;
	}
	public void setProducttype(Map<String, Boolean> producttype) {
		this.producttype = producttype;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Boolean getStarttimechecked() {
		return starttimechecked;
	}
	public void setStarttimechecked(Boolean starttimechecked) {
		this.starttimechecked = starttimechecked;
	}
	public Boolean getEndtimechecked() {
		return endtimechecked;
	}
	public void setEndtimechecked(Boolean endtimechecked) {
		this.endtimechecked = endtimechecked;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ExpertTopicAuditObj", ExpertTopicAuditObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
	}

}
