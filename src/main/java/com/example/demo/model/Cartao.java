package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Cartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String idCartao;

    @Column(nullable = false)
    private String email;

    @Deprecated
    public Cartao(){}

    public Cartao(String email, String idCartao) {
        this.email = email;
        this.idCartao = idCartao;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getEmail() {
        return email;
    }
}
