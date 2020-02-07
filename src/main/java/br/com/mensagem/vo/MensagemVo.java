package br.com.mensagem.vo;

import org.springframework.beans.BeanUtils;

import br.com.mensagem.model.MensagemModel;
import br.com.mensagem.util.DataUtil;


public class MensagemVo {

	public MensagemVo() {}
	
	public MensagemVo(MensagemModel sendModel) {
		
		BeanUtils.copyProperties(sendModel,this);
		
		if(sendModel.getCreatedAt() != null) {
			this.setCreatedAt( DataUtil.converteData(sendModel.getCreatedAt()) );
		}
		
		if(sendModel.getReiceivedAt() != null) {
			this.setReiceivedAt( DataUtil.converteData(sendModel.getReiceivedAt()) );
			this.setRecebida(true);
		}else{
			this.setRecebida(false);
		}

		if(sendModel.getReadAt() != null) {
			this.setReadAt( DataUtil.converteData(sendModel.getReadAt()) );
			this.setLida(true);
		}else {
			this.setLida(false);
		}
	}
	
	private Long id;

	private String UUID;
	
	private String idMonitoredPont;
	
	private String direction;
	
	private String createdAt;

	private String reiceivedAt;

	private String readAt;
	
	private String content;
	
	private Integer size;
	
	private boolean isLida;
	
	private boolean isRecebida;
	
	private boolean isOnline;
	
	private String transport;
	               
	
	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getIdMonitoredPont() {
		return idMonitoredPont;
	}

	public void setIdMonitoredPont(String idMonitoredPont) {
		this.idMonitoredPont = idMonitoredPont;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getReiceivedAt() {
		return reiceivedAt;
	}

	public void setReiceivedAt(String reiceivedAt) {
		this.reiceivedAt = reiceivedAt;
	}

	public String getReadAt() {
		return readAt;
	}

	public void setReadAt(String readAt) {
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

	public boolean isLida() {
		return isLida;
	}

	public void setLida(boolean isLida) {
		this.isLida = isLida;
	}

	public boolean isRecebida() {
		return isRecebida;
	}

	public void setRecebida(boolean isRecebida) {
		this.isRecebida = isRecebida;
	}
	
}
