package br.com.fujideia.iesp.tecback.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.PlayedMovies;
import br.com.fujideia.iesp.tecback.model.Usuario;
import br.com.fujideia.iesp.tecback.repository.PlayedMoviesRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PlayService {

    private PlayedMoviesRepository repository;
    private UsuarioService usuarioService;
    private FilmeService filmeService;

    public PlayedMovies criar(Integer userId, Integer filmeId) {
        PlayedMovies registro = new PlayedMovies();
        Usuario user = usuarioService.buscarPorId(userId);
        Filme filme = filmeService.buscarPorId(filmeId);

        if (user != null && filme != null) {
            registro.setFilme(filme);
            registro.setUsuario(user);
            registro.setDataAssistida(new Date());

            return repository.save(registro);
        }
        return null;
    }

    public List<PlayedMovies> BuscarPorUsuarioId(Integer userId) {
        return repository.findByUsuarioId(userId);
    }

}
