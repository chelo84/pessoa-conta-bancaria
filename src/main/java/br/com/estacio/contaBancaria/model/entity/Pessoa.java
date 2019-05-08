package br.com.estacio.contaBancaria.model.entity;

import br.com.estacio.contaBancaria.model.enums.PessoaTipo;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Pessoa {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    @OneToOne(fetch = FetchType.LAZY)
    private ContaBancaria contaBancaria;
    private PessoaTipo pessoaTipo;
    private String cpfCnpj;
}
