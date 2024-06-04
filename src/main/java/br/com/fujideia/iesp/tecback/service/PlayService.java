package br.com.fujideia.iesp.tecback.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        var filmesAssistidos = buscarPorUsuarioId(userId);

        Date hoje = FormatarData(new Date());
        Integer assistidosHoje = 0;

        for (var el : filmesAssistidos) {
            Date dataFilme = FormatarData(el.getDataAssistida());
            if (dataFilme.compareTo(hoje) == 0) {
                assistidosHoje++;
            }
        }
        if (assistidosHoje > user.getPlano().getQtdeFilmesDiario()) {
            {
                return null;
            }
        }

        Filme filme = filmeService.buscarPorId(filmeId);

        if (user != null && filme != null) {
            registro.setFilme(filme);
            registro.setUsuario(user);
            registro.setDataAssistida(new Date());

            return repository.save(registro);
        }
        return null;
    }

    public List<PlayedMovies> buscarPorUsuarioId(Integer userId) {
        return repository.findByUsuarioId(userId);
    }

    private Date FormatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            return sdf.parse(sdf.format(data));
        } catch (Exception e) {
            return null;
        }
    }

}
