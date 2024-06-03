package br.com.fujideia.iesp.tecback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fujideia.iesp.tecback.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByNomeCompleto(String nomeCompleto);

    List<Usuario> findAllByOrderByNomeCompletoAsc();

    Usuario findByEmail(String email);
}
