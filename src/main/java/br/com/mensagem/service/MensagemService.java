package br.com.mensagem.service;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.mensagem.enums.ResponseEnum;
import br.com.mensagem.mode.MensagemModel;
import br.com.mensagem.repository.MensagemRepository;
import br.com.mensagem.util.DataUtil;
import br.com.mensagem.vo.ResponseVo;
import javassist.NotFoundException;
import br.com.mensagem.vo.MensagemVo;


@Service
public class MensagemService {

	private static final Logger logger = LogManager.getLogger(MensagemService.class);
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	public ResponseVo salva(MensagemVo sendVo) throws Exception{
		
		try {
			
			int size = 0;
			if(sendVo.getContent() != null) {
				size = sendVo.getContent().length();
			}
			short direction = '0';
			if(sendVo.getDirection() != null) {
				direction = Short.parseShort( sendVo.getDirection() );
			}
			
			mensagemRepository.salva(java.util.UUID.randomUUID(), sendVo.getIdMonitoredPont() , 
					direction , DataUtil.getDataAtual(), sendVo.getContent() , size);
			
			return new ResponseVo( ResponseEnum.OK );
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	} 
	
	public ResponseVo lida(Long idMensagem) throws Exception{
		
		try {
			if( mensagemRepository.findReadAt(idMensagem) != null ) {
				mensagemRepository.atualizaReadAt(idMensagem);
			}
			return new ResponseVo( ResponseEnum.OK );
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	} 
	
	
	public List<MensagemVo> find(String identificadorDestino) throws Exception{
		
		try {
				
			List<MensagemVo> list = mensagemRepository.findTop20ByIdMonitoredPontOrderByIdAtDesc(identificadorDestino)
				.parallelStream().map( this :: convert ).collect(Collectors.toList());

			return list;
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	private MensagemVo convert(Object[] object) {
		
		MensagemVo mensagemVo = new MensagemVo();
		
		mensagemVo.setId( object[0] != null ? Long.valueOf(  object[0].toString() ) : null );
		mensagemVo.setUUID( object[1] != null ? object[1].toString() : null );
		mensagemVo.setIdMonitoredPont( object[2] != null ? object[2].toString() : null );
		mensagemVo.setDirection( object[3] != null ? object[3].toString() : null );
		mensagemVo.setCreatedAt(  object[4] != null ? object[4].toString() : null );
		mensagemVo.setReiceivedAt(object[5] != null ? object[5].toString() : null );
		mensagemVo.setReadAt(object[6] != null ? object[6].toString() : null );
		mensagemVo.setContent( object[7] != null ? object[7].toString() : null );
		mensagemVo.setSize( object[8] != null ? Integer.valueOf(  object[8].toString() ) : null );
		
		return mensagemVo;
	}
	
}
