package cn.edu.ncut.eavp.model;

import java.io.Serializable;

import javax.persistence.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import cn.edu.ncut.eavp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the TB_PRODUCT database table.
 * 
 */
@Entity
@Table(name = "TB_PRODUCT")
public class ProductObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_PRODUCT_PRODUCTID_GENERATOR", sequenceName = "SEQ_PRODUCT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_PRODUCT_PRODUCTID_GENERATOR")
	private Long productid;

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
	private Date createtime;

	private BigDecimal dimensions;

	private String important;

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

	@Temporal(TemporalType.DATE)
	private Date publishtime;

	private String publishtype;

	private String reader;

	private String topicname;

	private BigDecimal userid;

	private String usetype;

	private String topicnum;

	private Boolean iselec;
	private String topicnumberself;
	private String seriesname;
	private String importantbakcode;
	private String otherimportantindex;
	private String backup1;
	private String backup2;
	private String backup3;
	private String backup4;
	private String backup5;
	// bi-directional many-to-one association to IsbnrecycleObj
	@OneToMany(mappedBy = "tbProduct")
	@XStreamOmitField
	private List<IsbnrecycleObj> tbIsbnrecycles;

	// bi-directional many-to-one association to TopicObj
	@ManyToOne
	@JoinColumn(name = "TOPICID")
	@XStreamOmitField
	private TopicObj tbTopic;
	@Transient
	private Long topicid;

	// bi-directional many-to-one association to TransferObj
	@OneToMany(mappedBy = "tbProduct")
	@XStreamOmitField
	private List<TransferObj> tbTransfers;

	// bi-directional many-to-one association to ProductisrcObj
	@OneToMany(mappedBy = "tbProduct")
	@XStreamOmitField
	private List<ProductisrcObj> tbProductisrcs;

	@Transient
	private List<ProductisrcObj> productisrcs;

	public ProductObj() {
	}

	public Long getProductid() {
		return this.productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public String getAuditendsuggestion() {
		return this.auditendsuggestion;
	}

	public void setAuditendsuggestion(String auditendsuggestion) {
		this.auditendsuggestion = auditendsuggestion;
	}

	public String getAuditonename() {
		return this.auditonename;
	}

	public void setAuditonename(String auditonename) {
		this.auditonename = auditonename;
	}

	public String getAuditthreename() {
		return this.auditthreename;
	}

	public void setAuditthreename(String auditthreename) {
		this.auditthreename = auditthreename;
	}

	public String getAudittwoname() {
		return this.audittwoname;
	}

	public void setAudittwoname(String audittwoname) {
		this.audittwoname = audittwoname;
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

	public Boolean getIsmodify() {
		return ismodify;
	}

	public void setIsmodify(Boolean ismodify) {
		this.ismodify = ismodify;
	}

	public String getIspublished() {
		return this.ispublished;
	}

	public void setIspublished(String ispublished) {
		this.ispublished = ispublished;
	}

	public Boolean getIssuspend() {
		return this.issuspend;
	}

	public void setIssuspend(Boolean issuspend) {
		this.issuspend = issuspend;
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

	public String getProductisbn() {
		return this.productisbn;
	}

	public void setProductisbn(String productisbn) {
		this.productisbn = productisbn;
	}

	public String getProductstatus() {
		return this.productstatus;
	}

	public void setProductstatus(String productstatus) {
		this.productstatus = productstatus;
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

	public String getTopicname() {
		return this.topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public String getUsetype() {
		return this.usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

	public Boolean getIslocked() {
		return islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	public String getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(String topicnum) {
		this.topicnum = topicnum;
	}

	public List<IsbnrecycleObj> getTbIsbnrecycles() {
		return this.tbIsbnrecycles;
	}

	public void setTbIsbnrecycles(List<IsbnrecycleObj> tbIsbnrecycles) {
		this.tbIsbnrecycles = tbIsbnrecycles;
	}

	public TopicObj getTbTopic() {
		return this.tbTopic;
	}

	public void setTbTopic(TopicObj tbTopic) {
		this.tbTopic = tbTopic;
	}

	public List<TransferObj> getTbTransfers() {
		return this.tbTransfers;
	}

	public void setTbTransfers(List<TransferObj> tbTransfers) {
		this.tbTransfers = tbTransfers;
	}

	public Long getTopicid() {
		// 返回给前端时，需要返回关联值
		if (tbTopic != null && tbTopic.getTopicid() != null) {
			return tbTopic.getTopicid();
		}
		return topicid;
	}

	public void setTopicid(Long topicid) {
		this.topicid = topicid;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ProductObj", ProductObj.class);
		xstream.alias("ProductisrcObj", ProductisrcObj.class);
		xstream.setMode(XStream.NO_REFERENCES);

	}

	public Boolean getIselec() {
		return iselec;
	}

	public void setIselec(Boolean iselec) {
		this.iselec = iselec;
	}

	public List<ProductisrcObj> getTbProductisrcs() {
		return tbProductisrcs;
	}

	public void setTbProductisrcs(List<ProductisrcObj> tbProductisrcs) {
		this.tbProductisrcs = tbProductisrcs;
	}

	public List<ProductisrcObj> getProductisrcs() {
		return productisrcs;
	}

	public void setProductisrcs(List<ProductisrcObj> productisrcs) {
		this.productisrcs = productisrcs;
	}

	public BigDecimal getUserid() {
		return userid;
	}

	public void setUserid(BigDecimal userid) {
		this.userid = userid;
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