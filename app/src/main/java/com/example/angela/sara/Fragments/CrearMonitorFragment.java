package com.example.angela.sara.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angela.sara.R;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 *
 * Fragmento CrearMonitor
 */
public class CrearMonitorFragment extends Fragment {

    /**
     * Constructor vacio de la clase CrearMonitorFragment
     */
    public CrearMonitorFragment() {
        // Required empty public constructor
    }

    /**
     * Método que permite inicar fragment_crear_monitor
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_monitor, container, false);
    }

}
