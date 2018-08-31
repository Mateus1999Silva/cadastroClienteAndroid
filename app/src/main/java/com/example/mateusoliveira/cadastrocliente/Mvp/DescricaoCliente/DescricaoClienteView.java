package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.MapCliente.MapClienteView;
import com.example.mateusoliveira.cadastrocliente.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private DescricaoClientePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_cliente);
        ButterKnife.bind(this);
        preencherDados();
        presenter = new DescricaoClientePresenter();
        presenter.setView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemMenuMap) {
            presenter.getInformacoesMapa();
        }
        return true;
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

    @OnClick(R.id.buttonEditar)
    @Override
    public void editarCliente() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        EnderecoModel enderecoModel =(EnderecoModel) bundle.getSerializable("endereco");
        ClienteModel clienteModel = (ClienteModel) bundle.getSerializable("cliente");
        presenter.editarCliente(clienteModel.getId(), enderecoModel.getId());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public EditText getBairro() {
        return txtBairro;
    }

    @Override
    public EditText getNumero() {
        return txtNumero;
    }

    @Override
    public EditText getEstado() {
        return txtEstado;
    }

    @Override
    public EditText getLogradouro() {
        return txtLogradouro;
    }

    @Override
    public EditText getNome() {
        return txtNome;
    }

    @Override
    public EditText getCpf() {
        return txtCpf;
    }

    @Override
    public TextView getDataNascimento() {
        return txtDataNascimento;
    }

    @Override
    public EditText getCep() {
        return txtCep;
    }


}
