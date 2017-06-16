package cn.edu.ncut.eavp.model.assistant;

import cn.edu.ncut.eavp.model.base.BaseModel;

import com.thoughtworks.xstream.XStream;



	public class Parameter extends BaseModel implements java.io.Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4854801430539064281L;
		private String pressName;
		private String eleISBN;
		public String getPressName() {
			return pressName;
		}
		public void setPressName(String pressName) {
			this.pressName = pressName;
		}
		public String getEleISBN() {
			return eleISBN;
		}
		public void setEleISBN(String eleISBN) {
			this.eleISBN = eleISBN;
		}
		@Override
		protected void setConvertRules(XStream xstream) {
			// TODO Auto-generated method stub
			xstream.alias("param", Parameter.class);
			xstream.setMode(XStream.NO_REFERENCES);
		}
		
		
	}



