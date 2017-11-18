package com.example.angela.sara.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.angela.sara.Fragments.DetalleDeMonitorFragment;
import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * Created by angela on 22/10/17.
 */

public class DetalleDeMonitorActivity extends AppCompatActivity {

    /**
     * creación de un FloatingActionButton
     */
    @BindView(R.id.btn_flotante_agregar_cita) protected FloatingActionButton btnAgregarCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_de_monitor);

        DetalleDeMonitorFragment detalleMonitor = (DetalleDeMonitorFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_detalle_monitores);
        Monitor monitor = (Monitor) getIntent().getExtras().getParcelable("Monitor");
        detalleMonitor.mostrarMonitor(monitor);

        ButterKnife.bind(this);


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
        inflater.inflate(R.menu.menu_share, menu);
        return true;
    }

    /**
     * Método que permite verifiacar que opcion se elege en el menu
     * @param item
     * @return
     */
    @Override
     public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_compartir) {
           /** Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.msg_btn_compartir_prueba));
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.msg_btn_compartir)));
          */
            Intent intent = new Intent(this, EditarMonitorAcctivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que permite abrir calendario
     * @param view
     */
    public void onAddEventClicked(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(Events.TITLE, "Monitor 1");
        intent.putExtra(Events.DESCRIPTION,  "Descripción");
        intent.putExtra(Events.EVENT_LOCATION, "Universidad del Quindío");
        intent.putExtra(Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }
}
