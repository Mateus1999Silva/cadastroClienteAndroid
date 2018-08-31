package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

public interface DescricaoClienteContrato {

    interface DescricaoClientePresenter{
        void setView(DescricaoClienteView view);
        void getInformacoesMapa();
        void editarCliente(long idCliente, long idEndereco);
    }

    interface DescricaoClienteView{
        void preencherDados();
        Context getContext();
        EditText getNome();
        EditText getCpf();
        TextView getDataNascimento();
        EditText getCep();
        EditText getBairro();
        EditText getNumero();
        EditText getEstado();
        EditText getLogradouro();
        void editarCliente();
    }
}
