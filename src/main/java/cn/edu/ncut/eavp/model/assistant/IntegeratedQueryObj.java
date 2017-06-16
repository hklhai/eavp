package cn.edu.ncut.eavp.model.assistant;

import java.util.Date;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

public class IntegeratedQueryObj extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8691722584130699474L;

	public Map<String, Boolean> producttype;

	public Map<String, Boolean> yeartype;

	public Map<String, Boolean> auditstatus;

	public Map<String, Boolean> stopflag;

	public Map<String, Boolean> pauseflag;

	public Map<String, Boolean> downloadflag;

	public Map<String, Boolean> completionflag;

	public Date starttime;

	public Date endtime;

	public Boolean starttimechecked;

	public Boolean endtimechecked;

	public Map<String, Boolean> timetype;

	public String keyword;

	public Map<String, Boolean> keywordtype;

	public String threeauditor;

	public Map<String, Boolean> threeauditortype;

	public String orgshortname;

	public Map<String, Boolean> orgtype;

	public String author;

	public String startisbn;

	public String endisbn;

	public String topicnum;

	public String publishtype;
	public String made;
	public String otherimportant;

	public String contenttype;

	public String mediatype;

	public String usetype;

	public String publishmethod;

	public String reader;

	public String planguage;

	public Boolean istranslate;

	public String important;

	public String importantindex;
    
	public Map<String, Boolean> getProducttype() {
		return producttype;
	}

	public void setProducttype(Map<String, Boolean> producttype) {
		this.producttype = producttype;
	}

	public Map<String, Boolean> getYeartype() {
		return yeartype;
	}

	public void setYeartype(Map<String, Boolean> yeartype) {
		this.yeartype = yeartype;
	}

	public Map<String, Boolean> getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Map<String, Boolean> auditstatus) {
		this.auditstatus = auditstatus;
	}

	public Map<String, Boolean> getStopflag() {
		return stopflag;
	}

	public void setStopflag(Map<String, Boolean> stopflag) {
		this.stopflag = stopflag;
	}

	public Map<String, Boolean> getPauseflag() {
		return pauseflag;
	}

	public void setPauseflag(Map<String, Boolean> pauseflag) {
		this.pauseflag = pauseflag;
	}

	public Map<String, Boolean> getDownloadflag() {
		return downloadflag;
	}

	public void setDownloadflag(Map<String, Boolean> downloadflag) {
		this.downloadflag = downloadflag;
	}

	public Map<String, Boolean> getCompletionflag() {
		return completionflag;
	}

	public void setCompletionflag(Map<String, Boolean> completionflag) {
		this.completionflag = completionflag;
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

	public Map<String, Boolean> getTimetype() {
		return timetype;
	}

	public void setTimetype(Map<String, Boolean> timetype) {
		this.timetype = timetype;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Map<String, Boolean> getKeywordtype() {
		return keywordtype;
	}

	public void setKeywordtype(Map<String, Boolean> keywordtype) {
		this.keywordtype = keywordtype;
	}

	public String getThreeauditor() {
		return threeauditor;
	}

	public void setThreeauditor(String threeauditor) {
		this.threeauditor = threeauditor;
	}

	public Map<String, Boolean> getThreeauditortype() {
		return threeauditortype;
	}

	public void setThreeauditortype(Map<String, Boolean> threeauditortype) {
		this.threeauditortype = threeauditortype;
	}

	public String getOrgshortname() {
		return orgshortname;
	}

	public void setOrgshortname(String orgshortname) {
		this.orgshortname = orgshortname;
	}

	public Map<String, Boolean> getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(Map<String, Boolean> orgtype) {
		this.orgtype = orgtype;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStartisbn() {
		return startisbn;
	}

	public void setStartisbn(String startisbn) {
		this.startisbn = startisbn;
	}

	public String getEndisbn() {
		return endisbn;
	}

	public void setEndisbn(String endisbn) {
		this.endisbn = endisbn;
	}

	public String getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(String topicnum) {
		this.topicnum = topicnum;
	}

	public String getPublishtype() {
		return publishtype;
	}

	public void setPublishtype(String publishtype) {
		this.publishtype = publishtype;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String getUsetype() {
		return usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

	public String getPublishmethod() {
		return publishmethod;
	}

	public void setPublishmethod(String publishmethod) {
		this.publishmethod = publishmethod;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public String getPlanguage() {
		return planguage;
	}

	public void setPlanguage(String planguage) {
		this.planguage = planguage;
	}

	public Boolean getIstranslate() {
		return istranslate;
	}

	public void setIstranslate(Boolean istranslate) {
		this.istranslate = istranslate;
	}

	public String getImportant() {
		return important;
	}

	public void setImportant(String important) {
		this.important = important;
	}

	public String getImportantindex() {
		return importantindex;
	}

	public void setImportantindex(String importantindex) {
		this.importantindex = importantindex;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public String getOtherimportant() {
		return otherimportant;
	}

	public void setOtherimportant(String otherimportant) {
		this.otherimportant = otherimportant;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("IntegeratedQueryObj", IntegeratedQueryObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
	}
}
