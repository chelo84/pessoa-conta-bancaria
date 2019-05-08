package br.com.estacio.contaBancaria.dto;

import br.com.estacio.contaBancaria.model.entity.ContaBancaria;
import br.com.estacio.contaBancaria.model.entity.Pessoa;
import br.com.estacio.contaBancaria.model.enums.PessoaTipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private ContaBancariaDTO contaBancaria;
    private String pessoaTipo;
    private String cpfCnpj;

    public PessoaDTO(Pessoa pessoa) {
        if(nonNull(pessoa)) {
            this.id = pessoa.getId();
            this.nome = pessoa.getNome();
            this.sobrenome = pessoa.getSobrenome();
            this.dataNascimento = pessoa.getDataNascimento();
            this.contaBancaria = new ContaBancariaDTO(pessoa.getContaBancaria());
            this.pessoaTipo = pessoa.getPessoaTipo().name();
            this.cpfCnpj = pessoa.getCpfCnpj();
        }
    }
}
