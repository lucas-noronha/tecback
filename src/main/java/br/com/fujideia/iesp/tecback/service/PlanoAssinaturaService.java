package br.com.fujideia.iesp.tecback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fujideia.iesp.tecback.model.PlanoAssinatura;
import br.com.fujideia.iesp.tecback.repository.PlanoAssinaturaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PlanoAssinaturaService {
    private PlanoAssinaturaRepository repository;

    public PlanoAssinatura salvar(PlanoAssinatura plano) {
        return repository.save(plano);
    }

    public List<PlanoAssinatura> listarTodos() {
        return repository.findAll();
    }

    public List<PlanoAssinatura> listarTodosOrdenado() {
        return repository.findAllOrderByNomeAsc();
    }

    public PlanoAssinatura atualizar(PlanoAssinatura plano) {
        if (plano.getId() == null) {
            throw new RuntimeException("Gênero sem ID");
        }
        return repository.save(plano);
    }

    public PlanoAssinatura buscarPorId(Integer id) {
        return repository.findById(id).get();
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }
}
