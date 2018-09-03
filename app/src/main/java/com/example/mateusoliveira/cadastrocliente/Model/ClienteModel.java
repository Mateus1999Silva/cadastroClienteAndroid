package com.example.mateusoliveira.cadastrocliente.Model;

import java.io.Serializable;
import java.util.Date;

public class ClienteModel implements Serializable {
    private long id;
    private String nome;
    private String cpf;
    private Date datanascimento;
    private EnderecoModel enderecoCliente;
    private StringBuilder builder = new StringBuilder();

    public static final String TABLE_NAME_CLIENTE = "clientes";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String DATA_NASCIMENTO = "dataNascimento";

    public String createTable() {
        builder.append(" CREATE TABLE ").append(TABLE_NAME_CLIENTE).append("(").append(
                ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,").append(
                NOME).append(" TEXT NOT NULL, ").append(
                CPF).append(" TEXT NOT NULL,").append(
                DATA_NASCIMENTO).append(" TEXT NOT NULL);");

        return builder.toString();
    }

    public ClienteModel() {

    }

    public ClienteModel(String nome, String cpf, Date datanascimento, int id) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.datanascimento = datanascimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public EnderecoModel getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(EnderecoModel enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }
}
