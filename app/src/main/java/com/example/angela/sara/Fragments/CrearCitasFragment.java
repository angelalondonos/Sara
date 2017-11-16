package com.example.angela.sara.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.angela.sara.R;
import com.example.angela.sara.util.MonitorAdapter;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class CrearCitasFragment extends Fragment {

    /**
     * creación de un Button
     */
    @BindView(R.id.btn_agregar_cita) protected Button btnCrearCita;
    /**
     * creación de un ImageButton
     */
    @BindView(R.id.button_horario_cita) protected ImageButton btnImagen;

    Spinner datos_monitores;
    ArrayList<Monitor> monitores;
    ArrayList<String> nombre_monitores;
    ArrayAdapter<String> comboAdapter;

    /**
     * Contructor vacio de la clase CrearCitaFragment
     */
    public CrearCitasFragment() {
        // Required empty public constructor
    }


    /**
     * Metodo que permite inicar el fragment_crear_cita
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_crear_citas, container, false);

        ButterKnife.bind(this, view);

        Spinner b = (Spinner) view.findViewById(R.id.datos_monitores);


         monitores = new ArrayList<>();
        //Arreglo con nombre de frutas
        //monitores.add(new Monitor("Rodrigo", "Proframación"));
        //monitores.add(new Monitor("Angela", "Proframación"));



        final int size = monitores.size();
        String [] nombres = new String [size];
        for (int i = 0; i < size; i++){
           nombres[i] = monitores.get(i).getNombre();
        }



        ArrayAdapter<String> spinnerCountShoesArrayAdapter =
                new ArrayAdapter<String>( getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, nombres);
        spinnerCountShoesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        b.setAdapter(spinnerCountShoesArrayAdapter);



        MonitorAdapter adapter = new MonitorAdapter(getActivity().getApplicationContext(), monitores);
        btnCrearCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarMensaje(getResources().getString(R.string.msg_btn_crear_cita));
            }
        });

        /**
         * configuración del btnImagen
         */
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");

                intent.putExtra(Events.TITLE, "Monitor 1");
                intent.putExtra(Events.DESCRIPTION,  getResources().getString(R.string.msg_calendario_estado));
                intent.putExtra(Events.EVENT_LOCATION, getResources().getString(R.string.msg_universidad));
                intent.putExtra(Events.RRULE, "FREQ=YEARLY");

                startActivity(intent);
            }
        }); //utiliza el onclick listener global


        // Inflate the layout for this fragment
        return view;
    }


    /**
     * Método para mostrar un mensaje en un evento de botón
     *
     * @param message
     */
    public void mostrarMensaje(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
