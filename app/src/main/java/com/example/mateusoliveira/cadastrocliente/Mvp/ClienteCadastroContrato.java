package com.example.mateusoliveira.cadastrocliente.Mvp;

import android.content.Context;
import android.widget.EditText;

public interface ClienteCadastroContrato {
    interface clienteCadastroView{
        void openList();
        Context getContext();

        EditText getNome();
        EditText getCpf();

    }
    interface clienteCadastroPresenter{
        void setView(ClienteCadastroView view);
        void insert();


    }
}
