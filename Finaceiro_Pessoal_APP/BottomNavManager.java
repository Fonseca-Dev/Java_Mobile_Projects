package org.code.poupa;

import android.content.Intent;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.code.poupa.action.ActionBottomSheetDialog;
import org.code.poupa.history.HistoryActivity;
import org.code.poupa.wallet.WalletActivity;

import java.util.HashMap;
import java.util.Map;

public class BottomNavManager implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final Map<Integer, Class<?>> menuItemToActivityMap;
    private final BottomNavigationView bottomNavView;
    private final BaseActivity context;

    public BottomNavManager(BaseActivity context, BottomNavigationView bottomNavView) {
        this.context = context;
        this.bottomNavView = bottomNavView;

        // Configuração das barras do sistema
        Window window = context.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(context, R.color.white));
        window.setNavigationBarColor(ContextCompat.getColor(context, R.color.white));

        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(
                window,
                window.getDecorView()
        );
        controller.setAppearanceLightStatusBars(true);
        controller.setAppearanceLightNavigationBars(true);

        menuItemToActivityMap = new HashMap<>();
        // Mapeia os itens do menu para suas respectivas Activities
        menuItemToActivityMap.put(R.id.nav_history, HistoryActivity.class);
        menuItemToActivityMap.put(R.id.nav_wallet, WalletActivity.class); // Adicionado aqui

        this.bottomNavView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.Main_fab) {
            showActionBottomSheet();
            return true;
        }

        Class<?> activityClass = menuItemToActivityMap.get(itemId);
        if (activityClass != null && !context.getClass().equals(activityClass)) {
            Intent intent = new Intent(context, activityClass);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);

            // Animações personalizadas (entrada da direita, saída para a esquerda)
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return true;
        }
        return false;
    }



    private void showActionBottomSheet() {
        // Cria e exibe o BottomSheetDialogFragment
        ActionBottomSheetDialog bottomSheet = new ActionBottomSheetDialog();
        bottomSheet.show(context.getSupportFragmentManager(), "ActionBottomSheet");
    }
}