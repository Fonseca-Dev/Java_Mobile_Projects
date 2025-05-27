package com.example.aula4carroapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }

    public void ListarCarros(View v){
        Intent i = new Intent(this, Carros_Cadastrados.class);
        startActivity(i);
    }
    public void Cadastrar(View v){

        EditText editTextPlaca = findViewById(R.id.Main_EditText_Placa);
        String placa = editTextPlaca.getText().toString().trim();
        EditText editTextModelo = findViewById(R.id.Main_EditText_Modelo);
        String modelo = editTextModelo.getText().toString().trim();
        Spinner spinnerCor = findViewById(R.id.Main_Spinner_Cor);
        String cor = spinnerCor.getSelectedItem().toString();
        EditText editTextAnoFabricacao = findViewById(R.id.Main_EditText_Ano_Fabricacao);
        String anoFabricacao = editTextAnoFabricacao.getText().toString().trim();
        Spinner spinnerEstadoConservacao = findViewById(R.id.Main_Spinner_Estado_Conservacao);
        String estadoConservacao = spinnerEstadoConservacao.getSelectedItem().toString();

        // Validação da placa
        if (placa.isEmpty() || !placa.matches("\\w{3}-\\w{4}")) {
            editTextPlaca.setError("Placa inválida! Formato esperado: ABC-1234");
            return;
        }

        // Validação do modelo
        if (modelo.isEmpty()) {
            editTextModelo.setError("Modelo não pode estar vazio");
            return;
        }

        // Validação do ano de fabricação
        if (anoFabricacao.isEmpty()) {
            editTextAnoFabricacao.setError("Ano de fabricação não pode estar vazio");
            return;
        }

        int anoFabricacaonum;
        try {
            anoFabricacaonum = Integer.parseInt(anoFabricacao);
            int anoAtual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            if (anoFabricacaonum > anoAtual) {
                editTextAnoFabricacao.setError("Ano de fabricação não pode ser maior que o ano atual");
                return;
            }
        } catch (NumberFormatException e) {
            editTextAnoFabricacao.setError("Ano de fabricação deve ser um número");
            return;
        }

        String carroCadastrado = ("Placa: " + placa
                + " , Modelo: " + modelo
                + " , Cor: " + cor
                + " , Ano de Fabricação: " + anoFabricacao
                + " , Estado de Conservação: " + estadoConservacao);

        Carros.carros.add(carroCadastrado);

        Intent i = new Intent(this, Carros_Cadastrados.class);
        startActivity(i);
    }



}