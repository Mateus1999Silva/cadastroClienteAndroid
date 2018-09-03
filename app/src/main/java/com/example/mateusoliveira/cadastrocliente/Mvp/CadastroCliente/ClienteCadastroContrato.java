package com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente;

import android.content.Context;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;

import java.util.List;

public interface ClienteCadastroContrato {
    interface clienteCadastroView{
        Context getContext();
        EditText getNome();
        EditText getCpf();
        TextView getDataNascimento();
        EditText getCep();
        EditText getBairro();
        EditText getNumero();
        EditText getEstado();
        EditText getLogradrouro();
        void setBairro(String bairro);
        void setEstado(String estado);
        void setLogradrouro(String logradouro);
        ProgressBar progress();
        void cep();



    }
    interface clienteCadastroPresenter{
        void setView(ClienteCadastroView view);
        void insert();
        List<ClienteModel> readClientes();
        ClienteModel readCliente(int id);
       //List<EnderecoModel> coutn();
        void apiCep();
    }
}
