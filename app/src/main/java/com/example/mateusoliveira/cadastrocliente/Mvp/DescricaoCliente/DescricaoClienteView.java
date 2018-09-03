package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.ApiEndereco.ApiRequest;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente.ClienteCadastroView;
import com.example.mateusoliveira.cadastrocliente.Mvp.MapCliente.MapClienteView;
import com.example.mateusoliveira.cadastrocliente.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class DescricaoClienteView extends AppCompatActivity implements DescricaoClienteContrato.DescricaoClienteView {

    @BindView(R.id.editNomeCompleto)
    EditText txtNome;

    @BindView(R.id.editCpf)
    EditText txtCpf;

    @BindView(R.id.textDataNascimento)
    TextView txtDataNascimento;

    @BindView(R.id.editCep)
    EditText txtCep;

    @BindView(R.id.editBairro)
    EditText txtBairro;

    @BindView(R.id.editNumero)
    EditText txtNumero;

    @BindView(R.id.editLogradouro)
    EditText txtLogradouro;

    @BindView(R.id.editEstado)
    EditText txtEstado;

    private DescricaoClienteContrato.DescricaoClientePresenter presenter;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_cliente);
        ButterKnife.bind(this);
        presenter = new DescricaoClientePresenter();
        presenter.setView(this);
        preencherDados();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemMenuMap) {
            presenter.getInformacoesMapa();
        }
        return true;
    }

    public void datePicker(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dYear, int dMonth, int mDayOfMonth) {
                Calendar dataUsuario = Calendar.getInstance();
                dataUsuario.set(dYear, dMonth, mDayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtDataNascimento.setText(sdf.format(dataUsuario.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @OnClick(R.id.textDataNascimento)
    public void txtDataNascimento(){
        datePicker();
    }

    @OnFocusChange(R.id.editLogradouro)
    public void cep(){
        presenter.cep();
    }


    @Override
    public void preencherDados() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        EnderecoModel enderecoModel = (EnderecoModel) bundle.getSerializable("endereco");
        ClienteModel clienteModel = (ClienteModel) bundle.getSerializable("cliente");
        presenter.preencherDados(clienteModel, enderecoModel);
    }

    @OnClick(R.id.buttonEditar)
    @Override
    public void editarCliente() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        EnderecoModel enderecoModel = (EnderecoModel) bundle.getSerializable("endereco");
        ClienteModel clienteModel = (ClienteModel) bundle.getSerializable("cliente");
        presenter.editarCliente(clienteModel.getId(), enderecoModel.getId());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public EditText getBairro() {
        return txtBairro;
    }

    @Override
    public EditText getNumero() {
        return txtNumero;
    }

    @Override
    public EditText getEstado() {
        return txtEstado;
    }

    @Override
    public EditText getLogradouro() {
        return txtLogradouro;
    }

    @Override
    public void setNome(String nome) {
        txtNome.setText(nome);
    }

    @Override
    public void setCpf(String cpf) {
        txtCpf.setText(cpf);
    }

    @Override
    public void setDataNascimento(String dataNascimento) {
        txtDataNascimento.setText(dataNascimento);
    }

    @Override
    public void setCep(String cep) {
        txtCep.setText(cep);
    }

    @Override
    public void setNumero(String numero) {
        txtNumero.setText(numero);
    }

    @Override
    public void setBairro(String bairro) {
        txtBairro.setText(bairro);
    }

    @Override
    public void setEstado(String estado) {
        txtEstado.setText(estado);
    }

    @Override
    public void setLogradrouro(String logradouro) {
        txtLogradouro.setText(logradouro);
    }

    @Override
    public EditText getNome() {
        return txtNome;
    }

    @Override
    public EditText getCpf() {
        return txtCpf;
    }

    @Override
    public TextView getDataNascimento() {
        return txtDataNascimento;
    }

    @Override
    public EditText getCep() {
        return txtCep;
    }


}
