package com.example.concredito.RecyclerViews;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.concredito.Activities.ActualizarProspecto;
import com.example.concredito.Activities.Prospecto;
import com.example.concredito.Objetos.ProspectosObj;
import com.example.concredito.R;

import java.util.ArrayList;

public class RecyclerViewProspectos extends RecyclerView.Adapter<RecyclerViewProspectos.ViewHolder> {

    private ArrayList<ProspectosObj> localDataSet;

    public RecyclerViewProspectos(ArrayList<ProspectosObj> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreView;
        public TextView statusView;
        public ImageButton prospectosInformacion;
        public ImageButton prospectosActualizar;
        public ImageView prospectosAvatar;
        public ViewHolder(View itemView) {
            super(itemView);
            this.nombreView = itemView.findViewById(R.id.prospectosNombre);
            this.statusView = itemView.findViewById(R.id.prospectosStatus);
            this.prospectosInformacion = itemView.findViewById(R.id.prospectosInformacion);
            this.prospectosActualizar = itemView.findViewById(R.id.prospectosActualizar);
            this.prospectosAvatar = itemView.findViewById(R.id.prospectosAvatar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.prospectos, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProspectosObj myListData = localDataSet.get(position);

        String name = myListData.getNombre() + " " + myListData.getApellidoP() + " " + myListData.getApellidoM();
        String estatus;

        switch (myListData.getEstatus())
        {
            default:
            case 0:
                estatus = "Enviado";
                break;
            case 1:
                estatus = "Autorizado";
                break;
            case 2:
                estatus = "Rechazado";
                break;
        }

        holder.nombreView.setText(name);
        holder.statusView.setText(estatus);
        holder.prospectosInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Prospecto.class);
                intent.putExtra("prospecto", String.valueOf(myListData.get_id()));
                view.getContext().startActivity(intent);
            }
        });

        holder.prospectosActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActualizarProspecto.class);
                intent.putExtra("prospecto", String.valueOf(myListData.get_id()));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
