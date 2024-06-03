package br.com.fujideia.iesp.tecback.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fujideia.iesp.tecback.DTO.LoginDto;
import br.com.fujideia.iesp.tecback.config.JwtConfig;
import br.com.fujideia.iesp.tecback.model.Usuario;
import br.com.fujideia.iesp.tecback.service.UsuarioService;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private UsuarioService service;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping
    public String Logar(@Valid @RequestBody LoginDto loginInfo) {
        Usuario user = service.buscarPorEmail(loginInfo.getEmail());

        if (user == null || !user.getSenha().equals(loginInfo.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        String token = gerarToken(user);
        return token;
    }

    private String gerarToken(Usuario user) {
        long expirationTime = 1000 * 60 * 60; // 1 hora

        Key key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("nomeCompleto", user.getNomeCompleto())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }
}
