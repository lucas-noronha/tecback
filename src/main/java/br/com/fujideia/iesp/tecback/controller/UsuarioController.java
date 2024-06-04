package br.com.fujideia.iesp.tecback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import br.com.fujideia.iesp.tecback.model.Cartao;
import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Usuario;
import br.com.fujideia.iesp.tecback.service.MailService;
import br.com.fujideia.iesp.tecback.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService service;

    @PostMapping
    public Usuario salvar(@Valid @RequestBody Usuario entidade) {
        try {
            if (!entidade.getSenha().equals(entidade.getConfirmarSenha())) {
                throw new Exception("As senhas digitadas não coincidem");
            }

            return service.salvar(entidade);

        } catch (Exception e) {
            return null;
        }

    }

    @GetMapping("confirmacao/{userId}")
    public String getMethodName(@PathVariable Integer userId) {
        service.ConfirmarEmail(userId);
        return "Email Confirmado!";
    }

    @PutMapping
    public Usuario atualizar(@RequestBody Usuario entidade) {
        return service.atualizar(entidade);
    }

    @PutMapping("adicionar_cartao/{userId}")
    public String adicionarCartaoAoUsuario(@PathVariable Integer userId, @RequestBody Cartao entity) {
        service.adicionarCartao(userId, entity);
        return "Adicionado";
    }

    @PutMapping("remover_cartao/{userId}/{cartaoId}")
    public String removerCartaoAoUsuario(@PathVariable Integer userId, @PathVariable Integer cartaoId) {
        service.removerCartao(userId, cartaoId);
        return "Removido";
    }

    @PutMapping("editar_cartao/{userId}")
    public String editarCartao(@PathVariable Integer userId, @RequestBody Cartao entity) {

        service.editarCartao(userId, entity.getId(), entity);
        return "Alterado";
    }

    @PutMapping("adicionar_favoritos/{userId}/{filmeId}")
    public String adicionarFilmeAFavoritos(@PathVariable Integer userId, @PathVariable Integer filmeId) {
        service.adicionarFilmeAFavoritos(userId, filmeId);
        return "Adicionado";
    }

    @PutMapping("remover_favoritos/{userId}/{filmeId}")
    public String removerFavorito(@PathVariable Integer userId, @PathVariable Integer filmeId) {

        service.removerFilmeDosFavoritos(userId, filmeId);
        return "Removido";
    }

    @PutMapping("alterar_plano/{userId}/{planoId}")
    public String alterarPlano(@PathVariable Integer userId, @PathVariable Integer planoId) {

        service.mudarPlano(userId, planoId);
        return "Alterado";
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/ordenado")
    public List<Usuario> listarTodosOrdenado() {
        return service.listarTodosOrdenado();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/cartoes")
    public List<Cartao> BuscarCartoes(@PathVariable Integer userId) {
        return service.buscarCartaoPorUsuarioId(userId);
    }

    @GetMapping("/favoritos/{userId}")
    public List<Filme> BuscarFavoritos(@PathVariable Integer userId) {
        return service.buscarFavoritosDoUsuario(userId);
    }

    @DeleteMapping("/{id}")
    public void excluirPorId(@PathVariable Integer id) {
        service.excluir(id);
    }

}
