package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;


/**
 * The persistent class for the TB_ATTACHMENT database table.
 * 
 */
@Entity
@Table(name="TB_ATTACHMENT")
public class AttachmentObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ATTACHMENT_ATTACHID_GENERATOR", sequenceName="SEQ_ATTACHMENT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ATTACHMENT_ATTACHID_GENERATOR")
	private long attachid;

	private String attachext;

	private String attachname;

	private String attachpath;

	private String attachtype;

	private String datascope;

	@Column(name="\"DESCRIBE\"")
	private String describe;

	//bi-directional many-to-one association to UserObj
	@ManyToOne
	@JoinColumn(name="USERID")
	private UserObj tbUser;

	public AttachmentObj() {
	}

	public long getAttachid() {
		return this.attachid;
	}

	public void setAttachid(long attachid) {
		this.attachid = attachid;
	}

	public String getAttachext() {
		return this.attachext;
	}

	public void setAttachext(String attachext) {
		this.attachext = attachext;
	}

	public String getAttachname() {
		return this.attachname;
	}

	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}

	public String getAttachpath() {
		return this.attachpath;
	}

	public void setAttachpath(String attachpath) {
		this.attachpath = attachpath;
	}

	public String getAttachtype() {
		return this.attachtype;
	}

	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}

	public String getDatascope() {
		return this.datascope;
	}

	public void setDatascope(String datascope) {
		this.datascope = datascope;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public UserObj getTbUser() {
		return this.tbUser;
	}

	public void setTbUser(UserObj tbUser) {
		this.tbUser = tbUser;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("AttachmentObj", AttachmentObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
	}

}