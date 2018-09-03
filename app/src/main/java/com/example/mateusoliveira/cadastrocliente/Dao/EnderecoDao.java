package com.example.mateusoliveira.cadastrocliente.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.SqLite.Sqlite;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {

    private Context context;
    private EnderecoModel enderecoModel;
    private List<EnderecoModel> enderecoList;

    public EnderecoDao(Context context) {
        this.context = context;
    }

    public ContentValues converterParaContentValues(EnderecoModel enderecoModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(enderecoModel.CEP, enderecoModel.getCep());
        contentValues.put(enderecoModel.BAIRRO, enderecoModel.getBairro());
        contentValues.put(enderecoModel.LOGRADOURO, enderecoModel.getLogradouro());
        contentValues.put(enderecoModel.ESTADO, enderecoModel.getEstado());
        contentValues.put(enderecoModel.NUMERO, enderecoModel.getNumero());
        contentValues.put(enderecoModel.ID_CLIENTE, enderecoModel.getCliente());
        return contentValues;
    }

    public void createAddres(EnderecoModel enderecoModel) {
        Sqlite dmHelper = new Sqlite(this.context);
        SQLiteDatabase db = dmHelper.getWritableDatabase();
        db.insert(enderecoModel.TABLE_NAME_ENDERECO, null, converterParaContentValues(enderecoModel));
    }

    public EnderecoModel readAddres(int id) {
        Sqlite dmHelper = new Sqlite(this.context);
        SQLiteDatabase db = dmHelper.getWritableDatabase();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM endereco WHERE id = ").append(id);
        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);

        if (cursor.moveToFirst()) {
            do {
                String cep = cursor.getString(cursor.getColumnIndex(EnderecoModel.CEP));
                String bairro = cursor.getString(cursor.getColumnIndex(EnderecoModel.BAIRRO));
                String numero = cursor.getString(cursor.getColumnIndex(EnderecoModel.NUMERO));
                String logradouro = cursor.getString(cursor.getColumnIndex(EnderecoModel.LOGRADOURO));
                String estado = cursor.getString(cursor.getColumnIndex(EnderecoModel.ESTADO));
                String cliente = cursor.getString(cursor.getColumnIndex(EnderecoModel.ID_CLIENTE));

                enderecoModel = new EnderecoModel();
                enderecoModel.setCep(cep);
                enderecoModel.setBairro(bairro);
                enderecoModel.setNumero(numero);
                enderecoModel.setLogradouro(logradouro);
                enderecoModel.setEstado(estado);
                enderecoModel.setCliente(Integer.parseInt(cliente));


            } while (cursor.moveToNext());
        }
        return enderecoModel;
    }

    public List<EnderecoModel> readEndereco() {
        enderecoList = new ArrayList();
        Sqlite dmHelper = new Sqlite(this.context);
        SQLiteDatabase db = dmHelper.getWritableDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM endereco");

        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);
        if (cursor.moveToFirst()) {
            do {
                String cep = cursor.getString(cursor.getColumnIndex(EnderecoModel.CEP));
                String bairro = cursor.getString(cursor.getColumnIndex(EnderecoModel.BAIRRO));
                String numero = cursor.getString(cursor.getColumnIndex(EnderecoModel.NUMERO));
                String logradouro = cursor.getString(cursor.getColumnIndex(EnderecoModel.LOGRADOURO));
                String estado = cursor.getString(cursor.getColumnIndex(EnderecoModel.ESTADO));
                long cliente = Long.parseLong(cursor.getString(cursor.getColumnIndex(EnderecoModel.ID_CLIENTE)));

                enderecoModel = new EnderecoModel();
                enderecoModel.setCep(cep);
                enderecoModel.setBairro(bairro);
                enderecoModel.setNumero(numero);
                enderecoModel.setLogradouro(logradouro);
                enderecoModel.setEstado(estado);
                enderecoModel.setCliente(cliente);

                enderecoList.add(enderecoModel);

            } while (cursor.moveToNext());
        }
        return enderecoList;
    }

    public boolean update(EnderecoModel endereco){
        Sqlite dmHelper = new Sqlite(this.context);
        SQLiteDatabase db = dmHelper.getWritableDatabase();
        db.update(EnderecoModel.TABLE_NAME_ENDERECO, converterParaContentValues(endereco),"ID = ?",new String[] { String.valueOf(endereco.getId())});
        return true;
    }
}
