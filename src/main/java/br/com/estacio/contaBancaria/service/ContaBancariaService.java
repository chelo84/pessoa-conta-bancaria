package br.com.estacio.contaBancaria.service;

import br.com.estacio.contaBancaria.dto.ContaBancariaDTO;
import br.com.estacio.contaBancaria.model.entity.ContaBancaria;
import br.com.estacio.contaBancaria.model.entity.Pessoa;
import br.com.estacio.contaBancaria.model.enums.ContaTipo;
import br.com.estacio.contaBancaria.model.repository.ContaBancariaRepository;
import br.com.estacio.contaBancaria.model.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaService {

    @Autowired
    public ContaBancariaRepository contaBancariaRepository;
    @Autowired
    public PessoaRepository pessoaRepository;

    public ContaBancaria novo(ContaBancariaDTO contaBancariaDTO) {
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setAgencia(contaBancariaDTO.getAgencia());
        contaBancaria.setConta(contaBancariaDTO.getConta());
        contaBancaria.setContaTipo(ContaTipo.valueOf(contaBancariaDTO.getContaTipo()));
        contaBancaria.setDigito(contaBancariaDTO.getDigito());
        Pessoa pessoa = pessoaRepository.findById(contaBancariaDTO.getPessoa())
                            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        contaBancaria.setPessoa(pessoa);
        this.contaBancariaRepository.save(contaBancaria);

        pessoa.setContaBancaria(contaBancaria);
        this.pessoaRepository.save(pessoa);

        return contaBancaria;
    }
}
