package com.example.mateusoliveira.cadastrocliente.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.SqLite.Sqlite;

import java.text.SimpleDateFormat;

public class ClienteDao {

    private Context context;
    private ClienteModel cliente;


    public ClienteDao(Context context){
        this.context = context;
    }

    public ContentValues converterParaContentValues(ClienteModel clienteModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(cliente.NOME, clienteModel.getNome() );
        contentValues.put(cliente.CPF, clienteModel.getCpf());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(cliente.getDatanascimento());
        contentValues.put(cliente.DATA_NASCIMENTO, date);

        return contentValues;
    }

   public void createUser(ClienteModel clienteModel){
       Sqlite dmHelper = new Sqlite(this.context);
       SQLiteDatabase db = dmHelper.getWritableDatabase();
       db.insert(cliente.TABLE_NAME_CLIENTE,null, converterParaContentValues(clienteModel));

   }
}

