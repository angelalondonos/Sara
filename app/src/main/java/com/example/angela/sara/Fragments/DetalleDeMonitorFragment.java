package com.example.angela.sara.Fragments;


import android.content.Intent;
import android.net.Uri;
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
public class DetalleDeMonitorFragment extends Fragment implements View.OnClickListener{

    private TextView txtNombre;
    private Monitor monitor;

    public DetalleDeMonitorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_de_monitor, container, false);
    }

    public void mostrarMonitor (Monitor monitor) {
        this.monitor = monitor;
        txtNombre = (TextView) getView().findViewById(R.id.descripcion_de_detalle_personaje);
        txtNombre.setText(monitor.getNombre());
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=hP3fmnMuZZU"));
        startActivity(intent);
    }
}
