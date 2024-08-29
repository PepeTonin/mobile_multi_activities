package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private Button btnVoltar;
    private TextView tvNome;
    private TextView tvIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnVoltar = findViewById(R.id.btnVoltar);

        tvNome = findViewById(R.id.tvNome);
        tvIdade = findViewById(R.id.tvIdade);

        Bundle dados = getIntent().getExtras();

        Usuario usuario = (Usuario) dados.getSerializable("objeto");
        tvNome.setText(usuario.getNome());
        tvIdade.setText(usuario.getEmail());
        /*
        String nome = dados.getString("nome");
        int idade = dados.getInt("idade");
        tvNome.setText(nome);
        tvIdade.setText(String.valueOf(idade));
        */

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            }
        });
    }
}