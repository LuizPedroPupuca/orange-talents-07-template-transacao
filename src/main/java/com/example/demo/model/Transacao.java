package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String idTransacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Estabelecimento estabelecimento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    @Column(nullable = false)
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao(){}

    public Transacao(String idTransacao, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao, LocalDateTime efetivadaEm) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }
}
