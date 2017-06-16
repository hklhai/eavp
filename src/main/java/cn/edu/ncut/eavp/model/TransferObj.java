package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.webservice.base.BaseParameters;


import java.util.Date;


/**
 * The persistent class for the TB_TRANSFER database table.
 * 
 */
@Entity
@Table(name="TB_TRANSFER")
public class TransferObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TRANSFER_TRANSFERID_GENERATOR",allocationSize=1, sequenceName="SEQ_TRANSFER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TRANSFER_TRANSFERID_GENERATOR")
	private long transferid;

	private String auditendsuggestion;
	private String topicnum;

	private String auditonename;

	private String auditthreename;

	private String audittwoname;

	private String author;

	private Long capacity;

	private String capacityunit;

	private String contentabstract;

	private String contenttype;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	private Long dimensions;

	private String important;

	private String importantindex;

	private Boolean islocked;

	private String ispublished;

	private Boolean issuspend;

	private String istranslate;

	private Boolean isyear;

	private String languageother;

	private String made;

	private String mediatype;

	private String mediatypeother;

	private String otherimportant;

	private String planguage;

	private Long pmonth;

	private String productstatus;

	private Long psize;

	private String publishid;

	private String publishmethod;

	@Temporal(TemporalType.TIMESTAMP)
	private Date publishtime;

	private String publishtype;

	private String reader;

	private String stagereason;

	private String stagestatus;
	private String productisbn;
	private long stageuser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date stagetime; 

	private String topicid;
	
	@Transient
	@XStreamAlias(value="productid")
	private Long productid;

	private String topicname;

	private Long userid;

	private String usetype;
	
	private Boolean iselec;

	//bi-directional many-to-one association to ProductObj
	@ManyToOne
	@JoinColumn(name="PRODUCTID")
	@XStreamOmitField
	private ProductObj tbProduct;

	public TransferObj() {
	}

	public long getTransferid() {
		return this.transferid;
	}

	public void setTransferid(long transferid) {
		this.transferid = transferid;
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

	public Long getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Long capacity) {
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

	public Long getDimensions() {
		return this.dimensions;
	}

	public void setDimensions(Long dimensions) {
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

	public Boolean getIslocked() {
		return this.islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
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

	public Long getPmonth() {
		return this.pmonth;
	}

	public void setPmonth(Long pmonth) {
		this.pmonth = pmonth;
	}

	public String getProductstatus() {
		return this.productstatus;
	}

	public void setProductstatus(String productstatus) {
		this.productstatus = productstatus;
	}

	public Long getPsize() {
		return this.psize;
	}

	public void setPsize(Long psize) {
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

	public long getStageuser() {
		return stageuser;
	}

	public void setStageuser(long stageuser) {
		this.stageuser = stageuser;
	}

	public Date getStagetime() {
		return stagetime;
	}

	public void setStagetime(Date stagetime) {
		this.stagetime = stagetime;
	}

	public String getTopicid() {
		return this.topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getTopicname() {
		return this.topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public Boolean getIselec() {
		return iselec;
	}

	public void setIselec(Boolean iselec) {
		this.iselec = iselec;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsetype() {
		return this.usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

	public ProductObj getTbProduct() {
		return this.tbProduct;
	}

	public void setTbProduct(ProductObj tbProduct) {
		this.tbProduct = tbProduct;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public String getProductisbn() {
		return productisbn;
	}

	public void setProductisbn(String productisbn) {
		this.productisbn = productisbn;
	}
	

	public String getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(String topicnum) {
		this.topicnum = topicnum;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("TransferObj", TransferObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
	}

}