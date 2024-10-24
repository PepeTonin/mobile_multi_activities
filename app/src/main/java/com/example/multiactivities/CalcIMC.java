package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcIMC extends AppCompatActivity {

    private EditText inputNome, inputAltura, inputPeso;
    private Button btnVoltar, btnCalcular;
    private TextView textResultImc;

    private String nome, resultImc;
    private Float altura, peso, imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        inputNome = findViewById(R.id.inputNome);
        inputAltura = findViewById(R.id.inputAltura);
        inputPeso = findViewById(R.id.inputPeso);
        textResultImc = findViewById(R.id.tvResultImc);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringAltura = inputAltura.getText().toString();
                String stringPeso = inputPeso.getText().toString();

                if(stringAltura.equals("") || stringPeso.equals("") || !isConvertibleToFloat(stringAltura) || !isConvertibleToFloat(stringPeso)) {
                    Toast.makeText(getApplicationContext(), "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
                    return;
                }

                nome = inputNome.getText().toString();
                altura = convertStringToFloat(stringAltura);
                peso = convertStringToFloat(stringPeso);
                imc = calcImc(peso, altura);
                resultImc = resultImc(imc);

                textResultImc.setText("Seu IMC é: " + imc.toString() + "\nClassificação: " + resultImc(imc));

                SQLiteDatabase bd = openOrCreateDatabase("app", MODE_PRIVATE, null);
                ContentValues contentValues = new ContentValues();
                contentValues.put("nome", nome);
                contentValues.put("imc", imc);
                contentValues.put("resultado", resultImc);
                bd.insert("pacientes", null, contentValues);
                Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public Float calcImc(Float peso, Float altura) {
        return peso / (altura * altura);
    }

    public String resultImc(Float imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade grau 1";
        } else if (imc < 39.9) {
            return "Obesidade grau 2";
        } else {
            return "Obesidade grau 3";
        }
    }

    public static boolean isConvertibleToFloat(String input) {
        String formattedInput = input.replace(",", ".");
        try {
            Float.parseFloat(formattedInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Float convertStringToFloat(String input) {
        input = input.replace(",", ".");
        return Float.parseFloat(input);
    }
}