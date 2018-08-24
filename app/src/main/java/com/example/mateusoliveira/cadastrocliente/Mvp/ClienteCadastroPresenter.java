package com.example.mateusoliveira.cadastrocliente.Mvp;

import android.content.Intent;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.Dao.ClienteDao;
import com.example.mateusoliveira.cadastrocliente.Dao.EnderecoDao;
import com.example.mateusoliveira.cadastrocliente.ListCliente;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClienteCadastroPresenter implements ClienteCadastroContrato.clienteCadastroPresenter {
    private ClienteCadastroContrato.clienteCadastroView view;
    private ClienteDao clienteDao;
    private EnderecoDao enderecoDao;
    private Date data = null;

    @Override
    public void setView(ClienteCadastroView view) {
        this.view = view;
    }

    @Override
    public void insert() {
        try {
            clienteDao = new ClienteDao(this.view.getContext());
            clienteDao.createUser(createClienteModel());

            enderecoDao = new EnderecoDao(this.view.getContext());
            enderecoDao.createAddres(createEnderecoModel());

            Toast.makeText(view.getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.view.getContext(), ListCliente.class);
            view.getContext().startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ClienteModel createClienteModel() {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome(this.view.getNome().getText().toString());
        cliente.setCpf(this.view.getCpf().getText().toString());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            data = sdf.parse(this.view.getDataNascimento().getText().toString());
            cliente.setDatanascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    private EnderecoModel createEnderecoModel() {
        EnderecoModel endereco = new EnderecoModel();
        endereco.setCep(this.view.getCep().getText().toString());
        endereco.setBairro(this.view.getBairro().getText().toString());
        endereco.setLogradouro(this.view.getLogradrouro().getText().toString());
        endereco.setNumero(this.view.getNumero().getText().toString());
        endereco.setEstado(this.view.getEstado().getText().toString());
        return endereco;
    }

    @Override
    public List<ClienteModel> readClientes() {
        clienteDao = new ClienteDao(view.getContext());
        List<ClienteModel> clientes = clienteDao.readClientes();

        for (ClienteModel cliente : clientes){
            enderecoDao = new EnderecoDao(this.view.getContext());
            EnderecoModel enderecoModel = enderecoDao.readAddres(cliente.getId());
        }
        return clientes;
    }

    @Override
    public ClienteModel readCliente(int id) {
        clienteDao = new ClienteDao(view.getContext());
        ClienteModel cliente = clienteDao.readCliente(id);
        return cliente;
    }

}
