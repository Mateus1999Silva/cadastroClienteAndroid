package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.R;

import butterknife.BindView;

public class DescricaoClienteView extends AppCompatActivity{

    @BindView(R.id.editNomeCompleto)
    EditText txtNome;

    @BindView(R.id.editCpf)
    EditText txtCpf;

    @BindView(R.id.textDataNascimento)
    EditText txtDataNascimento;

    @BindView(R.id.editCep)
    EditText txtCep;

    @BindView(R.id.editBairro)
    EditText txtBairro;

    @BindView(R.id.textNumero)
    EditText txtNumero;

    @BindView(R.id.textLogradouro)
    EditText txtLogradouro;

    @BindView(R.id.textEstado)
    EditText txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_cliente);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        EnderecoModel enderecoModel =(EnderecoModel) bundle.getSerializable("endereco");
        Log.i("OBJECT",    enderecoModel.getBairro());
    }
}
