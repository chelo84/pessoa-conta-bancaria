package br.com.estacio.contaBancaria.dto;

import br.com.estacio.contaBancaria.model.entity.Transacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
public class TransacaoDTO {
    private Long id;
    private LocalDate dataTransacao;
    private BigDecimal valor;
    private String transacaoTipo;

    public TransacaoDTO(Transacao transacao) {
        if(nonNull(transacao)) {
            this.id = transacao.getId();
            this.dataTransacao = transacao.getDataTransacao();
            this.valor = transacao.getValor();
            this.transacaoTipo = transacao.getTransacaoTipo().name();
        }
    }
}
