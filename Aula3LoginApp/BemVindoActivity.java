package com.example.aula3loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BemVindoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bem_vindo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView raNome = findViewById(R.id.BemVIndo_TextView_raNome);
        TextView aulasSemana = findViewById(R.id.BemVIndo_TextView_aulaSemana);

        raNome.setText("RA: 123456 | Nome: João da Silva");

        String aulas = "Aulas da Semana:\n\n" +
                "- Segunda: Estrutura de Dados\n" +
                "- Terça: POO em Java\n" +
                "- Quarta: Banco de Dados\n" +
                "- Quinta: Desenvolvimento Android";

        aulasSemana.setText(aulas);
    }

    public void voltar (View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}