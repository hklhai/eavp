package cn.edu.ncut.eavp.model.assistant;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.PrefixinfoObj;
import cn.edu.ncut.eavp.model.ProductObj;
import cn.edu.ncut.eavp.model.ProductisrcObj;
import cn.edu.ncut.eavp.model.PublishinfoObj;
import cn.edu.ncut.eavp.model.TransferObj;
import cn.edu.ncut.eavp.model.base.BaseModel;
import cn.edu.ncut.eavp.model.view.TransferuserObj;

public class ProductInfoAssistantObj extends BaseModel implements Serializable {

	private static final long serialVersionUID = -163296068413979447L;
	private ProductObj productObj;
	private PublishinfoObj publishinfoObj;
	private List<PrefixinfoObj> prefixinfoObjs;
	private List<TransferuserObj> transferuserObjs;
	
	public ProductObj getProductObj() {
		return productObj;
	}

	public void setProductObj(ProductObj productObj) {
		this.productObj = productObj;
	}

	public PublishinfoObj getPublishinfoObj() {
		return publishinfoObj;
	}

	public void setPublishinfoObj(PublishinfoObj publishinfoObj) {
		this.publishinfoObj = publishinfoObj;
	}


	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ProductInfoAssistantObj", ProductInfoAssistantObj.class);
		xstream.alias("ProductObj", ProductObj.class);
		xstream.alias("ProductisrcObj", ProductisrcObj.class);
		xstream.alias("PublishinfoObj", PublishinfoObj.class);
		xstream.alias("TransferObj", TransferObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
	}

	public List<TransferuserObj> getTransferuserObjs() {
		return transferuserObjs;
	}

	public void setTransferuserObjs(List<TransferuserObj> transferuserObjs) {
		this.transferuserObjs = transferuserObjs;
	}

	public List<PrefixinfoObj> getPrefixinfoObjs() {
		return prefixinfoObjs;
	}

	public void setPrefixinfoObjs(List<PrefixinfoObj> prefixinfoObjs) {
		this.prefixinfoObjs = prefixinfoObjs;
	}

}
