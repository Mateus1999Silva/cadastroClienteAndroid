package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.content.Intent;

import com.example.mateusoliveira.cadastrocliente.Dao.ClienteDao;
import com.example.mateusoliveira.cadastrocliente.Dao.EnderecoDao;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente.ListClienteView;
import com.example.mateusoliveira.cadastrocliente.Mvp.MapCliente.MapClienteView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DescricaoClientePresenter implements DescricaoClienteContrato.DescricaoClientePresenter {

    private DescricaoClienteContrato.DescricaoClienteView view;

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

        if (numero != null && bairro != null && logradouro != null && estado != null) {
            Intent intent = new Intent(view.getContext(), MapClienteView.class);
            intent.putExtra("endereco", endereco.toString());
            view.getContext().startActivity(intent);
        }
    }

    @Override
    public void preencherDados(ClienteModel clienteModel, EnderecoModel enderecoModel) {
        view.setNome(clienteModel.getNome());
        view.setCpf(clienteModel.getCpf());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        String data = sdf.format(clienteModel.getDatanascimento());
        view.setDataNascimento(data);

        view.setCep(enderecoModel.getCep());
        view.setBairro(enderecoModel.getBairro());
        view.setNumero(enderecoModel.getNumero());
        view.setLogradrouro(enderecoModel.getLogradouro());
        view.setEstado(enderecoModel.getEstado());
    }

    @Override
    public void editarCliente(long idCliente, long idEndereco) {

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

    public ClienteModel createClienteModel(long idCliente, long idEndereco) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(idCliente);
        clienteModel.setCpf(view.getCpf().getText().toString());
        clienteModel.setNome(view.getNome().getText().toString());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
        enderecoModel.setCep(view.getCep().getText().toString());
        enderecoModel.setId((int) idEndereco);
        enderecoModel.setCliente(idCliente);
        return enderecoModel;
    }
}
