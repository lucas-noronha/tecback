package br.com.fujideia.iesp.tecback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fujideia.iesp.tecback.model.PlayedMovies;

import java.util.List;

public interface PlayedMoviesRepository extends JpaRepository<PlayedMovies, Integer> {

    List<PlayedMovies> findByFilmeId(Integer id);

    @Query("select p from PlayedMovies p " +
            "where p.usuarioId=:usuarioId")
    List<PlayedMovies> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
