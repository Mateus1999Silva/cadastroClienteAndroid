package com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

import java.util.List;

public interface ListClienteContrato {

    interface ListClientePresenter{
        boolean deleteClienteRecyrcleView(int idCliente, int idEndereco);
        void editarClienteRecyclerView(ClienteModel clienteModel);
        void setView(ListClienteView view);
        List<ClienteModel> readCliente();
    }

    interface ListClienteView{
        boolean deleteCliente(int idCliente, int idEndereco);
        void editarCliente(ClienteModel clienteModel);
        Context getContext();
        void recyrcleView();
    }
}
