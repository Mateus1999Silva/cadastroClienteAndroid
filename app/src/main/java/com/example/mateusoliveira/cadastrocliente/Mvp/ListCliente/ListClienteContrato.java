package com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente;

import android.content.Context;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

import java.util.List;

public interface ListClienteContrato {

    interface ListClientePresenter{
        void setView(ListClienteView view);
        List<ClienteModel> readCliente();
    }

    interface ListClienteView{
        Context getContext();
        void recyrcleView();
    }
}
