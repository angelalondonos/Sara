package com.example.angela.sara.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.angela.sara.Fragments.DetalleDeMonitorFragment;
import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * Created by angela on 22/10/17.
 */

public class DetalleDeMonitorActivity extends AppCompatActivity implements View.OnClickListener{

     /**
      * creación de un ImageButton
     */
    private ImageButton btnImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_de_monitor);

        DetalleDeMonitorFragment detalleMonitor = (DetalleDeMonitorFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_monitores);
        Monitor monitor = (Monitor) getIntent().getExtras().get("per");
        detalleMonitor.mostrarMonitor(monitor);


        /**
         * configuración del btnImagen
         */
        btnImagen = (ImageButton) findViewById(R.id.btn_editar);
        btnImagen.setOnClickListener(this); //utiliza el onclick listener global
    }

    @Override
    public void onClick(View v) {
        /**
         * configuración del btnImagen
         */
        if (v.getId() == btnImagen.getId()) {
            pasarAEditarMonitor(v);
        }
    }

    /**
     * Método para pasar al activity EditarMonitorAcctivity
     *
     * @param view
     */
    public void pasarAEditarMonitor(View view) {
        Intent intent = new Intent(this, EditarMonitorAcctivity.class);
        startActivity(intent);
    }
}
