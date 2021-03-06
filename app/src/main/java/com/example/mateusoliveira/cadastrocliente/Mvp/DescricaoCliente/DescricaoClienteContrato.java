package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Context;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;

import java.util.List;

public interface DescricaoClienteContrato {

    interface DescricaoClientePresenter{
        void setView(DescricaoClienteView view);

        void getInformacoesMapa();

        void editarCliente(long idCliente, long idEndereco);

        void preencherDados(ClienteModel clienteModel , EnderecoModel enderecoModel);

        void cep();

        boolean validacaoCampos();

        void validacaoBuscaCep(boolean hasFocus);
    }

    interface DescricaoClienteView{
        void preencherDados();

        Context getContext();

        EditText getNome();

        EditText getCpf();

        TextView getDataNascimento();

        EditText getCep();

        EditText getNumero();

        EditText getBairro();

        EditText getEstado();

        EditText getLogradouro();

        EditText getComplemento();

        TextView getTextViewError();

        void setNome(String nome);

        void setCpf(String cpf);

        void setDataNascimento(String dataNascimento);

        void setCep(String cep);

        void setNumero(String numero);

        void setComplemento(String complemento);

        void setBairro(String bairro);

        void setEstado(String estado);

        void setLogradrouro(String logradouro);

        void editarCliente();

        ProgressBar progress();
    }
}
