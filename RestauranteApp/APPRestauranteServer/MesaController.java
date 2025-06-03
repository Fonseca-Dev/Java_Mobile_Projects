package com.example.Aula8RestauranteServer;

import com.example.Aula8RestauranteServer.dao.MesaDao;
import com.example.Aula8RestauranteServer.dao.ProdutoDao;
import com.example.Aula8RestauranteServer.model.Mesa;
import com.example.Aula8RestauranteServer.model.Pedido;
import com.example.Aula8RestauranteServer.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MesaController {

    @Autowired
    MesaDao mesaDao;

    @Autowired
    ProdutoDao produtoDao;

    @GetMapping("/restaurante/mesas")
    public List<Mesa> getMesa() {
        return mesaDao.getMesa();
    }

    @GetMapping("/restaurante/mesas/{id}")
    public Mesa getMesaByID(@PathVariable int id) throws Exception{
        return mesaDao.getMesaByID(id);
    }

    @PostMapping("/restaurante/mesas")
    public Mesa postMesa(@RequestBody Mesa mesa){
        return mesaDao.postMesa(mesa);
    }

    @PutMapping("/restaurante/mesas/{id}")
    public Mesa putMesa(@RequestBody Mesa mesa, @PathVariable Integer id) throws Exception{
        return mesaDao.putMesa(mesa, id);
    }

    @DeleteMapping("/restaurante/mesas/{id}")
    public Mesa deletarMesa(@PathVariable Integer id){
        return mesaDao.deletarMesa(id);
    }

    @GetMapping("/restaurante/produtos")
    public List<Produto> getProdutos() {
        return produtoDao.getProdutos();
    }


}
