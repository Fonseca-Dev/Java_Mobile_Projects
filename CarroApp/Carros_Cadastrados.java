package com.example.aula4carroapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Carros_Cadastrados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carros_cadastrados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_carro, R.id.checkedTextView, Carros.carros);
        ListView listView = findViewById(R.id.Carros_ListView_Carros_Cadastrados);
        listView.setAdapter(adapter);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);

    }

    public void Voltar(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void remover(View v){
        ListView listView = findViewById(R.id.Carros_ListView_Carros_Cadastrados);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        List<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++){
            int position = checked.keyAt(i);
            if (checked.valueAt(i)) {
                selectedItems.add(adapter.getItem(position));
            }
        }

        //Limpar a seleção
        for (int i = 0; i < adapter.getCount(); i++) {
            listView.setItemChecked(i, false);
        }

        for (String item : selectedItems) {
            adapter.remove(item);
            Log.i("Selecionados", "Removido a cor " + item);
        }


    }
}