package cn.edu.ncut.eavp.model.assistant;

import java.util.List;

import cn.edu.ncut.eavp.model.base.BaseModel;

import com.thoughtworks.xstream.XStream;

public class ISBNParameters extends BaseModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -434121907108819495L;
	private Parameter param;
	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("params", ISBNParameters.class);
		xstream.alias("param", Parameter.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
	}
	public Parameter getParam() {
		return param;
	}
	public void setParam(Parameter param) {
		this.param = param;
	}
	

	
	
}