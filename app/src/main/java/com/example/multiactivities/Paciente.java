package com.example.multiactivities;

public class Paciente {
    private String nome;
    private Float imc;
    private String resultImc;

    public Paciente(String nome, Float imc, String resultImc) {
        this.nome = nome;
        this.imc = imc;
        this.resultImc = resultImc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getImc() {
        return imc;
    }

    public void setImc(Float imc) {
        this.imc = imc;
    }

    public String getResultImc() {
        return resultImc;
    }

    public void setResultImc(String resultImc) {
        this.resultImc = resultImc;
    }
}
