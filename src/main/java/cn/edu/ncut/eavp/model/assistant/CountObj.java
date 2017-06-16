package cn.edu.ncut.eavp.model.assistant;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

public class CountObj extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long count;
	Long canUsePrefix;
	
	public Long getCanUsePrefix() {
		return canUsePrefix;
	}

	public void setCanUsePrefix(Long canUsePrefix) {
		this.canUsePrefix = canUsePrefix;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("CountObj", CountObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
	}
	
}
