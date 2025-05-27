package org.code.poupa.action.income;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.android.material.textfield.TextInputEditText;
import org.code.poupa.R;
import java.util.Calendar;
import java.util.Locale;

public class IncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        View rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            int topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setPadding(0, topInset, 0, 0); // aplica padding superior para não sobrepor a status bar
            return insets;
        });

        // Configuração do botão de voltar
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        // Configuração do campo de data
        TextInputEditText etDate = findViewById(R.id.etDate);
        etDate.setOnClickListener(v -> showDatePicker());

        // Configuração dos dropdowns
        setupDropdowns();

        // Configuração das barras do sistema
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.white));

        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(
                window,
                window.getDecorView()
        );
        controller.setAppearanceLightStatusBars(true);
        controller.setAppearanceLightNavigationBars(true);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> {
            String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", day, month + 1, year);
            ((TextInputEditText) findViewById(R.id.etDate)).setText(selectedDate);
        },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setupDropdowns() {
        // Categorias de receita
        String[] categories = {"Salário", "Freelance", "Investimentos", "Presente", "Outros"};
        AutoCompleteTextView categoryDropdown = findViewById(R.id.actvCategory);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, categories);
        categoryDropdown.setAdapter(categoryAdapter);

        // Tipos de receita
        String[] incomeTypes = {"Dinheiro", "Transferência", "PIX", "Cheque", "Outro"};
        AutoCompleteTextView incomeTypeDropdown = findViewById(R.id.actvIncomeType);
        ArrayAdapter<String> incomeTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, incomeTypes);
        incomeTypeDropdown.setAdapter(incomeTypeAdapter);
    }
}