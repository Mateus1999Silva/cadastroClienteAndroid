package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

public interface DescricaoClienteContrato {

    interface DescricaoClientePresenter{
        void setView(DescricaoClienteView view);
    }

    interface DescricaoClienteView{
        Context getContext();
        void preencherDados();
    }
}
