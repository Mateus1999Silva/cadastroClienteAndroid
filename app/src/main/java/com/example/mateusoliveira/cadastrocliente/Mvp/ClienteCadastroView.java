package com.example.mateusoliveira.cadastrocliente.Mvp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClienteCadastroView extends AppCompatActivity implements ClienteCadastroContrato.clienteCadastroView {

    @BindView(R.id.editNomeCompleto)
    EditText nome;

    @BindView(R.id.editCpf)
    EditText cpf;

    @BindView(R.id.button)
    Button btnDataNascimento;

    @BindView(R.id.textDataNascimento)
    TextView txtDataNascimento;

    @BindView(R.id.editCep)
    EditText cep;

    @BindView(R.id.editEstado)
    EditText estado;

    @BindView(R.id.editBairro)
    EditText bairro;

    @BindView(R.id.editLogradouro)
    EditText logradouro;

    @BindView(R.id.editNumero)
    EditText numero;

    private Calendar calendario;
    private DatePickerDialog datePickerDialog;
    private ClienteCadastroContrato.clienteCadastroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_cliente);
        ButterKnife.bind(this);
        presenter = new ClienteCadastroPresenter();
        presenter.setView(ClienteCadastroView.this);
    }

    public void datePicker() {
        calendario = Calendar.getInstance();
        int day = calendario.get(Calendar.DAY_OF_MONTH);
        int month = calendario.get(Calendar.MONTH);
        final int year = calendario.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(ClienteCadastroView.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                Calendar date = Calendar.getInstance();
                date.set(mYear, mMonth, mDay);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                txtDataNascimento.setText(sdf.format(date.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.textDataNascimento)
    public void dataNascimento() {
        datePicker();
    }

//    @OnClick(R.id.cadastrar)
//    public void cadastrar(){
//        Log.i("clienteid", String.valueOf(presenter.readClientes().size()));
//        for (ClienteModel enderecoModel:presenter.readClientes()){
//            Log.i("clienteid", String.valueOf(enderecoModel.getEnderecoCliente().getId()));
//        }
//    }

    @OnClick(R.id.button)
    public void insertCliente() {
        presenter.insert();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public EditText getNome() {
        return nome;
    }

    @Override
    public EditText getCpf() {
        return cpf;
    }

    @Override
    public TextView getDataNascimento() {
        return txtDataNascimento;
    }

    @Override
    public EditText getCep() {
        return cep;
    }

    @Override
    public EditText getBairro() {
        return bairro;
    }

    @Override
    public EditText getNumero() {
        return numero;
    }

    @Override
    public EditText getEstado() {
        return estado;
    }

    @Override
    public EditText getLogradrouro() {
        return logradouro;
    }

}
