package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.service.FilmeService;
import br.com.fujideia.iesp.tecback.service.PlayService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/filme")
public class FilmeController {

    private FilmeService service;
    private PlayService playService;

    @PostMapping
    public Filme salvar(@Valid @RequestBody Filme filme) {
        return service.salvar(filme);
    }

    @PostMapping("/assistir/{filmeId}/{userId}")
    public String postMethodName(@PathVariable Integer filmeId, @PathVariable Integer userId) {

        var retorno = playService.criar(userId, filmeId);
        if (retorno == null) {
            return "Limite diário atingido";
        }
        return "Assistido";
    }

    @PutMapping
    public Filme atualizar(@RequestBody Filme filme) {
        return service.atualizar(filme);
    }

    @GetMapping
    public List<Filme> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluirPorId(@PathVariable Integer id) {
        service.excluir(id);
    }

}
