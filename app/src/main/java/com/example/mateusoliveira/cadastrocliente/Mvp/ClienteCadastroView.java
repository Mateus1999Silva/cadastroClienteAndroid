package com.example.mateusoliveira.cadastrocliente.Mvp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.R;

import java.util.Calendar;

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

    private Calendar calendario;
    private DatePickerDialog datePicker;
    private ClienteCadastroContrato.clienteCadastroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_cliente);
        ButterKnife.bind(this);
        presenter = new ClienteCadastroPresenter();
        presenter.setView(ClienteCadastroView.this);
    }


//    @OnClick(R.id.buttonCalendario)
//    public Date datePicker(){
//        calendario = Calendar.getInstance();
//        int day = calendario.get(Calendar.DAY_OF_MONTH);
//        int month = calendario.get(Calendar.MONTH);
//        final int year = calendario.get(Calendar.YEAR);
//
//        datePicker = new DatePickerDialog(ClienteCadastroView.this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
//               Calendar date = Calendar.getInstance();
//               date.add(Calendar.MONTH, mMonth);
//               date.add(Calendar.DAY_OF_MONTH, mDay);
//               date.add(Calendar.YEAR, mYear);
//            }
//        },day ,month,year);
//        datePicker.show();
//    }

    @Override
    public void openList() {
        Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.button)
    public void insertCliente(){
            presenter.insert();
    }
}
