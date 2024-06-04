package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.DTO.FilmeMovieDb;
import br.com.fujideia.iesp.tecback.clients.TheMovieDbClient;
import br.com.fujideia.iesp.tecback.clients.ViaCepClient;
import br.com.fujideia.iesp.tecback.config.JwtConfig;
import br.com.fujideia.iesp.tecback.config.MovieApiConfig;
import br.com.fujideia.iesp.tecback.handler.UnreacheableExternalApiException;
import br.com.fujideia.iesp.tecback.model.Endereco;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/moviedb")
public class MovieDbController {

    @Autowired
    private TheMovieDbClient moviDbClient;

    @Autowired
    private MovieApiConfig movieConfig;

    @GetMapping
    public List<FilmeMovieDb> BuscarMoviesDb() {

        var moviesDb = moviDbClient.consultarFilmes("Bearer " + movieConfig.getToken()).getResults();
        if (moviesDb == null) {
            throw new UnreacheableExternalApiException("Api externa não está disponível");
        }
        return moviesDb;
    }
}
