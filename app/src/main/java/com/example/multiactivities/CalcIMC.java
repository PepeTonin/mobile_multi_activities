package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcIMC extends AppCompatActivity {

    private EditText inputAltura;
    private EditText inputPeso;
    private Button btnSalvar;
    private TextView textResultImc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        inputAltura = findViewById(R.id.inputAltura);
        inputPeso = findViewById(R.id.inputPeso);
        btnSalvar = findViewById(R.id.btnSalvar);
        textResultImc = findViewById(R.id.tvResultImc);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringAltura = inputAltura.getText().toString();
                String stringPeso = inputPeso.getText().toString();

                if(stringAltura.equals("") || stringPeso.equals("") || !isConvertibleToFloat(stringAltura) || !isConvertibleToFloat(stringPeso)) {
                    Toast.makeText(getApplicationContext(), "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
                    return;
                }

                Float altura = convertStringToFloat(stringAltura);
                Float peso = convertStringToFloat(stringPeso);
                Float imc = calcImc(peso, altura);

                textResultImc.setText("Seu IMC é: " + imc.toString() + "\nClassificação: " + resultImc(imc));
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
            return "Obesidade grau 2 (severa)";
        } else {
            return "Obesidade grau 3 (mórbida)";
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