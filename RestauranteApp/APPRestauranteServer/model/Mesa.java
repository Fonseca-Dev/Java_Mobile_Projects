package com.example.Aula8RestauranteServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Mesa {
    private int id;
    private String status;
    private String cliente;
    private List<Pedido> pedidos;
    private float valorConta;

    public Mesa(int id){
        this.id = id;
        this.status = "Livre";
        this.cliente = "";
        this.pedidos = new ArrayList<>();
        this.valorConta = 0.0f;
    }
}


