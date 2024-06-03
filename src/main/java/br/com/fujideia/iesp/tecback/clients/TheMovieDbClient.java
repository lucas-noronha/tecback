package br.com.fujideia.iesp.tecback.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.fujideia.iesp.tecback.DTO.FilmeMovieDb;

@FeignClient(name = "themoviedb", url = "https://api.themoviedb.org/3/")
public interface TheMovieDbClient {

    @GetMapping("/movie/popular?language=pt-BR")
    public FilmeMovieDb consultarFilmes(@RequestHeader("Authorization") String token);
}
