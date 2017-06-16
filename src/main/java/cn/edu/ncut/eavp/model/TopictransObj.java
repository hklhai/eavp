package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_TOPICTRANS database table.
 * 
 */
@Entity
@Table(name="TB_TOPICTRANS")
public class TopictransObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TOPICTRANS_TOPICTRANSFERID_GENERATOR", allocationSize=1,sequenceName="SEQ_TOPICTRANS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TOPICTRANS_TOPICTRANSFERID_GENERATOR")
	private long topictransferid;

	private String auditendsuggestion;

	private String author;

	private BigDecimal capacity;

	private String capacityunit;

	private String contentabstract;

	private String contenttype;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	private BigDecimal dimensions;

	private String important;

	private String importantindex;

	private Boolean iselec;

	private String istranslate;

	private Boolean isyear;

	private String languageother;

	private String made;

	private String mediatype;

	private String mediatypeother;

	private String otherimportant;

	private String planguage;

	private BigDecimal pmonth;

	private BigDecimal psize;

	private String publishid;

	private String publishmethod;

	@Temporal(TemporalType.TIMESTAMP)
	private Date publishtime;

	private String publishtype;

	private String reader;
	private Long topicyear;

	private String stagereason;

	private String stagestatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date stagetime;

	private Long stageuser;

	private Long topicid;

	private String topicname;

	private String topicnum;

	private String topicstatus;

	private BigDecimal userid;

	private String usetype;
	private String otherimportantindex;
	private String backup1;
	private String backup2;
	private String backup3;
	private String backup4;
	private String backup5;
	public TopictransObj() {
	}

	public long getTopictransferid() {
		return this.topictransferid;
	}

	public void setTopictransferid(long topictransferid) {
		this.topictransferid = topictransferid;
	}

	public String getAuditendsuggestion() {
		return this.auditendsuggestion;
	}

	public void setAuditendsuggestion(String auditendsuggestion) {
		this.auditendsuggestion = auditendsuggestion;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getCapacity() {
		return this.capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public String getCapacityunit() {
		return this.capacityunit;
	}

	public void setCapacityunit(String capacityunit) {
		this.capacityunit = capacityunit;
	}

	public String getContentabstract() {
		return this.contentabstract;
	}

	public void setContentabstract(String contentabstract) {
		this.contentabstract = contentabstract;
	}

	public String getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public BigDecimal getDimensions() {
		return this.dimensions;
	}

	public void setDimensions(BigDecimal dimensions) {
		this.dimensions = dimensions;
	}

	public String getImportant() {
		return this.important;
	}

	public void setImportant(String important) {
		this.important = important;
	}

	public String getImportantindex() {
		return this.importantindex;
	}

	public void setImportantindex(String importantindex) {
		this.importantindex = importantindex;
	}

	public Boolean getIselec() {
		return this.iselec;
	}

	public void setIselec(Boolean iselec) {
		this.iselec = iselec;
	}

	public String getIstranslate() {
		return this.istranslate;
	}

	public void setIstranslate(String istranslate) {
		this.istranslate = istranslate;
	}

	public Boolean getIsyear() {
		return this.isyear;
	}

	public void setIsyear(Boolean isyear) {
		this.isyear = isyear;
	}

	public String getLanguageother() {
		return this.languageother;
	}

	public void setLanguageother(String languageother) {
		this.languageother = languageother;
	}

	public String getMade() {
		return this.made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public String getMediatype() {
		return this.mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String getMediatypeother() {
		return this.mediatypeother;
	}

	public void setMediatypeother(String mediatypeother) {
		this.mediatypeother = mediatypeother;
	}

	public String getOtherimportant() {
		return this.otherimportant;
	}

	public void setOtherimportant(String otherimportant) {
		this.otherimportant = otherimportant;
	}

	public String getPlanguage() {
		return this.planguage;
	}

	public void setPlanguage(String planguage) {
		this.planguage = planguage;
	}

	public BigDecimal getPmonth() {
		return this.pmonth;
	}

	public void setPmonth(BigDecimal pmonth) {
		this.pmonth = pmonth;
	}

	public BigDecimal getPsize() {
		return this.psize;
	}

	public void setPsize(BigDecimal psize) {
		this.psize = psize;
	}

	public String getPublishid() {
		return this.publishid;
	}

	public void setPublishid(String publishid) {
		this.publishid = publishid;
	}

	public String getPublishmethod() {
		return this.publishmethod;
	}

	public void setPublishmethod(String publishmethod) {
		this.publishmethod = publishmethod;
	}

	public Date getPublishtime() {
		return this.publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public String getPublishtype() {
		return this.publishtype;
	}

	public void setPublishtype(String publishtype) {
		this.publishtype = publishtype;
	}

	public String getReader() {
		return this.reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public String getStagereason() {
		return this.stagereason;
	}

	public void setStagereason(String stagereason) {
		this.stagereason = stagereason;
	}

	public String getStagestatus() {
		return this.stagestatus;
	}

	public void setStagestatus(String stagestatus) {
		this.stagestatus = stagestatus;
	}

	public Date getStagetime() {
		return this.stagetime;
	}

	public void setStagetime(Date stagetime) {
		this.stagetime = stagetime;
	}

	public Long getStageuser() {
		return this.stageuser;
	}

	public void setStageuser(Long stageuser) {
		this.stageuser = stageuser;
	}

	public Long getTopicid() {
		return this.topicid;
	}

	public void setTopicid(Long topicid) {
		this.topicid = topicid;
	}

	public String getTopicname() {
		return this.topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public String getTopicnum() {
		return this.topicnum;
	}

	public void setTopicnum(String topicnum) {
		this.topicnum = topicnum;
	}

	public String getTopicstatus() {
		return this.topicstatus;
	}

	public void setTopicstatus(String topicstatus) {
		this.topicstatus = topicstatus;
	}

	public BigDecimal getUserid() {
		return this.userid;
	}

	public void setUserid(BigDecimal userid) {
		this.userid = userid;
	}

	public String getUsetype() {
		return this.usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

	public Long getTopicyear() {
		return topicyear;
	}

	public void setTopicyear(Long topicyear) {
		this.topicyear = topicyear;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("TopictransObj", TopictransObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
		
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
	

}