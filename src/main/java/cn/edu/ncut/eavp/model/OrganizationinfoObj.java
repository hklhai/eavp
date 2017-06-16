package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import cn.edu.ncut.eavp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the TB_ORGANIZATIONINFO database table.
 * 
 */
@Entity
@Table(name="TB_ORGANIZATIONINFO")
public class OrganizationinfoObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String orgid;

	private String address;

	private String departfunction;

	private String departtype;

	private String fax;

	private String orgfullname;

	private BigDecimal orgnum;

	private String orgshortname;

	private BigDecimal orgstatus;

	private String phone;

	private String remark;

	private String zip;
	
	private String parentid;

	//bi-directional many-to-one association to AuditorObj
	@OneToMany(mappedBy="tbOrganizationinfo")
	@XStreamOmitField
	private List<AuditorObj> tbAuditors;

	//bi-directional many-to-one association to PublishinfoObj
	@OneToMany(mappedBy="organizationinfo")
	@XStreamOmitField
	private List<PublishinfoObj> tbPublishinfos;

	//bi-directional many-to-one association to UserObj
	@OneToMany(mappedBy="tbOrganizationinfo")
	@XStreamOmitField
	private List<UserObj> tbUsers;

	//bi-directional many-to-one association to OrgmessageObj
	@OneToMany(mappedBy="tbOrganizationinfo1")
	@XStreamOmitField
	private List<OrgmessageObj> tbOrgmessages1;

	//bi-directional many-to-one association to OrgmessageObj
	@OneToMany(mappedBy="tbOrganizationinfo2")
	@XStreamOmitField
	private List<OrgmessageObj> tbOrgmessages2;

	public OrganizationinfoObj() {
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartfunction() {
		return this.departfunction;
	}

	public void setDepartfunction(String departfunction) {
		this.departfunction = departfunction;
	}

	public String getDeparttype() {
		return this.departtype;
	}

	public void setDeparttype(String departtype) {
		this.departtype = departtype;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getOrgfullname() {
		return this.orgfullname;
	}

	public void setOrgfullname(String orgfullname) {
		this.orgfullname = orgfullname;
	}

	public BigDecimal getOrgnum() {
		return this.orgnum;
	}

	public void setOrgnum(BigDecimal orgnum) {
		this.orgnum = orgnum;
	}

	public String getOrgshortname() {
		return this.orgshortname;
	}

	public void setOrgshortname(String orgshortname) {
		this.orgshortname = orgshortname;
	}

	public BigDecimal getOrgstatus() {
		return this.orgstatus;
	}

	public void setOrgstatus(BigDecimal orgstatus) {
		this.orgstatus = orgstatus;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<AuditorObj> getTbAuditors() {
		return this.tbAuditors;
	}

	public void setTbAuditors(List<AuditorObj> tbAuditors) {
		this.tbAuditors = tbAuditors;
	}

	public List<PublishinfoObj> getTbPublishinfos() {
		return this.tbPublishinfos;
	}

	public void setTbPublishinfos(List<PublishinfoObj> tbPublishinfos) {
		this.tbPublishinfos = tbPublishinfos;
	}

	public List<UserObj> getTbUsers() {
		return this.tbUsers;
	}

	public void setTbUsers(List<UserObj> tbUsers) {
		this.tbUsers = tbUsers;
	}

	public List<OrgmessageObj> getTbOrgmessages1() {
		return this.tbOrgmessages1;
	}

	public void setTbOrgmessages1(List<OrgmessageObj> tbOrgmessages1) {
		this.tbOrgmessages1 = tbOrgmessages1;
	}

	public List<OrgmessageObj> getTbOrgmessages2() {
		return this.tbOrgmessages2;
	}

	public void setTbOrgmessages2(List<OrgmessageObj> tbOrgmessages2) {
		this.tbOrgmessages2 = tbOrgmessages2;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("OrganizationinfoObj", OrganizationinfoObj.class);
		xstream.setMode(XStream.NO_REFERENCES);		
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}