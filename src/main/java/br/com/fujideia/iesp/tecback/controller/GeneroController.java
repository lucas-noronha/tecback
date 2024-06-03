package br.com.fujideia.iesp.tecback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.*;
import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.service.GeneroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/generos")
public class GeneroController {
    private GeneroService service;

    @PostMapping
    public Genero salvar(@Valid @RequestBody Genero entidade) {
        return service.salvar(entidade);
    }

    @PutMapping
    public Genero atualizar(@RequestBody Genero entidade) {
        return service.atualizar(entidade);
    }

    @GetMapping
    public List<Genero> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/ordenado")
    public List<Genero> ListarTodosOrdenado() {
        return service.listarTodosOrdenado();
    }

    @GetMapping("/{id}")
    public Genero buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluirPorId(@PathVariable Integer id) {
        service.excluir(id);
    }
}
