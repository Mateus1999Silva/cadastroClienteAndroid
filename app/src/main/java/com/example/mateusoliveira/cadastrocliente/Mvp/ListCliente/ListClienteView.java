package com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente.ClienteCadastroView;
import com.example.mateusoliveira.cadastrocliente.R;
import com.example.mateusoliveira.cadastrocliente.RecyrcleView.Adapter.RecyrcleViewAdapter;
import com.example.mateusoliveira.cadastrocliente.utils.SwipeRecyrcleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListClienteView extends AppCompatActivity implements ListClienteContrato.ListClienteView {

    @BindView(R.id.recycleView)
    RecyclerView recyrcle;

    private List<ClienteModel> cliente;
    private RecyrcleViewAdapter recyrcleViewAdapter;
    private ListClientePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cliente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Clientes");
        ButterKnife.bind(this);
        presenter = new ListClientePresenter();
        presenter.setView(ListClienteView.this);
        recyrcleView();

    }

    @OnClick(R.id.floatingButton)
    public void activityInsert() {
        Intent intent = new Intent(ListClienteView.this, ClienteCadastroView.class);
        startActivity(intent);
    }

    @Override
    public boolean deleteCliente(int idCliente, int idEndereco) {
        if(presenter.deleteClienteRecyrcleView(idCliente,idEndereco)){
            return true;
        }
        return false;
    }

    @Override
    public void editarCliente(ClienteModel clienteModel) {
        presenter.editarClienteRecyclerView(clienteModel);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void recyrcleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyrcle.setLayoutManager(linearLayoutManager);
        recyrcleViewAdapter = new RecyrcleViewAdapter(presenter.readCliente(), this, this);
        recyrcle.setAdapter(recyrcleViewAdapter);
    }
}
