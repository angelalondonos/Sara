package com.example.angela.sara.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.angela.sara.R;
import com.example.angela.sara.util.AdaptadorDeMonitor;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

/**
 * Fragmento que contiene la lista de monitores
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class ListaDeMonitoresFragment extends Fragment{

    private AdaptadorDeMonitor adaptador;
    private RecyclerView listadoDeMonitores;
    private ArrayList<Monitor> monitores;


    /**
     * Método constructor de la clase ListaDeMonitoresFragment
     */
    public ListaDeMonitoresFragment() {
        // Required empty public constructor
    }

    /**
     * Método que al cual se le asigma un layout determinado.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_de_monitores, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptador = new AdaptadorDeMonitor(monitores);
        listadoDeMonitores = (RecyclerView) getView().findViewById(R.id.listaMonitores);
        listadoDeMonitores.setAdapter(adaptador);
        listadoDeMonitores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * Método que permite verifiar que opcion se eleige en el menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_agregar) {
            monitores.add(0, new Monitor("Nuevo personaje","Programación"));
            adaptador.notifyItemInserted(0);
        }
        if (id == R.id.menu_eliminar) {
            monitores.remove(0);
            adaptador.notifyItemRemoved(0);
        }
        if (id == R.id.menu_modificar) {
            Monitor aux = monitores.get(1);
            monitores.set(1,monitores.get(2));
            monitores.set(2, aux);
            adaptador.notifyItemMoved(1, 2);
        }

        return super.onOptionsItemSelected(item);

    }

}
