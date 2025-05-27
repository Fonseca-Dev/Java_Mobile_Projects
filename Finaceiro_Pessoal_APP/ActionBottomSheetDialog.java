package org.code.poupa.action;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.code.poupa.R;
import org.code.poupa.action.economic.EconomicActivity;
import org.code.poupa.action.expense.ExpenseActivity;
import org.code.poupa.action.income.IncomeActivity;

public class ActionBottomSheetDialog extends BottomSheetDialogFragment {

    public interface ActionBottomSheetListener {
        void onActionSelected(String action);
    }

    private ActionBottomSheetListener listener;

    public void setActionBottomSheetListener(ActionBottomSheetListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_action, container, false);

        // Configuração das barras do sistema
        if (getActivity() != null) {
            Window window = getActivity().getWindow();
            window.setNavigationBarColor(ContextCompat.getColor(getActivity(), R.color.white));
            window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.white));

            WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(
                    window,
                    window.getDecorView()
            );
            controller.setAppearanceLightStatusBars(true);
            controller.setAppearanceLightNavigationBars(true);
        }

        // Configuração dos cliques
        view.findViewById(R.id.option_expense).setOnClickListener(v -> {
            handleAction("expense");
            openExpenseActivity();
        });

        view.findViewById(R.id.option_income).setOnClickListener(v -> {
            handleAction("income");
            openIncomeActivity();
        });

        view.findViewById(R.id.option_save).setOnClickListener(v -> {
            handleAction("save");
            openEconomicActivity();
        });

        return view;
    }

    private void openExpenseActivity() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ExpenseActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    private void openIncomeActivity() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), IncomeActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    private void openEconomicActivity() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), EconomicActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setDimAmount(0.2f);
        }
    }

    private void handleAction(String action) {
        if (listener != null) {
            listener.onActionSelected(action);
        }
        dismiss();
    }
}