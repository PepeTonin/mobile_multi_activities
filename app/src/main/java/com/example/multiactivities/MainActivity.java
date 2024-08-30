package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnEnviar;
    private EditText editNome;
    private EditText editEmail;
    private EditText editTelefone;
    private EditText editCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = findViewById(R.id.btnEnviar);
        editNome = findViewById(R.id.inputNome);
        editEmail = findViewById(R.id.inputEmail);
        editTelefone = findViewById(R.id.inputTelefone);
        editCpf = findViewById(R.id.inputCpf);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DadosUsuario.class);

                String nome = editNome.getText().toString();
                if (nome.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Insira um nome válido", Toast.LENGTH_LONG).show();
                    return;
                }
                String email = editEmail.getText().toString();
                if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Insira um email válido", Toast.LENGTH_LONG).show();
                    return;
                }
                String telefone = editTelefone.getText().toString();
                if (telefone.isEmpty() || telefone.length() != 11){
                    Toast.makeText(getApplicationContext(), "Insira um telefone válido", Toast.LENGTH_LONG).show();
                    return;
                }
                String cpf = editCpf.getText().toString();
                if (cpf.isEmpty() || cpf.length() != 11){
                    Toast.makeText(getApplicationContext(), "Insira um CPF válido", Toast.LENGTH_LONG).show();
                    return;
                }

                Usuario usuario = new Usuario(nome,email,telefone,cpf);

                intent.putExtra("usuario", usuario);

                startActivity(intent);
            }
        });
    }
}