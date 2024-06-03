package br.com.fujideia.iesp.tecback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fujideia.iesp.tecback.model.PlanoAssinatura;
import br.com.fujideia.iesp.tecback.service.PlanoAssinaturaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/planos")
public class PlanoController {
    private PlanoAssinaturaService service;

    @PostMapping
    public PlanoAssinatura salvar(@RequestBody PlanoAssinatura entidade) {
        return service.salvar(entidade);
    }

    @PutMapping
    public PlanoAssinatura atualizar(@RequestBody PlanoAssinatura entidade) {
        return service.atualizar(entidade);
    }

    @GetMapping
    public List<PlanoAssinatura> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/ordenado")
    public List<PlanoAssinatura> ListarTodosOrdenado() {
        return service.listarTodosOrdenado();
    }

    @GetMapping("/{id}")
    public PlanoAssinatura buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluirPorId(@PathVariable Integer id) {
        service.excluir(id);
    }
}
