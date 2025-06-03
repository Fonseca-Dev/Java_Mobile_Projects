package com.example.Aula8RestauranteServer.dao;

import com.example.Aula8RestauranteServer.model.Mesa;
import com.example.Aula8RestauranteServer.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class ProdutoDao {

    Map<Integer, Produto> produtos = new TreeMap<>();

    public ProdutoDao() {

        Produto p1 = new Produto(1, "Coca-Cola",  5.50f);
        Produto p2 = new Produto(2, "Batata-Frita",  38.50f);
        Produto p3 = new Produto(3, "Suco de Laranja", 9.99f);
        Produto p4 = new Produto(4, "X-Salada", 30);

        produtos.put(p1.getId(), p1);
        produtos.put(p2.getId(), p2);
        produtos.put(p3.getId(), p3);
        produtos.put(p4.getId(), p4);

    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos.values());
    }
}
