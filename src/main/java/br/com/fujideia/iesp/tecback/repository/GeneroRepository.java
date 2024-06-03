package br.com.fujideia.iesp.tecback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fujideia.iesp.tecback.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    List<Genero> findByDescricao(String descricao);

    List<Genero> findAllByOrderByDescricaoAsc();
}
