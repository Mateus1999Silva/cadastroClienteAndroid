package com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente;

public class DescricaoClientePresenter implements DescricaoClienteContrato.DescricaoClientePresenter {

    private DescricaoClienteContrato.DescricaoClienteView view;

    @Override
    public void setView(DescricaoClienteContrato.DescricaoClienteView view) {
        this.view = view;
    }
}
