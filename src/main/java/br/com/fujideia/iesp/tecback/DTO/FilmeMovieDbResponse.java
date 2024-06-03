package br.com.fujideia.iesp.tecback.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeMovieDbResponse {
    private int page;
    private List<FilmeMovieDb> results;
}
