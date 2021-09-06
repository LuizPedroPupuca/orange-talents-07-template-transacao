package com.example.demo.controller;

import com.example.demo.dto.TransacaoResponse;
import com.example.demo.model.Cartao;
import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/{idCartao}")
    ResponseEntity<List<TransacaoResponse>> consultaUltimasDezCompras(@PathVariable String idCartao){
        List<Transacao> transacoes = transacaoRepository.findFirst10ByCartaoIdOrderByEfetivadaEmDesc(idCartao);
        if (transacoes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        TransacaoResponse transacaoResponse = new TransacaoResponse();
        List<TransacaoResponse> transacoesResponse = transacaoResponse.toModel(transacoes);
        return ResponseEntity.ok().body(transacoesResponse);
    }
}
