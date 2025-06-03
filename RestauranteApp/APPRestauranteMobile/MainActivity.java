package com.example.aula7restauranteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        estadosMesas();

        SharedPreferences prefs = getSharedPreferences("ip", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ip_servidor", ipLocal());
        editor.apply();
    }

    public String ipLocal() {
        return "192.168.1.103";
    }

    public void estadosMesas() {

        String url = "http://" + ipLocal() + ":8080/restaurante/mesas";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {

                    try {
                        JSONArray mesas = new JSONArray(response);

                        for (int i = 0; i < mesas.length(); i++) {
                            JSONObject mesaStatus = mesas.getJSONObject(i);
                            int id = mesaStatus.getInt("id"); // deve ser de 1 a 8
                            String status = mesaStatus.getString("status");

                            // Obtem o ID do botão dinamicamente com base no padrão mesa1, mesa2, etc.
                            int resID = getResources().getIdentifier("button" + id, "id", getPackageName());
                            Button btnMesa = findViewById(resID);

                            if (btnMesa != null) {
                                if (status.equalsIgnoreCase("livre")) {
                                    btnMesa.setEnabled(true);
                                    btnMesa.setBackgroundColor(Color.parseColor("#4CAF50")); // Verde
                                    btnMesa.setTextColor(Color.WHITE); // Texto normal
                                    btnMesa.setTypeface(null, Typeface.NORMAL);
                                } else {
                                    btnMesa.setBackgroundColor(Color.RED); // Fundo vermelho
                                    btnMesa.setTextColor(Color.WHITE);     // Texto branco
                                    btnMesa.setTypeface(null, Typeface.BOLD);
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Erro ao processar resposta JSON", Toast.LENGTH_LONG).show();
                    }
                },
                erro -> tratarErro(erro)
        );

        requestQueue.add(stringRequest);
    }

    public void abrirMesa(View v) {

        String url = "http://" + ipLocal() + ":8080/restaurante/mesas";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray mesas = new JSONArray(response);

                        // Identifica o botão clicado
                        int idBotao = v.getId(); // ID do botão clicado
                        String nomeRecurso = getResources().getResourceEntryName(idBotao);
                        String numeroMesa = nomeRecurso.replace("button", "");


                        for (int i = 0; i < mesas.length(); i++) {
                            JSONObject mesaStatus = mesas.getJSONObject(i);
                            int id = mesaStatus.getInt("id");

                            // Se achou a mesa clicada no JSON
                            if (String.valueOf(id).equals(numeroMesa)) {
                                String status = mesaStatus.getString("status");
                                String nomeCliente = mesaStatus.getString("cliente");

                                if (status.equalsIgnoreCase("Ocupado")) {
                                    // Vai para outra Activity se a mesa estiver ocupada
                                    Intent intent = new Intent(this, AnotarPedidoActivity.class);
                                    intent.putExtra("id_mesa", id);
                                    intent.putExtra("nome_cliente", nomeCliente);
                                    startActivity(intent);
                                } else {
                                    // Vai para AbrirMesaActivity se estiver livre
                                    Intent intent = new Intent(this, AbrirMesaActivity.class);
                                    intent.putExtra("numeroMesa", numeroMesa);
                                    intent.putExtra("estadoMesa", status);
                                    startActivity(intent);
                                }
                                break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Erro ao processar resposta JSON", Toast.LENGTH_LONG).show();
                    }
                },
                erro -> tratarErro(erro)
        );

        requestQueue.add(stringRequest);

    }


    public void tratarErro(VolleyError erro) {
        String mensagem;

        if (erro.networkResponse != null && erro.networkResponse.data != null) {
            mensagem = new String(erro.networkResponse.data);
        } else if (erro.getMessage() != null) {
            mensagem = ("1º else" + erro.getMessage());
        } else {
            mensagem = "Erro desconhecido na rede";
        }

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
        Log.e("VolleyError", mensagem, erro);
    }


}