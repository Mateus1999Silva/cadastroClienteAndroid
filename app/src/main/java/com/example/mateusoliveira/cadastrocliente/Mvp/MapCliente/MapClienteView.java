package com.example.mateusoliveira.cadastrocliente.Mvp.MapCliente;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mateusoliveira.cadastrocliente.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapClienteView extends AppCompatActivity implements OnMapReadyCallback, MapClienteContrato.MapClienteView {

    private MapClienteContrato.MapClientePresenter presenter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_cliente);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        presenter = new MapClientePresenter();
        presenter.setView(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        Intent intent = getIntent();
        String endereco = intent.getExtras().getString("endereco");
        presenter.map(endereco, map);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
