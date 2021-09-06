package com.example.demo.dto;

import com.example.demo.model.Cartao;
import com.example.demo.model.Estabelecimento;
import com.example.demo.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoResponse {


    private String id;

    private BigDecimal valor;

    private String estabelecimento;

    private String cidade;

    private String endereco;

    private String email;

    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoResponse(){}

    public TransacaoResponse(Transacao transacao) {
        this.id = transacao.getIdTransacao();
        this.valor = transacao.getValor();
        this.estabelecimento = transacao.getEstabelecimento().getNome();
        this.cidade = transacao.getEstabelecimento().getCidade();
        this.endereco = transacao.getEstabelecimento().getEndereco();
        this.email = transacao.getCartao().getEmail();
        this.efetivadaEm = transacao.getEfetivadaEm();
    }



    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public List<TransacaoResponse> toModel(List<Transacao> transacoes) {
        return transacoes.stream().map(TransacaoResponse::new).collect(Collectors.toList());
    }
}
