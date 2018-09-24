package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.ApiEndereco.ApiRequest;
import com.example.mateusoliveira.cadastrocliente.Dao.ClienteDao;
import com.example.mateusoliveira.cadastrocliente.Dao.EnderecoDao;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente.ListClienteView;
import com.example.mateusoliveira.cadastrocliente.Mvp.MapCliente.MapClienteView;
import com.example.mateusoliveira.cadastrocliente.interfaceResult.SyncResult;
import com.example.mateusoliveira.cadastrocliente.utils.ClienteValidationsUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DescricaoClientePresenter implements DescricaoClienteContrato.DescricaoClientePresenter, SyncResult {

    private DescricaoClienteContrato.DescricaoClienteView view;
    private ApiRequest apiRequest;

    @Override
    public void setView(DescricaoClienteContrato.DescricaoClienteView view) {
        this.view = view;
    }

    @Override
    public void getInformacoesMapa() {
        String bairro = view.getBairro().getText().toString();
        String logradouro = view.getLogradouro().getText().toString();
        String estado = view.getEstado().getText().toString();
        String numero = view.getNumero().getText().toString();

        StringBuilder endereco = new StringBuilder();
        endereco.append(logradouro).append(numero).append(bairro).append(estado);

        if (numero != null && bairro != null && logradouro != null && estado != null && ClienteValidationsUtils.connectionInternet(view.getContext())) {
            Intent intent = new Intent(view.getContext(), MapClienteView.class);
            intent.putExtra("endereco", endereco.toString());
            view.getContext().startActivity(intent);
        } else {
            Toast.makeText(view.getContext(), "Mapa indisponivel, sem acesso a internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void preencherDados(ClienteModel clienteModel, EnderecoModel enderecoModel) {
        view.setNome(clienteModel.getNome());
        view.setCpf(clienteModel.getCpf());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String data = sdf.format(clienteModel.getDatanascimento());
            view.setDataNascimento(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setCep(enderecoModel.getCep());
        view.setBairro(enderecoModel.getBairro());
        view.setNumero(enderecoModel.getNumero());
        view.setComplemento(enderecoModel.getComplemento());
        view.setLogradrouro(enderecoModel.getLogradouro());
        view.setEstado(enderecoModel.getEstado());
    }

    @Override
    public boolean validacaoCampos() {
        if (!ClienteValidationsUtils.EditEmpty(view.getNome().getText().toString())) {
            view.getNome().setError("Campo inválido, preencha a informação");
            view.getNome().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.EditEmpty(view.getCpf().getText().toString())) {
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

            if (!ClienteValidationsUtils.EditEmpty(view.getDataNascimento().getText().toString())) {
                view.getTextViewError().setError("Selecione uma data");
                view.getTextViewError().requestFocus();
                return false;

            } else if (!ClienteValidationsUtils.dates(sdf.parse(view.getDataNascimento().getText().toString()))) {
                view.getTextViewError().setError("Data inválida, Data maior que a data de hoje");
                view.getTextViewError().requestFocus();
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!ClienteValidationsUtils.EditEmpty(view.getCep().getText().toString())) {
            view.getCep().setError("Campo Vazio, preencha a informação");
            view.getCep().requestFocus();
            return false;
        } else if (!ClienteValidationsUtils.cepIsValid(view.getCep().getText().toString())) {
            view.getCep().setError("Campo Inválido, preencha a informação");
            view.getCep().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.EditEmpty(view.getLogradouro().getText().toString())) {
            view.getLogradouro().setError("Campo inválido, preencha a informação");
            view.getLogradouro().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.EditEmpty(view.getBairro().getText().toString())) {
            view.getBairro().setError("Campo inválido, preencha a informação");
            view.getBairro().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.EditEmpty(view.getNumero().getText().toString())) {
            view.getNumero().setError("Campo inválido, preencha a informação");
            view.getNumero().requestFocus();
            return false;
        }

        if (!ClienteValidationsUtils.EditEmpty(view.getEstado().getText().toString())) {
            view.getEstado().setError("Campo inválido, preencha a informação");
            view.getEstado().requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void cep() {
        apiRequest = new ApiRequest(this);
        apiRequest.getEndereco(view.getCep().getText().toString());
    }

    @Override
    public void validacaoBuscaCep(boolean hasFocus) {
        if (!hasFocus && !ClienteValidationsUtils.cepIsValid(view.getCep().getText().toString())) {
            view.getCep().setError("Cep Inválido");
            view.getCep().clearFocus();

        } else if (!hasFocus && !ClienteValidationsUtils.connectionInternet(view.getContext())) {
            view.getCep().setError("Sem acesso a internet, preencha a informação");
            view.getCep().clearFocus();

        } else if (!hasFocus) {
            view.progress().setVisibility(View.VISIBLE);
            cep();
        }
    }

    @Override
    public void onSucess() {
        EnderecoModel enderecoModel = apiRequest.getEnderecoModel();
        view.setLogradrouro(enderecoModel.getLogradouro());
        view.setEstado(enderecoModel.getEstado());
        view.setBairro(enderecoModel.getBairro());
        view.progress().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void editarCliente(long idCliente, long idEndereco) {

        if (validacaoCampos()) {
            try {
                ClienteDao clienteDao = new ClienteDao(view.getContext());
                clienteDao.update(createClienteModel(idCliente, idEndereco));

                EnderecoDao enderecoDao = new EnderecoDao(view.getContext());
                enderecoDao.update(createEnderecoModel(idCliente, idEndereco));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(view.getContext(), ListClienteView.class);
            view.getContext().startActivity(intent);
        }

    }

    public ClienteModel createClienteModel(long idCliente, long idEndereco) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(idCliente);
        clienteModel.setCpf(view.getCpf().getText().toString());
        clienteModel.setNome(view.getNome().getText().toString());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            clienteModel.setDatanascimento(sdf.parse(view.getDataNascimento().getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EnderecoDao enderecoDao = new EnderecoDao(view.getContext());
        EnderecoModel enderecoModel = enderecoDao.readAddres((int) idEndereco);
        clienteModel.setEnderecoCliente(enderecoModel);
        return clienteModel;
    }

    public EnderecoModel createEnderecoModel(long idCliente, long idEndereco) {
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setBairro(view.getBairro().getText().toString());
        enderecoModel.setNumero(view.getNumero().getText().toString());
        enderecoModel.setLogradouro(view.getLogradouro().getText().toString());
        enderecoModel.setEstado(view.getEstado().getText().toString());
        enderecoModel.setComplemento(view.getComplemento().getText().toString());
        enderecoModel.setCep(view.getCep().getText().toString());
        enderecoModel.setId((int) idEndereco);
        enderecoModel.setCliente(idCliente);
        return enderecoModel;
    }
}
