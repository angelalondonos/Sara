package com.example.angela.sara.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.angela.sara.R;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 *
 * Clase Editar Monitor Activity
 */

public class EditarMonitorAcctivity extends AppCompatActivity {

    /**
     * creación de un ImageView
     */
    private ImageView imagenMonitor;
    /**
     * creación de un Button
     */
    private Button btnActualizarMonitor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_monitor);

        imagenMonitor = (ImageView) findViewById(R.id.imagen_detalle);
        imagenMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        btnActualizarMonitor= (Button) findViewById(R.id.btn_actualizar_monitor);
        btnActualizarMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_btn_actualizar_monitor), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Método que pemite cargar imagen y elegir el lugar donde esta la foto
     */
    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, getResources().getString(R.string.msg_btn_cargar_imagen)),10);
    }

    /**
     * Método que permite
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            Uri path = data.getData();
            imagenMonitor.setImageURI(path);
        }
    }
}
