package com.example.aula7restauranteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnotarPedidoActivity extends AppCompatActivity {

    final private ArrayList<String> listaProdutosPedido = new ArrayList<>();

    final private ArrayList<String> listaPedidosEfetuados = new ArrayList<>();

    private ArrayAdapter<String> adapterProdutosPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anotar_pedido);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa adapter e lista para ListView dos pedidos
        adapterProdutosPedido = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, listaProdutosPedido);
        ListView listView = findViewById(R.id.AnotarPedido_ListView_ProdutosPedido);
        listView.setAdapter(adapterProdutosPedido);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        produtosCadastrados();

        Intent intent = getIntent();
        String nomeCliente = intent.getStringExtra("nome_cliente");
        int idMesa = intent.getIntExtra("id_mesa", -1); // default -1 para caso não exista

        TextView idMesaTV = findViewById(R.id.AnotarPedido_TextView_IDMesa);


        if (idMesa != -1) {
            idMesaTV.setText("Mesa " + idMesa + " - Cliente: " + nomeCliente);
        } else {
            idMesaTV.setText("Mesa inválida - Cliente: " + nomeCliente);
        }

        valorConta();
        pedidosEfetuados();

    }

    public String formatarValor(String valorStr) {
        try {
            float valor = Float.parseFloat(valorStr.replace(",", ".")); // troca vírgula por ponto se vier assim
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return formatoMoeda.format(valor); // ex: R$ 1.234,56
        } catch (NumberFormatException e) {
            return "R$ 0,00"; // valor padrão em caso de erro
        }
    }

    public void valorConta() {

        // Recupera a intent que iniciou a Activity
        Intent intent = getIntent();
        // Obtém o ID da mesa da intent (ou -1 se não encontrado)
        int idMesa = intent.getIntExtra("id_mesa", -1);

        // Verifica se o ID da mesa é válido
        if (idMesa == -1) {
            Toast.makeText(this, "Mesa inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        final int mesaIdFinal = idMesa; // variável efetivamente final

        RequestQueue queue = Volley.newRequestQueue(this);

        // URL para obter os dados da mesa via GET
        String urlGetMesa = "http://" + ipLocal() + ":8080/restaurante/mesas/" + mesaIdFinal;

        // Requisição GET para obter os dados da mesa específica
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlGetMesa, null,
                response -> {
                    try {
                        //Cria um Json Array para armazenar os pedidos atuais da mesa
                        String valorConta = response.getString("valorConta");

                        TextView valorContaTV = findViewById(R.id.AnotarPedido_TextView_ValorConta);
                        valorContaTV.setText("Valor da Conta: R$ " + formatarValor(valorConta));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, error -> {
        });
        queue.add(request);


    }

    public void pedidosEfetuados() {
        // Recupera a intent que iniciou a Activity
        Intent intent = getIntent();
        // Obtém o ID da mesa da intent (ou -1 se não encontrado)
        int idMesa = intent.getIntExtra("id_mesa", -1);

        // Verifica se o ID da mesa é válido
        if (idMesa == -1) {
            Toast.makeText(this, "Mesa inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        final int mesaIdFinal = idMesa; // variável efetivamente final

        RequestQueue queue = Volley.newRequestQueue(this);

        // URL para obter os dados da mesa via GET
        String urlGetMesa = "http://" + ipLocal() + ":8080/restaurante/mesas/" + mesaIdFinal;

        // Requisição GET para obter os dados da mesa específica
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlGetMesa, null,
                response -> {
                    try {
                        //Cria um Json Array para armazenar os pedidos atuais da mesa
                        JSONArray pedidosMesa = response.getJSONArray("pedidos");

                        for (int i = 0; i < pedidosMesa.length(); i++) {
                            JSONObject pedido = pedidosMesa.getJSONObject(i);
                            JSONObject produto = pedido.getJSONObject("produto");
                            String nomeProduto = produto.getString("nome");
                            int quantidadeProduto = pedido.getInt("quantidade");
                            double precoProduto = produto.getDouble("precoUnitario");
                            double precoTotalProduto = precoProduto * quantidadeProduto;
                            String precoTotalProdutoSt = Double.toString(precoTotalProduto);

                            String item = nomeProduto + " - Quantidade: " + quantidadeProduto
                                    + "\nPreço: " + formatarValor(precoTotalProdutoSt);
                            listaPedidosEfetuados.add(item);
                        }
                        ListView pedidosMesaLV = findViewById(R.id.AnotarPedido_ListView_PedidosEfetuados);

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                android.R.layout.simple_list_item_1, listaPedidosEfetuados);
                        pedidosMesaLV.setAdapter(adapter);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Erro ao carregar pedidos da mesa", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(this, "Erro ao buscar dados da mesa", Toast.LENGTH_SHORT).show();
        });
        queue.add(request);
    }

    public String ipLocal() {
        SharedPreferences prefs = getSharedPreferences("ip", MODE_PRIVATE);
        return prefs.getString("ip_servidor", "IP - Null");
    }

    public void adicionarProduto(View v) {
        Spinner produtoSP = findViewById(R.id.AnotarPedido_Spinner_Produtos);
        String produtoSelecionado = produtoSP.getSelectedItem().toString();

        EditText qtdET = findViewById(R.id.AnotarPedido_EditText_Quantidade);
        String qtde = qtdET.getText().toString();

        if (qtde.isEmpty() || Integer.parseInt(qtde) <= 0) {
            Toast.makeText(this, "Informe uma quantidade válida", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://" + ipLocal() + ":8080/restaurante/produtos";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray produtos = new JSONArray(response);

                for (int i = 0; i < produtos.length(); i++) {
                    JSONObject produto = produtos.getJSONObject(i);
                    String nomeProduto = produto.getString("nome");

                    if (nomeProduto.equals(produtoSelecionado)) {
                        String itemPedido = nomeProduto + " - Quantidade: " + qtde;
                        listaProdutosPedido.add(itemPedido);
                        adapterProdutosPedido.notifyDataSetChanged();
                        Toast.makeText(this, "Produto adicionado", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao processar resposta JSON", Toast.LENGTH_LONG).show();
            }
        }, error -> {
            Toast.makeText(this, "Erro na requisição: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        });
        requestQueue.add(stringRequest);
    }

    public void produtosCadastrados() {
        String url = "http://" + ipLocal() + ":8080/restaurante/produtos";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray produtos = new JSONArray(response);
                List<String> nomesProdutos = new ArrayList<>();

                for (int i = 0; i < produtos.length(); i++) {
                    JSONObject produto = produtos.getJSONObject(i);
                    String nome = produto.getString("nome");
                    nomesProdutos.add(nome);

                    // Cria o adapter com os nomes
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            this,
                            android.R.layout.simple_spinner_item,
                            nomesProdutos
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    Spinner spinnerProdutos = findViewById(R.id.AnotarPedido_Spinner_Produtos);
                    spinnerProdutos.setAdapter(adapter);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao processar resposta JSON", Toast.LENGTH_LONG).show();
            }
        }, error -> {
            Toast.makeText(this, "Erro na requisição: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        });
        requestQueue.add(stringRequest);
    }


    public void salvarPedido(View v) {
        // Recupera a intent que iniciou a Activity
        Intent intent = getIntent();
        // Obtém o nome do cliente da intent
        String nomeCliente = intent.getStringExtra("nome_cliente");
        // Obtém o ID da mesa da intent (ou -1 se não encontrado)
        int idMesa = intent.getIntExtra("id_mesa", -1);

        // Verifica se o ID da mesa é válido
        if (idMesa == -1) {
            Toast.makeText(this, "Mesa inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        final int mesaIdFinal = idMesa; // variável efetivamente final

        RequestQueue queue = Volley.newRequestQueue(this);

        // URL para obter os dados da mesa via GET
        String urlGetMesa = "http://" + ipLocal() + ":8080/restaurante/mesas/" + mesaIdFinal;

        // Requisição GET para obter os dados da mesa específica
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlGetMesa, null,
                response -> {
                    try {
                        //Cria um Json Array para armazenar os pedidos atuais da mesa
                        JSONArray pedidosAtuais = response.getJSONArray("pedidos");
                        int ultimoIdPedido = 0;

                        // Encontra o maior ID de pedido atual da mesa
                        for (int i = 0; i < pedidosAtuais.length(); i++) {
                            JSONObject pedido = pedidosAtuais.getJSONObject(i);
                            int id = pedido.getInt("idPedido");
                            if (id > ultimoIdPedido) {
                                ultimoIdPedido = id;
                            }
                        }

                        // Cria uma cópia da lista de produtos selecionados para o pedido atual
                        ArrayList<String> listaItens = new ArrayList<>(listaProdutosPedido); //Recupera item da ListView

                        // Itera sobre todos os itens do pedido
                        for (int index = 0; index < listaItens.size(); index++) {
                            // Obtém o item da lista na posição 'index'. Exemplo de item: "Coca-Cola - Quantidade: 2"
                            String item = listaItens.get(index);
                            // Divide a string do item em duas partes, separando pelo texto " - Quantidade: "
                            // Resultado: partes[0] = "Coca-Cola", partes[1] = "2"
                            String[] partes = item.split(" - Quantidade: ");
                            // Nome do produto
                            String nomeProduto = partes[0];
                            // Quantidade do produto
                            int quantidade = Integer.parseInt(partes[1]);

                            // URL para buscar todos os produtos cadastrados
                            String urlProduto = "http://" + ipLocal() + ":8080/restaurante/produtos";
                            //Atribui o ID do Produto atual para variável finalIndex
                            int finalIndex = index;
                            //Atribui o ID do ultimo pedido
                            int finalUltimoIdPedido = ultimoIdPedido;

                            // Requisição GET para obter a lista de produtos disponíveis
                            JsonArrayRequest produtoRequest = new JsonArrayRequest(Request.Method.GET, urlProduto, null,
                                    responseProdutos -> {
                                        try {
                                            // Procura o produto correspondente pelo nome
                                            for (int j = 0; j < responseProdutos.length(); j++) {
                                                //Cria um objeto dos produtos
                                                JSONObject produtoObj = responseProdutos.getJSONObject(j);
                                                // Se o nome do produto for igual ao item selecionado
                                                if (produtoObj.getString("nome").equals(nomeProduto)) {
                                                    // Cria um novo objeto JSON representando o pedido
                                                    JSONObject pedidoNovo = new JSONObject();
                                                    //Coloquei +1 para o ID dos pedidos iniciar no 1
                                                    pedidoNovo.put("idPedido", finalUltimoIdPedido + 1);
                                                    pedidoNovo.put("produto", produtoObj);
                                                    pedidoNovo.put("quantidade", quantidade);
                                                    pedidoNovo.put("observacao", "");

                                                    //Adiciona o Json aos pedidos atuais da mesa
                                                    pedidosAtuais.put(pedidoNovo);
                                                    break; // Encerra o loop ao encontrar o produto
                                                }
                                            }

                                            // Se for o último item da lista, atualiza os dados da mesa
                                            if (finalIndex == listaItens.size() - 1) {
                                                // Cria um novo objeto JSON com os dados atualizados da mesa
                                                JSONObject mesaJSON = new JSONObject();
                                                mesaJSON.put("id", mesaIdFinal);
                                                mesaJSON.put("status", "Ocupado");
                                                mesaJSON.put("cliente", nomeCliente);
                                                mesaJSON.put("pedidos", pedidosAtuais);

                                                // Calcula o valor total da conta
                                                float valorConta = 0;
                                                for (int k = 0; k < pedidosAtuais.length(); k++) {
                                                    JSONObject pedido = pedidosAtuais.getJSONObject(k);
                                                    JSONObject produto = pedido.getJSONObject("produto");
                                                    float preco = (float) produto.getDouble("precoUnitario");
                                                    int qtd = pedido.getInt("quantidade");
                                                    valorConta += preco * qtd;
                                                }
                                                mesaJSON.put("valorConta", valorConta);

                                                // URL para atualizar a mesa com PUT
                                                String urlPutMesa = "http://" + ipLocal() + ":8080/restaurante/mesas/" + mesaIdFinal;
                                                JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, urlPutMesa, mesaJSON,
                                                        r -> {
                                                            // Sucesso ao salvar o pedido
                                                            Toast.makeText(this, "Pedido salvo com sucesso", Toast.LENGTH_SHORT).show();
                                                            listaProdutosPedido.clear(); // limpa lista após salvar
                                                            adapterProdutosPedido.notifyDataSetChanged(); // Atualiza a ListView
                                                        },
                                                        error -> Toast.makeText(this, "Erro ao salvar pedido", Toast.LENGTH_SHORT).show());
                                                queue.add(putRequest);
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, "Erro ao processar produto", Toast.LENGTH_SHORT).show();
                                        }
                                    },
                                    error -> Toast.makeText(this, "Erro ao buscar produto", Toast.LENGTH_SHORT).show());
                            queue.add(produtoRequest);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Erro ao processar resposta da mesa", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Erro ao buscar mesa", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }


    public void voltar(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void remover(View v) {
        ListView listView = findViewById(R.id.AnotarPedido_ListView_ProdutosPedido);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        List<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            int position = checked.keyAt(i);
            if (checked.valueAt(i)) {
                selectedItems.add(adapter.getItem(position));
            }
        }

        //Limpar a seleção
        for (int i = 0; i < adapter.getCount(); i++) {
            listView.setItemChecked(i, false);
        }

        for (String item : selectedItems) {
            adapter.remove(item);
            Log.i("Selecionados", "Removido a cor " + item);
        }


    }

    public void fecharMesa(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Fechar Mesa")
                .setMessage("Tem certeza que deseja fechar esta mesa?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    // Código para fechar a mesa
                    Intent intent = getIntent();
                    int idMesa = intent.getIntExtra("id_mesa", -1);
                    String url = "http://" + ipLocal() + ":8080/restaurante/mesas/" + idMesa;

                    RequestQueue requestQueue = Volley.newRequestQueue(this);

                    StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                            response -> {
                                Toast.makeText(this, "Mesa fechada com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent mudarTela = new Intent(this, MainActivity.class);
                                startActivity(mudarTela);
                            },
                            erro -> {
                                Toast.makeText(this, "Falha no fechamento da mesa", Toast.LENGTH_SHORT).show();
                            }
                    );

                    requestQueue.add(stringRequest);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

}