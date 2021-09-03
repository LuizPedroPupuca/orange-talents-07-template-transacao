package com.example.demo.dto;

import com.example.demo.model.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CartaoRequest {


    private String id;


    private String email;

    @Deprecated
    public CartaoRequest(){}

    public CartaoRequest(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cartao toModel() {
        return new Cartao(email, id);
    }
}
