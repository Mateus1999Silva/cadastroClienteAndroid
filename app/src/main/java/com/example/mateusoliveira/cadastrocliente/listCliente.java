package com.example.mateusoliveira.cadastrocliente;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class listCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Clientes");

    }
}
