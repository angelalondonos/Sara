package com.example.angela.sara.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.angela.sara.R;

/**
 * Created by angela on 26/10/17.
 */

public class EditarMonitorAcctivity extends AppCompatActivity {

    private ImageView imagenMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_monitor);

        imagenMonitor = (ImageView) findViewById(R.id.imagen_detalle);
    }

    public void onClick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Alicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            Uri path = data.getData();
            imagenMonitor.setImageURI(path);
        }
    }
}
