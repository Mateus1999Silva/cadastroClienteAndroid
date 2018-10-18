package com.example.mateusoliveira.cadastrocliente.Mvp.MapCliente;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;

public interface MapClienteContrato {

    interface MapClienteView{
        Context getContext();
    }

    interface MapClientePresenter{
        void setView(MapClienteView view);

        void map(String endereco, GoogleMap map);
    }


}
