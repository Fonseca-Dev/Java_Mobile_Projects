package org.code.poupa.action.economic;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import org.code.poupa.R;
import java.util.Calendar;
import java.util.Locale;

public class EconomicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economic);

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
        // Categorias de economia
        String[] categories = {
                "Reserva Emergencial",
                "Investimentos",
                "Fundo de Férias",
                "Compras Futuras",
                "Educação",
                "Outros"
        };

        AutoCompleteTextView categoryDropdown = findViewById(R.id.actvCategory);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categories
        );
        categoryDropdown.setAdapter(categoryAdapter);
    }

    private void saveEconomicData() {
        // Implemente a lógica para salvar os dados da economia
        // Exemplo de obtenção dos valores:
        /*
        TextInputEditText etName = findViewById(R.id.etName);
        TextInputEditText etValue = findViewById(R.id.etValue);
        TextInputEditText etDate = findViewById(R.id.etDate);
        AutoCompleteTextView actvCategory = findViewById(R.id.actvCategory);
        TextInputEditText etDescription = findViewById(R.id.etDescription);

        String name = etName.getText().toString();
        String value = etValue.getText().toString();
        String date = etDate.getText().toString();
        String category = actvCategory.getText().toString();
        String description = etDescription.getText().toString();

        // Validação e lógica de salvamento aqui
        */

        // Após salvar, feche a activity
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}