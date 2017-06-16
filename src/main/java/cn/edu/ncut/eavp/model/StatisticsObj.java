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
@Table(name="TB_STATISTICS")
public class StatisticsObj extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="TB_STATISTICS_STATISTICSID_GENERATOR", allocationSize=1,sequenceName="SEQ_STATISTICS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_STATISTICS_STATISTICSID_GENERATOR")
	private Long statisticsid;
	private BigDecimal nodeid;
	private String nodename;
	private String forquery;
	private BigDecimal parentid;
	private BigDecimal countelec;
	private BigDecimal countelectotal;
	private String percentselec;
	private BigDecimal countaudio;
	private BigDecimal countaudiototal;
	private String percentsaudio;
	private int topicyear;
	public String getForquery() {
		return forquery;
	}
	public void setForquery(String forquery) {
		this.forquery = forquery;
	}
	public Long getStatisticsid() {
		return statisticsid;
	}
	public void setStatisticsid(Long statisticsid) {
		this.statisticsid = statisticsid;
	}
	public BigDecimal getNodeid() {
		return nodeid;
	}
	public void setNodeid(BigDecimal nodeid) {
		this.nodeid = nodeid;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public BigDecimal getParentid() {
		return parentid;
	}
	public void setParentid(BigDecimal parentid) {
		this.parentid = parentid;
	}
	public BigDecimal getCountelec() {
		return countelec;
	}
	public void setCountelec(BigDecimal countelec) {
		this.countelec = countelec;
	}
	public BigDecimal getCountelectotal() {
		return countelectotal;
	}
	public void setCountelectotal(BigDecimal countelectotal) {
		this.countelectotal = countelectotal;
	}
	public String getPercentselec() {
		return percentselec;
	}
	public void setPercentselec(String percentselec) {
		this.percentselec = percentselec;
	}
	public BigDecimal getCountaudio() {
		return countaudio;
	}
	public void setCountaudio(BigDecimal countaudio) {
		this.countaudio = countaudio;
	}
	public BigDecimal getCountaudiototal() {
		return countaudiototal;
	}
	public void setCountaudiototal(BigDecimal countaudiototal) {
		this.countaudiototal = countaudiototal;
	}
	public String getPercentsaudio() {
		return percentsaudio;
	}
	public void setPercentsaudio(String percentsaudio) {
		this.percentsaudio = percentsaudio;
	}
	public int getTopicyear() {
		return topicyear;
	}
	public void setTopicyear(int topicyear) {
		this.topicyear = topicyear;
	}
	
	@Override
	protected void setConvertRules(XStream xstream) {
		xstream.alias("StatisticsObj", StatisticsObj.class);
		xstream.setMode(XStream.NO_REFERENCES);	
	}
}
