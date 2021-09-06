package com.example.demo.repository;

import com.example.demo.model.Cartao;
import com.example.demo.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
//    Boolean existsByCartaoIdCartao(String idCartao);

//    List<Transacao> findAllByCartaoIdCartao(String idCartao);

    @Query(value = "SELECT * from transacao t where t.cartao_id = :idCartao order by t.efetivada_em desc limit 10", nativeQuery = true)
    List<Transacao> findFirst10ByCartaoIdOrderByEfetivadaEmDesc(String idCartao);
}
