package com.example.angela.sara;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.angela.sara.util.AdaptadorDeMonitor;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

public class SaraActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private RecyclerView listadoDeMonitores;
    private ArrayList<Monitor> monitores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sara);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);
        navView.setItemIconTintList(null);


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

        listadoDeMonitores= (RecyclerView) findViewById(R.id.listaMonitores);
        final AdaptadorDeMonitor adaptador = new  AdaptadorDeMonitor(monitores);
        listadoDeMonitores.setAdapter(adaptador);
        listadoDeMonitores.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
