package com.example.angela.sara.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    /**
     * Método constructor de la clase AdaptadorDeMonitor
     * @param monitores
     */
    public AdaptadorDeMonitor(ArrayList<Monitor> monitores) {
        this.monitores = monitores;
    }

    @Override
    public MonitorViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_personaje, parent, false);
        MonitorViewHolder monitorVH = new MonitorViewHolder(itemView);
        return monitorVH;
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

    public static class MonitorViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNombrePresonaje;
        private TextView txtLineaMonitoria;

        public MonitorViewHolder(View itemView) {
            super(itemView);
            txtNombrePresonaje = (TextView) itemView.findViewById(R.id.nombre);
            txtLineaMonitoria = (TextView) itemView.findViewById(R.id.linea_monitoria);
        }

        public void binMonitor(Monitor monitor) {
            txtNombrePresonaje.setText(monitor.getNombre());

            txtLineaMonitoria.setText(monitor.getLineaMonitoria());
        }
    }
}
