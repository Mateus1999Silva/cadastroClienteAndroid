package com.example.mateusoliveira.cadastrocliente.RecyrcleView.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.example.mateusoliveira.cadastrocliente.Model.ClienteModel;
import com.example.mateusoliveira.cadastrocliente.Mvp.DescricaoCliente.DescricaoClienteView;
import com.example.mateusoliveira.cadastrocliente.R;

import java.io.Serializable;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final ClienteModel item =  dados.get(position);;
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

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, " Click : " + item.getNome() + " \n" + item.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Edit  " + viewHolder.txtNome.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Deleted " + viewHolder.txtNome.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
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

        @BindView(R.id.swipe)
        SwipeLayout swipeLayout;

        @BindView(R.id.Delete)
        TextView Delete;

        @BindView(R.id.Edit)
        TextView Edit;

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
            bundle.putSerializable("cliente", (Serializable) clienteModel);
            bundle.putSerializable("endereco", (Serializable) clienteModel.getEnderecoCliente());
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
