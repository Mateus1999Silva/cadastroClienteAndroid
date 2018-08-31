package com.example.mateusoliveira.cadastrocliente.Mvp.MapCliente;

import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapClientePresenter implements MapClienteContrato.MapClientePresenter {

    private MapClienteContrato.MapClienteView view;

    @Override
    public void setView(MapClienteContrato.MapClienteView view) {
        this.view = view;
    }

    @Override
    public void map(String endereco, GoogleMap map) {
        Geocoder geocoder = new Geocoder(view.getContext());
        try {
            List<Address> geocoders = geocoder.getFromLocationName(endereco, 5);
            for (Address address : geocoders) {
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                map.addMarker(new MarkerOptions()
                        .position(latLng));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                map.getUiSettings().setZoomControlsEnabled(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
