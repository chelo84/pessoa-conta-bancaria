package br.com.estacio.contaBancaria.model.entity;

import br.com.estacio.contaBancaria.model.enums.ContaTipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class ContaBancaria {
    @Id
    @GeneratedValue
    private Long id;
    private String agencia;
    private String conta;
    private String digito;
    private BigDecimal saldo;
    @OneToMany(mappedBy = "contaBancaria", fetch = FetchType.LAZY)
    private List<Transacao> transacoes;
    @Enumerated(EnumType.STRING)
    private ContaTipo contaTipo;
    @OneToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public ContaBancaria() {
        this.saldo = BigDecimal.ZERO;
    }
}
