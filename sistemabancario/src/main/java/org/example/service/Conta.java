package org.example.service;

import org.example.model.ContaBancaria;

public class Conta {
    private String nome;
    private String usuario;
    private String senha;
    private ContaBancaria contaBancaria;

    public Conta (String nome, String usuario, String senha){
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.contaBancaria = new ContaBancaria(nome);
    }

    public ContaBancaria getContaBancaria(){
        return contaBancaria;
    }

    public String getNome(){
        return nome;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getSenha(){
        return senha;
    }

}
