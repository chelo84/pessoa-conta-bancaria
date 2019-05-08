package br.com.estacio.contaBancaria.model.repository;

import br.com.estacio.contaBancaria.model.entity.ContaBancaria;
import br.com.estacio.contaBancaria.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
}
