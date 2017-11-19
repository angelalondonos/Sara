package com.example.angela.sara.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angela.sara.R;
import com.example.angela.sara.util.ManagerFireBase;
import com.example.angela.sara.vo.Monitor;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.imagen_detalle) protected ImageView imagenMonitor;
    /**
     * creación de un Button
     */
    @BindView(R.id.btn_actualizar_monitor) protected Button btnActualizarMonitor;
    /*
       * Atributo de clase ManagerFireBase
        */
    private ManagerFireBase managerFireBase;

    /**
     * creación de TextView
     */
    @BindView(R.id.editText_nombre_editar) protected EditText txtNombre;
    @BindView(R.id.spinner_linea) protected Spinner txtLinea;
    @BindView(R.id.editText_contrasena_editar) protected EditText txtContrasena;
    @BindView(R.id.editText_userName_editar) protected EditText txtUsename;
    @BindView(R.id.editText_telefono_editar) protected EditText txtTelefono;
    @BindView(R.id.spinner_semestre) protected Spinner txtSemestre;
    @BindView(R.id.editText_lugar_asesoria_editar) protected TextView txtLugarAsesoria;

    /**
     * creación de un Monitor
     */
    private Monitor monitor;

    /**
     * Método constructor de la actividad  Editar Monitor
      */
    public EditarMonitorAcctivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_monitor);

        ButterKnife.bind(this);

        imagenMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

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


    /**
     * Método que permite mostrar el detalle del monitor
     * @param monitor
     */
    public void mostrarMonitor (Monitor monitor) {
        this.monitor = monitor;
        txtNombre.setText(monitor.getNombre());
        txtContrasena.setText(monitor.getContrasena());
        txtUsename.setText(monitor.getUserName());
        txtTelefono.setText(monitor.getTelefono());
        txtLugarAsesoria.setText(monitor.getLugarAsesoria());
    }
}
