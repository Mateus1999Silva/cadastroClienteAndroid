package com.example.mateusoliveira.cadastrocliente;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mateusoliveira.cadastrocliente.Mvp.ClienteCadastroView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cliente);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Clientes");
    }

    @OnClick(R.id.floatingButton)
    public void activityInsert(){
        Intent intent = new Intent(ListCliente.this, ClienteCadastroView.class);
        startActivity(intent);
    }

}
