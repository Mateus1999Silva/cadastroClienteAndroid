package com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.R;
import com.example.mateusoliveira.cadastrocliente.utils.ClienteValidationsUtils;
import com.example.mateusoliveira.cadastrocliente.utils.MaskUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClienteCadastroView extends AppCompatActivity implements ClienteCadastroContrato.clienteCadastroView {

    @BindView(R.id.editNomeCompleto)
    EditText nome;

    @BindView(R.id.editCpf)
    EditText cpf;

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

    @BindView(R.id.loading)
    ProgressBar progressBar;

    @BindView(R.id.editComplemento)
    EditText editComplemento;

    @BindView(R.id.viewErroNascimento)
    TextView textErrorNascimento;

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
        MaskUtils.putMaskCep(cep);
        MaskUtils.putMaskCpf(cpf);
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
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtDataNascimento.setText(sdf.format(date.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.textDataNascimento)
    public void dataNascimento() {
        datePicker();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus && presenter.validacaoBuscaCep()) {
                    progress().setVisibility(View.VISIBLE);
                    presenter.apiCep();
                }
            }
        });
    }

    @OnClick(R.id.button)
    public void insertCliente() {
        if (presenter.validacaoCampos())
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
    public TextView getTextViewError() {
        return textErrorNascimento;
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

    @Override
    public EditText getComplemento() {
        return editComplemento;
    }

    @Override
    public void setBairro(String bairro) {
        this.bairro.setText(bairro);
    }

    @Override
    public void setEstado(String estado) {
        this.estado.setText(estado);
    }

    @Override
    public void setLogradrouro(String logradouro) {
        this.logradouro.setText(logradouro);
    }

    @Override
    public ProgressBar progress() {
        return progressBar;
    }

}
