package com.example.mateusoliveira.cadastrocliente.ApiEndereco;

import android.util.Log;

import com.example.mateusoliveira.cadastrocliente.Model.EnderecoModel;
import com.example.mateusoliveira.cadastrocliente.interfaceResult.SyncResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequest {
    private ApiInterface apiInterface;
    private EnderecoModel enderecoModel = new EnderecoModel();
    private SyncResult syncResult;

    public ApiRequest(SyncResult syncResult) {
        this.syncResult = syncResult;
    }

    public void getEndereco(String cep) {
        apiInterface = ApiEndereco.getClient().create(ApiInterface.class);
        Call<EnderecoModel> call = apiInterface.getEnderecoCep(cep);
        call.enqueue(new Callback<EnderecoModel>() {
            @Override
            public void onResponse(Call<EnderecoModel> call, Response<EnderecoModel> response) {
                    if(response != null && response.body() != null) {
                        enderecoModel = response.body();
                        syncResult.onSucess();

                }
            }

            @Override
            public void onFailure(Call<EnderecoModel> call, Throwable t) {
                Log.e("Erro Retrofit", String.valueOf(t));
            }
        });
    }

    public EnderecoModel getEnderecoModel() {
        return enderecoModel;
    }
}
