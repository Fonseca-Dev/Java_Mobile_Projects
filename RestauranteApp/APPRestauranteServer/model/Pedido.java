package com.example.Aula8RestauranteServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private int idPedido;
    private Produto produto;
    private int quantidade;
    private String observacao;

    public float getSubtotal() {
        return produto.getPrecoUnitario() * quantidade;
    }
}

