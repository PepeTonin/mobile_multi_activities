package com.example.multiactivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multiactivities.R;
import com.example.multiactivities.Paciente;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Paciente> listaPacientes;

    public Adapter(List<Paciente> lista){
        this.listaPacientes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Paciente paciente = listaPacientes.get(position);
        holder.tvNome.setText(paciente.getNome());
        holder.tvImc.setText(paciente.getImc().toString());
        holder.tvResultado.setText(paciente.getResultImc());
    }

    @Override
    public int getItemCount() {
        return listaPacientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvImc;
        TextView tvResultado;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvImc = itemView.findViewById(R.id.tvImc);
            tvResultado = itemView.findViewById(R.id.tvResultado);
        }
    }

}
