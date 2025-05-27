package org.code.poupa.drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.code.poupa.BottomNavManager;
import org.code.poupa.LoginActivity;
import org.code.poupa.R;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        View rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            int topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setPadding(0, topInset, 0, 0); // aplica padding superior para não sobrepor a status bar
            return insets;
        });

        // Configura a toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Botão de voltar
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        // Configuração das barras do sistema
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.white));
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(
                window, window.getDecorView());
        controller.setAppearanceLightStatusBars(true);
        controller.setAppearanceLightNavigationBars(true);

        //Recupera os dados da Intent
        String nome = getIntent().getStringExtra("usuario_nome");
        String email = getIntent().getStringExtra("usuario_email");
        String telefone = getIntent().getStringExtra("usuario_telefone");
        String nascimento = getIntent().getStringExtra("usuario_nascimento");
        String cpf = getIntent().getStringExtra("usuario_cpf");

        //Encontra os TextViews
        TextView tvNome = findViewById(R.id.tvUserName);
        TextView tvEmail = findViewById(R.id.tvUserEmail);
        TextView tvTelefone = findViewById(R.id.tvPhone);
        TextView tvNascimento = findViewById(R.id.tvBirthDate);
        TextView tvCpf = findViewById(R.id.tvCpf);

        //Define os valores nos campos
        tvNome.setText(nome != null ? nome : "Nome não disponível");
        tvEmail.setText(email != null ? email : "Email não disponível");
        tvTelefone.setText(telefone != null ? telefone : "Telefone não disponível");
        tvNascimento.setText(nascimento != null ? nascimento : "Data não disponível");
        tvCpf.setText(cpf != null ? cpf : "CPF não disponível");

        LinearLayout btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            // Ação de logout: limpar SharedPreferences, ir para LoginActivity, etc.
            SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }

}