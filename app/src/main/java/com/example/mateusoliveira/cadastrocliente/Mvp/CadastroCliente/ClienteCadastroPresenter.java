package com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.ApiEndereco.ApiRequest;
import com.example.mateusoliveira.cadastrocliente.Dao.ClienteDao;
import com.example.mateusoliveira.cadastrocliente.Dao.EnderecoDao;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente.ListClienteView;
import com.example.mateusoliveira.cadastrocliente.interfaceResult.SyncResult;
import com.example.mateusoliveira.cadastrocliente.utils.ClienteValidationsUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClienteCadastroPresenter implements ClienteCadastroContrato.clienteCadastroPresenter, SyncResult {
    private ClienteCadastroContrato.clienteCadastroView view;
    private ClienteDao clienteDao;
    private EnderecoDao enderecoDao;
    private Date data = null;
    private ApiRequest apiRequest = new ApiRequest(this);

    @Override
    public void setView(ClienteCadastroView view) {
        this.view = view;
    }

    @Override
    public boolean validationInternetCep() {
        return ClienteValidationsUtils.connectionInternet(view.getContext(), view.camposCep());
    }

    @Override
    public boolean validationsEdits() {
        return (ClienteValidationsUtils.EditEmpty(view.getNome()) &&
                ClienteValidationsUtils.EditEmpty(view.getCpf()) &&
                ClienteValidationsUtils.validateCPF(view.getCpf()) &&
                ClienteValidationsUtils.dates(view.getDataNascimento(), view.getTextViewError()) &&
                ClienteValidationsUtils.EditEmpty(view.getCep()) &&
                ClienteValidationsUtils.EditEmpty(view.getBairro()) &&
                ClienteValidationsUtils.EditEmpty(view.getEstado()) &&
                ClienteValidationsUtils.EditEmpty(view.getLogradrouro()) &&
                ClienteValidationsUtils.EditEmpty(view.getNumero()));
    }

    @Override
    public void insert() {
        try {
            clienteDao = new ClienteDao(this.view.getContext());
            long cliente = clienteDao.createUser(createClienteModel());

            enderecoDao = new EnderecoDao(this.view.getContext());
            enderecoDao.createAddres(createEnderecoModel(cliente));

            Toast.makeText(view.getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.view.getContext(), ListClienteView.class);
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            data = sdf.parse(this.view.getDataNascimento().getText().toString());
            cliente.setDatanascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    private EnderecoModel createEnderecoModel(long clienteId) {
        EnderecoModel endereco = new EnderecoModel();
        endereco.setCep(this.view.getCep().getText().toString());
        endereco.setBairro(this.view.getBairro().getText().toString());
        endereco.setLogradouro(this.view.getLogradrouro().getText().toString());
        endereco.setNumero(this.view.getNumero().getText().toString());
        endereco.setEstado(this.view.getEstado().getText().toString());
        endereco.setComplemento(view.getComplemento().getText().toString());
        endereco.setCliente(clienteId);

        return endereco;
    }

    @Override
    public List<ClienteModel> readClientes() {
        clienteDao = new ClienteDao(view.getContext());
        List<ClienteModel> clientes = clienteDao.readClientes();
        return clientes;
    }

    @Override
    public ClienteModel readCliente(int id) {
        clienteDao = new ClienteDao(view.getContext());
        ClienteModel cliente = clienteDao.readClienteId(id);
        return cliente;
    }

    @Override
    public void apiCep() {
        apiRequest.getEndereco(view.getCep().getText().toString());
    }

    @Override
    public void onSucess() {
        EnderecoModel enderecoModel = apiRequest.getEnderecoModel();
        view.setBairro(enderecoModel.getBairro());
        view.setEstado(enderecoModel.getEstado());
        view.setLogradrouro(enderecoModel.getLogradouro());
        view.progress().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure() {

    }
}
