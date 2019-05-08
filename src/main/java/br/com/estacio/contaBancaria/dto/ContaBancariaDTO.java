package br.com.estacio.contaBancaria.dto;

import br.com.estacio.contaBancaria.model.entity.ContaBancaria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Getter
@Setter
@NoArgsConstructor
public class ContaBancariaDTO {
    private Long id;
    private String agencia;
    private String conta;
    private String digito;
    private BigDecimal saldo;
    private List<TransacaoDTO> transacoes;
    private String contaTipo;
    private Long pessoa;

    public ContaBancariaDTO(ContaBancaria contaBancaria) {
        if(nonNull(contaBancaria)) {
            this.id = contaBancaria.getId();
            this.agencia = contaBancaria.getAgencia();
            this.conta = contaBancaria.getConta();
            this.digito = contaBancaria.getDigito();
            this.saldo = contaBancaria.getSaldo();
            this.transacoes = emptyIfNull(contaBancaria.getTransacoes())
                    .stream()
                    .map(TransacaoDTO::new)
                    .collect(Collectors.toList());
            this.contaTipo = contaBancaria.getContaTipo().name();
            this.pessoa = contaBancaria.getPessoa().getId();
        }
    }
}
