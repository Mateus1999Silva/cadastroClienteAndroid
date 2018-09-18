package com.example.mateusoliveira.cadastrocliente.RecyrcleView.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente.DescricaoClienteView;
import com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente.ListClienteContrato;
import com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente.ListClienteView;
import com.example.mateusoliveira.cadastrocliente.R;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyrcleViewAdapter extends RecyclerView.Adapter<RecyrcleViewAdapter.ViewHolder> {
    private List<ClienteModel> dados;
    private Context context;
    private ListClienteContrato.ListClientePresenter presenter;

    public RecyrcleViewAdapter(List<ClienteModel> dados, Context context, ListClienteContrato.ListClientePresenter presenter) {
        this.dados = dados;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final ClienteModel item = dados.get(position);
        if ((dados != null) && (dados.size() > 0)) {
            viewHolder.txtCpf.setText(item.getCpf());
            viewHolder.txtNome.setText(item.getNome());
        }


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kanan
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));


        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });


        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteModel clienteModel = dados.get(position);
                presenter.editarClienteRecyclerView(clienteModel);
            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Deletar Cliente")
                        .setMessage("Deseja Realmente deletar o cliente")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ClienteModel clienteModel = dados.get(position);
                                int idCliente = (int) clienteModel.getId();
                                int idEndereco = clienteModel.getEnderecoCliente().getId();
                                if (presenter.deleteClienteRecyrcleView(idCliente, idEndereco)) {
                                    dados.remove(position);
                                    notifyItemRemoved(position);
                                }
                            }
                        })
                        .setNegativeButton("Não", null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textCpf)
        TextView txtCpf;

        @BindView(R.id.textNome)
        TextView txtNome;

        @BindView(R.id.swipe)
        SwipeLayout swipeLayout;

        @BindView(R.id.Delete)
        TextView Delete;

        @BindView(R.id.Edit)
        TextView Edit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
