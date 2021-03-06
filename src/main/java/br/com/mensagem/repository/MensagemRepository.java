package br.com.mensagem.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mensagem.model.MensagemModel;

@Repository
public interface MensagemRepository extends JpaRepository<MensagemModel, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into messaging.messages (\"UUID\", \"MONITORED_POINT_ID\", \"DIRECTION\", \"CREATED_AT\", \"CONTENT\", \"SIZE\") \n" + 
			"values (?1, ?2 ,  ?3 , ?4, ?5 , ?6 )" , nativeQuery = true)
	void salva( java.util.UUID UUID ,  String idMonitoredPont , short direction , Date dtCreated , String content , Integer size);
	
	
	@Query(value = "SELECT \"ID\", Cast( \"UUID\" as varchar) , \"MONITORED_POINT_ID\",  \"DIRECTION\", \"CREATED_AT\", \"RECEIVED_AT\", \"READ_AT\", \"CONTENT\", \"SIZE\" , \"TRANSPORT\" \n" + 
			" FROM messaging.messages where  \"MONITORED_POINT_ID\" = ?1 order by \"ID\" DESC LIMIT 20 ;" , nativeQuery = true)
	List<Object[]> findTop20ByIdMonitoredPontOrderByIdAtDesc(String idMonitoredPont);
	

	@Query(value = "SELECT  \"READ_AT\" \n" + 
			" FROM messaging.messages where  \"ID\" = ?1 ;" , nativeQuery = true)
	Object[] findReadAt(Long idMensagem);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE messaging.messages SET \"READ_AT\" = current_timestamp WHERE  \"ID\" = ?1 " , nativeQuery = true)
	void atualizaReadAt(Long id);
}
