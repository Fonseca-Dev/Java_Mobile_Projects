package org.code.poupa.wallet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import org.code.poupa.R;

import java.text.NumberFormat;
import java.util.Locale;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wallet);

        View rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            int topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setPadding(0, topInset, 0, 0); // aplica padding superior para não sobrepor a status bar
            return insets;
        });


        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        String saldoUsuario = prefs.getString("usuario_saldo", "0.0");

        // Atualiza o TextView com o saldo
        TextView saldoTextView = findViewById(R.id.Wallet_TextView_Saldo);
        String saldoUsuarioFormatado = formatarValor(saldoUsuario);
        saldoTextView.setText(saldoUsuarioFormatado);

        TextView receitasTV = findViewById(R.id.Wallet_TextView_Receitas);
        float receitasFL = (Float.parseFloat(saldoUsuario)) + 1000;
        String receitasFLtoStr = Float.toString(receitasFL);
        String receitasFormatado = formatarValor(receitasFLtoStr);
        receitasTV.setText(receitasFormatado);

        TextView despesasTV = findViewById(R.id.Wallet_TextView_Despesas);
        float despesasFL = receitasFL - (Float.parseFloat(saldoUsuario));
        String despesasFLtoStr = Float.toString(despesasFL);
        String despesasFormatado = formatarValor(despesasFLtoStr);
        despesasTV.setText(despesasFormatado);






        // Botão de voltar para MainActivity
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finaliza a Activity atual e volta para a MainActivity
                finish();
                // Adiciona animação de voltar (deslize da esquerda para direita)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        // Configuração das barras do sistema
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.branco_cinzentado));

        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(
                window,
                window.getDecorView()
        );
        controller.setAppearanceLightStatusBars(true);
        controller.setAppearanceLightNavigationBars(true);




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
}