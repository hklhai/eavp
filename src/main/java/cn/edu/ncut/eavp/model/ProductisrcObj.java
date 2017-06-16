package cn.edu.ncut.eavp.model;

import java.io.Serializable;

import javax.persistence.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import cn.edu.ncut.eavp.model.base.BaseModel;


/**
 * The persistent class for the TB_PRODUCTISRC database table.
 * 
 */
@Entity
@Table(name="TB_PRODUCTISRC")
public class ProductisrcObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PRODUCTISRC_PRODUCTISRCID_GENERATOR", sequenceName="SEQ_PRODUCTISRC")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PRODUCTISRC_PRODUCTISRCID_GENERATOR")
	private Long productisrcid;

	private String isrcworkname;

	private String isrcnum;

	//bi-directional many-to-one association to ProductObj
	@ManyToOne
	@JoinColumn(name="PRODUCTID")
	@XStreamOmitField
	private ProductObj tbProduct;

	public ProductisrcObj() {
	}

	public Long getProductisrcid() {
		return this.productisrcid;
	}

	public void setProductisrcid(Long productisrcid) {
		this.productisrcid = productisrcid;
	}

	public String getIsrcworkname() {
		return this.isrcworkname;
	}

	public void setIsrcworkname(String isrcworkname) {
		this.isrcworkname = isrcworkname;
	}

	public String getIsrcnum() {
		return this.isrcnum;
	}

	public void setIsrcnum(String isrcnum) {
		this.isrcnum = isrcnum;
	}

	public ProductObj getTbProduct() {
		return this.tbProduct;
	}

	public void setTbProduct(ProductObj tbProduct) {
		this.tbProduct = tbProduct;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ProductisrcObj", ProductisrcObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
		
	}

}