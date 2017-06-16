package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_ISBNRECYCLE database table.
 * 
 */
@Entity
@Table(name="TB_ISBNRECYCLE")
public class IsbnrecycleObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ISBNRECYCLE_RECYCLEID_GENERATOR", sequenceName="SEQ_ISBNRECYCLE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ISBNRECYCLE_RECYCLEID_GENERATOR")
	private long recycleid;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private Boolean isbnstatus;

	private Boolean isbntype;

	private String productisbn;

	@Temporal(TemporalType.DATE)
	private Date usedate;

	//bi-directional many-to-one association to ProductObj
	@ManyToOne
	@JoinColumn(name="PRODUCTID")
	private ProductObj tbProduct;

	//bi-directional many-to-one association to PublishinfoObj
	@ManyToOne
	@JoinColumn(name="PUBLISHID")
	private PublishinfoObj tbPublishinfo;

	public IsbnrecycleObj() {
	}

	public long getRecycleid() {
		return this.recycleid;
	}

	public void setRecycleid(long recycleid) {
		this.recycleid = recycleid;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Boolean getIsbnstatus() {
		return this.isbnstatus;
	}

	public void setIsbnstatus(Boolean isbnstatus) {
		this.isbnstatus = isbnstatus;
	}

	public Boolean getIsbntype() {
		return this.isbntype;
	}

	public void setIsbntype(Boolean isbntype) {
		this.isbntype = isbntype;
	}

	public String getProductisbn() {
		return this.productisbn;
	}

	public void setProductisbn(String productisbn) {
		this.productisbn = productisbn;
	}

	public Date getUsedate() {
		return this.usedate;
	}

	public void setUsedate(Date usedate) {
		this.usedate = usedate;
	}

	public ProductObj getTbProduct() {
		return this.tbProduct;
	}

	public void setTbProduct(ProductObj tbProduct) {
		this.tbProduct = tbProduct;
	}

	public PublishinfoObj getTbPublishinfo() {
		return this.tbPublishinfo;
	}

	public void setTbPublishinfo(PublishinfoObj tbPublishinfo) {
		this.tbPublishinfo = tbPublishinfo;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("IsbnrecycleObj", IsbnrecycleObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
	}

}