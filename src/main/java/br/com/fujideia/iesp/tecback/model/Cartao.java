package br.com.fujideia.iesp.tecback.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fujideia.iesp.tecback.validator.DataValidade;
import br.com.fujideia.iesp.tecback.validator.Length;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Data
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Length(max = 16)
    private String numero;

    @Length(max = 3)
    private String codigoSeguranca;
    private String nomeTitular;

    @DataValidade
    private Date dataValidade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
