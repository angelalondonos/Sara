package com.example.angela.sara;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.angela.sara.Fragments.CrearMonitorFragment;
import com.example.angela.sara.Fragments.ListaDeMonitoresFragment;
import com.example.angela.sara.activity.DetalleDeMonitorActivity;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

public class SaraActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListaDeMonitoresFragment.OnMonitorSeleccionadoListener{

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ArrayList<Monitor> monitores;
    
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

        ListaDeMonitoresFragment listaDeMonitoresFragment = (ListaDeMonitoresFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_monitores);
        listaDeMonitoresFragment.setMonitores(monitores);


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

    @Override
    public void onMonitorSeleccionado(int position) {
        Intent intent = new Intent(this, DetalleDeMonitorActivity.class);
        intent.putExtra("per", monitores.get(position));
        startActivity(intent);
    }


    private void remplazarFragmento(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Toast.makeText(this, "Estoy aquí", Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.menu_monitores:
                remplazarFragmento(new ListaDeMonitoresFragment());
                break;
            case R.id.menu_crear_monitor:
                remplazarFragmento(new CrearMonitorFragment());
                break;
            case R.id.menu_crear_cita:
                Log.i("NavigationView", "Pulsada seccion 3");
                break;
            case R.id.menu_citas:
                Log.i("NavigationView", "Pulsada seccion 3");
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
}

