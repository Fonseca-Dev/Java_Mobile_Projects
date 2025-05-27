package org.code.poupa;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        View rootView = findViewById(android.R.id.content);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            int topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setPadding(0, topInset, 0, 0); // aplica padding superior para não sobrepor a status bar
            return insets;
        });

        TextView entrarTV = findViewById(R.id.Cadastro_TextView_Entrar);

        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapConfirmed(MotionEvent e) {
                // Redirecionar para CadastroActivity
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            }
        });

        // Vincular GestureDetector ao TextView
        entrarTV.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));


    }

    public void onEntrarSeClick(View view) {
        view.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100)
                .withEndAction(() -> {
                    view.animate().scaleX(1f).scaleY(1f).setDuration(100).start();

                    // Redirecionar após a animação
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                })
                .start();
    }
}