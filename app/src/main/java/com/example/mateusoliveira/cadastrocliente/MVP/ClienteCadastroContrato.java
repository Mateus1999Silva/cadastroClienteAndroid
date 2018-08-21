package com.example.mateusoliveira.cadastrocliente.MVP;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

public interface ClienteCadastroContrato {
    interface clienteCadastroView{
        void openList();
    }
    interface clienteCadastroPresenter{
        void setView(ClienteCadastroView view);
        void insert(ClienteModel clienteModel);
    }
}
