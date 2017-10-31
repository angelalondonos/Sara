package com.example.angela.sara.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

/**
 * Clase que permite ver el detalle del monitor
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class DetalleDeMonitorFragment extends Fragment {

    /**
     * creación de TextView
     */
    private TextView txtNombre;
    private TextView txtLinea;
    /**
     * creación de un Monitor
     */
    private Monitor monitor;



    /**
     * Método contructor de la clase
     */
    public DetalleDeMonitorFragment() {
        // Required empty public constructor
    }


    /**
     * Método que permite inicar fragment_detalle_de_monitor
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detalle_de_monitor, container, false);




        return view;
    }

    /**
     * Método que permite mostrar nombre y linea de monitoria en el detalle
     * @param monitor
     */
    public void mostrarMonitor (Monitor monitor) {
        this.monitor = monitor;
        txtNombre = (TextView) getView().findViewById(R.id.txtview_nombre);
        txtNombre.setText(monitor.getNombre());
        txtLinea = (TextView) getView().findViewById(R.id.txtview_linea_monitoria);
        txtLinea.setText(monitor.getLineaMonitoria());
    }


}
