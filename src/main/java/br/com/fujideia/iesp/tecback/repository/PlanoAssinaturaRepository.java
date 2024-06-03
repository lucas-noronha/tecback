package br.com.fujideia.iesp.tecback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fujideia.iesp.tecback.model.PlanoAssinatura;

public interface PlanoAssinaturaRepository extends JpaRepository<PlanoAssinatura, Integer> {

    @Query("SELECT p FROM PlanoAssinatura p ORDER BY p.nome ASC")
    List<PlanoAssinatura> findAllOrderByNomeAsc();
}
