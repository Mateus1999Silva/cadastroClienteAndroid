package com.example.mateusoliveira.cadastrocliente.Model;

import com.example.mateusoliveira.cadastrocliente.Dao.DaoEndereco;

import java.util.Date;

public class Cliente {
    public static final String DATABASE_NAME_CLIENTE = "clientes";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String DATA_NASCIMENTO = "dataNascimento";

    private Endereco endereco;
    private String nome;
    private String cpf;
    private Date datanascimento;

    private StringBuilder builder;


    public String createDataBase() {
      builder.append("create database ").append(DATABASE_NAME_CLIENTE).append("(").append(
                ID ).append("INTEGER primary key,").append(
                NOME).append("TEXT NOT NULL, ").append(
                CPF ).append("TEXT NOT NULL,").append(
                DATA_NASCIMENTO).append("TEXT NOT NULL,").append(
                "FOREIGN KEY(endereco_id) REFERENCES").append(endereco.DATABASE_NAME_ENDERECO).append("(").append(endereco.ID).append( "),");

      return builder.toString();
    }

    public Cliente(String nome, String cpf, Date datanascimento, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.datanascimento = datanascimento;
        this.endereco = endereco;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
