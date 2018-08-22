package com.example.mateusoliveira.cadastrocliente.Model;

import java.util.Date;

public class ClienteModel {
    private String nome;
    private String cpf;
    private Date datanascimento;
    private StringBuilder builder;

    public static final String TABLE_NAME_CLIENTE = "clientes";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String DATA_NASCIMENTO = "dataNascimento";

    public String createTable() {
      builder.append("create database ").append(TABLE_NAME_CLIENTE).append("(").append(
                ID ).append("INTEGER primary key,").append(
                NOME).append("TEXT NOT NULL, ").append(
                CPF ).append("TEXT NOT NULL,").append(
                DATA_NASCIMENTO).append("TEXT NOT NULL,");

      return builder.toString();
    }

    public ClienteModel(){

    }

    public ClienteModel(String nome, String cpf, Date datanascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.datanascimento = datanascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }
}
