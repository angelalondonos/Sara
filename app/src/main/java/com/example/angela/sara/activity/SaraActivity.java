package com.example.angela.sara.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.angela.sara.Fragments.CitaFragment;
import com.example.angela.sara.Fragments.CrearCitasFragment;
import com.example.angela.sara.Fragments.CrearMonitorFragment;
import com.example.angela.sara.Fragments.DetalleDeMonitorFragment;
import com.example.angela.sara.Fragments.ListaDeMonitoresFragment;
import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 *
 * Clase principal
 */
public class SaraActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListaDeMonitoresFragment.OnMonitorSeleccionadoListener, View.OnClickListener{

    /**
     * creación de un DrawerLayout
     */
    private DrawerLayout drawerLayout;
    /**
     * creación de un NavigationView
     */
    private NavigationView navView;
    /**
     * creación de un ArrayList de monitores
     */
    private ArrayList<Monitor> monitores;
    /**
     * creación de un FloatingActionButton
     */
    private FloatingActionButton btnAgregarMonitor;
    /**
     * creación de un Button
     */
    private Button btnCrearMonitor;
    /**
     * creación de un Button
     */
    private Button btnCrearCita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sara);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navview);
        navView.setItemIconTintList(null);
        navView.setNavigationItemSelectedListener(this);

        monitores = new ArrayList();
        monitores.add(new Monitor("Ronaldinho", "Programación"));
        monitores.add(new Monitor("Albert Einstein", "Programación"));
        monitores.add(new Monitor("Leonardo da Vinci", "Programación"));
        monitores.add(new Monitor("Goku", "Calculo"));
        monitores.add(new Monitor("Alejandro Magno", "Calculo"));
        monitores.add(new Monitor("Ronaldinho", "Calculo"));
        monitores.add(new Monitor("Albert Einstein", "Calculo"));
        monitores.add(new Monitor("Leonardo da Vinci", "Calculo"));
        monitores.add(new Monitor("Goku", "Calculo"));
        monitores.add(new Monitor("Alejandro Magno", "Calculo"));




        //ListaDeMonitoresFragment listaDeMonitoresFragment = (ListaDeMonitoresFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_monitores);
        //listaDeMonitoresFragment.setMonitores(monitores);


        ListaDeMonitoresFragment listaDeMonitoresFragment = new ListaDeMonitoresFragment();
        listaDeMonitoresFragment.setMonitores(monitores);
        remplazarFragmento(listaDeMonitoresFragment);


        //btnAgregarMonitor = (FloatingActionButton) findViewById(R.id.btn_flotante);
        //btnAgregarMonitor.setOnClickListener(this); //utiliza el onclick listener global

        //btnCrearMonitor = (Button) findViewById(R.id.btn_agregar_monitor);
       // btnCrearMonitor = (Button) findViewById(R.id.btn_agregar_monitor);

        //btnAgregarMonitor.setOnClickListener(this);

        btnCrearCita = (Button) findViewById(R.id.btn_agregar_cita);
        btnCrearCita.setOnClickListener(this);
    }

    /**
     * Metodo que permite crear el menu de opciones
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método que permite verifiar que opcion se eleige en el menu
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    /**
     * Método que verifica cual monitor es seleccionado de acuerdo a la posicion
     * @param position
     */
    @Override
    public void onMonitorSeleccionado(int position) {

        boolean esFragmento = getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_monitores) != null;
        if (esFragmento) {
            ((DetalleDeMonitorFragment)
                    getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_monitores
                    )).mostrarMonitor(monitores.get(position));
        } else {
            Intent intent = new Intent(this, DetalleDeMonitorActivity.class);
            intent.putExtra("per", monitores.get(position));
            startActivity(intent);
        }
    }


    private void remplazarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

    }


    /**
     * Método que permite cambiar de opcion en el Navigation Drawer
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_monitores:
                remplazarFragmento(new ListaDeMonitoresFragment());
                break;
            case R.id.menu_crear_monitor:
                remplazarFragmento(new CrearMonitorFragment());
                break;
            case R.id.menu_crear_cita:
                remplazarFragmento(new CrearCitasFragment());
                break;
            case R.id.menu_citas:
                remplazarFragmento(new CitaFragment());
                break;
            case R.id.menu_opcion_1:
                Log.i("NavigationView", "Pulsada opción 1");
                break;
            case R.id.menu_opcion_2:
                Log.i("NavigationView", "Pulsada opción 2");
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }

    /**
     * Método para mostrar un mensaje en un evento de botón
     *
     * @param message
     */
    public void mostrarMensaje(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {

            if (view.getId() == btnCrearMonitor.getId()) {
                mostrarMensaje(getResources().getString(R.string.msg_btn_crear_monitor));
            }
          /**  else {
                if (view.getId() == btnCrearCita.getId()) {
                    mostrarMensaje(getResources().getString(R.string.msg_btn_crear_cita));
                }
            */
    }
}

