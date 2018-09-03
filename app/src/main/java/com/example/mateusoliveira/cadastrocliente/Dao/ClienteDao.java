package com.example.mateusoliveira.cadastrocliente.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.SqLite.Sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDao {

    private Context context;
    private ClienteModel cliente;
    private StringBuilder stringBuilder;
    private EnderecoModel endereco;

    public ClienteDao(Context context) {
        this.context = context;
    }

    public ContentValues converterParaContentValues(ClienteModel clienteModel) {
        cliente = new ClienteModel();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cliente.NOME, clienteModel.getNome());
        contentValues.put(cliente.CPF, clienteModel.getCpf());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(clienteModel.getDatanascimento());
        contentValues.put(cliente.DATA_NASCIMENTO, date);
        return contentValues;
    }

    public long createUser(ClienteModel clienteModel) {
        try {
            Sqlite dmHelper = new Sqlite(this.context);
            SQLiteDatabase db = dmHelper.getWritableDatabase();
            long id = db.insert(cliente.TABLE_NAME_CLIENTE, null, converterParaContentValues(clienteModel));
            clienteModel.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clienteModel.getId();
    }

    public List<ClienteModel> readClientes() {
        List<ClienteModel> clientes = new ArrayList();
        Sqlite mdHelper = new Sqlite(this.context);
        SQLiteDatabase db = mdHelper.getWritableDatabase();

        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ").append(
                ClienteModel.TABLE_NAME_CLIENTE).append(
                " INNER JOIN ").append(
                EnderecoModel.TABLE_NAME_ENDERECO).append(
                " ON ").append(
                ClienteModel.TABLE_NAME_CLIENTE).append(
                ".").append(
                ClienteModel.ID).append(
                " = ").append(
                EnderecoModel.TABLE_NAME_ENDERECO).append(
                ".").append(
                EnderecoModel.ID_CLIENTE).append(
                ";");

        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);
        if (cursor.moveToFirst()) {
            do {
                Integer idCliente = Integer.parseInt(cursor.getString(cursor.getColumnIndex(cliente.ID)));
                String nomeCliente = cursor.getString(cursor.getColumnIndex(cliente.NOME));
                String cpfCliente = cursor.getString(cursor.getColumnIndex(cliente.CPF));
                String dataNascimentoCliente = cursor.getString(cursor.getColumnIndex(cliente.DATA_NASCIMENTO));

                Integer idEndereco = Integer.parseInt(cursor.getString(cursor.getColumnIndex(endereco.ID)));
                String cep = cursor.getString(cursor.getColumnIndex(endereco.CEP));
                String bairro = cursor.getString(cursor.getColumnIndex(endereco.BAIRRO));
                String numero = cursor.getString(cursor.getColumnIndex(endereco.NUMERO));
                String estado = cursor.getString(cursor.getColumnIndex(endereco.ESTADO));
                String logradouro = cursor.getString(cursor.getColumnIndex(endereco.LOGRADOURO));

                cliente = new ClienteModel();
                cliente.setId(idCliente);
                cliente.setNome(nomeCliente);
                cliente.setCpf(cpfCliente);

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = sdf.parse(dataNascimentoCliente);
                    cliente.setDatanascimento(data);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                endereco = new EnderecoModel();
                endereco.setId(idEndereco);
                endereco.setCep(cep);
                endereco.setEstado(estado);
                endereco.setLogradouro(logradouro);
                endereco.setNumero(numero);
                endereco.setBairro(bairro);
                cliente.setEnderecoCliente(endereco);

                clientes.add(cliente);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return clientes;
    }

    public ClienteModel readClienteId(int id) {
        Sqlite dmHelper = new Sqlite(this.context);
        SQLiteDatabase db = dmHelper.getWritableDatabase();
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM clientes WHERE id = ").append(id);

        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);
        if (cursor.moveToFirst()) {
            do {
                Integer idCliente  = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ClienteModel.ID)));
                String nomeCliente = cursor.getString(cursor.getColumnIndex(ClienteModel.NOME));
                String cpfCliente = cursor.getString(cursor.getColumnIndex(ClienteModel.CPF));
                String datanascimentoCliente = cursor.getString(cursor.getColumnIndex(ClienteModel.DATA_NASCIMENTO));

                Integer idEndereco = Integer.parseInt(cursor.getString(cursor.getColumnIndex(endereco.ID)));
                String cep = cursor.getString(cursor.getColumnIndex(endereco.CEP));
                String bairro = cursor.getString(cursor.getColumnIndex(endereco.BAIRRO));
                String numero = cursor.getString(cursor.getColumnIndex(endereco.NUMERO));
                String estado = cursor.getString(cursor.getColumnIndex(endereco.ESTADO));
                String logradouro = cursor.getString(cursor.getColumnIndex(endereco.LOGRADOURO));

                cliente = new ClienteModel();
                cliente.setId(idCliente);
                cliente.setNome(nomeCliente);
                cliente.setCpf(cpfCliente);

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    cliente.setDatanascimento(sdf.parse(datanascimentoCliente));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                endereco = new EnderecoModel();
                endereco.setId(idEndereco);
                endereco.setCep(cep);
                endereco.setEstado(estado);
                endereco.setLogradouro(logradouro);
                endereco.setNumero(numero);
                endereco.setBairro(bairro);
                cliente.setEnderecoCliente(endereco);
            } while (cursor.moveToNext());
        }
        return cliente;
    }

    public boolean update(ClienteModel cliente){
        Sqlite dmHelper = new Sqlite(this.context);
        SQLiteDatabase db = dmHelper.getWritableDatabase();
        db.update(ClienteModel.TABLE_NAME_CLIENTE, converterParaContentValues(cliente),"ID = ?",new String[] { String.valueOf(cliente.getId())});
        return true;
    }

//    public List<ModelList> readCliente() {
//        List<ModelList> clienteList = new ArrayList();
//        Sqlite dmHelper = new Sqlite(this.context);
//        SQLiteDatabase db = dmHelper.getWritableDatabase();
//        stringBuilder = new StringBuilder();
//        stringBuilder.append("SELECT * FROM clientes");
//
//        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);
//        if (cursor.moveToFirst()) {
//            do {
//                String id = cursor.getString(cursor.getColumnIndex(ClienteModel.ID));
//                String nome = cursor.getString(cursor.getColumnIndex(ClienteModel.NOME));
//                String cpf = cursor.getString(cursor.getColumnIndex(ClienteModel.CPF));
//                String dataNascimento = cursor.getString(cursor.getColumnIndex(ClienteModel.DATA_NASCIMENTO));
//
//                ModelList clienteDados = new ModelList();
//                clienteDados.setId(Long.parseLong(id));
//                clienteDados.setNome(nome);
//                clienteDados.setCpf(cpf);
//
//                clienteList.add(clienteDados);
//            } while (cursor.moveToNext());
//        }
//        return clienteList;
//    }
}


