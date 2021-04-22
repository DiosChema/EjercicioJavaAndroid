package com.example.concredito.RecyclerViews;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.example.concredito.Objetos.DocumentoObj;
        import com.example.concredito.R;

        import java.util.ArrayList;

public class RecyclerViewDocumentos extends RecyclerView.Adapter<RecyclerViewDocumentos.ViewHolder> {

    private ArrayList<DocumentoObj> localDataSet;

    public RecyclerViewDocumentos(ArrayList<DocumentoObj> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView documentosNombre;
        public ImageView documentosIcono;
        public ViewHolder(View itemView) {
            super(itemView);
            this.documentosNombre = itemView.findViewById(R.id.documentosNombre);
            this.documentosIcono = itemView.findViewById(R.id.documentosIcono);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.documentos, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DocumentoObj myListData = localDataSet.get(position);

        holder.documentosNombre.setText(myListData.getNombre());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
