package br.com.mensagem.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mensagem.mode.MensagemModel;

@Repository
public interface MensagemRepository extends JpaRepository<MensagemModel, Long> {

	List<MensagemModel> findTop20ByIdMonitoredPontOrderByCreatedAtDesc(String idMonitoredPont);
	
	
	List<MensagemModel> findByIdIn(List<Long> ids);
	
	@Query(value = "SELECT max(id) FROM messages GROUP BY monitored_point_id ORDER BY max(created_at) DESC", nativeQuery = true)
	List<Long> getIdDestinos();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE messages SET REICEIVED_AT = current_timestamp WHERE ID = ?1 " , nativeQuery = true)
	void atualizaReiceivedAt(Long id);
}
