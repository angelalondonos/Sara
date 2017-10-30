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
 * Fragmento de Cita
 */
public class CitaFragment extends Fragment {

    /**
     * Constructor vacio de la clase CitaFragment
     */
    public CitaFragment() {

        // Required empty public constructor
    }


    /**
     * Método que permite inicar el fragmento Cita
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cita, container, false);
    }
}
