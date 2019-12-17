package br.com.mensagem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.mensagem.service.MensagemService;
import br.com.mensagem.vo.ResponseVo;
import br.com.mensagem.vo.MensagemVo;


@RestController
@RequestMapping(path = "/mensagem")
public class MensagemController extends Controller {

	@Autowired
	private MensagemService mensagemService;
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody MensagemVo sendVo) {

		try {
			return ResponseEntity.ok().body( mensagemService.salva(sendVo) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@GetMapping(path="/{identificadorDestino}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<MensagemVo>> getAll(@PathVariable("identificadorDestino") final String identificadorDestino){
		try {
			return ResponseEntity.ok().body(mensagemService.find(identificadorDestino) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/destinos/", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<MensagemVo>> getAllDestino(){
		try {
			return ResponseEntity.ok().body(mensagemService.findDestino() ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/ler/{idMensagem}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> getAll(@PathVariable("idMensagem") final Long idMensagem){
		try {
			return ResponseEntity.ok().body(mensagemService.lida(idMensagem) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
}
