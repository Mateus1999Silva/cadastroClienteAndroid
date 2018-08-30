package com.example.mateusoliveira.cadastrocliente.ApiEndereco;

import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("{cep}/json/")
    Call<EnderecoModel> getEnderecoCep(@Path("cep") String cep);
}
