package com.example.mateusoliveira.cadastrocliente.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.SqLite.Sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private Context context;
    private ClienteModel cliente;
    private StringBuilder stringBuilder;

    public ClienteDao(Context context) {
        this.context = context;
    }

    public ContentValues converterParaContentValues(ClienteModel clienteModel) {
        cliente = new ClienteModel();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cliente.NOME, clienteModel.getNome());
        contentValues.put(cliente.CPF, clienteModel.getCpf());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(clienteModel.getDatanascimento());

        contentValues.put(cliente.DATA_NASCIMENTO, date);
        return contentValues;
    }

    public int createUser(ClienteModel clienteModel) {
        try {
            Sqlite dmHelper = new Sqlite(this.context);
            SQLiteDatabase db = dmHelper.getWritableDatabase();
            db.insert(cliente.TABLE_NAME_CLIENTE, null, converterParaContentValues(clienteModel));
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
        stringBuilder.append("SELECT * FROM ").append(cliente.TABLE_NAME_CLIENTE);
        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);

        if (cursor.moveToFirst()) {
            do {
                Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(cliente.ID)));
                String nome = cursor.getString(cursor.getColumnIndex(cliente.NOME));
                String cpf = cursor.getString(cursor.getColumnIndex(cliente.CPF));

                cliente = new ClienteModel();
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCpf(cpf);

                clientes.add(cliente);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return clientes;
    }

    public ClienteModel readCliente(int id){
        Sqlite dmHelper = new Sqlite(this.context);
        SQLiteDatabase db = dmHelper.getWritableDatabase();
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM clientes WHERE id = ").append(id);

        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);
        if(cursor.moveToFirst()){
            String nome = cursor.getString(cursor.getColumnIndex(ClienteModel.NOME));
            String cpf = cursor.getString(cursor.getColumnIndex(ClienteModel.CPF));
            String datanascimento = cursor.getString(cursor.getColumnIndex(ClienteModel.DATA_NASCIMENTO));

            cliente = new ClienteModel();
            cliente.setId(id);
            cliente.setNome(nome);
            cliente.setCpf(cpf);

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                cliente.setDatanascimento(sdf.parse(datanascimento));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }
}

