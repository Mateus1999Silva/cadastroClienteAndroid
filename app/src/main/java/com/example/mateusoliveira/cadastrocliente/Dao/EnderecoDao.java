package com.example.mateusoliveira.cadastrocliente.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.SqLite.Sqlite;

public class EnderecoDao {

    private Context context;
    private EnderecoModel enderecoModel;

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
        stringBuilder.append("SELECT * FROM endereco WHERE id_cliente = ").append(id);
        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);

        if (cursor.moveToFirst()) {
            String cep = cursor.getString(cursor.getColumnIndex(EnderecoModel.CEP));
            String bairro = cursor.getString(cursor.getColumnIndex(EnderecoModel.BAIRRO));
            String numero = cursor.getString(cursor.getColumnIndex(EnderecoModel.NUMERO));
            String logradouro = cursor.getString(cursor.getColumnIndex(EnderecoModel.LOGRADOURO));
            String estado = cursor.getString(cursor.getColumnIndex(EnderecoModel.ESTADO));

            enderecoModel = new EnderecoModel();
            enderecoModel.setCep(cep);
            enderecoModel.setBairro(bairro);
            enderecoModel.setNumero(numero);
            enderecoModel.setLogradouro(logradouro);
            enderecoModel.setEstado(estado);
        }
        return enderecoModel;
    }
}
