package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar;
    private RecyclerView recyclerView;
    private List<Paciente> listaPacientes = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase bd = openOrCreateDatabase("app", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS pacientes(nome VARCHAR, imc FLOAT, resultado VARCHAR)");

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        setListaPacientes();
        adapter = new Adapter(listaPacientes);
        recyclerView.setAdapter(adapter);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalcIMC.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListaPacientes();
        adapter.notifyDataSetChanged();
    }

    public void setListaPacientes() {
        listaPacientes.clear();
        SQLiteDatabase bd = openOrCreateDatabase("app", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS pacientes(nome VARCHAR, imc FLOAT, resultado VARCHAR)");

        Cursor cursor = bd.rawQuery("SELECT nome, imc, resultado FROM pacientes", null);

        int indiceNome = cursor.getColumnIndex("nome");
        int indiceImc = cursor.getColumnIndex("imc");
        int indiceResultado = cursor.getColumnIndex("resultado");

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(indiceNome);
                Float imc = cursor.getFloat(indiceImc);
                String resultado = cursor.getString(indiceResultado);
                Log.w("nome", nome);
                Log.w("imc", imc.toString());
                Log.w("resultado", resultado);

                Paciente paciente = new Paciente(nome, imc, resultado);
                listaPacientes.add(paciente);
            } while (cursor.moveToNext());  // Move para o próximo item até que não haja mais
        }
        cursor.close();
    }
}