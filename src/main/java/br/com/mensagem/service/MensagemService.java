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
			mensagemRepository.saveAndFlush( new MensagemModel(sendVo) );
			return new ResponseVo( ResponseEnum.OK );
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	} 
	
	public ResponseVo lida(Long idMensagem) throws Exception{
		
		try {
			MensagemModel mensagem = mensagemRepository.findById(idMensagem)
					.orElseThrow(() -> new NotFoundException("Não existe mensagem para "+idMensagem));
			
			if( mensagem.getReadAt() == null ) {
				mensagem.setReadAt( DataUtil.getDataAtual() );
				mensagemRepository.saveAndFlush( mensagem );
			}
			return new ResponseVo( ResponseEnum.OK );
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	} 
	
	public List<MensagemVo> findDestino() throws Exception{
		
		try {
				
			List<Long> listIdDestino = mensagemRepository.getIdDestinos();
			
			return mensagemRepository.findByIdIn(listIdDestino)
				.parallelStream().map( MensagemVo :: new).collect(Collectors.toList());
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}

	public List<MensagemVo> find(String identificadorDestino) throws Exception{
		
		try {
				
			List<MensagemVo> list = mensagemRepository.findTop20ByIdMonitoredPontOrderByCreatedAtDesc(identificadorDestino)
				.parallelStream().map( MensagemVo :: new).collect(Collectors.toList());
			
			this.setRecebida(list);
			
			return list;
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	private void setRecebida(List<MensagemVo> list) throws Exception {
		
		if( list != null && !list.isEmpty() ) {
			list.stream().forEach( msg ->{
				mensagemRepository.atualizaReiceivedAt(msg.getId());
			});
		}
	}
}
