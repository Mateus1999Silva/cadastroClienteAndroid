package com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.mateusoliveira.cadastrocliente.Dao.ClienteDao;
import com.example.mateusoliveira.cadastrocliente.Dao.EnderecoDao;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente.DescricaoClienteView;
import com.example.mateusoliveira.cadastrocliente.RecyrcleView.Adapter.RecyrcleViewAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListClientePresenter implements ListClienteContrato.ListClientePresenter {

    private ListClienteContrato.ListClienteView view;
    private List<ClienteModel> listCliente;
    private RecyrcleViewAdapter recyrcleViewAdapter;

    @Override
    public void setView(ListClienteContrato.ListClienteView view) {
        this.view = view;
    }

    @Override
    public void dataRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        view.getRecyclerView().setLayoutManager(linearLayoutManager);
        recyrcleViewAdapter = new RecyrcleViewAdapter(readCliente(), view.getContext(), this);
        view.getRecyclerView().setAdapter(recyrcleViewAdapter);
    }

    @Override
    public boolean deleteClienteRecyrcleView(int idCliente, int idEndereco) {
        ClienteDao clienteDao = new ClienteDao(view.getContext());
        boolean delete = clienteDao.delete(idCliente);
        if (delete){
            EnderecoDao enderecoDao = new EnderecoDao(view.getContext());
            enderecoDao.delete(idEndereco);
            return true;
        }
        return false;
    }

    @Override
    public void editarClienteRecyclerView(ClienteModel clienteModel) {
        Intent intent = new Intent(view.getContext(), DescricaoClienteView.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", (Serializable) clienteModel);
        bundle.putSerializable("endereco", (Serializable) clienteModel.getEnderecoCliente());
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }

    @Override
    public List<ClienteModel> readCliente() {
        ClienteDao clienteDao = new ClienteDao(view.getContext());
        listCliente = new ArrayList();
        listCliente = clienteDao.readClientes();
        return listCliente;
    }
}
