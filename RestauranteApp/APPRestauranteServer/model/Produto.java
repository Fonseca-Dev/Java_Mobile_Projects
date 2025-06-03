package com.example.Aula8RestauranteServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private int id;
    private String nome;
    private float precoUnitario;
}
