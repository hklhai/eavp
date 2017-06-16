package cn.edu.ncut.eavp.model.assistant;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.ProductObj;
import cn.edu.ncut.eavp.model.ProductisrcObj;
import cn.edu.ncut.eavp.model.PublishinfoObj;
import cn.edu.ncut.eavp.model.TopicObj;
import cn.edu.ncut.eavp.model.TopictransObj;
import cn.edu.ncut.eavp.model.TransferObj;
import cn.edu.ncut.eavp.model.base.BaseModel;

public class TopicInfoAssistantObj extends BaseModel implements Serializable {

	private static final long serialVersionUID = -163296068413979447L;
	private TopicObj topicObj;
	private TopictransObj topictransObj;
	


	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("ProductInfoAssistantObj", TopicInfoAssistantObj.class);
		xstream.alias("ProductObj", ProductObj.class);
		xstream.alias("ProductisrcObj", ProductisrcObj.class);
		xstream.alias("PublishinfoObj", PublishinfoObj.class);
		xstream.alias("TransferObj", TransferObj.class);
		xstream.setMode(XStream.NO_REFERENCES);
	}



	public TopicObj getTopicObj() {
		return topicObj;
	}



	public void setTopicObj(TopicObj topicObj) {
		this.topicObj = topicObj;
	}



	public TopictransObj getTopictransObj() {
		return topictransObj;
	}



	public void setTopictransObj(TopictransObj topictransObj) {
		this.topictransObj = topictransObj;
	}

}
