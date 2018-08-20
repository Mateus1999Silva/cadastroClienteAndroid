package com.example.mateusoliveira.cadastrocliente.SqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mateusoliveira.cadastrocliente.Model.Cliente;
import com.example.mateusoliveira.cadastrocliente.Model.Endereco;

public class Sqlite extends SQLiteOpenHelper {

    private Endereco endereco;
    private Cliente cliente;

    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(endereco.createTable());
        db.execSQL(cliente.createDataBase());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
