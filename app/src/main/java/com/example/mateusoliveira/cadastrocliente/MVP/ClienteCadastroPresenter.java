package com.example.mateusoliveira.cadastrocliente.MVP;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

public class ClienteCadastroPresenter implements ClienteCadastroContrato.clienteCadastroPresenter{

    private ClienteCadastroContrato.clienteCadastroView view;

    @Override
    public void setView(ClienteCadastroView view) {
        this.view = view;
    }

    @Override
    public void insert(ClienteModel clienteModel) {

        view.openList();
    }
}
