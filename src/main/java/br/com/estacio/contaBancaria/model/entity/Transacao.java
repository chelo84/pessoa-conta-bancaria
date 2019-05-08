package br.com.estacio.contaBancaria.model.entity;

import br.com.estacio.contaBancaria.model.enums.TransacaoTipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Transacao {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dataTransacao;
    private BigDecimal valor;
    @ManyToOne
    private ContaBancaria contaBancaria;
    @Enumerated(EnumType.STRING)
    private TransacaoTipo transacaoTipo;
}
