package com.example.aula3loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public void entrar(View v){
        EditText loginET = findViewById(R.id.Main_EditText_Login);
        EditText senhaET = findViewById(R.id.Main_EditText_Senha);

        String login = loginET.getText().toString();
        String senha = senhaET.getText().toString();


        if (login.equals(Valores.login) && senha.equals(Valores.senha)) {
            Intent intent = new Intent(this, BemVindoActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ErroActivity.class);
            startActivity(intent);
        }

    }
}