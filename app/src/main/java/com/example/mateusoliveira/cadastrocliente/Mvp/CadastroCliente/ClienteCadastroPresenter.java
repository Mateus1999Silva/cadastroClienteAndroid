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
    public void validacaoBuscaCep(boolean hasFocus) {

        if (!hasFocus && !ClienteValidationsUtils.cepIsValid(view.getCep().getText().toString())) {
            view.getCep().setError("Cep Inválido");
            view.getCep().clearFocus();

        } else if (!hasFocus && !ClienteValidationsUtils.connectionInternet(view.getContext())) {
            view.getCep().setError("Sem acesso a internet, preencha as informações");
            view.getCep().clearFocus();

        } else if (!hasFocus) {
            view.progress().setVisibility(View.VISIBLE);
            apiCep();
        }
    }

    @Override
    public boolean validacaoCampos() {
        if (!ClienteValidationsUtils.editEmpty(view.getNome().getText().toString())) {
            view.getNome().setError("Campo vazio, preencha a informação");
            view.getNome().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.editEmpty(view.getCpf().getText().toString())) {
            view.getCpf().setError("Campo Vazio, preencha a informação");
            view.getCpf().requestFocus();
            return false;

        } else if (!ClienteValidationsUtils.validateCPF(view.getCpf().getText().toString())) {
            view.getCpf().setError("Cpf Inválido, preencha com um cpf válido");
            view.getCpf().requestFocus();
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            if (!ClienteValidationsUtils.editEmpty(view.getDataNascimento().getText().toString())) {
                view.getTextViewError().setError("Campo vazio, Selecione uma data");
                view.getTextViewError().requestFocus();
                view.getTextViewError().setFocusableInTouchMode(true);
                return false;

            } else if (!ClienteValidationsUtils.dates(sdf.parse(view.getDataNascimento().getText().toString()))) {
                view.getTextViewError().setError("Data inválida, Data maior que a data de hoje");
                view.getTextViewError().requestFocus();
                view.getTextViewError().setFocusableInTouchMode(true);
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!ClienteValidationsUtils.editEmpty(view.getCep().getText().toString())) {
            view.getCep().setError("Campo vazio, preencha a informação");
            view.getCep().requestFocus();
            return false;

        } else if (!ClienteValidationsUtils.cepIsValid(view.getCep().getText().toString())) {
            view.getCep().setError("Campo Inválido, preencha a informação");
            view.getCep().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.editEmpty(view.getLogradrouro().getText().toString())) {
            view.getLogradrouro().setError("Campo vazio, preencha a informação");
            view.getLogradrouro().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.editEmpty(view.getBairro().getText().toString())) {
            view.getBairro().setError("Campo vazio, preencha a informação");
            view.getBairro().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.editEmpty(view.getNumero().getText().toString())) {
            view.getNumero().setError("Campo vazio, preencha a informação");
            view.getNumero().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.editEmpty(view.getEstado().getText().toString())) {
            view.getEstado().setError("Campo vazio, preencha a informação");
            view.getEstado().requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void insert() {
        if (validacaoCampos()) {
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
