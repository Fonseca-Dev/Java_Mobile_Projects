package com.example.aula7restauranteapp;

import java.util.List;

public class Mesa {
    private int id;
    private String status;
    private String cliente;
    private List<String> produtos;
    private float valorConta;

    public Mesa(int id, String status, String cliente, List<String> produtos, float valorConta) {
        this.id = id;
        this.status = status;
        this.cliente = cliente;
        this.produtos = produtos;
        this.valorConta = valorConta;
    }

    public Mesa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

    public float getValorConta() {
        return valorConta;
    }

    public void setValorConta(float valorConta) {
        this.valorConta = valorConta;
    }
}
