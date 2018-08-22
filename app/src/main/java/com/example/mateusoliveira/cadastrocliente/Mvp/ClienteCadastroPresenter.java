package com.example.mateusoliveira.cadastrocliente.Mvp;

import android.util.Log;

import com.example.mateusoliveira.cadastrocliente.Dao.ClienteDao;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteCadastroPresenter implements ClienteCadastroContrato.clienteCadastroPresenter {
    private ClienteCadastroContrato.clienteCadastroView view;
    private ClienteDao clienteDao;

    @Override
    public void setView(ClienteCadastroView view) {
        this.view = view;
    }

    @Override
    public void insert() {
        try {
            clienteDao = new ClienteDao(this.view.getContext());
            clienteDao.createUser(createClienteModel());
        } catch (Exception e) {
            Log.e("erro1", "erro1");
        }
        view.openList();
    }

    private ClienteModel createClienteModel() {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome(this.view.getNome().getText().toString());
        cliente.setCpf(this.view.getCpf().getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date data = null;
        try {
            data = sdf.parse("23-11-1999");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cliente.setDatanascimento(data);
        return cliente;
    }
}
