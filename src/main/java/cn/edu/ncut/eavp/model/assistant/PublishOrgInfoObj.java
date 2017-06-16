package cn.edu.ncut.eavp.model.assistant;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.OrganizationinfoObj;
import cn.edu.ncut.eavp.model.PublishinfoObj;
import cn.edu.ncut.eavp.model.base.BaseModel;

public class PublishOrgInfoObj extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private PublishinfoObj publishinfoobj;
	private OrganizationinfoObj organizationinfoobj;
	
	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("PublishOrgInfoObj", PublishOrgInfoObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
	}

	public PublishinfoObj getPublishinfoobj() {
		return publishinfoobj;
	}

	public void setPublishinfoobj(PublishinfoObj publishinfoobj) {
		this.publishinfoobj = publishinfoobj;
	}

	public OrganizationinfoObj getOrganizationinfoobj() {
		return organizationinfoobj;
	}

	public void setOrganizationinfoobj(OrganizationinfoObj organizationinfoobj) {
		this.organizationinfoobj = organizationinfoobj;
	}
}
