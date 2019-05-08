package br.com.estacio.contaBancaria.controller;

import br.com.estacio.contaBancaria.dto.DepositarSacarDTO;
import br.com.estacio.contaBancaria.dto.ExtratoDTO;
import br.com.estacio.contaBancaria.dto.PessoaDTO;
import br.com.estacio.contaBancaria.dto.TransacaoDTO;
import br.com.estacio.contaBancaria.model.entity.Pessoa;
import br.com.estacio.contaBancaria.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping()
    public @ResponseBody PessoaDTO novo(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = this.pessoaService.novo(pessoaDTO);

        return new PessoaDTO(pessoa);
    }

    @PostMapping("/depositar")
    public @ResponseBody BigDecimal depositar(@RequestBody DepositarSacarDTO depositarSacarDTO) {
        BigDecimal saldo = this.pessoaService.depositar(depositarSacarDTO);

        return saldo;
    }

    @PostMapping("/sacar")
    public @ResponseBody ResponseEntity<?> sacar(@RequestBody DepositarSacarDTO depositarSacarDTO) {
        try {
            BigDecimal saldo = this.pessoaService.sacar(depositarSacarDTO);

            return new ResponseEntity<>(saldo, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tirar-extrato")
    public @ResponseBody ExtratoDTO tirarExtrato(@RequestBody ExtratoDTO extratoDTO) {
        extratoDTO.setTransacoes(this.pessoaService.tirarExtrato(extratoDTO).stream().map(TransacaoDTO::new).collect(Collectors.toList()));

        return extratoDTO;
    }
}
