package org.example.model;

import java.util.ArrayList;

public class ContaBancaria {

    private String titular;
    private double saldo;
    private ArrayList<String> historico = new ArrayList<>();


    public ContaBancaria(String titular){
        this.titular = titular;
        this.saldo = 0.0;
    }

    public String getTitular(){
        return titular;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public void depositar(double valor){
        this.saldo += valor;
        historico.add("+ Depósito: R$" + valor + "| Saldo: R$" + getSaldo());
    }

    public boolean sacar(double valor){
        if (valor > saldo || valor <= 0){
            return false;
        }
        saldo -= valor;
        historico.add("- Saque: R$" + valor + "| Saldo: R$" + saldo);
        return true;

    }

    public void mostrarHistorico(){
        if (historico.isEmpty()){
            System.out.println("Nenhuma transação realizada.");
        }else{
            for(String transacao : historico ){
                System.out.println(transacao);
                }
        }
    }

}
