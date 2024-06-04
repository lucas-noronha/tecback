package br.com.fujideia.iesp.tecback.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fujideia.iesp.tecback.validator.Length;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeCompleto;
    private Date dataDeNascimento;

    @Column(unique = true)
    private String email;

    @Length(max = 15)
    private String senha;
    @Length(max = 15)
    private String confirmarSenha;

    @Length(max = 14)
    private String cpfOuCnpj;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cartao> cartoes;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USUARIO_FILMES_FAVORITOS", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "filme_id"))
    private List<Filme> filmesFavoritos;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanoAssinatura plano;

    @Transient
    private Integer planoId;
}
