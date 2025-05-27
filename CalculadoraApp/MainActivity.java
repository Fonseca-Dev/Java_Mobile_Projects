package com.example.aula3calculadoraapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    public void calcular(View v) {
        EditText ETNumero1 = findViewById(R.id.Main_EditText_Numero1);
        EditText ETNumero2 = findViewById(R.id.Main_EditText_Numero2);
        TextView TVResultado = findViewById(R.id.Main_TextView_Resultado);
        Spinner Spoperacao = findViewById(R.id.Main_Spinner_Operacao);
        String operacao = Spoperacao.getSelectedItem().toString();

        String strNum1 = ETNumero1.getText().toString();
        String strNum2 = ETNumero2.getText().toString();

        if (strNum1.isEmpty() || strNum2.isEmpty()) {
            TVResultado.setText("Por favor, insira ambos os números.");
            return;
        }

        try {
            float numero1 = Float.parseFloat(strNum1);
            float numero2 = Float.parseFloat(strNum2);
            float resultado;

            switch (operacao.toLowerCase()) {
                case "somar":
                    resultado = numero1 + numero2;
                    TVResultado.setText(String.format("Resultado: %.2f" , resultado));
                    break;
                case "subtrair":
                    resultado = numero1 - numero2;
                    TVResultado.setText(String.format("Resultado: %.2f" , resultado));
                    break;
                case "multiplicar":
                    resultado = numero1 * numero2;
                    TVResultado.setText(String.format("Resultado: %.2f" , resultado));
                    break;
                case "dividir":
                    if (numero2 == 0) {
                        TVResultado.setText("Erro: divisão por zero.");
                    } else {
                        resultado = numero1 / numero2;
                        TVResultado.setText(String.format("Resultado: %.2f" , resultado));
                    }
                    break;
                default:
                    TVResultado.setText("Operação inválida.");
                    break;
            }
        } catch (NumberFormatException e) {
            TVResultado.setText("Erro ao converter os números.");
        }
    }

}