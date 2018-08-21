package com.example.mateusoliveira.cadastrocliente.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.SqLite.Sqlite;

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
        //contentValues.put(clienteModel.DATA_NASCIMENTO, clienteModel.getDatanascimento().toString());
       return contentValues;
    }

   public void createUser(ClienteModel clienteModel){
       Sqlite dmHelper = new Sqlite(this.context);
       SQLiteDatabase db = dmHelper.getWritableDatabase();
       db.insert(cliente.TABLE_NAME_CLIENTE,null, converterParaContentValues(clienteModel));

   }
}

