package com.example.angela.sara.Fragments;


import android.app.Activity;
import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragmento que contiene la lista de monitores
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class ListaDeMonitoresFragment extends Fragment implements AdaptadorDeMonitor.OnClickAdaptadorDeMonitor{

    private AdaptadorDeMonitor adaptador;
    @BindView(R.id.listaMonitores) protected RecyclerView listadoDeMonitores;
    private ArrayList<Monitor> monitores;
    private OnMonitorSeleccionadoListener listener;
    private Unbinder unbider;


    /**
     * Método constructor de la clase ListaDeMonitoresFragment
     */
    public ListaDeMonitoresFragment() {
        // Required empty public constructor
    }


    /**
     * Crea la conexion entre el fragmento y su parte grafica
     */
    public interface OnMonitorSeleccionadoListener {
        void onMonitorSeleccionado(int position);
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

        View view=inflater.inflate(R.layout.activity_detalle_de_monitor, container, false);
        // Inflate the layout for this fragment

        unbider= ButterKnife.bind(this, view); //Inicializa la vista

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptador = new AdaptadorDeMonitor(monitores, this);
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

        if (id == R.id.cambiar_idioma) {

        }
     /**   if (id == R.id.menu_eliminar) {
            monitores.remove(0);
            adaptador.notifyItemRemoved(0);
        }
        if (id == R.id.menu_modificar) {
            Monitor aux = monitores.get(1);
            monitores.set(1,monitores.get(2));
            monitores.set(2, aux);
            adaptador.notifyItemMoved(1, 2);
        }
**/
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClickPosition(int pos) {
        listener.onMonitorSeleccionado(pos);
    }

    /**
     * Método para fijar una referencia a la Activity,
     * el cual debe implementar la interfaz OnMonitorSeleccionadoListener,
     * que luego podemos utilizar para pasar objetos del Fragment
     * a la Activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                listener = (OnMonitorSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnMonitorSeleccionadoListener");
            }
        }
    }

    public void setMonitores(ArrayList<Monitor> monitores) {
        this.monitores = monitores;
    }
}
