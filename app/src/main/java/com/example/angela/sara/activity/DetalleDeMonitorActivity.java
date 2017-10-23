package com.example.angela.sara.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.angela.sara.Fragments.DetalleDeMonitorFragment;
import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

/**
 * Created by angela on 22/10/17.
 */

public class DetalleDeMonitorActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_de_monitor);

        DetalleDeMonitorFragment detalleMonitor = (DetalleDeMonitorFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_monitores);
        Monitor monitor = (Monitor) getIntent().getExtras().get("per");
        detalleMonitor.mostrarMonitor(monitor);
    }


}
