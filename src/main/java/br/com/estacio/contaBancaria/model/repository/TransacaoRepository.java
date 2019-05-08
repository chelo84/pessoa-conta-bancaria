package br.com.estacio.contaBancaria.model.repository;

import br.com.estacio.contaBancaria.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Query(value = "select t from Transacao t " +
            "join t.contaBancaria cb " +
            "where cb.id = ?1 and " +
            "t.dataTransacao between ?2 and ?3 ")
    List<Transacao> findByContaBancariaAndDate(Long contaBancaria, LocalDate dataInicio, LocalDate dataFim);
}
