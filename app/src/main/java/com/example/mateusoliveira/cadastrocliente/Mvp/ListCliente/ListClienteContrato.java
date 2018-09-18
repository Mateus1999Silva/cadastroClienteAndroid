package com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

import java.util.List;

public interface ListClienteContrato {

    interface ListClientePresenter{
        boolean deleteClienteRecyrcleView(int idCliente, int idEndereco);
        void editarClienteRecyclerView(ClienteModel clienteModel);
        void setView(ListClienteView view);
        List<ClienteModel> readCliente();
        void dataRecyclerView();
    }

    interface ListClienteView{
        Context getContext();
        void populationRecyclerView();
        RecyclerView getRecyclerView();
    }
}
