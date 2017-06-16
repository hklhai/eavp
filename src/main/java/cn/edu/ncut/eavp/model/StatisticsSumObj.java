package cn.edu.ncut.eavp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.thoughtworks.xstream.XStream;

import cn.edu.ncut.eavp.model.base.BaseModel;

@Entity
@Table(name="TB_STATISTICSSUM")
public class StatisticsSumObj extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_STATISTICSSUM_STATISTICSSUMID_GENERATOR", allocationSize=1,sequenceName="SEQ_STATISTICSSUM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_STATISTICSSUM_STATISTICSSUMID_GENERATOR")
	private BigDecimal statisticssumid;
	private BigDecimal audioyeartopic;
	private BigDecimal elecyeartopic;
	private BigDecimal audionoyeartopic;
	private BigDecimal elecnoyeartopic;
	private int topicyear;
	
	public int getTopicyear() {
		return topicyear;
	}
	public void setTopicyear(int topicyear) {
		this.topicyear = topicyear;
	}
	public BigDecimal getStatisticssumid() {
		return statisticssumid;
	}
	public void setStatisticssumid(BigDecimal statisticssumid) {
		this.statisticssumid = statisticssumid;
	}
	public BigDecimal getAudioyeartopic() {
		return audioyeartopic;
	}
	public void setAudioyeartopic(BigDecimal audioyeartopic) {
		this.audioyeartopic = audioyeartopic;
	}
	public BigDecimal getElecyeartopic() {
		return elecyeartopic;
	}
	public void setElecyeartopic(BigDecimal elecyeartopic) {
		this.elecyeartopic = elecyeartopic;
	}
	public BigDecimal getAudionoyeartopic() {
		return audionoyeartopic;
	}
	public void setAudionoyeartopic(BigDecimal audionoyeartopic) {
		this.audionoyeartopic = audionoyeartopic;
	}
	public BigDecimal getElecnoyeartopic() {
		return elecnoyeartopic;
	}
	public void setElecnoyeartopic(BigDecimal elecnoyeartopic) {
		this.elecnoyeartopic = elecnoyeartopic;
	}
	
	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("StatisticsSumObj", StatisticsSumObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
	}
}
