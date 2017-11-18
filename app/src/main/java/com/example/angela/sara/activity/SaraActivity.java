package com.example.angela.sara.activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.angela.sara.Fragments.CrearCitasFragment;
import com.example.angela.sara.Fragments.CrearMonitorFragment;
import com.example.angela.sara.Fragments.DetalleDeMonitorFragment;
import com.example.angela.sara.Fragments.ListaDeMonitoresFragment;
import com.example.angela.sara.Fragments.TabletFragment;
import com.example.angela.sara.R;
import com.example.angela.sara.util.Utilidades;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 *
 * Clase principal
 */
public class SaraActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListaDeMonitoresFragment.OnMonitorSeleccionadoListener{

    /**
     * creación de un DrawerLayout
     */
    @BindView(R.id.drawer_layout) protected DrawerLayout drawerLayout;
    /**
     * creación de un NavigationView
     */
    @BindView(R.id.navview) protected NavigationView navView;
    /**
     * creación de un ArrayList de monitores
     */
    private ArrayList<Monitor> monitores;

    private ListaDeMonitoresFragment listaDeMonitoresFragment;

    private CrearCitasFragment crearCitasFragment;

    /**
     * creación de un Button
     */
    private Button btnCrearCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilidades.obtenerLenguaje(this);
        setContentView(R.layout.activity_sara);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        if(isNetDisponible())
        {
            navView.setItemIconTintList(null);
            navView.setNavigationItemSelectedListener(this);


            listaDeMonitoresFragment = new ListaDeMonitoresFragment();
            crearCitasFragment = new CrearCitasFragment();
            //ListaDeMonitoresFragment listaDeMonitoresFragment = (ListaDeMonitoresFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_monitores);
            //listaDeMonitoresFragment.setMonitores(monitores);

            if (findViewById(R.id.fragmento_tablet) == null) {
                Log.i("NavigationView", "Estoy en Celular");


                remplazarFragmento(listaDeMonitoresFragment);
            }else{
                Log.i("NavigationView", "Estoy en la tablet");

                TabletFragment tabletFragment = new TabletFragment();
                remplazarFragmento(tabletFragment);
            }
        }else
        {
            Toast.makeText(this, getResources().getString(R.string.msg_no_hay_red), Toast.LENGTH_SHORT).show();
        }
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
            case R.id.cambiar_idioma:
                Utilidades.cambiarIdioma(this);
                Intent intent = getIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    /**
     * Método que verifica cual monitor es seleccionado de acuerdo a la posicion
     * @param monitor
     */
    @Override
    public void onMonitorSeleccionado(Monitor monitor) {

        boolean esFragmento = getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_monitores) != null;
        if (esFragmento) {
            ((DetalleDeMonitorFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_monitores)).mostrarMonitor(monitor);
        } else {
            Intent intent = new Intent(this, DetalleDeMonitorActivity.class);
            intent.putExtra("Monitor", monitor);
            startActivity(intent);
        }
    }

    /**
     * Método que permite cambiar de fragment
     * @param fragment
     */
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
                remplazarFragmento(listaDeMonitoresFragment);
                break;
            case R.id.menu_crear_monitor:
                remplazarFragmento(new CrearMonitorFragment());
                break;
            case R.id.menu_crear_cita:
                remplazarFragmento(crearCitasFragment);
                break;
            case R.id.menu_citas:

                long startMillis= 0;

                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
                startActivity(intent);

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
    /*
    *Metodo para comprovar si la Network esta habilitada
     */

    private boolean isNetDisponible() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    /*
    *Metodo para comprovar si hay acceso a internet
     */

    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public ListaDeMonitoresFragment getListaDeMonitoresFragment() {
        return listaDeMonitoresFragment;
    }
}

