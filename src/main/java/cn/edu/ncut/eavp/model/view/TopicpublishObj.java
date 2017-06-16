package cn.edu.ncut.eavp.model.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

@Entity
@Table(name = "V_TOPICPUBLISH")
public class TopicpublishObj extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long topicid;

	private String author;

	private BigDecimal capacity;

	private String capacityunit;

	private String contentabstract;

	private String contenttype;

	private Long baktime;

	private BigDecimal dimensions;

	private String important;

	private String importantindex;

	private String istranslate;

	private BigDecimal isyear;

	private String languageother;

	private String made;

	private String mediatype;

	private String mediatypeother;
	@Transient
	private String topicyear;

	private String otherimportant;

	private String planguage;

	private BigDecimal pmonth;

	private BigDecimal psize;

	private String publishid;

	private String publishmethod;

	@Temporal(TemporalType.DATE)
	private Date publishtime;

	private String publishtype;

	private String reader;

	private String topicname;

	private String usetype;

	private long iselec;

	private String topicnum;

	private String publishname;

	private String topicstatus;

	private String topicnumberself;

	private String seriesname;

	private String importantbakcode;
	private String otherimportantindex;
	private String backup1;
	private String backup2;
	private String backup3;
	private String backup4;
	private String backup5;
	

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

	@Temporal(TemporalType.DATE)
	@Column(name = "createtime")
	private Date applytime;

	public long getTopicid() {
		return topicid;
	}

	public void setTopicid(long topicid) {
		this.topicid = topicid;
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

	public String getTopicyear() {
		return topicyear;
	}

	public void setTopicyear(String topicyear) {
		this.topicyear = topicyear;
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

	public Long getBaktime() {
		return baktime;
	}

	public void setBaktime(Long baktime) {
		this.baktime = baktime;
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

	public String getIstranslate() {
		return istranslate;
	}

	public void setIstranslate(String istranslate) {
		this.istranslate = istranslate;
	}

	public BigDecimal getIsyear() {
		return isyear;
	}

	public void setIsyear(BigDecimal isyear) {
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

	public String getUsetype() {
		return usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

	public long getIselec() {
		return iselec;
	}

	public void setIselec(long iselec) {
		this.iselec = iselec;
	}

	public String getPublishname() {
		return publishname;
	}

	public void setPublishname(String publishname) {
		this.publishname = publishname;
	}

	public String getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(String topicnum) {
		this.topicnum = topicnum;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("TopicpublishObj", TopicpublishObj.class);
		xstream.setMode(XStream.NO_REFERENCES);

	}

	public String getTopicstatus() {
		return topicstatus;
	}

	public void setTopicstatus(String topicstatus) {
		this.topicstatus = topicstatus;
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

	public Date getApplytime() {
		return applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
}
