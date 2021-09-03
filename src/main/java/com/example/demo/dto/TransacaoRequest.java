package com.example.demo.dto;

import com.example.demo.model.Cartao;
import com.example.demo.model.Estabelecimento;
import com.example.demo.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoRequest {

    @NotBlank
    private String id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private EstabelecimentoRequest estabelecimento;

    @NotNull
    private CartaoRequest cartao;

    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoRequest(){}

    public TransacaoRequest(String id, BigDecimal valor,
                            EstabelecimentoRequest estabelecimento, CartaoRequest cartao,
                            LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public Transacao toModel() {
        return new Transacao(id, valor, estabelecimento.toModel(), cartao.toModel(), efetivadaEm);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public EstabelecimentoRequest getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(EstabelecimentoRequest estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public CartaoRequest getCartao() {
        return cartao;
    }

    public void setCartao(CartaoRequest cartao) {
        this.cartao = cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public void setEfetivadaEm(LocalDateTime efetivadaEm) {
        this.efetivadaEm = efetivadaEm;
    }
}
