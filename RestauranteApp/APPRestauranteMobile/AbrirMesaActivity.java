package com.example.aula7restauranteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.nio.charset.StandardCharsets;

public class AbrirMesaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abrir_mesa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String numeroMesa = intent.getStringExtra("numeroMesa");
        String estadoMesa = intent.getStringExtra("estadoMesa");

        TextView mesaTV = findViewById(R.id.AbrirMesa_TextView_Mesa);

        mesaTV.setText("Mesa: " + numeroMesa + " - " + estadoMesa);

    }

    public String ipLocal() {
        SharedPreferences prefs = getSharedPreferences("ip", MODE_PRIVATE);
        return prefs.getString("ip_servidor", "IP - Null");
    }

    public void abrirMesa(View v) {
        Intent intent = getIntent();
        String numeroMesa = intent.getStringExtra("numeroMesa");
        EditText nomeClienteET = findViewById(R.id.AbrirMesa_TextInputEditText_NomeCliente);
        String nomeCliente = nomeClienteET.getText().toString();

        if (nomeCliente.trim().isEmpty()) {
            Toast.makeText(this, "Digite o nome do cliente!", Toast.LENGTH_LONG).show();
            return;
        }


        String url = "http://" + ipLocal() + ":8080/restaurante/mesas/" + numeroMesa;


        // Cria JSON com os dados da mesa
        JSONObject json = new JSONObject();
        try {
            json.put("status", "Ocupado");
            json.put("cliente", nomeCliente);
            json.put("pedidos", new JSONArray());
            json.put("valorConta", 0);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao montar JSON", Toast.LENGTH_LONG).show();
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                response -> {
                    Toast.makeText(this, "Mesa aberta com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent mudarTela = new Intent(this, MainActivity.class);
                    startActivity(mudarTela);
                },
                erro -> tratarErro(erro)) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return json.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        requestQueue.add(stringRequest);


    }


    public void voltar(View v){
        Intent mudarTela = new Intent(this, MainActivity.class);
        startActivity(mudarTela);
    }


    public void tratarErro(VolleyError erro) {
        String mensagem = (erro.getMessage() != null) ? erro.getMessage() : "Erro inesperado!";
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

}