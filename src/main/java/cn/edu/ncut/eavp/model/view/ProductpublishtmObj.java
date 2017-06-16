package cn.edu.ncut.eavp.model.view;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;
@Entity
@Table(name="V_PRODUCTPUBLISHTM")
public class ProductpublishtmObj extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long productid;

	private String auditendsuggestion;

	private String auditonename;

	private String auditthreename;

	private String audittwoname;

	private String author;

	private BigDecimal capacity;

	private String capacityunit;

	private String contentabstract;

	private String contenttype;

	@Temporal(TemporalType.DATE)
	@Column(name="createtime")
	private Date applytime;

	private BigDecimal dimensions;

	private String important;
	private Long iselec;
	

	private String importantindex;

	private Boolean ismodify;

	private String ispublished;
	private Boolean islocked;

	private Boolean issuspend;

	private String istranslate;

	private Boolean isyear;

	private String languageother;

	private String made;

	private String mediatype;

	private String mediatypeother;

	private String otherimportant;

	private String planguage;

	private BigDecimal pmonth;

	private String productisbn;

	private String productstatus;

	private BigDecimal psize;

	private String publishid;

	private String publishmethod;
	
	private String sponsor;
	
	private String managename;

	@Temporal(TemporalType.DATE)
	private Date publishtime;

	private String publishtype;

	private String reader;

	private String topicname;

	private String userid;

	private String usetype;
	private String publishname;
	private String topicnum;
	public String provinceid;
	//条码用户增加最后审核时间    lh   2015年1月13日18:12:23
	public String stagetime;
	
	private String topicnumberself;

	private String seriesname;	
	private String otherimportantindex;
	private String backup1;
	private String backup2;
	private String backup3;
	private String backup4;
	private String backup5;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStagetime() {
		return stagetime;
	}

	public void setStagetime(String stagetime) {
		this.stagetime = stagetime;
	}

	public String getOtherimportantindex() {
		return otherimportantindex;
	}

	public void setOtherimportantindex(String otherimportantindex) {
		this.otherimportantindex = otherimportantindex;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public String getBackup4() {
		return backup4;
	}

	public void setBackup4(String backup4) {
		this.backup4 = backup4;
	}

	public String getBackup5() {
		return backup5;
	}

	public void setBackup5(String backup5) {
		this.backup5 = backup5;
	}

	private String importantbakcode;
	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getTopicnum() {
		return topicnum;
	}

	public Long getIselec() {
		return iselec;
	}

	public void setIselec(Long iselec) {
		this.iselec = iselec;
	}

	public void setTopicnum(String topicnum) {
		this.topicnum = topicnum;
	}

	public String getPublishname() {
		return publishname;
	}

	public void setPublishname(String publishname) {
		this.publishname = publishname;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getAuditendsuggestion() {
		return auditendsuggestion;
	}

	
	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getManagename() {
		return managename;
	}

	public void setManagename(String managename) {
		this.managename = managename;
	}

	public void setAuditendsuggestion(String auditendsuggestion) {
		this.auditendsuggestion = auditendsuggestion;
	}

	public String getAuditonename() {
		return auditonename;
	}

	public void setAuditonename(String auditonename) {
		this.auditonename = auditonename;
	}

	public String getAuditthreename() {
		return auditthreename;
	}

	public void setAuditthreename(String auditthreename) {
		this.auditthreename = auditthreename;
	}

	public String getAudittwoname() {
		return audittwoname;
	}

	public void setAudittwoname(String audittwoname) {
		this.audittwoname = audittwoname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public String getCapacityunit() {
		return capacityunit;
	}

	public void setCapacityunit(String capacityunit) {
		this.capacityunit = capacityunit;
	}

	public String getContentabstract() {
		return contentabstract;
	}

	public void setContentabstract(String contentabstract) {
		this.contentabstract = contentabstract;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}



	public Date getApplytime() {
		return applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}

	public BigDecimal getDimensions() {
		return dimensions;
	}

	public void setDimensions(BigDecimal dimensions) {
		this.dimensions = dimensions;
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

	public Boolean getIsmodify() {
		return ismodify;
	}

	public void setIsmodify(Boolean ismodify) {
		this.ismodify = ismodify;
	}

	public String getIspublished() {
		return ispublished;
	}

	public void setIspublished(String ispublished) {
		this.ispublished = ispublished;
	}

	public Boolean getIslocked() {
		return islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	public Boolean getIssuspend() {
		return issuspend;
	}

	public void setIssuspend(Boolean issuspend) {
		this.issuspend = issuspend;
	}

	public String getIstranslate() {
		return istranslate;
	}

	public void setIstranslate(String istranslate) {
		this.istranslate = istranslate;
	}

	public Boolean getIsyear() {
		return isyear;
	}

	public void setIsyear(Boolean isyear) {
		this.isyear = isyear;
	}

	public String getLanguageother() {
		return languageother;
	}

	public void setLanguageother(String languageother) {
		this.languageother = languageother;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String getMediatypeother() {
		return mediatypeother;
	}

	public void setMediatypeother(String mediatypeother) {
		this.mediatypeother = mediatypeother;
	}

	public String getOtherimportant() {
		return otherimportant;
	}

	public void setOtherimportant(String otherimportant) {
		this.otherimportant = otherimportant;
	}

	public String getPlanguage() {
		return planguage;
	}

	public void setPlanguage(String planguage) {
		this.planguage = planguage;
	}

	public BigDecimal getPmonth() {
		return pmonth;
	}

	public void setPmonth(BigDecimal pmonth) {
		this.pmonth = pmonth;
	}

	public String getProductisbn() {
		return productisbn;
	}

	public void setProductisbn(String productisbn) {
		this.productisbn = productisbn;
	}

	public String getProductstatus() {
		return productstatus;
	}

	public void setProductstatus(String productstatus) {
		this.productstatus = productstatus;
	}

	public BigDecimal getPsize() {
		return psize;
	}

	public void setPsize(BigDecimal psize) {
		this.psize = psize;
	}

	public String getPublishid() {
		return publishid;
	}

	public void setPublishid(String publishid) {
		this.publishid = publishid;
	}

	public String getPublishmethod() {
		return publishmethod;
	}

	public void setPublishmethod(String publishmethod) {
		this.publishmethod = publishmethod;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public String getPublishtype() {
		return publishtype;
	}

	public void setPublishtype(String publishtype) {
		this.publishtype = publishtype;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsetype() {
		return usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ProductpublishObj", ProductpublishtmObj.class);
		xstream.setMode(XStream.NO_REFERENCES);			
		
	}

	public String getTopicnumberself() {
		return topicnumberself;
	}

	public void setTopicnumberself(String topicnumberself) {
		this.topicnumberself = topicnumberself;
	}

	public String getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}

	public String getImportantbakcode() {
		return importantbakcode;
	}

	public void setImportantbakcode(String importantbakcode) {
		this.importantbakcode = importantbakcode;
	}

}
