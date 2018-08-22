package com.example.mateusoliveira.cadastrocliente.SqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;

public class Sqlite extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cliente";

    private EnderecoModel enderecoModel;
    private ClienteModel clienteModel;

    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(enderecoModel.createTable());
        db.execSQL(clienteModel.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + enderecoModel.TABLE_NAME_ENDERECO);
            db.execSQL("DROP TABLE IF EXISTS " + clienteModel.TABLE_NAME_CLIENTE);
            onCreate(db);
        }
    }
}
