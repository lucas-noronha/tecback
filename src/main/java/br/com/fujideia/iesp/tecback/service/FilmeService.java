package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import br.com.fujideia.iesp.tecback.repository.GeneroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FilmeService {

    private FilmeRepository repository;
    private GeneroRepository genreRepository;

    public Filme salvar(Filme filme) {
        if (filme.getGeneroId() != null && filme.getGeneroId() != 0) {
            Genero genre = genreRepository.findById(filme.getGeneroId()).get();
            filme.setGenero(genre);
        }
        return repository.save(filme);
    }

    public List<Filme> listarTodos() {
        return repository.findAll();
    }

    public Filme atualizar(Filme filme) {
        if (filme.getId() == null) {
            throw new RuntimeException("Filme sem ID");
        }
        Filme filmeBd = buscarPorId(filme.getId());
        filme.setUsuariosFavoritos(filmeBd.getUsuariosFavoritos());
        return salvar(filmeBd);
    }

    public Filme buscarPorId(Integer id) {
        return repository.findById(id).get();
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

}
