package com.example.mateusoliveira.cadastrocliente.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.R;

public class ClienteCadastroView extends AppCompatActivity implements ClienteCadastroContrato.clienteCadastroView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_cliente);
    }

    @Override
    public void openList() {
      // Intent intent = new Intent(this,  )
        Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
    }
}
