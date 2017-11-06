package com.example.angela.sara.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.angela.sara.Fragments.ListaDeMonitoresFragment;
import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 */

public class AdaptadorDeMonitor extends RecyclerView.Adapter<AdaptadorDeMonitor.MonitorViewHolder> {

    private ArrayList<Monitor> monitores;
    private static OnClickAdaptadorDeMonitor listener;

    /**
     * Método constructor de la clase AdaptadorDeMonitor
     * @param monitores
     */
    public AdaptadorDeMonitor(ArrayList<Monitor> monitores, ListaDeMonitoresFragment listaDeMonitoresFragment) {
        this.monitores = monitores;
        try{
            listener= (OnClickAdaptadorDeMonitor) listaDeMonitoresFragment;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public MonitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_monitor, parent, false);
        MonitorViewHolder monitorVH = new MonitorViewHolder(itemView);
        return monitorVH;
    }

    public interface OnClickAdaptadorDeMonitor{
        void onClickPosition(int pos);
    }


    @Override
    public void onBindViewHolder(MonitorViewHolder holder, int position) {

        Monitor monitor = monitores.get(position);
        holder.binMonitor(monitor);
    }

    @Override
    public int getItemCount() {
        return monitores.size();
    }

    /**
     * Clase que carga la información que se muestra del monitor
     */

    public static class MonitorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtNombreMonitor;
        private TextView txtLineaMonitoria;

        public MonitorViewHolder(View itemView) {
            super(itemView);
            txtNombreMonitor = (TextView) itemView.findViewById(R.id.nombre_resumen);
            txtLineaMonitoria = (TextView) itemView.findViewById(R.id.linea_monitoria_resumen);
            itemView.setOnClickListener(this);

        }

        public void binMonitor(Monitor monitor) {
            txtNombreMonitor.setText(monitor.getNombre());
            txtLineaMonitoria.setText(monitor.getLineaMonitoria());
        }

        @Override
        public void onClick(View view) {

            listener.onClickPosition(getAdapterPosition());
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked. "+ txtNombreMonitor.getText());
        }
    }
}
