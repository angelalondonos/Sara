package com.example.angela.sara.activity;

import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.angela.sara.R;

import java.util.Calendar;

public class CrearCitasActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * creación de un ImageButton
     */
    private ImageButton btnImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_citas);


        /**
         * configuración del btnImagen
         */
        btnImagen = (ImageButton) findViewById(R.id.button_horario_cita);
        btnImagen.setOnClickListener(this); //utiliza el onclick listener global

    }

    @Override
    public void onClick(View view) {

    }

    /**
     * Método que permite brri calendario
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
