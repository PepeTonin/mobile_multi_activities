package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DadosUsuario extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvEmail;
    private TextView tvTelefone;
    private TextView tvCpf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvNome = findViewById(R.id.tvNome);
        tvEmail = findViewById(R.id.tvEmail);
        tvTelefone = findViewById(R.id.tvTelefone);
        tvCpf = findViewById(R.id.tvCpf);

        Bundle dados = getIntent().getExtras();

        Usuario usuario = (Usuario) dados.getSerializable("usuario");

        String cpf = formatCpf(usuario.getCpf());
        String telefone = formatTelefone(usuario.getTelefone());

        tvNome.setText(usuario.getNome());
        tvEmail.setText(usuario.getEmail());
        tvTelefone.setText(telefone);
        tvCpf.setText(cpf);

    }

    private static String formatCpf(String cpf){
        String formatedCpf = "";
        for(int i = 0; i < cpf.length(); i++){
            if(i==2 || i==5){
                formatedCpf = formatedCpf + cpf.charAt(i) + ".";
            } else if(i==8) {
                formatedCpf = formatedCpf + cpf.charAt(i) + "-";
            } else {
                formatedCpf = formatedCpf + cpf.charAt(i);
            }
        }
        return formatedCpf;
    }

    private static String formatTelefone(String telefone){
        String formatedTelefone = "";
        for(int i = 0; i < telefone.length(); i++){
            if(i==0){
                formatedTelefone += "(" + telefone.charAt(i);
            } else if(i==1){
                formatedTelefone += telefone.charAt(i) + ") ";
            } else if(i==6){
                formatedTelefone += telefone.charAt(i) + "-";
            } else{
                formatedTelefone += telefone.charAt(i);
            }
        }
        return formatedTelefone;
    }
}