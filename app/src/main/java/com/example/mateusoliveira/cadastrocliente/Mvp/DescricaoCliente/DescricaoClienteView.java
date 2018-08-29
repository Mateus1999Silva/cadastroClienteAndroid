package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescricaoClienteView extends AppCompatActivity implements DescricaoClienteContrato.DescricaoClienteView{

    @BindView(R.id.editNomeCompleto)
    EditText txtNome;

    @BindView(R.id.editCpf)
    EditText txtCpf;

    @BindView(R.id.textDataNascimento)
    TextView txtDataNascimento;

    @BindView(R.id.editCep)
    EditText txtCep;

    @BindView(R.id.editBairro)
    EditText txtBairro;

    @BindView(R.id.editNumero)
    EditText txtNumero;

    @BindView(R.id.editLogradouro)
    EditText txtLogradouro;

    @BindView(R.id.editEstado)
    EditText txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_cliente);
        ButterKnife.bind(this);
        preencherDados();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void preencherDados() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        EnderecoModel enderecoModel =(EnderecoModel) bundle.getSerializable("endereco");
        ClienteModel clienteModel = (ClienteModel) bundle.getSerializable("cliente");

        txtNome.setText(clienteModel.getNome());
        txtCpf.setText(clienteModel.getCpf());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        String data = sdf.format(clienteModel.getDatanascimento());
        txtDataNascimento.setText(data);

        txtCep.setText(enderecoModel.getCep());
        txtBairro.setText(enderecoModel.getBairro());
        txtNumero.setText(enderecoModel.getNumero());
        txtLogradouro.setText(enderecoModel.getLogradouro());
        txtEstado.setText(enderecoModel.getEstado());
    }
}
