package com.example.angela.sara.Fragments;


import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.txtview_nombre) protected TextView txtNombre;
    @BindView(R.id.txtview_linea_monitoria) protected TextView txtLinea;
    @BindView(R.id.contrasena_detalle) protected TextView txtContrasena;
    @BindView(R.id.userName_detalle) protected TextView txtUsename;
    @BindView(R.id.telefono_detalle) protected TextView txtTelefono;
    @BindView(R.id.semestre_detalle) protected TextView txtSemestre;
    @BindView(R.id.lugar_detalle) protected TextView txtLugarAsesoria;
    /**
     * creación de un Monitor
     */
    private Monitor monitor;
    @BindView(R.id.button_horario_detalle) protected ImageButton btnHorario;


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

        ButterKnife.bind(this, view);
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
        txtNombre.setText(monitor.getUserName());
        txtLinea.setText(monitor.getTelefono());
        txtContrasena.setText(monitor.getContrasena());
        txtUsename.setText(monitor.getContrasena());
        txtTelefono.setText(monitor.getLugarAsesoria());
        txtContrasena.setText(monitor.getNombre());
        txtLugarAsesoria.setText(monitor.getSemestre());
        txtSemestre.setText(monitor.getLineaMonitoria());

        Log.e("netHabilitada", monitor.getCitas().toString());
    }


}
