package cn.edu.ncut.eavp.model.assistant;

import java.util.Date;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;


//专家作品审读Obj
public class ExpertAuditObj extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8981386479304128571L;
	
	public Map<String, Boolean> producttype;
	
	//public Map<String, Boolean> yeartype;
	
	public Date starttime;

	public Date endtime;
	
	public Boolean starttimechecked;

	public Boolean endtimechecked;
	
	//public Map<String, Boolean> timetype;
	
	
	public Map<String, Boolean> getProducttype() {
		return producttype;
	}

	public void setProducttype(Map<String, Boolean> producttype) {
		this.producttype = producttype;
	}
	
	/*
	public Map<String, Boolean> getYeartype() {
		return yeartype;
	}

	public void setYeartype(Map<String, Boolean> yeartype) {
		this.yeartype = yeartype;
	}
	*/

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
	
	/*
	public Map<String, Boolean> getTimetype() {
		return timetype;
	}

	public void setTimetype(Map<String, Boolean> timetype) {
		this.timetype = timetype;
	}
	*/
	
	
	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ExpertAuditObj", ExpertAuditObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
	}
	
}
