package br.com.mensagem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.core.config.Scheduled;
import org.springframework.beans.BeanUtils;

import br.com.mensagem.util.DataUtil;
import br.com.mensagem.vo.MensagemVo;

@Entity
@Table(name = "messages")
public class MensagemModel {

	public MensagemModel() {}
	
	public MensagemModel(MensagemVo mensagemVo){
		BeanUtils.copyProperties(mensagemVo , this);
		this.setUUID( java.util.UUID.randomUUID());
		this.setCreatedAt( DataUtil.getDataAtual() );
		if(mensagemVo.getContent() != null) {
			this.setSize(mensagemVo.getContent().length());
		}else {
			this.setSize(0);
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false )
	private Long id;
	
	@Column(name = "UUID" )
	private java.util.UUID UUID;
	
	@Column(name = "MONITORED_POINT_ID" )
	private String idMonitoredPont;
	
	@Column(name = "DIRECTION" )
	private short direction;
	
	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "REICEIVED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reiceivedAt;
	
	@Column(name = "READ_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date readAt;
	
	@Column(name = "CONTENT" )
	private String content;
	
	@Column(name = "SIZE" )
	private Integer size;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.UUID getUUID() {
		return UUID;
	}

	public void setUUID(java.util.UUID uUID) {
		UUID = uUID;
	}

	public String getIdMonitoredPont() {
		return idMonitoredPont;
	}

	public void setIdMonitoredPont(String idMonitoredPont) {
		this.idMonitoredPont = idMonitoredPont;
	}

	public short getDirection() {
		return direction;
	}

	public void setDirection(short direction) {
		this.direction = direction;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getReiceivedAt() {
		return reiceivedAt;
	}

	public void setReiceivedAt(Date reiceivedAt) {
		this.reiceivedAt = reiceivedAt;
	}

	public Date getReadAt() {
		return readAt;
	}

	public void setReadAt(Date readAt) {
		this.readAt = readAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
}
