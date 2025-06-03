package com.example.Aula8RestauranteServer.dao;

import com.example.Aula8RestauranteServer.model.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class MesaDao {
    private final ProdutoDao produtoDao;

    Map<Integer, Mesa> mesas = new TreeMap<>();

    @Autowired
    public MesaDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;

        for (int i = 1; i <= 8; i++) {
            mesas.put(i, new Mesa(i));
        }

    }

    public Mesa postMesa(Mesa mesa){
        mesa.setId(nextId());
        mesas.put(mesa.getId(), mesa);
        return mesa;
    }

    public int nextId(){
        int max = 1;
        for( int id : mesas.keySet()){
            if(max <= id){
                max = id + 1;
            }
        }
        return max;
    }

    public List<Mesa> getMesa() {
        return new ArrayList<>(mesas.values());
    }

    public Mesa getMesaByID(int id) {
        return mesas.get(id);
    }

    public Mesa putMesa(Mesa mesa, Integer id) throws Exception {
        Mesa mesaExistente = mesas.get(id);
        mesaExistente.setStatus(mesa.getStatus());
        mesaExistente.setCliente(mesa.getCliente());
        mesaExistente.setPedidos(mesa.getPedidos());
        mesaExistente.setValorConta(mesa.getValorConta());
        return mesaExistente;
    }

    public Mesa deletarMesa(Integer id) {
        Mesa mesaRemovida = mesas.remove(id);

        if (mesaRemovida != null) {
            Mesa novaMesa = new Mesa(id);
            mesas.put(id, novaMesa);
        }

        return mesaRemovida;
    }




}
