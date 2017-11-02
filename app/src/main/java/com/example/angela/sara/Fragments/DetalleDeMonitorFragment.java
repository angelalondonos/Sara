package com.example.angela.sara.Fragments;


import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private ImageButton btnHorario;


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

        btnHorario = (ImageButton) view.findViewById(R.id.button_horario_detalle);
        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long startMillis = 0;

                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder,startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());

                startActivity(intent);
            }
        });

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
