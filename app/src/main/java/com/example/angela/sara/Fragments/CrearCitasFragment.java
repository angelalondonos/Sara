package com.example.angela.sara.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.angela.sara.R;
import com.example.angela.sara.activity.SaraActivity;
import com.example.angela.sara.util.AdaptadorDeMonitor;
import com.example.angela.sara.util.ManagerFireBase;
import com.example.angela.sara.util.MonitorAdapter;
import com.example.angela.sara.vo.Cita;
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
public class CrearCitasFragment extends Fragment implements AdaptadorDeMonitor.OnClickAdaptadorDeMonitor, ManagerFireBase.OnActualizarAdaptadorListener{

    /**
     * creación de un Button
     */
    @BindView(R.id.btn_agregar_cita) protected Button btnCrearCita;
    /**
     * creación de un ImageButton
     */
    @BindView(R.id.button_horario_cita) protected ImageButton btnImagen;

    //@BindView(R.id.fragmento_crear_cita) protected RecyclerView fragmento_crear_cita;


    ArrayList<Monitor> monitores;
    private ManagerFireBase managerFireBase;

    private EditText editText_nombre;
    private EditText editText_identificacion;
    private Spinner spinner_semestre;
    private Spinner spinner_lista_monitores;
    private Spinner datos_monitores;

    private ListaDeMonitoresFragment.OnMonitorSeleccionadoListener listener;
    private AdaptadorDeMonitor adaptador;
    private String [] nombres;



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

        managerFireBase = ManagerFireBase.getInstancia();

        View view= inflater.inflate(R.layout.fragment_crear_citas, container, false);

        ButterKnife.bind(this, view);

        datos_monitores = (Spinner) view.findViewById(R.id.datos_monitores);

        editText_nombre = (EditText) view.findViewById(R.id.editText_nombre);
        editText_identificacion = (EditText) view.findViewById(R.id.editText_identificacion);
        spinner_semestre = (Spinner) view.findViewById(R.id.spinner_semestre);
        spinner_lista_monitores = (Spinner) view.findViewById(R.id.spinner_lista_monitores);




        //monitores = new ArrayList<>();
        //Arreglo con nombre de frutas
        //monitores.add(new Monitor("Rodrigo", "Proframación"));
        //monitores.add(new Monitor("Angela", "Proframación"));

        //monitores = new ArrayList<>();
        //managerFireBase = ManagerFireBase.getInstancia();
        //managerFireBase.escucharEventoFireBase();

        //adaptador = new AdaptadorDeMonitor(monitores, this);
        //fragmento_crear_cita.setAdapter(adaptador);
        //fragmento_crear_cita.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        monitores = ((SaraActivity)getActivity()).getListaDeMonitoresFragment().getMonitores();

        final int size = monitores.size();
        nombres = new String [size];
        for (int i = 0; i < size; i++){
            nombres[i] = monitores.get(i).getNombre();
        }


        ArrayAdapter<String> spinnerCountShoesArrayAdapter =
                new ArrayAdapter<String>( getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, nombres);
        spinnerCountShoesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        datos_monitores.setAdapter(spinnerCountShoesArrayAdapter);




        btnCrearCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = datos_monitores.getSelectedItem().toString();

                if(nombre != null) {
                    Monitor monitor = new Monitor();

                    for (Monitor monitor1 : monitores) {
                        if (monitor1.getNombre().equals(nombre)) {
                            monitor = monitor1;
                        }
                    }

                    managerFireBase.agregarCitaMonitor(crearCita(), monitor);
                    mostrarMensaje(getResources().getString(R.string.msg_btn_crear_cita));
                }else {
                    mostrarMensaje(getResources().getString(R.string.msg_btn_no_crear_cita));
                }
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private Cita crearCita() {

        Cita cita = new Cita(editText_nombre.getText().toString(), editText_identificacion.getText().toString(),
                             spinner_semestre.getSelectedItem().toString(), spinner_lista_monitores.getSelectedItem().toString());
        return cita;
    }


    /**
     * Método para mostrar un mensaje en un evento de botón
     *
     * @param message
     */
    public void mostrarMensaje(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickPosition(int pos) {
        listener.onMonitorSeleccionado(monitores.get(pos));
    }

    @Override
    public void actualizarAdaptador(Monitor monitor) {
        monitores.add(monitor);
        adaptador.notifyItemInserted(monitores.size()-1);
    }
}
