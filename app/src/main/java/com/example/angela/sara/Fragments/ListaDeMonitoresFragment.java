package com.example.angela.sara.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angela.sara.R;
import com.example.angela.sara.util.AdaptadorDeMonitor;
import com.example.angela.sara.util.ManagerFireBase;
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
public class ListaDeMonitoresFragment extends Fragment implements AdaptadorDeMonitor.OnClickAdaptadorDeMonitor, ManagerFireBase.OnActualizarAdaptadorListener{

    /**
     * creación de un AdaptadorDeMonitor
     */
    private AdaptadorDeMonitor adaptador;
    /**
     * creación de un RecyclerView
     */
    @BindView(R.id.listaMonitores) protected RecyclerView listadoDeMonitores;
    private Unbinder unbinder;
    /**
     * creación de un ArrayList<Monitor>
     */
    private ArrayList<Monitor> monitores;
    /**
     * creación de un OnMonitorSeleccionadoListener
     */
    private OnMonitorSeleccionadoListener listener;
    /**
     * creación de un FloatingActionButton
     */
    private FloatingActionButton btnAgregarMonitor;

    /*
    * Atributo de clase ManagerFireBase
     */
    private ManagerFireBase managerFireBase;

    /**
     * Método constructor de la clase ListaDeMonitoresFragment
     */
    public ListaDeMonitoresFragment() {
        // Required empty public constructor
    }

    @Override
    public void actualizarAdaptador(Monitor monitor) {
        monitores.add(monitor);
        adaptador.notifyItemInserted(monitores.size()-1);
    }

    /**
     * Crea la conexion entre el fragmento y su parte grafica
     */
    public interface OnMonitorSeleccionadoListener {
        void onMonitorSeleccionado(Monitor monitor);
    }


    /**
     * Método al que se le asigna un layout determinado.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_de_monitores, container, false);

        unbinder= ButterKnife.bind(this, view);

        btnAgregarMonitor = (FloatingActionButton) view.findViewById(R.id.btn_flotante_agregar_monitor);
        btnAgregarMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remplazarFragmento(new CrearMonitorFragment());
            }
        });//utiliza el onclick listener global

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

        monitores = new ArrayList<>();
        managerFireBase = ManagerFireBase.instanciar(this);
        managerFireBase.escucharEventoFireBase();

        adaptador = new AdaptadorDeMonitor(monitores, this);
        listadoDeMonitores.setAdapter(adaptador);
        listadoDeMonitores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClickPosition(int pos) {listener.onMonitorSeleccionado(monitores.get(pos));
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

    private void remplazarFragmento(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
