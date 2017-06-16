package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

import java.math.BigDecimal;


/**
 * The persistent class for the TB_AUDITOR database table.
 * 
 */
@Entity
@Table(name="TB_AUDITOR")
public class AuditorObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_AUDITOR_AUDITORID_GENERATOR", sequenceName="SEQ_AUDITOR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_AUDITOR_AUDITORID_GENERATOR")
	private long auditorid;

	private String aname;

	private BigDecimal auditorright;

	private BigDecimal auditorstatus;

	@Column(name="\"DESCRIBE\"")
	private String describe;

	private String idcardno;

	private String mobile;

	private String phone;

	private String sex;

	private String title;

	//bi-directional many-to-one association to OrganizationinfoObj
	@ManyToOne
	@JoinColumn(name="ORGID")
	private OrganizationinfoObj tbOrganizationinfo;

	public AuditorObj() {
	}

	public long getAuditorid() {
		return this.auditorid;
	}

	public void setAuditorid(long auditorid) {
		this.auditorid = auditorid;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public BigDecimal getAuditorright() {
		return this.auditorright;
	}

	public void setAuditorright(BigDecimal auditorright) {
		this.auditorright = auditorright;
	}

	public BigDecimal getAuditorstatus() {
		return this.auditorstatus;
	}

	public void setAuditorstatus(BigDecimal auditorstatus) {
		this.auditorstatus = auditorstatus;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getIdcardno() {
		return this.idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public OrganizationinfoObj getTbOrganizationinfo() {
		return this.tbOrganizationinfo;
	}

	public void setTbOrganizationinfo(OrganizationinfoObj tbOrganizationinfo) {
		this.tbOrganizationinfo = tbOrganizationinfo;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("AuditorObj", AuditorObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
	}

}