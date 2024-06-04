package br.com.fujideia.iesp.tecback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.repository.GeneroRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GeneroService {

    private GeneroRepository repository;

    public Genero salvar(Genero genero) {
        return repository.save(genero);
    }

    public List<Genero> listarTodos() {
        return repository.findAll();
    }

    public List<Genero> listarTodosOrdenado() {
        return repository.findAllByOrderByDescricaoAsc();
    }

    public Genero atualizar(Genero genero) {
        if (genero.getId() == null) {
            throw new RuntimeException("Gênero sem ID");
        }
        Genero generoBd = buscarPorId(genero.getId());
        genero.setFilmes(generoBd.getFilmes());
        return repository.save(genero);
    }

    public Genero buscarPorId(Integer id) {
        return repository.findById(id).get();
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }
}
