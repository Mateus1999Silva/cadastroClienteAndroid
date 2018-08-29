package com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente;

import com.example.mateusoliveira.cadastrocliente.Dao.ClienteDao;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ListClientePresenter implements ListClienteContrato.ListClientePresenter {

    private ListClienteContrato.ListClienteView view;
    private List<ClienteModel> listCliente;

    @Override
    public void setView(ListClienteContrato.ListClienteView view) {
        this.view = view;
    }

    @Override
    public List<ClienteModel> readCliente() {
        ClienteDao clienteDao = new ClienteDao(view.getContext());
        listCliente = new ArrayList();
        listCliente = clienteDao.readClientes();
        return listCliente;
    }
}
