package com.example.mateusoliveira.cadastrocliente.RecyrcleView.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente.DescricaoClienteView;
import com.example.mateusoliveira.cadastrocliente.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyrcleViewAdapter extends RecyclerView.Adapter<RecyrcleViewAdapter.ViewHolder> {
    private List<ClienteModel> dados;
    private Context context;

    public RecyrcleViewAdapter(List<ClienteModel> dados, Context context) {
        this.dados = dados;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if ((dados != null) && (dados.size() > 0)) {
            ClienteModel dadosModel = dados.get(position);
            holder.txtCpf.setText(dadosModel.getCpf());
            holder.txtNome.setText(dadosModel.getNome());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textCpf)
        TextView txtCpf;

        @BindView(R.id.textNome)
        TextView txtNome;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ClienteModel clienteModel = dados.get(position);
            Intent intent = new Intent(context, DescricaoClienteView.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("cliente", (Serializable)clienteModel);
            bundle.putSerializable("endereco", (Serializable)clienteModel.getEnderecoCliente());
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
