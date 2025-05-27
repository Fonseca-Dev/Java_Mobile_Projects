package com.example.seriesapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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

    public void exibir(View v) {
        Toast.makeText(this, "Kauê Rocha da Fonseca - RA: 12417172", Toast.LENGTH_LONG).show();
    }

    public void validar(View v) {
        Serie s = obterSerie();

        EditText temporadaET = findViewById(R.id.Main_EditText_Temporada);
        String temporadaStr = temporadaET.getText().toString();

        EditText avaliacaoET = findViewById(R.id.Main_EditText_Avaliacao);
        String avaliacaoStr = avaliacaoET.getText().toString();


        if (s.titulo.isEmpty()) {
            Toast.makeText(this, "Título não pode ser vazio", Toast.LENGTH_LONG).show();
            return;
        }

        if (temporadaStr.isEmpty()) {
            Toast.makeText(this, "Temporada não pode ser vazia", Toast.LENGTH_LONG).show();
            return;
        }

        if (s.temporada < 1) {
            Toast.makeText(this, "Temporada deve ser maior que 0", Toast.LENGTH_LONG).show();
            return;
        }

        if (avaliacaoStr.isEmpty()) {
            Toast.makeText(this, "Avaliação não pode ser vazia", Toast.LENGTH_LONG).show();
            return;
        }

        if (s.avaliacao < 0 || s.avaliacao > 10) {
            Toast.makeText(this, "A avaliação deve estar entre 0 e 10", Toast.LENGTH_LONG).show();
            return;
        }

        if (s.genero.isEmpty()) {
            Toast.makeText(this, "Gênero não pode ser vazio", Toast.LENGTH_LONG).show();
            return;
        }

        if (s.finalizada.isEmpty()) {
            Toast.makeText(this, "Campo finalizada não pode estar vazio!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!s.finalizada.equalsIgnoreCase("Sim")
                && !s.finalizada.equalsIgnoreCase("Não")
                && !s.finalizada.equalsIgnoreCase("S")
                && !s.finalizada.equalsIgnoreCase("N")
                && !s.finalizada.equalsIgnoreCase("Nao")) {
            Toast.makeText(this, "Informação de Finalizada inválida!", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Série válida!", Toast.LENGTH_LONG).show();

        Toast.makeText(this, "Título da Série: " + s.titulo + " , Temporada: " + s.temporada, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Avaliação da Série: " + s.avaliacao + " , Gênero: " + s.genero, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Série Finalizada? " + s.finalizada, Toast.LENGTH_LONG).show();

    }



    public Serie obterSerie() {
        Serie s = new Serie();

        EditText tituloET = findViewById(R.id.Main_EditText_Titulo);
        s.titulo = tituloET.getText().toString();

        EditText temporadaET = findViewById(R.id.Main_EditText_Temporada);
        String temporadaStr = temporadaET.getText().toString();
        s.temporada = temporadaStr.isEmpty() ? 0 : Integer.parseInt(temporadaStr);

        EditText avaliacaoET = findViewById(R.id.Main_EditText_Avaliacao);
        String avaliacaoStr = avaliacaoET.getText().toString();
        s.avaliacao = avaliacaoStr.isEmpty() ? 0.0 : Double.parseDouble(avaliacaoStr);

        EditText generoET = findViewById(R.id.Main_EditText_Genero);
        s.genero = generoET.getText().toString();

        EditText finalizadaET = findViewById(R.id.Main_EditText_Finalizada);
        s.finalizada = finalizadaET.getText().toString();


        return s;
    }
}
