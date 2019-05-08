package br.com.estacio.contaBancaria.service;

import br.com.estacio.contaBancaria.dto.DepositarSacarDTO;
import br.com.estacio.contaBancaria.dto.ExtratoDTO;
import br.com.estacio.contaBancaria.dto.PessoaDTO;
import br.com.estacio.contaBancaria.model.entity.ContaBancaria;
import br.com.estacio.contaBancaria.model.entity.Pessoa;
import br.com.estacio.contaBancaria.model.entity.Transacao;
import br.com.estacio.contaBancaria.model.enums.PessoaTipo;
import br.com.estacio.contaBancaria.model.enums.TransacaoTipo;
import br.com.estacio.contaBancaria.model.repository.ContaBancariaRepository;
import br.com.estacio.contaBancaria.model.repository.PessoaRepository;
import br.com.estacio.contaBancaria.model.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    public Pessoa novo(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpfCnpj(pessoaDTO.getCpfCnpj());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobrenome(pessoaDTO.getSobrenome());
        pessoa.setPessoaTipo(PessoaTipo.valueOf(pessoaDTO.getPessoaTipo()));

        pessoa = this.pessoaRepository.save(pessoa);

        return pessoa;
    }

    public BigDecimal depositar(DepositarSacarDTO depositarSacarDTO) {
        Pessoa pessoa = pessoaRepository.findById(depositarSacarDTO.getPessoa())
                            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        ContaBancaria contaBancaria = pessoa.getContaBancaria();

        Transacao transacao = this.novaTransacao(depositarSacarDTO, contaBancaria, TransacaoTipo.DEPOSITO);

        contaBancaria.setSaldo(contaBancaria.getSaldo().add(depositarSacarDTO.getValor()));
        List<Transacao> transacoes = Optional.ofNullable(contaBancaria.getTransacoes()).orElse(new ArrayList<>());
        transacoes.add(transacao);
        contaBancaria.setTransacoes(transacoes);
        contaBancariaRepository.save(contaBancaria);

        return contaBancaria.getSaldo();
    }

    public BigDecimal sacar(DepositarSacarDTO depositarSacarDTO) {
        Pessoa pessoa = pessoaRepository.findById(depositarSacarDTO.getPessoa())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        ContaBancaria contaBancaria = pessoa.getContaBancaria();
        if(contaBancaria.getSaldo().subtract(depositarSacarDTO.getValor()).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(String.format("Saldo insuficiente\nSaldo atual: %s", contaBancaria.getSaldo()));
        }

        Transacao transacao = novaTransacao(depositarSacarDTO, contaBancaria, TransacaoTipo.SAQUE);

        contaBancaria.setSaldo(contaBancaria.getSaldo().subtract(depositarSacarDTO.getValor()));
        List<Transacao> transacoes = Optional.ofNullable(contaBancaria.getTransacoes()).orElse(new ArrayList<>());
        transacoes.add(transacao);
        contaBancaria.setTransacoes(transacoes);
        contaBancariaRepository.save(contaBancaria);

        return contaBancaria.getSaldo();
    }

    public List<Transacao> tirarExtrato(ExtratoDTO extratoDTO) {
        Pessoa pessoa = pessoaRepository.findById(extratoDTO.getPessoa())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        return transacaoRepository.findByContaBancariaAndDate(pessoa.getContaBancaria().getId(), extratoDTO.getDataInicio(), extratoDTO.getDataFim());
    }

    private Transacao novaTransacao(DepositarSacarDTO depositarSacarDTO, ContaBancaria contaBancaria, TransacaoTipo transacaoTipo) {
        Transacao transacao = new Transacao();
        transacao.setContaBancaria(contaBancaria);
        transacao.setDataTransacao(LocalDate.now());
        transacao.setTransacaoTipo(TransacaoTipo.SAQUE);
        transacao.setValor(depositarSacarDTO.getValor());
        transacaoRepository.save(transacao);

        return transacao;
    }
}
