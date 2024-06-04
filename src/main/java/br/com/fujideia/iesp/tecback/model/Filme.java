package br.com.fujideia.iesp.tecback.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fujideia.iesp.tecback.validator.IdadeRange;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @IdadeRange(min = 7, max = 23)
    private Integer idade;

    @Transient
    private Integer anoLancamento;

    private Float duracao;

    @JsonIgnore
    @ManyToMany(mappedBy = "filmesFavoritos", cascade = CascadeType.PERSIST)
    private List<Usuario> usuariosFavoritos;

    @Transient
    private Integer generoId;
}
