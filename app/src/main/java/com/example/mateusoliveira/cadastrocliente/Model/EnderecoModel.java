package com.example.mateusoliveira.cadastrocliente.Model;

import java.io.Serializable;
import java.util.List;

public class EnderecoModel implements Serializable{

    private int id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;
    private String estado;
    private long cliente;
    private StringBuilder builder = new StringBuilder();

    public static final String TABLE_NAME_ENDERECO = "endereco";
    public static final String ID = "id";
    public static final String CEP = "cep";
    public static final String LOGRADOURO = "logradouro";
    public static final String BAIRRO = "bairro";
    public static final String ESTADO = "estado";
    public static final String NUMERO = "numero";
    public static final String ID_CLIENTE = "id_cliente";

    public String createTable() {
        builder.append("CREATE TABLE ").append(TABLE_NAME_ENDERECO).append("(").append(
                ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,").append(
                CEP).append(" TEXT NOT NULL,").append(
                LOGRADOURO).append(" TEXT NOT NULL,").append(
                BAIRRO).append(" TEXT NOT NULL ,").append(
                NUMERO).append(" TEXT NOT NULL ,").append(
                ESTADO).append(" TEXT NOT NULL,").append(
                ID_CLIENTE).append(" TEXT NOT NULL,").append(
                "FOREIGN KEY(").append(ID_CLIENTE).append(") REFERENCES ").append(ClienteModel.TABLE_NAME_CLIENTE).append("(").append(ClienteModel.ID).append("));");

        return builder.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getCliente() {
        return cliente;
    }

    public void setCliente(long cliente) {
        this.cliente = cliente;
    }
}
