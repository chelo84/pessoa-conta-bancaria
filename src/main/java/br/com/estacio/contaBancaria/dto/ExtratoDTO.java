package br.com.estacio.contaBancaria.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ExtratoDTO {
    private Long pessoa;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<TransacaoDTO> transacoes;
}
