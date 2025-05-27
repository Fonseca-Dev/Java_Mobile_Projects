package org.code.poupa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class LoginActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verifica se o usuário já está logado
        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        boolean logado = prefs.getBoolean("logado", false);

        if (logado) {
            // Redireciona diretamente para a MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario_nome", prefs.getString("usuario_nome", ""));
            intent.putExtra("usuario_email", prefs.getString("usuario_email", ""));
            intent.putExtra("usuario_saldo", prefs.getString("usuario_saldo", ""));
            intent.putExtra("despesa_cartao_credito", prefs.getString("despesa_cartao_credito", ""));
            intent.putExtra("despesa_cartao_debito", prefs.getString("despesa_cartao_debito", ""));
            intent.putExtra("usuario_telefone", prefs.getString("usuario_telefone", ""));
            intent.putExtra("usuario_nascimento", prefs.getString("usuario_nascimento", ""));
            intent.putExtra("usuario_cpf", prefs.getString("usuario_cpf", ""));
            startActivity(intent);
            finish(); // Encerra a LoginActivity para não voltar ao login com o botão "Voltar"
            return;
        }

        // Se não estiver logado, exibe a tela de login normalmente
        setContentView(R.layout.activity_login);

        View rootView = findViewById(android.R.id.content);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            int topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setPadding(0, topInset, 0, 0); // aplica padding superior para não sobrepor a status bar
            return insets;
        });

        /* textView7 = findViewById(R.id.textView7);

        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapConfirmed(MotionEvent e) {
                // Redirecionar para CadastroActivity
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                return true;
            }
        });

        // Vincular GestureDetector ao TextView
        textView7.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));*/


    }

    public void onCadastreSeClick(View view) {
        view.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100)
                .withEndAction(() -> {
                    view.animate().scaleX(1f).scaleY(1f).setDuration(100).start();

                    // Redirecionar após a animação
                    Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                    startActivity(intent);
                })
                .start();
    }

    public void postLogin(View v) throws Exception {
        String ip_lele = "192.168.0.116";
        String ip = "192.168.1.103";

        //Recuperar valores
        EditText emailET = findViewById(R.id.Login_EditText_Email);
        EditText senhaET = findViewById(R.id.Login_EditText_Senha);
        String email = emailET.getText().toString();
        String senha = senhaET.getText().toString();

        //Transformar em JSON os valores
        JSONObject object = new JSONObject();
        object.put("login", email);
        object.put("senha", senha);
        String json = object.toString();

        //Enviar ao Servidor o JSON
        String url = "http://" + ip + ":8080/login";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> tratarOK(response), erro -> tratarErro(erro)) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return json.getBytes(StandardCharsets.UTF_8);
            }
        };
        requestQueue.add(stringRequest);

    }

    private void tratarErro(VolleyError erro) {
        String mensagem;

        // Tenta extrair mensagem do corpo da resposta, se disponível
        if (erro.networkResponse != null && erro.networkResponse.data != null) {
            mensagem = new String(erro.networkResponse.data, StandardCharsets.UTF_8);
        } else if (erro.getMessage() != null) {
            mensagem = erro.getMessage();
        } else {
            mensagem = "Erro inesperado ao tentar fazer login.";
        }

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    public void tratarOK(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            String status = jsonResponse.optString("status", "NOK");

            if ("OK".equals(status)) {
                JSONObject usuarioObj = jsonResponse.getJSONObject("usuario");
                String nomeUsuario = usuarioObj.optString("nome");
                String emailUsuario = usuarioObj.optString("email");
                String saldoUsuario = usuarioObj.optString("saldo");
                String gastosUsuario = usuarioObj.optString("gastos");
                String investimentoUsuario = usuarioObj.optString("investimento");
                String metasUsuarrio = usuarioObj.optString("metas");
                String despesaCreditoUsuario = usuarioObj.optString("despesa_cartao_credito");
                String despesaDebitoUsuario = usuarioObj.optString("despesa_cartao_debito");
                String telefoneUsuario = usuarioObj.optString("telefone");
                String nascimentoUsuario = usuarioObj.optString("data_nascimento");
                String cpfUsuario = usuarioObj.optString("cpf");


                // Salvar dados no SharedPreferences
                SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("usuario_nome", nomeUsuario);
                editor.putString("usuario_email", emailUsuario);
                editor.putString("usuario_saldo", saldoUsuario);
                editor.putString("usuario_gastos", gastosUsuario);
                editor.putString("usuario_investimentos", investimentoUsuario);
                editor.putString("usuario_metas", metasUsuarrio);
                editor.putString("despesa_cartao_credito", despesaCreditoUsuario);
                editor.putString("despesa_cartao_debito", despesaDebitoUsuario);
                editor.putString("usuario_telefone", telefoneUsuario);
                editor.putString("usuario_nascimento", nascimentoUsuario);
                editor.putString("usuario_cpf", cpfUsuario);
                editor.putBoolean("logado", true); // marca que o usuário está logado
                editor.apply();

                Toast.makeText(this, "Login Efetuado!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Falha no Login!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao processar resposta do servidor.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}