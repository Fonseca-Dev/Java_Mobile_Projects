package org.code.poupa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

import org.code.poupa.action.ActionBottomSheetDialog;
import org.code.poupa.drawer.profileActivity;
import org.code.poupa.wallet.WalletActivity;

import java.util.Calendar;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            int topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setPadding(0, topInset, 0, 0); // aplica padding superior para não sobrepor a status bar
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        String nomeUsuario = prefs.getString("usuario_nome", "Usuário");
        String emailUsuario = prefs.getString("usuario_email", "email@exemplo.com");
        String saldoUsuario = prefs.getString("usuario_saldo", "0.0");
        String gastosUsuario = prefs.getString("usuario_gastos", "0.0");
        String investimentosUsuario = prefs.getString("usuario_investimentos", "0.0");
        String metasUsuario = prefs.getString("usuario_metas", "0.0");
        String despesaCredito = prefs.getString("despesa_cartao_credito", "0.0");
        String despesaDebito = prefs.getString("despesa_cartao_debito", "0.0");
        String telefoneUsuario = prefs.getString("usuario_telefone", "");
        String nascimentoUsuario = prefs.getString("usuario_nascimento", "");
        String cpfUsuario = prefs.getString("usuario_cpf", "");

        findViewById(R.id.Main_fab).setOnClickListener(v -> {
            ActionBottomSheetDialog bottomSheet = new ActionBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "ActionBottomSheet");
        });

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

        // Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.Main_topAppBar);
        setSupportActionBar(toolbar);

        // Configuração do Drawer
        drawerLayout = findViewById(R.id.Main_drawer_layout);
        NavigationView navigationView = findViewById(R.id.Main_nav_view);
        View headerView = navigationView.getHeaderView(0);

        // Acessa os TextViews do header
        TextView txtHeaderName = headerView.findViewById(R.id.txtHeaderName);
        TextView txtHeaderEmail = headerView.findViewById(R.id.txtHeaderEmail);

        // Define os valores recebidos via Intent ou outro meio
        txtHeaderName.setText(nomeUsuario); // já está no seu Intent
        txtHeaderEmail.setText(emailUsuario);

        // Cria o DrawerArrowDrawable e configura o toggle
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        // Define a cor do ícone como preto
        DrawerArrowDrawable drawerArrow = new DrawerArrowDrawable(this);
        drawerArrow.setColor(ContextCompat.getColor(this, R.color.black));
        drawerToggle.setDrawerArrowDrawable(drawerArrow);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // Configura o listener do navigation drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                Intent profileIntent = new Intent(this, profileActivity.class);
                profileIntent.putExtra("usuario_nome", nomeUsuario);
                profileIntent.putExtra("usuario_email", emailUsuario);
                profileIntent.putExtra("usuario_telefone", telefoneUsuario);
                profileIntent.putExtra("usuario_nascimento", nascimentoUsuario);
                profileIntent.putExtra("usuario_cpf", cpfUsuario);
                startActivity(profileIntent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

            if (id == R.id.nav_logout) {
                SharedPreferences sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Limpa tudo
                editor.apply();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });

        // Configura o botão de notificação
        ImageButton btnNotification = findViewById(R.id.Main_btnNotification);
        btnNotification.setOnClickListener(v ->
                Toast.makeText(this, "Notificações em breve!", Toast.LENGTH_SHORT).show()
        );

        // Configura o toggle de visibilidade do saldo
        ShapeableImageView toggleBalance = findViewById(R.id.Main_toggleBalanceVisibility);


        final TextView accountBalance = findViewById(R.id.Main_accountBalance);
        final boolean[] isBalanceVisible = {true};

        toggleBalance.setOnClickListener(v -> {
            if (isBalanceVisible[0]) {
                accountBalance.setText("••••••••••••••••");
                toggleBalance.setImageResource(R.drawable._ic_off_visibility_icon); // Supondo que tenha ícone "olho fechado"
            } else {
                accountBalance.setText(formatarValor(saldoUsuario));
                toggleBalance.setImageResource(R.drawable.ic_visibility); // Ícone "olho aberto"
            }
            isBalanceVisible[0] = !isBalanceVisible[0];
        });


        // Atualiza os dados do usuário
        updateUserData(nomeUsuario, saldoUsuario, despesaCredito, despesaDebito, gastosUsuario, investimentosUsuario, metasUsuario);
        setupBottomNavigation();

        //Enviar o valor do Saldo do Usuário para a Tela Carteira
        Intent intent = new Intent(this, WalletActivity.class);
        String saldoFormatado = formatarValor(saldoUsuario);
        intent.putExtra("saldoUsuario", saldoFormatado);

    }

    private void updateUserData(String nomeUsuario, String saldoUsuario, String despesaCredito, String despesaDebito, String gastosUsuario, String investimentosUsuario, String metasUsuario) {
        TextView greetingText = findViewById(R.id.Main_greetingText);
        TextView userName = findViewById(R.id.Main_userName);
        TextView accountHolderName = findViewById(R.id.Main_accountHolderName);
        TextView accountBalance = findViewById(R.id.Main_accountBalance);
        TextView despesaCreditoTV = findViewById(R.id.Main_creditValue);
        TextView despesaDebitoTV = findViewById(R.id.Main_debitValue);
        TextView cardSaldoTV = findViewById(R.id.button_value1);
        TextView cardGastosTV = findViewById(R.id.button_value2);
        TextView cardInvestTV = findViewById(R.id.button_value3);
        TextView cardMetasTV = findViewById(R.id.button_value4);

        greetingText.setText(getGreetingBasedOnTime());
        userName.setText(nomeUsuario);
        accountHolderName.setText(nomeUsuario);


        // Aplica a formatação correta
        String saldoFormatado = formatarValor(saldoUsuario);
        accountBalance.setText(saldoFormatado);
        cardSaldoTV.setText(saldoFormatado);
        String despesaCreditoFormatado = formatarValor(despesaCredito);
        despesaCreditoTV.setText(despesaCreditoFormatado);
        String despesaDebitoFormatado = formatarValor(despesaDebito);
        despesaDebitoTV.setText(despesaDebitoFormatado);
        String gastosFormatado = formatarValor(gastosUsuario);
        cardGastosTV.setText(gastosFormatado);
        String investFormatado = formatarValor(investimentosUsuario);
        cardInvestTV.setText(investFormatado);
        String metasFormatado = formatarValor(metasUsuario);
        cardMetasTV.setText(metasFormatado);

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


    private String getGreetingBasedOnTime() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay < 12) {
            return "Bom dia,";
        } else if (timeOfDay < 18) {
            return "Boa tarde,";
        } else {
            return "Boa noite,";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}