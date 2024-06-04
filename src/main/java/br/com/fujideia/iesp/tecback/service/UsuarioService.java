package br.com.fujideia.iesp.tecback.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fujideia.iesp.tecback.model.Usuario;
import br.com.fujideia.iesp.tecback.model.Cartao;
import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.PlanoAssinatura;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import br.com.fujideia.iesp.tecback.repository.PlanoAssinaturaRepository;
import br.com.fujideia.iesp.tecback.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository repository;
    private FilmeRepository filmeRepository;
    private PlanoAssinaturaService planoService;

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario.getPlanoId() != null && usuario.getPlanoId() != 0) {
            PlanoAssinatura plano = planoService.buscarPorId(usuario.getPlanoId());
            usuario.setPlano(plano);
        }
        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public List<Usuario> listarTodosOrdenado() {
        return repository.findAllByOrderByNomeCompletoAsc();
    }

    public Usuario atualizar(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new RuntimeException("Gênero sem ID");
        }
        Usuario usuarioBd = repository.findById(usuario.getId()).get();
        usuario.setCartoes(usuarioBd.getCartoes());
        usuario.setPlano(usuarioBd.getPlano());
        usuario.setFilmesFavoritos(usuario.getFilmesFavoritos());
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Integer id) {
        return repository.findById(id).get();
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    public void adicionarFilmeAFavoritos(Integer userId, Integer filmeId) {
        Usuario usuario = repository.findById(userId).get();

        if (usuario != null && usuario.getFilmesFavoritos() != null) {
            Filme filme = filmeRepository.findById(filmeId).get();
            usuario.getFilmesFavoritos().add(filme);
            salvar(usuario);
        } else if (usuario != null && usuario.getFilmesFavoritos() == null) {
            Filme filme = filmeRepository.findById(filmeId).get();

            List<Filme> filmes = new ArrayList<Filme>();
            filmes.add(filme);
            usuario.setFilmesFavoritos(filmes);
            salvar(usuario);
        }
    }

    public void removerFilmeDosFavoritos(Integer userId, Integer filmeId) {
        Usuario usuario = repository.findById(userId).get();

        if (usuario != null && usuario.getFilmesFavoritos() != null) {

            Filme filme = filmeRepository.findById(filmeId).get();
            usuario.getFilmesFavoritos().remove(filme);
            salvar(usuario);

        }
    }

    public void adicionarCartao(Integer userId, Cartao cartao) {
        Usuario usuario = repository.findById(userId).get();

        if (usuario != null && usuario.getCartoes() != null) {
            cartao.setUsuario(usuario);
            usuario.getCartoes().add(cartao);
            salvar(usuario);
        } else if (usuario != null && usuario.getCartoes() == null) {
            usuario.setCartoes(new ArrayList<>());
            usuario.getCartoes().add(cartao);
            salvar(usuario);
        }
    }

    public void removerCartao(Integer userId, Integer cartaoId) {
        Usuario usuario = repository.findById(userId).get();

        if (usuario != null && usuario.getCartoes() != null) {
            usuario.getCartoes().removeIf(elemento -> elemento.getId() == cartaoId);
            salvar(usuario);
        }
    }

    public void mudarPlano(Integer userId, Integer planoId) {
        Usuario usuario = repository.findById(userId).get();
        PlanoAssinatura plano = planoService.buscarPorId(planoId);
        if (plano != null) {
            usuario.setPlanoId(plano.getId());
            salvar(usuario);
        }
    }

    public List<Cartao> buscarCartaoPorUsuarioId(Integer userId) {
        Usuario usuario = repository.findById(userId).get();
        return usuario.getCartoes();
    }

    public void editarCartao(Integer userId, Integer cartaoId, Cartao cartao) {
        Usuario usuario = repository.findById(userId).get();
        Cartao cartaoUsuario = null;

        for (Cartao el : usuario.getCartoes()) {
            if (el.getId() == cartaoId) {
                cartaoUsuario = el;
            }
        }

        if (cartaoUsuario != null && cartaoUsuario.getId() == cartao.getId()) {
            usuario.getCartoes().removeIf(el -> el.getId() == cartaoId);
            cartao.setUsuario(usuario);
            usuario.getCartoes().add(cartao);
            salvar(usuario);
        }

    }

    public List<Filme> buscarFavoritosDoUsuario(Integer userId) {
        Usuario usuario = repository.findById(userId).get();
        return usuario.getFilmesFavoritos();
    }

    public Integer validarPlay(Integer userId) {
        Usuario usuario = repository.findById(userId).get();
        if (usuario.getPlano() != null) {
            return usuario.getPlano().getQtdeFilmesDiario();
        }
        return 0;
    }

}
